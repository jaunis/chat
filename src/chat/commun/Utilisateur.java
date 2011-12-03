package chat.commun;

public class Utilisateur 
{
	protected String id;
	protected boolean connecte;
	
	public Utilisateur(String id)
	{
		this.id = id;
		connecte = true;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the connecte
	 */
	public boolean isConnecte() {
		return connecte;
	}
	/**
	 * @param connecte the connecte to set
	 */
	public void setConnecte(boolean connecte) {
		this.connecte = connecte;
	}
	
}
