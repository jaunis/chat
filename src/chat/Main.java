package chat;

import chat.client.Client;
import chat.client.GestionnaireTexte;
import chat.client.Updater;
import chat.serveur.Serveur;

public class Main {

    /**
     * Main function. Launches a new client. No arguments.
     */
    public static void main() {

        // TODO : how to initialize this serveur ?
        Serveur serveur = null;
        Client client = new Client(serveur);
    }
}
