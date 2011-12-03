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

    public void sendMessage(Message message) {
        this.serveur.send(message, this.client.getUtilisateur());
    }
}
