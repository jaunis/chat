package chat.exceptions;

/**
 * Implémente une exception si l'id demandé est déjà utilisé.
 * @author Jean Aunis, Daniel Lefevre
 */
public class IdAlreadyUsedException extends Exception {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * /** Constructeur avec message d'erreur.
     * @param id
     *            l'id concerné
     */
    public IdAlreadyUsedException(final String id) {
        super("L'id " + id + " est déjà utilisé.");
    }
}
