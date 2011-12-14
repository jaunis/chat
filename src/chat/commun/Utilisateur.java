package chat.commun;

import java.io.Serializable;

/**
 * Cette classe implémente un utilisateur, avec un id de type String, et une
 * référence créée par RMI.
 * @author Daniel
 */
public class Utilisateur implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;
    /**
     * L'id de l'utilisateur, ie le pseudo.
     */
    private String id;
    /**
     * Une référence créée par RMI.
     */
    private String reference;

    /**
     * Constructeur.
     * @param idIn
     *            l'id
     */
    public Utilisateur(final String idIn) {
        this.id = idIn;
    }

    @Override
    public final boolean equals(final Object o) {
        if (o instanceof Utilisateur) {
            return ((Utilisateur) o).getId().equals(this.id);
        }
        return false;
    }

    /**
     * @return the id
     */
    public final String getId() {
        return this.id;
    }

    /**
     * @return the reference
     */
    public final String getReference() {
        return this.reference;
    }

    @Override
    public final int hashCode() {
        return super.hashCode();
    }

    /**
     * @param idIn
     *            the id to set
     */
    public final void setId(final String idIn) {
        this.id = idIn;
    }

    /**
     * @param referenceIn
     *            the reference to set
     */
    public final void setReference(final String referenceIn) {
        this.reference = referenceIn;
    }

    @Override
    public final String toString() {
        return this.id;
    }
}
