package chat.serveur;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

import chat.commun.Message;
import chat.commun.Utilisateur;

public class ServeurImpl extends UnicastRemoteObject implements Serveur {


    private static final long serialVersionUID = 1L;
    protected ArrayList<Utilisateur> listeUtilisateurs = new ArrayList<>();
    protected ArrayList<Message> listeMessages = new ArrayList<>();

    public static int port = 70;

    public ServeurImpl() throws RemoteException {
        super();
    }

    public static void main(String args[]) {
        String URL;
        try {
            // Création du serveur de nom - rmiregistry
            LocateRegistry.createRegistry(port);
            // Création d'une instance de l'objet serveur
            Serveur obj = new ServeurImpl();
            // Calcul de l'URL du serveur
            URL = "//" + InetAddress.getLocalHost().getHostName() + ":" + port
                    + "/serveur";
            Naming.rebind(URL, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Utilisateur connect(String id) throws RemoteException {
        Utilisateur nouveau = new Utilisateur(id);
        if (this.listeUtilisateurs.contains(nouveau))
            throw new RemoteException("Cet id est déjà utilisé");
        this.listeUtilisateurs.add(nouveau);
        return nouveau;

    }

    @Override
    public void send(String message, Utilisateur expediteur)
            throws RemoteException {
        this.listeMessages.add(new Message(message, expediteur));
    }

    @Override
    public void bye(Utilisateur utilisateur) throws RemoteException {
        this.listeUtilisateurs.remove(utilisateur);
    }

    @Override
    public ArrayList<Utilisateur> who() throws RemoteException {
        return this.listeUtilisateurs;
    }

    @Override
    public ArrayList<Message> getMessages(Date date) throws RemoteException {
        ArrayList<Message> listeTemp = new ArrayList<>();
        for (Message m : this.listeMessages) {
            if (m.getDateEmission().after(date))
                listeTemp.add(m);
        }
        return listeTemp;
    }
}
