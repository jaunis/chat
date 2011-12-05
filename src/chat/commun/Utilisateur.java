package chat.commun;

import java.io.Serializable;

public class Utilisateur implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    private boolean connected;

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

    public boolean isConnected() {
        return this.connected;
    }

    public void disconnect() {
        this.connected = false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
