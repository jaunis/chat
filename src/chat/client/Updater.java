package chat.client;

import java.rmi.RemoteException;

import chat.exceptions.NotConnectedException;

public class Updater extends Thread {

    /**
     * Client actuel.
     */
    Client client;
    private boolean enMarche = true;

    /**
     * Constructeur.
     * @param clientIn
     *            le client
     * @param serveurIn
     *            le serveur
     */
    public Updater(Client clientIn) {
        super();
        this.client = clientIn;
    }

    @Override
    public void run() {
        while (enMarche) {
            try {
                this.client.getLienServeur().getMessages();
            } catch (RemoteException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (NotConnectedException e1) {
                Visualisateur.displayError(e1.getMessage());
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void arreter() {
        enMarche = false;
    }
}
