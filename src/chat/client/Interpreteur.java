package chat.client;

import java.util.NoSuchElementException;

import chat.commun.Commandes;

/**
 * Cette classe implémente un interpréteur de commandes.
 * @author Daniel Lefèvre
 */
public class Interpreteur {

    /**
     * Le client.
     */
    private Client client;

    /**
     * Constructeur.
     * @param clientIn
     *            le client
     */
    public Interpreteur(Client clientIn) {
        this.client = clientIn;
    }

    /**
     * Extrait le premier mot d'un texte et vérifie si c'est une commande, ou
     * pas.
     * @param texte
     *            le texte entré par l'utilsateur
     * @return la commande si l'utilisateur a commencé son entrée par une
     *         commande connue, null sinon
     */
    public static String getCommand(String texte) {
        for (String m : Commandes.getListeMotsCles()) {
            if (texte.startsWith(m)) {
                return m;
            }
        }
        return null;
    }

    /**
     * Traiter le texte entré par l'utilisateur.
     * @param texte
     *            le texte entré
     */
    public void traiterTexte(String texte) {
        // Séparer le début du mot.
        try {
            if (Interpreteur.getCommand(texte) != null) {
                // Si c'est un mot-clé, traiter cette commande.
                this.client.getLienServeur().traiterCommande(texte);
            }
        } catch (NoSuchElementException e) {
            // Si la ligne est vide, ne rien faire.
        }
    }
}
