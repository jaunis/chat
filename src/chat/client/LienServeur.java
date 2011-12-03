package chat.client;

import chat.commun.Message;
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
            this.serveur.bye(this.client.getUtilisateur());
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
        this.client.setUtilisateur(this.serveur.connect(userID));
        this.client.getVisualisateur().getTextViewer()
                .append("Utilisateur " + userID + " has connected.");
    }

    public void getMessages() {
        if (this.client.isConnected()) {
            this.client.setMessages(this.serveur.getMessages());
        }
    }

    public void sendMessage(Message message) {
        if (this.client.isConnected()) {
            this.serveur.send(message, this.client.getUtilisateur());

            this.client
                    .getVisualisateur()
                    .getTextViewer()
                    .append("Utilisateur "
                            + this.client.getUtilisateur().toString()
                            + " has send this : " + message.toString());

            this.client.getVisualisateur().repaint();
        }
    }

    public void who() {
        if (this.client.isConnected()) {
            this.client.setListeUtilisateurs(this.serveur.who());

            this.client.getVisualisateur().getTextViewer()
                    .append("Asked for users");
        }
    }
}
