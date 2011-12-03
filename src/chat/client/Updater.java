package chat.client;

import chat.serveur.Serveur;

public class Updater extends Thread {

    /**
     * Interface avec le serveur.
     */
    Serveur serveur;

    /**
     * Client actuel.
     */
    Client client;

    /**
     * Constructeur.
     * @param clientIn
     *            le client
     * @param serveurIn
     *            le serveur
     */
    public Updater(Client clientIn, Serveur serveurIn) {
        super();
        this.client = clientIn;
        this.serveur = serveurIn;
    }

    @Override
    public void run() {
        while (true) {
            this.client.addMessages(this.serveur.getMessages());
        }
    }
}
