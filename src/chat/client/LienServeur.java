package chat.client;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import chat.commun.Commandes;
import chat.commun.Message;
import chat.commun.Utilisateur;
import chat.exceptions.AlreadyConnectedException;
import chat.exceptions.IdAlreadyUsedException;
import chat.exceptions.NotConnectedException;
import chat.serveur.Serveur;

/**
 * Cette classe implémente la liaison du client avec le serveur. Toute
 * transaction doit passer par ici.
 * @author Daniel Lefevre
 */
public class LienServeur {

    /**
     * La référence vers le serveur.
     */
    private Serveur serveur;
    /**
     * Le client.
     */
    private Client client;
    /**
     * La date du dernier message.
     */
    private Date dateDernierMessage;

    /**
     * Constructeur.
     * @param clientIn
     *            le client
     * @param serveurIn
     *            le serveur
     */
    public LienServeur(Client clientIn, Serveur serveurIn) {
        this.client = clientIn;
        this.serveur = serveurIn;
    }

    // TODO : make only one method action managing all the commands and treating
    // all the exceptions.
    /**
     * Traite la commande entrée par l'utilisateur. En fonction de celle-ci,
     * appelle la fonction correspondante.
     * @param texte
     *            la commande, suivie du texte entré par l'utilisateur
     */
    public void traiterCommande(String texte) {
        String commande;
        try {
            commande = Interpreteur.getCommand(texte);

            if (commande.equalsIgnoreCase(Commandes.connect)) {
                String reste = texte.substring(texte.indexOf(' ') + 1);
                this.client.getLienServeur().connect(reste);
            } else if (commande.equalsIgnoreCase(Commandes.bye)) {
                this.client.getLienServeur().bye();
            } else if (commande.equalsIgnoreCase(Commandes.who)) {
                this.client.getLienServeur().who();
            } else if (commande.equalsIgnoreCase(Commandes.send)) {
                String reste = texte.substring(texte.indexOf(' ') + 1);
                this.client.getLienServeur().sendMessage(reste);
            }
        } catch (NoSuchElementException e) {
            // Si la ligne est vide, ne rien faire.
        } catch (NotConnectedException e) {
            Visualisateur.displayError(e.getMessage());
        } catch (IdAlreadyUsedException e) {
            Visualisateur.displayError(e.getMessage());
        } catch (AlreadyConnectedException e) {
            Visualisateur.displayError(e.getMessage());
        } catch (RemoteException e) {
            Visualisateur.displayError(e.getMessage());
        }
    }

    /**
     * Commande "bye", c'est-à-dire se déconnecter.
     * @throws RemoteException
     *             si une erreur apparait dans le serveur
     */
    public void bye() throws RemoteException, NotConnectedException {

        this.serveur.bye(this.client.getUtilisateur());
        Visualisateur.display(this.client.getUtilisateur()
                + " s'est déconnecté.");
    }

    /**
     * Demande au serveur de se connecter sous le pseudo userID.
     * @param userID
     *            le pseudo demandé
     * @throws RemoteException
     *             si une erreur apparait dans le serveur
     */
    public void connect(String pseudo) throws RemoteException,
            AlreadyConnectedException, IdAlreadyUsedException {

        Message retour = this.serveur.connect(pseudo);
        this.client.setUtilisateur(retour.getExpediteur());
        this.dateDernierMessage = retour.getDateEmission();
        List<Message> liste = new ArrayList<>();
        liste.add(retour);
        this.client.addMessages(liste);
    }

    /**
     * Appelle la méthode updateMessages pour récupérer les derniers messages du
     * serveur uniquement si l'utilisateur est déjà connecté.
     */
    public void getMessages() {
        try {
            this.updateMessages();
        } catch (RemoteException e) {
            Visualisateur.displayError(e.getMessage());
        } catch (NotConnectedException e) {
            Visualisateur.displayError(e.getMessage());
        }
    }

    /**
     * Met à jour la liste des messages avec les derniers message du serveur.
     * @throws RemoteException
     *             si une erreur apparait dans le serveur
     */
    public void updateMessages() throws RemoteException, NotConnectedException {
        // Récupérer la liste des nouveaux messages (depuis la date du
        // dernier message.
        List<Message> nouveauxMessages = this.serveur
                .getMessages(this.dateDernierMessage);

        if (!nouveauxMessages.isEmpty()) {
            // S'il y a au moins un message, mettre à jour la liste des
            // messages.
            this.client.addMessages(nouveauxMessages);

            // Si il y a au moins un message, mettre à jour la date de dernier
            // message.
            this.dateDernierMessage = new Date(nouveauxMessages
                    .get(nouveauxMessages.size() - 1).getDateEmission()
                    .getTime());

            // Petite combine pour éviter de télécharger à l'infini le dernier
            // message.
            this.dateDernierMessage
                    .setTime(this.dateDernierMessage.getTime() + 10);
        }
    }

    /**
     * Getter.
     * @return la date du dernier message
     */
    public Date getDateDernierMessage() {
        return this.dateDernierMessage;
    }

    /**
     * Envoye le message au serveur pour être affiché.
     * @param message
     *            le message
     * @throws RemoteException
     *             si une erreur apparait dans le serveur
     */
    public void sendMessage(String message) throws RemoteException,
            NotConnectedException {
        this.serveur.send(message, this.client.getUtilisateur());
    }

    /**
     * Demande au serveur la liste des utilisateurs et l'affiche.
     * @throws RemoteException
     *             si une erreur apparait dans le serveur
     */
    public void who() throws RemoteException, NotConnectedException {
        List<Utilisateur> listeU = this.serveur.who();

        // Demande au visualisateur d'afficher tout cela.
        for (Utilisateur u : listeU) {
            Visualisateur.display(u.getId());
        }
    }
}
