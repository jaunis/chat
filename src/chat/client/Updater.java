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
