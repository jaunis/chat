package chat.client;

import chat.commun.Utilisateur;
import chat.serveur.Serveur;

/**
 * @author Daniel
 */
/**
 * @author Daniel
 */
public class Client {

    /**
     * L'utilisateur utilisant ce client, s'il existe (c'est-à-dire, s'il est
     * connecté).
     */
    private Utilisateur utilisateur;

    /**
     * L'interpréteur de texte.
     */
    private Interpreteur interpreteur;
    /**
     * Le metteur à jour des messages.
     */
    private MessageUpdater messageUpdater;
    /**
     * Le lien entre le client et le serveur.
     */
    private LienServeur lienServeur;
    /**
     * L'interface graphique associée au client.
     */
    private InterfaceGraphique interfaceGraphique;

    /**
     * Constructeur.
     * @param serveur
     *            le serveur auquel se connecter.
     */
    public Client(final Serveur serveur) {
        this.lienServeur = new LienServeur(this, serveur);
        this.interfaceGraphique = new InterfaceGraphique(this);
        this.interpreteur = new Interpreteur(this);
    }

    /**
     * Se déconnecter, c'est-à-dire détruire l'utilisateur.
     */
    public final void disconnect() {
        if (this.isConnected()) {
            this.utilisateur = null;
        }
    }

    /**
     * Getter.
     * @return l'interface graphique
     */
    public final InterfaceGraphique getInterfaceGraphique() {
        return this.interfaceGraphique;
    }

    /**
     * Getter.
     * @return l'interpréteur
     */
    public final Interpreteur getInterpreteur() {
        return this.interpreteur;
    }

    /**
     * Getter.
     * @return le lien avec le serveur
     */
    public final LienServeur getLienServeur() {
        return this.lienServeur;
    }

    /**
     * Getter.
     * @return le metteur à jour de message
     */
    public final MessageUpdater getMessageUpdater() {
        return this.messageUpdater;
    }

    /**
     * Getter.
     * @return l'utilisateur
     */
    public final Utilisateur getUtilisateur() {
        return this.utilisateur;
    }

    /**
     * Mettre en pause le message updater.
     */
    public final void interrompreUpdate() {
        if (this.messageUpdater != null) {
            this.messageUpdater.pause();
        }
    }

    /**
     * Vérifie si le client est connecté, c'est-à-dire que l'utilisateur est
     * null.
     * @return true si l'utilisateur est différent de null, false sinon
     */
    public final boolean isConnected() {
        return this.utilisateur != null;
    }

    /**
     * Relancer le metteur à jour de messages.
     */
    public final void reprendreUpdate() {
        if (this.messageUpdater != null) {
            this.messageUpdater.reprendre();
        }
    }

    /**
     * Setter.
     * @param utilisateurIn
     *            le nouvel utilisateur
     */
    public final void setUtilisateur(final Utilisateur utilisateurIn) {
        this.utilisateur = utilisateurIn;
    }

    /**
     * Lancer le MessageUpdater.
     */
    public final void startUpdater() {
        this.messageUpdater = new MessageUpdater(this);
        this.messageUpdater.start();
    }

    /**
     * Arrêter définitivement le MessageUpdater.
     */
    public final void stopUpdater() {
        this.messageUpdater.stopThread();
    }
}
