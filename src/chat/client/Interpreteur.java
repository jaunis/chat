package chat.client;

import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import chat.commun.Commandes;

public class Interpreteur {

    private Client client;

    public Interpreteur(Client clientIn) {
        this.client = clientIn;
    }

    private static String getCommand(String texte)
            throws NoSuchElementException {
        String premierMot = new StringTokenizer(texte, " ").nextToken();

        for (String m : Commandes.getListeMotsCles()) {
            if (premierMot.equalsIgnoreCase(m)) {
                return premierMot;
            }
        }

        return null;
    }

    private void traiterCommande(String texte) {
        String commande;
        try {
            commande = Interpreteur.getCommand(texte);

            String[] reste = texte.split(" ");

            if (commande.equalsIgnoreCase(Commandes.connect)) {
                this.client.getLienServeur().connect(reste[1]);
            } else if (commande.equalsIgnoreCase(Commandes.bye)) {
                this.client.getLienServeur().bye();
            } else if (commande.equalsIgnoreCase(Commandes.who)) {
                this.client.getLienServeur().who();
            }
        } catch (NoSuchElementException e) {
            // Si la ligne est vide, ne rien faire.
        }
    }

    private void traiterMessage(String texte) {
        this.client.getLienServeur().sendMessage(texte);
    }

    public void traiterTexte(String texte) {
        // Séparer le début du mot.
        try {
            if (Interpreteur.getCommand(texte) != null) {
                // Si c'est un mot-clé, traiter cette commande.
                this.traiterCommande(texte);

            } else {
                // Sinon, c'est un message et on le traite.
                this.traiterMessage(texte);
            }
        } catch (NoSuchElementException e) {
            // Si la ligne est vide, ne rien faire.
        }
    }
}
