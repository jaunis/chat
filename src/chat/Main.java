package chat;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import chat.client.Client;
import chat.serveur.Serveur;
import chat.serveur.ServeurImpl;

/**
 * Classe princiale.
 * @author Daniel
 */
public final class Main {

    /**
     * Constructeur privé.
     */
    private Main() {
    }

    /**
     * Main function. Launches a new client.
     * @param args
     *            no arguments
     */
    @SuppressWarnings("unused")
    public static void main(final String[] args) {
        String url;
        try {
            url = "//" + InetAddress.getLocalHost().getHostAddress() + ":"
                    + ServeurImpl.port + "/serveur";
            Serveur serveur = (Serveur) Naming.lookup(url);
            new Client(serveur);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}
