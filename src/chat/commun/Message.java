package chat.commun;

import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {

    private static final long serialVersionUID = 1L;
    private String contenu;
    protected Date dateEmission;
    private Utilisateur expediteur;

    public Message(String contenuIn, Utilisateur expediteurIn) {
        this.contenu = contenuIn;
        this.expediteur = expediteurIn;
        this.dateEmission = new Date();
    }

    @Override
    public String toString() {
        return this.contenu;
    }

    /**
     * @return the expediteur
     */
    public Utilisateur getExpediteur() {
        return this.expediteur;
    }

    /**
     * @param expediteurIn
     *            the expediteur to set
     */
    public void setExpediteur(Utilisateur expediteurIn) {
        this.expediteur = expediteurIn;
    }

    /**
     * @return the dateEmission
     */
    public Date getDateEmission() {
        return this.dateEmission;
    }

    /**
     * @param dateEmissionIn
     *            the dateEmission to set
     */
    public void setDateEmission(Date dateEmissionIn) {
        this.dateEmission = dateEmissionIn;
    }

    /**
     * @return the contenu
     */
    public String getContenu() {
        return this.contenu;
    }

    /**
     * @param contenuIn
     *            the contenu to set
     */
    public void setContenu(String contenuIn) {
        this.contenu = contenuIn;
    }
}
