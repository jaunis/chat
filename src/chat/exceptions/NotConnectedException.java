package chat.exceptions;

/**
 * Implémente une exception utilisée quand l'utilisateur adresse une requête au
 * serveur alors qu'il n'est pas connecté.
 * @author Daniel Lefevre, Jean Aunis
 */
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
