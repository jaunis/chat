package chat.commun;

import java.util.ArrayList;
import java.util.List;

public class Commandes {

    public static final String connect = "connect";

    public static final String bye = "bye";

    public static final String who = "who";
    public static final String send = "send";

    private static List<String> listeMotsCles;

    public static List<String> getListeMotsCles() {
        if (!Commandes.isInit()) {
            Commandes.init();
        }

        return Commandes.listeMotsCles;
    }

    private static void init() {
        Commandes.listeMotsCles = new ArrayList<>();
        Commandes.listeMotsCles.add(connect);
        Commandes.listeMotsCles.add(bye);
        Commandes.listeMotsCles.add(who);
        Commandes.listeMotsCles.add(send);
    }

    private static boolean isInit() {
        return Commandes.listeMotsCles != null;
    }
}
