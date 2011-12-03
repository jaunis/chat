package chat;

import java.net.InetAddress;
import java.rmi.Naming;

import chat.client.Client;
import chat.serveur.Serveur;
import chat.serveur.ServeurImpl;

public class Main {

    /**
     * Main function. Launches a new client. No arguments.
     */
    public static void main(String[] args) {
        // FIXME
		String URL;
		try 
		{
			URL = "//" + InetAddress.getLocalHost().getHostName() + ":" + 
			        ServeurImpl.port + "/serveur";
			Serveur serveur = (Serveur) Naming.lookup(URL);
	        Client client = new Client(serveur);
		} catch (Exception e)
		{
			e.printStackTrace();
		}    	
    }
}
