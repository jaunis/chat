package chat.commun;

import java.util.ArrayList;
import java.util.List;

public class Commandes {

    private static final String connect = "connect";

    private static final String bye = "bye";

    private static final String who = "who";

    private static List<String> listeMotsCles;

    private static void init() {
        Commandes.listeMotsCles = new ArrayList<>();
        Commandes.listeMotsCles.add(connect);
        Commandes.listeMotsCles.add(bye);
        Commandes.listeMotsCles.add(who);
    }

    private static boolean isInit() {
        return Commandes.listeMotsCles == null;
    }

    public static List<String> getListeMotsCles() {
        if (!Commandes.isInit()) {
            Commandes.init();
        }

        return Commandes.listeMotsCles;
    }
}
