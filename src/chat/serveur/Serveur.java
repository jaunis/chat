package chat.serveur;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import chat.commun.Message;
import chat.commun.Utilisateur;
import chat.exceptions.AlreadyConnectedException;
import chat.exceptions.IdAlreadyUsedException;
import chat.exceptions.NotConnectedException;

public interface Serveur extends java.rmi.Remote {

	/**
	 * <p>Méthode permettant à un client de se connecter avec un id donné.
	 * Si la connexion est un succès, la méthode renvoie un message disant que l'utilisateur
	 * est connecté. La date d'émission de ce message sert de référence au client.</p>
	 * 
	 * @param id
	 * @return message confirmant la connexion
	 * @throws RemoteException
	 * @throws AlreadyConnectedException si le client est déjà connecté avec un autre id
	 * @throws IdAlreadyUsedException si l'id est déjà utilisé
	 */
    public Message connect(String id) 
    		throws RemoteException, AlreadyConnectedException, IdAlreadyUsedException;

    /**
     * <p>Envoie un message à tous les utilisateurs. L'expéditeur doit être connu.</p>
     * @param message
     * @param expediteur
     * @throws RemoteException
     * @throws NotConnectedException si l'utilisateur est inconnu
     */
    public void send(String message, Utilisateur expediteur)
            throws RemoteException, NotConnectedException;
    
    /**
     * <p>Déconnecte l'utilisateur passé en paramètre, en le supprimant de
     * la liste des utilisateurs.</p>
     * @param utilisateur
     * @throws RemoteException
     * @throws NotConnectedException si l'utilisateur est inconnu
     */
    public void bye(Utilisateur utilisateur) throws RemoteException, NotConnectedException;

    /**
     * Donne la liste des utilisateurs connectés.
     * @return la liste des utilisateurs
     * @throws RemoteException
     * @throws NotConnectedException si l'utilisateur n'est pas connecté
     */
    public ArrayList<Utilisateur> who() throws RemoteException, NotConnectedException;

    /**
     * Donne la liste des messages postérieurs à la date passée en paramètre
     * @param date en-decà de laquelle les messages ne sont pas renvoyés
     * @return la liste des messages postérieurs à la date donnée
     * @throws RemoteException
     * @throws NotConnectedException si l'utilisateur n'est pas connecté
     */
    public ArrayList<Message> getMessages(Date date) throws RemoteException, NotConnectedException;
}
