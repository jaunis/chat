package chat.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * Interface graphique du client. Avec gestion du texte et de l'affichage.
 * @author Daniel Lefevre
 */
public class InterfaceGraphique extends JFrame {

    /**
     * Client actuel.
     */
    private Client client;

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * La zone de texte où afficher les messages.
     */
    private JTextArea chatText;
    /**
     * La zone de texte où entrer les messages.
     */
    private TextReader chatLine;

    /**
     * Constructeur.
     * @param clientIn
     *            le client
     */
    public InterfaceGraphique(final Client clientIn) {
        super("RMI Chat");

        this.setClient(clientIn);

        this.initGUIComponents();
    }

    /**
     * Affiche un texte.
     * @param texte
     *            le texte
     */
    public final void display(final String texte) {
        this.chatText.append(texte + "\n");
        this.repaint();
    }

    /**
     * Affiche un message d'erreur, c'est-à-dire le message avec le mot
     * "Error : " avant.
     * @param texte
     *            le message d'erreur à afficher
     */
    public final void displayError(final String texte) {
        this.chatText.append("Error : " + texte + "\n");
        this.repaint();
    }

    /**
     * Getter.
     * @return le client
     */
    public final Client getClient() {
        return this.client;
    }

    /**
     * Initialise les composants graphiques de la fenêtre.
     */
    private void initGUIComponents() {
        // Sets up the chat pane
        JPanel chatPane = new JPanel(new BorderLayout());

        this.chatText = new JTextArea(10, 20);
        this.chatText.setLineWrap(true);
        this.chatText.setEditable(false);
        this.chatText.setForeground(Color.blue);

        JScrollPane chatTextPane = new JScrollPane(this.chatText,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        this.chatLine = new TextReader();

        chatPane.add(this.chatLine, BorderLayout.SOUTH);
        chatPane.add(chatTextPane, BorderLayout.CENTER);
        chatPane.setPreferredSize(new Dimension(400, 400));

        // Sets up the main pane
        JPanel mainPane = new JPanel(new BorderLayout());
        mainPane.add(chatPane, BorderLayout.CENTER);

        // Sets up the main frame
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPane);
        this.setSize(this.getPreferredSize());
        this.setLocation(100, 100);
        this.pack();
        this.setVisible(true);
    }

    /**
     * Setter.
     * @param clientIn
     *            le nouveau client
     */
    public final void setClient(final Client clientIn) {
        this.client = clientIn;
    }

    /**
     * Donne le texte entré à l'interpréteur pour que celui-ci l'analyse.
     */
    public final void traiterTexte() {
        this.client.getInterpreteur().traiterTexte(this.chatLine.getText());
        this.chatLine.setText("");
    }

    /**
     * Sur-implémente un JTextField qui sert d'entrée pour l'utilisateur.
     * @author Daniel Lefevre
     */
    public class TextReader extends JTextField {

        /**
         * Serial version UID.
         */
        private static final long serialVersionUID = 1L;

        /**
         * Constructeur.
         */
        public TextReader() {
            super();
            this.setEnabled(true);
            this.addKeyListener(new java.awt.event.KeyAdapter() {

                @Override
                public void keyPressed(final KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        InterfaceGraphique.this.traiterTexte();
                    }
                }

                @Override
                public void keyReleased(final KeyEvent e) {
                    // Not used.
                }

                @Override
                public void keyTyped(final KeyEvent e) {
                    // Not used.
                }
            });

            this.getDocument().addDocumentListener(new DocumentListener() {

                @Override
                public void changedUpdate(final DocumentEvent e) {
                    // Not used because it doesn't work.
                }

                @Override
                public void insertUpdate(final DocumentEvent e) {
                    this.testEmpty();
                }

                @Override
                public void removeUpdate(final DocumentEvent e) {
                    this.testEmpty();
                }

                public void testEmpty() {
                    if (!TextReader.this.getText().equals("")) {
                        InterfaceGraphique.this.getClient().interrompreUpdate();
                    } else {
                        InterfaceGraphique.this.getClient().reprendreUpdate();
                    }
                }
            });
        }
    }
}
