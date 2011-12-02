package chat.serveur;

import java.util.List;
import chat.commun.Message;
import chat.commun.Utilisateur;

public interface Serveur 
{
	public Utilisateur connect(String id);
	public void send(Message message, Utilisateur expediteur);
	public void bye(Utilisateur utilisateur);
	public List<Utilisateur> who();
	public List<Message> getMessages();
}
