/**
 * 
 */
package chat.exceptions;


/**
 * @author Jean AUNIS
 *
 */
public class IdAlreadyUsedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public IdAlreadyUsedException(String id)
	{
		super("L'id " + id + " est déjà utilisé.");
	}
}
