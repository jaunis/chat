package chat.exceptions;

import java.rmi.RemoteException;

public class NotConnectedException extends RemoteException {

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
