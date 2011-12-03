package chat.client;

import java.io.IOException;

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
     * L'interpréteur de commandes.
     */
    private Interpreteur interpreteur;

    /**
     * Constructeur.
     * @param interpreteurIn
     */
    public GestionnaireTexte(Client clientIn, Interpreteur interpreteurIn) {
        super();
        this.client = clientIn;
        this.interpreteur = interpreteurIn;
    }

    /**
     * Lance une boucle infinie qui attend l'entrée de l'utilisateur et l'envoie
     * alors au client.
     */
    @Override
    public void run() {
        while (true) {
            try {
                this.interpreteur.traiterCommande(String
                        .valueOf((char) System.in.read()));
            } catch (IOException e) {
                // TODO
                e.printStackTrace();
            }
        }
    }
}