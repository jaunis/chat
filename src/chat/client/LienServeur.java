package chat.client;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;

import chat.commun.Message;
import chat.serveur.Serveur;

public class LienServeur {

    private Serveur serveur;
    private Client client;
    private Date derniereMaj;

    public LienServeur(Client clientIn, Serveur serveurIn) {
        this.client = clientIn;
        this.serveur = serveurIn;
    }

    public void bye() {
        if (this.client.isConnected()) {
            try {
                this.serveur.bye(this.client.getUtilisateur());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void connect(String userID) {
        try {
            Message retour = this.serveur.connect(userID);
        	this.client.setUtilisateur(retour.getExpediteur());
        	derniereMaj = retour.getDateEmission();
        	ArrayList<Message> liste = new ArrayList<>();
            liste.add(retour);
            this.client.setMessages(liste);
            System.out.println(retour);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getMessages() {
        if (this.client.isConnected() && derniereMaj != null) {
            try {
                ArrayList<Message> retour = this.serveur.getMessages(derniereMaj);
                this.client.setMessages(retour);
                if(!retour.isEmpty())
                	derniereMaj = retour.get(retour.size()-1).getDateEmission();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMessage(String message) {
        if (this.client.isConnected()) {
            try {
                this.serveur.send(message, this.client.getUtilisateur());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void who() {
        if (this.client.isConnected()) {
            try {
                System.out.println(this.serveur.who());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}
