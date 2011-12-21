package chat;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

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
        try {
            url = "//" + InetAddress.getLocalHost().getHostAddress() + ":"
                    + ServeurImpl.port + "/serveur";
            
            // Création du serveur de nom - rmiregistry
            LocateRegistry.createRegistry(ServeurImpl.port);
            // Création d'une instance de l'objet serveur
            Serveur obj = new ServeurImpl();
            Naming.rebind(url, obj);
            
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
