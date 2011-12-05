package chat.commun;

import java.util.ArrayList;
import java.util.List;

public class Commandes {

    public static final String connect = "connect";

    public static final String bye = "bye";

    public static final String who = "who";

    private static List<String> listeMotsCles;

    public static List<String> getListeMotsCles() {
        if (!Commandes.isInit()) {
            Commandes.init();
        }

        return Commandes.listeMotsCles;
    }

    private static void init() {
        Commandes.listeMotsCles = new ArrayList<String>();
        Commandes.listeMotsCles.add(connect);
        Commandes.listeMotsCles.add(bye);
        Commandes.listeMotsCles.add(who);
    }

    private static boolean isInit() {
        return Commandes.listeMotsCles != null;
    }
}
