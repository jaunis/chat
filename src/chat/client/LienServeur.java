package chat.client;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import chat.commun.Commandes;
import chat.commun.Message;
import chat.commun.Utilisateur;
import chat.exceptions.NotConnectedException;
import chat.serveur.Serveur;

public class LienServeur {

    private Serveur serveur;
    private Client client;
    private Date dateDernierMessage;

    public LienServeur(Client clientIn, Serveur serveurIn) {
        this.client = clientIn;
        this.serveur = serveurIn;
    }

    public void traiterCommande(String texte) {
        String commande;
        try {
            commande = Interpreteur.getCommand(texte);

            if (commande.equalsIgnoreCase(Commandes.connect)) {
                String reste = new StringTokenizer(texte, commande + " ")
                        .nextToken();
                this.client.getLienServeur().connect(reste);
            } else if (commande.equalsIgnoreCase(Commandes.bye)) {
                this.client.getLienServeur().bye();
            } else if (commande.equalsIgnoreCase(Commandes.who)) {
                this.client.getLienServeur().who();
            } else if (commande.equalsIgnoreCase(Commandes.send)) {
                String reste = new StringTokenizer(texte, commande + " ")
                        .nextToken();
                this.client.getLienServeur().sendMessage(reste);
            }
        } catch (NoSuchElementException e) {
            // Si la ligne est vide, ne rien faire.
        } catch (NotConnectedException e) {
            Visualisateur.displayError(e.getMessage());
        } catch (RemoteException e) {
            Visualisateur.displayError(e.getMessage());
        }
    }

    public void bye() throws RemoteException {
        this.serveur.bye(this.client.getUtilisateur());
    }

    public void connect(String pseudo) throws RemoteException {
        Message retour = this.serveur.connect(pseudo);
        this.client.setUtilisateur(retour.getExpediteur());
        this.dateDernierMessage = retour.getDateEmission();
        List<Message> liste = new ArrayList<>();
        liste.add(retour);
        this.client.addMessages(liste);
        System.out.println(retour);
    }

    public void getMessages() {
        try {
            this.updateMessages();
        } catch (RemoteException e) {
            Visualisateur.displayError(e.getMessage());
        }
    }

    public void updateMessages() throws RemoteException {
        if (this.dateDernierMessage != null) {
            List<Message> retour = this.serveur
                    .getMessages(this.dateDernierMessage);
            this.client.addMessages(retour);

            if (!retour.isEmpty()) {
                this.dateDernierMessage = retour.get(retour.size() - 1)
                        .getDateEmission();
            }
        }
    }

    public Date getDateDernierMessage() {
        return this.dateDernierMessage;
    }

    public void sendMessage(String message) throws RemoteException {
        this.serveur.send(message, this.client.getUtilisateur());
    }

    public void who() throws RemoteException {
        List<Utilisateur> listeU = this.serveur.who();
        for (Utilisateur u : listeU) {
            Visualisateur.display(u.getId());
        }
    }
}
