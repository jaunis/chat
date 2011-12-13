/**
 * 
 */
package chat.exceptions;

import chat.commun.Utilisateur;

/**
 * @author Jean AUNIS
 */
public class AlreadyConnectedException extends Exception {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    public AlreadyConnectedException(Utilisateur u) {
        super("Vous êtes déjà connecté avec l'id " + u);
    }
}
