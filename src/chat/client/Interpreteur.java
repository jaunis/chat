package chat.client;

import java.util.StringTokenizer;

import chat.commun.Commandes;
import chat.commun.Message;

public class Interpreteur {

    private LienServeur lienServeur;

    private Client client;

    public Interpreteur(Client clientIn, LienServeur lienServeurIn) {
        this.lienServeur = lienServeurIn;
        this.client = clientIn;
    }

    public void traiterCommande(String texte) {
        // Séparer le début du mot.
        if (Interpreteur.getCommand(texte) != null) {

            // Si c'est un mot-clé, traiter cette commande.
            this.traiterCommande(texte);
            Interpreteur.getCommand(texte);

        } else {
            // Sinon, c'est un message et on le traite.
            this.traiterMessage(texte);
        }
    }

    private void traiterMessage(String texte) {
        Message message = new Message(texte, this.client);
        this.lienServeur.sendMessage(message);
    }

    private static String getCommand(String texte) {
        String premierMot = new StringTokenizer(texte, " ").nextToken();

        for (String m : Commandes.getListeMotsCles()) {
            if (premierMot.equals(m)) {
                return premierMot;
            }
        }

        return null;
    }
}
