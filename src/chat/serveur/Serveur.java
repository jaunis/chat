package chat.serveur;

import java.util.List;

import chat.commun.Message;
import chat.commun.Utilisateur;

public interface Serveur {

    public void bye(Utilisateur utilisateur);

    public Utilisateur connect(String id);

    public List<Message> getMessages();

    public void send(Message message, Utilisateur expediteur);

    public List<Utilisateur> who();
}
