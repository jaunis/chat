package chat.serveur;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
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
            System.out.println("Serveur lancé.");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    /*
     * (non-Javadoc)
     * @see chat.serveur.Serveur#connect(java.lang.String)
     */
    @Override
    public Message connect(String id) throws RemoteException,
            IdAlreadyUsedException, AlreadyConnectedException {
        // si l'id est déjà utilisé, on renvoie une exception
        Utilisateur nouveau = new Utilisateur(id);
        if (this.listeUtilisateurs.contains(nouveau))
            throw new IdAlreadyUsedException(id);

        // vérifie que le client n'est pas déjà connecté avec un autre id
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

        /*
         * si tout est "normal", on ajoute l'utilisateurs à la liste des
         * utilisateurs connectés
         */
        this.listeUtilisateurs.add(nouveau);

        Message retour = new Message("L'utilisateur " + nouveau
                + " s'est connecté", nouveau);
        this.listeMessages.add(retour);
        System.out.println(retour);
        return retour;
    }

    /*
     * (non-Javadoc)
     * @see chat.serveur.Serveur#send(java.lang.String, chat.commun.Utilisateur)
     */
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

    /*
     * (non-Javadoc)
     * @see chat.serveur.Serveur#bye(chat.commun.Utilisateur)
     */
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

    /*
     * (non-Javadoc)
     * @see chat.serveur.Serveur#who()
     */
    @Override
    public ArrayList<Utilisateur> who() throws RemoteException,
            NotConnectedException {

        String reference;
        try {
            // on vérifie que le client envoyant la requête est connu
            reference = RemoteServer.getClientHost();
            boolean connecte = false;
            for (Utilisateur u : this.listeUtilisateurs) {
                connecte |= u.getReference().equals(reference);

            }

            // si le client est connu, on renvoie la liste des utilisateurs
            if (connecte) {
                System.out.println("Requête WHO");
                return this.listeUtilisateurs;
            } else
                throw new NotConnectedException();
        } catch (ServerNotActiveException e) {
            throw new RemoteException("Erreur interne.");
        }

    }

    /*
     * (non-Javadoc)
     * @see chat.serveur.Serveur#getMessages(java.util.Date)
     */
    @Override
    public ArrayList<Message> getMessages(Date date) throws RemoteException,
            NotConnectedException {
        try {
            // on vérifie que le client envoyant la requête est connu
            String reference = RemoteServer.getClientHost();
            boolean connecte = false;
            for (Utilisateur u : this.listeUtilisateurs) {
                connecte |= u.getReference().equals(reference);
            }

            // si le client est connu, on génère la liste des messages à
            // renvoyer.
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

    /**
     * <p>
     * Teste si l'utilisateur passé en paramètre possède la même référence que
     * le client qui a initié la requête, et vérifie que l'utilisateur est
     * connu.
     * </p>
     * @param u
     *            Utilisateur à valider
     * @return true si l'utilisateur est "valide"
     * @throws ServerNotActiveException
     */
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
