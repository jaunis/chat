package chat.client;

import java.rmi.RemoteException;

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
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            this.client.disconnect();
            this.client
                    .getVisualisateur()
                    .getTextViewer()
                    .append("Utilisateur "
                            + this.client.getUtilisateur().toString()
                            + " has quit.");
        }
    }

    public void connect(String userID) {
        try {
            this.client.setUtilisateur(this.serveur.connect(userID));
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.client.getVisualisateur().getTextViewer()
                .append("Utilisateur " + userID + " has connected.");
        // this.client.getVisualisateur().repaint();
    }

    public void getMessages() {
        if (this.client.isConnected()) {
            // FIXME : look at this.
            try {
                this.client.setMessages(this.serveur.getMessages(null));
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        if (this.client.isConnected()) {
            try {
                this.serveur.send(message, this.client.getUtilisateur());
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            this.client
                    .getVisualisateur()
                    .getTextViewer()
                    .append("Utilisateur "
                            + this.client.getUtilisateur().toString()
                            + " has send this : " + message);

            this.client.getVisualisateur().repaint();
        }
    }

    public void who() {
        if (this.client.isConnected()) {
            try {
                this.client.setListeUtilisateurs(this.serveur.who());
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            this.client.getVisualisateur().getTextViewer()
                    .append("Asked for users");
        }
    }
}
