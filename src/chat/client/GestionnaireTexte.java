package chat.client;

import java.util.Scanner;

/**
 * Cette classe sert à gérer les entrées au clavier de l'utilisateur. Elle
 * envoie ensuite les textes au client qui les traite.
 * @author Daniel Lefevre, Jean Aunis
 */
public class GestionnaireTexte extends Thread {

    /**
     * The parent guy who controls the big machine.
     */
    private Client client;

    /**
     * Getter.
     * @return le client
     */
    public Client getClient() {
        return this.client;
    }

    /**
     * Setter.
     * @param clientIn
     *            le nouveau client
     */
    public void setClient(Client clientIn) {
        this.client = clientIn;
    }

    /**
     * Constructeur.
     * @param interpreteurIn
     */
    public GestionnaireTexte(Client clientIn) {
        super();
        this.client = clientIn;
    }

    /**
     * Lance une boucle infinie qui attend l'entrée de l'utilisateur et l'envoie
     * alors au client.
     */
    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            this.client.getInterpreteur().traiterTexte(scanner.nextLine());
        }
    }
}
