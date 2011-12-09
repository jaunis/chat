package chat.commun;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe comprend des constantes décrivant les mot-clés correspondant à
 * chaque commande.
 * @author Daniel Lefevre
 */
public class Commandes {

    /**
     * Le texte de la commande connect.
     */
    public static final String connect = "connect";

    /**
     * Le texte de la commande bye.
     */
    public static final String bye = "bye";

    /**
     * Le texte de la commande who.
     */
    public static final String who = "who";

    /**
     * Le texte de la commande send.
     */
    public static final String send = "send";

    /**
     * La liste des mot-clés des commandes.
     */
    private static List<String> listeMotsCles;

    /**
     * Renvoie la liste des mots-clés. Initialise celle-ci si cela n'a pas déjà
     * été fait.
     * @return la liste des mots-clés correspondant à chaque commande
     */
    public static List<String> getListeMotsCles() {
        if (!Commandes.isInit()) {
            Commandes.init();
        }

        return Commandes.listeMotsCles;
    }

    /**
     * Initialise la liste des mots-clés.
     */
    private static void init() {
        Commandes.listeMotsCles = new ArrayList<>();
        Commandes.listeMotsCles.add(connect);
        Commandes.listeMotsCles.add(bye);
        Commandes.listeMotsCles.add(who);
        Commandes.listeMotsCles.add(send);
    }

    /**
     * Vérifie si la liste des commandes a été initialisée.
     * @return true si la liste des mots-clés est non nulle, false, sinon
     */
    private static boolean isInit() {
        return Commandes.listeMotsCles != null;
    }
}
