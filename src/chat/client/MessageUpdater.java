package chat.client;

/**
 * Cette classe implémente un Thread appelant régulièrement le serveur pour
 * récupérer les derniers messages.
 * @author Daniel Lefevre
 */
public class MessageUpdater extends Thread {

    /**
     * Client actuel.
     */
    private Client client;

    /**
     * Booléen permettant d'arrêter la boucle de l'updater.
     */
    private boolean continuer = true;
    /**
     * Booléen permettant de mettre en pause l'updater.
     */
    private boolean pause = false;

    /**
     * Constructeur.
     * @param clientIn
     *            le client
     */
    public MessageUpdater(final Client clientIn) {
        super();
        this.client = clientIn;
    }

    /**
     * Mettre en pause l'updater.
     */
    public final void pause() {
        this.pause = true;
    }

    /**
     * Reprendre après une pause.
     */
    public final void reprendre() {
        this.pause = false;
    }

    @Override
    public final void run() {
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

    /**
     * Arrêter l'updater.
     */
    public final void stopThread() {
        this.continuer = false;
    }
}
