package chat.client;

import java.util.ArrayList;
import java.util.List;

import chat.commun.Message;
import chat.commun.Utilisateur;
import chat.serveur.Serveur;

public class Client {

    private List<Message> listeMessages = new ArrayList<>();

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

        this.visualisateur.start();
        this.gestionnaireTexte.start();
        this.updater.start();
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

    public void setUtilisateur(Utilisateur utilisateurIn) {
        this.utilisateur = utilisateurIn;
    }

    public boolean isConnected() {
        return this.utilisateur != null;
    }

    public void disconnect() {
        if (this.isConnected()) {
            this.utilisateur = null;
        }
    }

    public List<Message> getAllMessages() {
        return this.listeMessages;
    }

    public void addMessages(List<Message> messages) {
        this.listeMessages.addAll(messages);
    }

    public List<Message> getDerniersMessages() {
        if (this.lienServeur.getDateDernierMessage() == null) {
            return this.getAllMessages();
        }
        List<Message> liste = new ArrayList<>();
        for (Message m : this.listeMessages) {
            if (m.getDateEmission().after(m.getDateEmission())) {
                liste.add(m);
            }
        }
        return liste;
    }
}
