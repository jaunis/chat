package chat.commun;

import java.io.Serializable;
import java.util.Date;

/**
 * Cette classe implémente un message, avec un contenu, une date d'émission, et
 * un expéditeur.
 * @author Daniel Lefevre, Jean Aunis
 */
public class Message implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Le contenu texte du message.
     */
    private String contenu;
    /**
     * La date à laquelle le message a été construit.
     */
    private Date dateEmission;
    /**
     * L'utilisateur qui a créé le message.
     */
    private Utilisateur expediteur;

    /**
     * Constructeur avec un contenu et un expéditeur.
     * @param contenuIn
     *            le contenu
     * @param expediteurIn
     *            l'expéditeur
     */
    public Message(final String contenuIn, final Utilisateur expediteurIn) {
        this.contenu = contenuIn;
        this.expediteur = expediteurIn;
        this.dateEmission = new Date();
    }

    /**
     * Getter.
     * @return le contenu
     */
    public final String getContenu() {
        return this.contenu;
    }

    /**
     * Getter.
     * @return the dateEmission
     */
    public final Date getDateEmission() {
        return this.dateEmission;
    }

    /**
     * @return the expediteur
     */
    public final Utilisateur getExpediteur() {
        return this.expediteur;
    }

    @Override
    public final String toString() {
        return this.expediteur + ": " + this.contenu;
    }
}
