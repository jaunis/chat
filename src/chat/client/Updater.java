package chat.client;

/**
 * Cette classe implémente un Thread appelant régulièrement le serveur pour
 * récupérer les derniers messages.
 * @author Daniel Lefevre
 */
public class Updater extends Thread {

    /**
     * Client actuel.
     */
    private Client client;

    private boolean continuer = true;
    private boolean pause = false;

    /**
     * Constructeur.
     * @param clientIn
     *            le client
     */
    public Updater(Client clientIn) {
        super();
        this.client = clientIn;
    }

    public void stopThread() {
        this.continuer = false;
    }

    public void pause() {
        this.pause = true;
    }

    public void reprendre() {
        this.pause = false;
    }

    @Override
    public void run() {
        while (this.continuer) {
            if (!this.pause) {
                this.client.getLienServeur().getMessages();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
