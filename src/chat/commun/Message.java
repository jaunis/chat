package chat.commun;

import chat.client.Client;

public class Message {

    private String contenu;
    private Client expéditeur;

    public Message(String contenuIn, Client expéditeurIn) {
        this.contenu = contenuIn;
        this.expéditeur = expéditeurIn;
    }

    @Override
    public String toString() {
        return this.contenu;
    }
}
