package chat.client;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import chat.commun.Commandes;
import chat.commun.Message;
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

            String reste = new StringTokenizer(texte, commande + " ")
                    .nextToken();

            if (commande.equalsIgnoreCase(Commandes.connect)) {
                this.client.getLienServeur().connect(reste);
            } else if (commande.equalsIgnoreCase(Commandes.bye)) {
                this.client.getLienServeur().bye();
            } else if (commande.equalsIgnoreCase(Commandes.who)) {
                this.client.getLienServeur().who();
            } else if (commande.equalsIgnoreCase(Commandes.send)) {
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

    public void connect(String userID) throws RemoteException {
        Message retour = this.serveur.connect(userID);
        this.client.setUtilisateur(retour.getExpediteur());
        this.dateDernierMessage = retour.getDateEmission();
        ArrayList<Message> liste = new ArrayList<>();
        liste.add(retour);
        this.client.setMessages(liste);
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
            this.client.setMessages(retour);

            if (!retour.isEmpty()) {
                this.dateDernierMessage = retour.get(retour.size() - 1)
                        .getDateEmission();

                for (Message m : retour) {
                    Visualisateur.display(m.getContenu());
                }
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
        System.out.println(this.serveur.who());
    }
}
