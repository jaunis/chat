package chat.serveur;

import java.util.List;

import chat.commun.Message;
import chat.commun.Utilisateur;

public class ServeurImpl implements Serveur {

    @Override
    public void bye(Utilisateur utilisateur) {
        // TODO Auto-generated method stub
        System.out.println("Utilisateur " + utilisateur.toString()
                + " has quit.");
    }

    @Override
    public Utilisateur connect(String id) {
        // TODO Auto-generated method stub
        System.out.println("Utilisateur " + id + " has connected.");
        return null;
    }

    @Override
    public List<Message> getMessages() {
        // TODO Auto-generated method stub
        System.out.println("Asked for messages");
        return null;
    }

    @Override
    public void send(Message message, Utilisateur expediteur) {
        // TODO Auto-generated method stub
        System.out.println("Utilisateur " + expediteur.toString()
                + " has send this : " + message.toString());
    }

    @Override
    public List<Utilisateur> who() {
        // TODO Auto-generated method stub
        System.out.println("Asked for users");
        return null;
    }

}
