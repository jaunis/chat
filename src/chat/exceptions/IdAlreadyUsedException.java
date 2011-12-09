/**
 * 
 */
package chat.exceptions;

import java.rmi.RemoteException;

/**
 * @author Jean AUNIS
 *
 */
public class IdAlreadyUsedException extends RemoteException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public IdAlreadyUsedException(String id)
	{
		super("L'id " + id + " est déjà utilisé.");
	}
}
