package chat.commun;

import java.io.Serializable;

public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private String reference;

    public Utilisateur(String idIn) {
        this.id = idIn;
    }

    /**
     * @return the reference
     */
    public String getReference() {
        return this.reference;
    }

    /**
     * @param referenceIn
     *            the reference to set
     */
    public void setReference(String referenceIn) {
        this.reference = referenceIn;
    }

    /**
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * @param idIn
     *            the id to set
     */
    public void setId(String idIn) {
        this.id = idIn;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Utilisateur) {
            return ((Utilisateur) o).getId().equals(this.id);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return this.id;
    }
}
