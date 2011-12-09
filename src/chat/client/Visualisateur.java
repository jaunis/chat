package chat.client;

import java.util.List;

import chat.commun.Message;

/**
 * @author Daniel Lefevre
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

            List<Message> messageAAfficher = this.client.getMessagesAAfficher();
            if (!messageAAfficher.isEmpty()) {
                for (Message m : messageAAfficher) {
                    System.out.println(m.getContenu());
                }
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
