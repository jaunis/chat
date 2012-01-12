package chat;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import chat.client.Client;
import chat.serveur.Serveur;
import chat.serveur.ServeurImpl;

/**
 * Classe principale.
 * @author Daniel
 */
public final class Main {

    /**
     * Constructeur privé.
     */
    private Main() {
    }

    /**
     * Lance une instance du serveur et du client.
     * @param args
     *            no arguments
     */
    public static void main(final String[] args) {
        String url;
        String machineName = "DANIEL-PC";
        try {
            url = "//" + machineName + ":" + ServeurImpl.port + "/serveur";

            Serveur serveur = (Serveur) Naming.lookup(url);
            new Client(serveur);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
