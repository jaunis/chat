package chat.client;

import java.rmi.RemoteException;
import java.util.Date;

import chat.serveur.Serveur;

public class LienServeur {

    private Serveur serveur;

    private Client client;

    public LienServeur(Client clientIn, Serveur serveurIn) {
        this.client = clientIn;
        this.serveur = serveurIn;
    }

    public void bye() {
        if (this.client.isConnected()) {
            try {
                this.serveur.bye(this.client.getUtilisateur());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void connect(String userID) {
        try {
            this.client.setUtilisateur(this.serveur.connect(userID));
            System.out.println(userID + " s'est connecté.");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getMessages() {
        if (this.client.isConnected() && !this.client.getMessages().isEmpty()) {
            try {
                Date lastMessageDate = this.client.getMessages()
                        .get(this.client.getMessages().size() - 1)
                        .getDateEmission();
                this.client.setMessages(this.serveur
                        .getMessages(lastMessageDate));
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        if (this.client.isConnected()) {
            try {
                this.serveur.send(message, this.client.getUtilisateur());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void who() {
        if (this.client.isConnected()) {
            try {
                System.out.println(this.serveur.who());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
