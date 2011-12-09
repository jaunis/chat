package chat.client;

public class Updater extends Thread {

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
    public Updater(Client clientIn) {
        super();
        this.client = clientIn;
    }

    /**
     * Lance une boucle infinie qui appelle le lienserveur régulièrement pour
     * lui demander de mettre à jour les messages.
     */
    @Override
    public void run() {
        while (true) {
            this.client.getLienServeur().getMessages();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
