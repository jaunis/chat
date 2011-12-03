package chat.serveur;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import chat.commun.Message;
import chat.commun.Utilisateur;

public interface Serveur extends java.rmi.Remote
{
	public Utilisateur connect(String id) throws RemoteException;
	public void send(String message, Utilisateur expediteur) throws RemoteException;
	public void bye(Utilisateur utilisateur) throws RemoteException;
	public ArrayList<Utilisateur> who() throws RemoteException;
	public ArrayList<Message> getMessages(Date date) throws RemoteException;
}
