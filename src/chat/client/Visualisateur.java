package chat.client;

import java.util.List;

import chat.commun.Message;

/**
 * @author Daniel
 */
public class Visualisateur extends Thread {

    /**
     * Client actuel.
     */
    private Client client;

    /**
     * Constructeur.
     * @param clientIn
     *            le client
     */
    public Visualisateur(Client clientIn) {
        super();
        this.client = clientIn;
    }

    @Override
    public void run() {
        while (true) {
            List<Message> display;

            if (this.client.getLienServeur().getDateDernierMessage() != null) {
                display = this.client.getDerniersMessages();
            } else {
                display = this.client.getAllMessages();
            }

            for (Message m : display) {
                System.out.println(m.getContenu());
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Affiche un texte.
     * @param texte
     *            le texte
     */
    public static void display(String texte) {
        System.out.println(texte);
    }

    /**
     * Affiche un texte dans la sortie d'erreurs.
     * @param texte
     *            le texte
     */
    public static void displayError(String texte) {
        System.err.println(texte);
    }
}
