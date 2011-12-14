package chat.commun;

import java.util.ArrayList;
import java.util.List;

/**
 * Cette classe comprend des constantes décrivant les mot-clés correspondant à
 * chaque commande.
 * @author Daniel Lefevre
 */
public final class Commandes {

    /**
     * Constructeur privé.
     */
    private Commandes() {
    }

    /**
     * Le texte de la commande CONNECT.
     */
    public static final String CONNECT = "CONNECT";

    /**
     * Le texte de la commande BYE.
     */
    public static final String BYE = "BYE";

    /**
     * Le texte de la commande WHO.
     */
    public static final String WHO = "WHO";

    /**
     * Le texte de la commande SEND.
     */
    public static final String SEND = "SEND";

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
        Commandes.listeMotsCles.add(CONNECT);
        Commandes.listeMotsCles.add(BYE);
        Commandes.listeMotsCles.add(WHO);
        Commandes.listeMotsCles.add(SEND);
    }

    /**
     * Vérifie si la liste des commandes a été initialisée.
     * @return true si la liste des mots-clés est non nulle, false, sinon
     */
    private static boolean isInit() {
        return Commandes.listeMotsCles != null;
    }
}
