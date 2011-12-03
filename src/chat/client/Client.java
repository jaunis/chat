package chat.client;

import java.util.ArrayList;
import java.util.List;

import chat.commun.Message;
import chat.commun.Utilisateur;
import chat.serveur.Serveur;

public class Client {

    private List<Message> messageList = new ArrayList<>();

    private Utilisateur utilisateur;

    private Interpreteur interpreteur;
    private Visualisateur visualisateur;
    private Updater updater;
    private GestionnaireTexte gestionnaireTexte;
    private LienServeur lienServeur;

    public Client(Serveur serveur) {
        this.lienServeur = new LienServeur(this, serveur);
        this.visualisateur = new Visualisateur();

        this.interpreteur = new Interpreteur(this, this.lienServeur);
        this.gestionnaireTexte = new GestionnaireTexte(this, this.interpreteur);
        this.updater = new Updater(this, serveur);

        this.gestionnaireTexte.start();
        this.updater.start();
    }

    public Utilisateur getUtilisateur() {
        return this.utilisateur;
    }

    /**
     * Add new messages to the list.
     * @param messages
     *            the list of messages
     */
    public void addMessages(List<Message> messages) {
        this.messageList.addAll(messages);
    }
}
