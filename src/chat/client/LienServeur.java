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
        this.serveur.bye(this.client.getUtilisateur());
    }

    public void connect(String userID) {
        this.serveur.connect(userID);
    }

    public void getMessages() {
        this.client.addMessages(this.serveur.getMessages());
    }

    public void sendMessage(Message message) {
        this.serveur.send(message, this.client.getUtilisateur());
    }

    public void who() {
        this.client.setListeUtilisateurs(this.serveur.who());
    }
}
