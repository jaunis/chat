/**
 * 
 */
package chat.exceptions;

import java.rmi.RemoteException;

import chat.commun.Utilisateur;

/**
 * @author Jean AUNIS
 *
 */
public class AlreadyConnectedException extends RemoteException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AlreadyConnectedException(Utilisateur u)
	{
		super("Vous êtes déjà conneté avec l'id " + u);
	}
}
