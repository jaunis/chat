package chat.client;

import java.util.StringTokenizer;

import chat.commun.Commandes;
import chat.commun.Message;

public class Interpreteur {

    private Client client;

    public Interpreteur(Client clientIn) {
        this.client = clientIn;
    }

    private static String getCommand(String texte) {
        String premierMot = new StringTokenizer(texte, " ").nextToken();

        for (String m : Commandes.getListeMotsCles()) {
            if (premierMot.equalsIgnoreCase(m)) {
                return premierMot;
            }
        }

        return null;
    }

    private void traiterCommande(String texte) {
        String commande = Interpreteur.getCommand(texte);
        String reste = texte.substring(commande.length());

        if (commande.equalsIgnoreCase(Commandes.connect)) {
            String userID = new StringTokenizer(texte, " ").nextToken();
            this.client.getLienServeur().connect(userID);
        } else if (commande.equalsIgnoreCase(Commandes.bye)) {
            this.client.getLienServeur().bye();
        } else if (commande.equalsIgnoreCase(Commandes.who)) {
            this.client.getLienServeur().who();
        }
    }

    private void traiterMessage(String texte) {
        Message message = new Message(texte, this.client);
        this.client.getLienServeur().sendMessage(message);
    }

    public void traiterTexte(String texte) {
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
}
