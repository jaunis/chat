package chat;

import chat.client.Client;
import chat.serveur.ServeurImpl;

public class Main {

    /**
     * Main function. Launches a new client. No arguments.
     */
    public static void main(String[] args) {

        // TODO : how to initialize this serveur ?
        ServeurImpl serveur = new ServeurImpl();
        Client client = new Client(serveur);
    }
}
