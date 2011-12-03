package chat.commun;

public class Utilisateur {

    private String id;
    private boolean connected;

    public Utilisateur(String idIn) {
        this.id = idIn;
        this.connected = true;
    }

    public void setID(String idIn) {
        this.id = idIn;
    }

    public boolean isConnected() {
        return this.connected;
    }

    public void disconnect() {
        this.connected = false;
    }

    @Override
    public String toString() {
        return this.id;
    }
}
