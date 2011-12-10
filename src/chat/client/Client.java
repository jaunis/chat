package chat.client;

import java.util.ArrayList;
import java.util.List;

import chat.commun.Message;
import chat.commun.Utilisateur;
import chat.serveur.Serveur;

public class Client {

    private Utilisateur utilisateur;

    private Interpreteur interpreteur;
    private Updater updater;
    private LienServeur lienServeur;
    private InterfaceGraphique interfaceGraphique;

    public Client(Serveur serveur) {
        this.lienServeur = new LienServeur(this, serveur);
        this.interfaceGraphique = new InterfaceGraphique(this);
        this.interpreteur = new Interpreteur(this);
    }

    public void stopUpdater() {
        this.updater.stopThread();
    }

    public void disconnect() {
        if (this.isConnected()) {
            this.utilisateur = null;
        }
    }

    public InterfaceGraphique getInterfaceGraphique() {
        return this.interfaceGraphique;
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

    public boolean isConnected() {
        return this.utilisateur != null;
    }

    public void setUtilisateur(Utilisateur utilisateurIn) {
        this.utilisateur = utilisateurIn;
    }

    public void startUpdater() {
        this.updater = new Updater(this);
        this.updater.start();
    }

    public void interrompreUpdate() {
        if (this.updater != null) {
            this.updater.pause();
        }
    }

    public void reprendreUpdate() {
        if (this.updater != null) {
            this.updater.reprendre();
        }
    }
}
