package chat.exceptions;

public class NotConnectedException extends Exception {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur.
     */
    public NotConnectedException() {
        super("Vous n'êtes pas connecté.");
    }
}
