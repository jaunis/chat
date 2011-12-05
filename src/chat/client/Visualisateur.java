package chat.client;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import chat.commun.Message;

public class Visualisateur extends JFrame {

    // /**
    // * Version ID.
    // */
    // private static final long serialVersionUID = 1L;
    //
    // private Client client;
    //
    // private JTextArea textViewer;
    // private JTextField textInput;
    // private JPanel panel;
    //
    // public Visualisateur(Client clientIn) {
    // super();
    // this.setTitle("Client");
    // this.setMinimumSize(new Dimension(500, 500));
    // this.setPreferredSize(new Dimension(500, 500));
    //
    // this.client = clientIn;
    //
    // this.panel = new JPanel();
    // this.panel.setLayout(new FlowLayout());
    // this.add(this.panel);
    //
    // this.textViewer = new JTextArea();
    // this.textViewer.setPreferredSize(new Dimension(150, 50));
    // this.panel.add(this.textViewer);
    //
    // this.textInput = new JTextField();
    // this.textInput.setPreferredSize(new Dimension(100, 20));
    // this.panel.add(this.textInput);
    //
    // this.setVisible(true);
    // }
    //
    // public void display(List<Message> messages) {
    // for (Message m : messages)
    // this.textViewer.append(m.toString());
    // }
    //
    // public JTextArea getTextViewer() {
    // return this.textViewer;
    // }
}
