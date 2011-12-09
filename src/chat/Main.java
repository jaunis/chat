package chat;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import chat.client.Client;
import chat.client.ig.InterfaceGraphique;
import chat.serveur.Serveur;
import chat.serveur.ServeurImpl;

public class Main {

    /**
     * Main function. Launches a new client. No arguments.
     */
    @SuppressWarnings("unused")
    public static void main(String[] args) {
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
