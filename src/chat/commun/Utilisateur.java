package chat.commun;

import java.io.Serializable;

public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    protected String id;

    public Utilisateur(String idIn) {
        this.id = idIn;
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
        // TODO Auto-generated method stub
        return super.hashCode();
    }
}
