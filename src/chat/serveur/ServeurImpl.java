package chat.serveur;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

import chat.commun.Message;
import chat.commun.Utilisateur;

public class ServeurImpl extends UnicastRemoteObject implements Serveur 
{
	private static final long serialVersionUID = 1521779512098629525L;

	protected ServeurImpl() throws RemoteException 
	{
		super();
	}

	protected ArrayList<Utilisateur> listeUtilisateurs = new ArrayList<Utilisateur>();
	protected ArrayList<Message> listeMessages = new ArrayList<Message>();
	
	@Override
	public Utilisateur connect(String id)  throws RemoteException
	{
		Utilisateur nouveau = new Utilisateur(id);
		if(listeUtilisateurs.contains(nouveau)) 
				throw new RemoteException("Cet id est déjà utilisé");
		else
		{
			listeUtilisateurs.add(nouveau);
			return nouveau;
		}
		
	}

	@Override
	public void send(String message, Utilisateur expediteur)  throws RemoteException
	{
		listeMessages.add(new Message(message, expediteur));
	}

	@Override
	public void bye(Utilisateur utilisateur)  throws RemoteException
	{
		listeUtilisateurs.remove(utilisateur);
	}

	@Override
	public ArrayList<Utilisateur> who()  throws RemoteException
	{
		return listeUtilisateurs;
	}

	@Override
	public ArrayList<Message> getMessages(Date date)  throws RemoteException
	{
		ArrayList<Message> listeTemp = new ArrayList<Message>();
		for(Message m: listeMessages)
		{
			if(m.getDateEmission().after(date)) listeTemp.add(m);
		}
		return listeTemp;
	}

}
