package chat.serveur;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.RemoteServer;
import java.rmi.server.ServerNotActiveException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

import chat.commun.Message;
import chat.commun.Utilisateur;
import chat.exceptions.AlreadyConnectedException;
import chat.exceptions.IdAlreadyUsedException;
import chat.exceptions.NotConnectedException;

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
    public Message connect(String id) throws RemoteException,
            IdAlreadyUsedException, AlreadyConnectedException {
        Utilisateur nouveau = new Utilisateur(id);
        if (this.listeUtilisateurs.contains(nouveau))
            throw new IdAlreadyUsedException(id);
        try {
            String reference = RemoteServer.getClientHost();
            for (Utilisateur u : this.listeUtilisateurs) {
                if (u.getReference().equals(reference))
                    throw new AlreadyConnectedException(u);
            }
            nouveau.setReference(reference);
        } catch (ServerNotActiveException e) {
            throw new RemoteException("Erreur interne");
        }
        this.listeUtilisateurs.add(nouveau);

        Message retour = new Message("L'utilisateur " + nouveau
                + " s'est connecté", nouveau);
        this.listeMessages.add(retour);
        System.out.println(retour);
        return retour;
    }

    @Override
    public void send(String message, Utilisateur expediteur)
            throws RemoteException, NotConnectedException {
        try {
            if (utilisateurValide(expediteur)) {
                this.listeMessages.add(new Message(message, expediteur));
                System.out.println(expediteur + ": " + message);
            } else {
                throw new NotConnectedException();
            }
        } catch (ServerNotActiveException e) {
            throw new RemoteException("Erreur interne.");
        }
    }

    @Override
    public void bye(Utilisateur utilisateur) throws RemoteException,
            NotConnectedException {
        try {
            if (utilisateurValide(utilisateur)) {
                this.listeUtilisateurs.remove(utilisateur);
                Message alerte = new Message(
                        utilisateur + " s'est déconnecté.", utilisateur);
                this.listeMessages.add(alerte);
                System.out.println(alerte);
            } else
                throw new NotConnectedException();
        } catch (ServerNotActiveException e) {
            throw new RemoteException("Erreur interne.");
        }
    }

    @Override
    public ArrayList<Utilisateur> who() throws RemoteException,
            NotConnectedException {
        String reference;
        try {
            reference = RemoteServer.getClientHost();
            boolean connecte = false;
            for (Utilisateur u : this.listeUtilisateurs) {
                connecte |= u.getReference().equals(reference);

            }
            if (connecte) {
                System.out.println("Requête WHO");
                return this.listeUtilisateurs;
            } else
                throw new NotConnectedException();
        } catch (ServerNotActiveException e) {
            throw new RemoteException("Erreur interne.");
        }

    }

    @Override
    public ArrayList<Message> getMessages(Date date) throws RemoteException,
            NotConnectedException {
        try {
            String reference = RemoteServer.getClientHost();
            boolean connecte = false;
            for (Utilisateur u : this.listeUtilisateurs) {
                connecte |= u.getReference().equals(reference);

            }
            if (connecte) {
                ArrayList<Message> listeTemp = new ArrayList<>();
                for (Message m : this.listeMessages) {
                    if (m.getDateEmission().after(date))
                        listeTemp.add(m);
                }
                return listeTemp;
            } else
                throw new NotConnectedException();
        } catch (ServerNotActiveException e) {
            throw new RemoteException("Erreur interne.");
        }
    }

    private boolean utilisateurValide(Utilisateur u)
            throws ServerNotActiveException {
        String reference = RemoteServer.getClientHost();
        if (u == null)
            return false;
        else if (u.getReference().equals(reference)
                && listeUtilisateurs.contains(u))
            return true;
        else
            return false;
    }
}
