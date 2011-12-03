package chat.client;

import java.util.ArrayList;
import java.util.List;

import chat.commun.Message;
import chat.commun.Utilisateur;
import chat.serveur.Serveur;

public class Client {

    private List<Message> listeMessages = new ArrayList<>();

    private List<Utilisateur> listeUtilisateurs = new ArrayList<>();

    private Utilisateur utilisateur;

    private Interpreteur interpreteur;

    private Visualisateur visualisateur;
    private Updater updater;
    private GestionnaireTexte gestionnaireTexte;
    private LienServeur lienServeur;

    public Client(Serveur serveur) {
        this.lienServeur = new LienServeur(this, serveur);
        this.visualisateur = new Visualisateur(this);
        this.interpreteur = new Interpreteur(this);
        this.gestionnaireTexte = new GestionnaireTexte(this);

        this.updater = new Updater(this);

        this.gestionnaireTexte.start();
        this.updater.start();
    }

    /**
     * Add new messages to the list.
     * @param messages
     *            the list of messages
     */
    public void addMessages(List<Message> messages) {
        this.listeMessages.addAll(messages);
    }

    public GestionnaireTexte getGestionnaireTexte() {
        return this.gestionnaireTexte;
    }

    public Interpreteur getInterpreteur() {
        return this.interpreteur;
    }

    public LienServeur getLienServeur() {
        return this.lienServeur;
    }

    public Updater getUpdater() {
        return this.updater;
    }

    public Utilisateur getUtilisateur() {
        return this.utilisateur;
    }

    public Visualisateur getVisualisateur() {
        return this.visualisateur;
    }

    public void setListeUtilisateurs(List<Utilisateur> listeUtilisateur) {
        this.listeUtilisateurs = listeUtilisateur;
    }
}
