package chat.commun;

import java.util.Date;

public class Message 
{
	protected Utilisateur expediteur;
	protected Date dateEmission;
	protected String contenu;
	
	public Message(String contenu, Utilisateur expediteur)
	{
		this.contenu = contenu;
		this.expediteur = expediteur;
		this.dateEmission = new Date();
	}
	/**
	 * @return the expediteur
	 */
	public Utilisateur getExpediteur() {
		return expediteur;
	}
	/**
	 * @param expediteur the expediteur to set
	 */
	public void setExpediteur(Utilisateur expediteur) {
		this.expediteur = expediteur;
	}
	/**
	 * @return the dateEmission
	 */
	public Date getDateEmission() {
		return dateEmission;
	}
	/**
	 * @param dateEmission the dateEmission to set
	 */
	public void setDateEmission(Date dateEmission) {
		this.dateEmission = dateEmission;
	}
	/**
	 * @return the contenu
	 */
	public String getContenu() {
		return contenu;
	}
	/**
	 * @param contenu the contenu to set
	 */
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	
	
}
