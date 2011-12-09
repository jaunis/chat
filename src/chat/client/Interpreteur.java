package chat.client;

import java.rmi.RemoteException;
import java.util.NoSuchElementException;

import chat.commun.Commandes;

public class Interpreteur {

    private Client client;

    public Interpreteur(Client clientIn) {
        this.client = clientIn;
    }

    public static String getCommand(String texte) throws NoSuchElementException {
        for (String m : Commandes.getListeMotsCles()) {
            if (texte.startsWith(m)) {
                return m;
            }
        }

        return null;
    }

    public void traiterMessage(String texte) {
        try {
            this.client.getLienServeur().sendMessage(texte);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

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
