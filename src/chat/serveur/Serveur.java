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

    public Message connect(String id) 
    		throws RemoteException, AlreadyConnectedException, IdAlreadyUsedException;

    public void send(String message, Utilisateur expediteur)
            throws RemoteException, NotConnectedException;

    public void bye(Utilisateur utilisateur) throws RemoteException, NotConnectedException;

    public ArrayList<Utilisateur> who() throws RemoteException, NotConnectedException;

    public ArrayList<Message> getMessages(Date date) throws RemoteException, NotConnectedException;
}
