package chat.client;

import chat.commun.Message;

public class Updater extends Thread {

    /**
     * Client actuel.
     */
    Client client;

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
        while (true) {
            this.client.getLienServeur().getMessages();
            for(Message m: client.getMessages())
            {
            	System.out.println(m);
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
