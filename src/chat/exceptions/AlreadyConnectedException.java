package chat.exceptions;

import chat.commun.Utilisateur;

/**
 * @author Jean Aunis, Daniel Lefevre
 */
public class AlreadyConnectedException extends Exception {

    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur avec message d'erreur.
     * @param u
     *            l'utilisateur concerné
     */
    public AlreadyConnectedException(final Utilisateur u) {
        super("Vous êtes déjà connecté avec l'id " + u);
    }
}
