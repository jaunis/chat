package chat.commun;

import java.io.Serializable;


public class Utilisateur implements Serializable
{
	
	private static final long serialVersionUID = -4050045596232047659L;
	protected String id;
			
	public Utilisateur(String id)
	{
		this.id = id;
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
	
	@Override
	public boolean equals(Object o)
	{
		if(o instanceof Utilisateur)
		{
			return ((Utilisateur)o).getId().equals(id);
		}
		else return false;
	}
}
