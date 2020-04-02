package fr.multiplayer;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

@SuppressWarnings("serial")
public class VueStart extends JPanel implements ActionListener {
	
	Client client;
	JFrame f;

	public VueStart(Client client) {
		super();
		this.client = client;
		//TODO Afficher deux zones de texte (prénom, ip) et un bouton (Valider)
		JLabel prenom_l = new JLabel("Prénom");
		JTextField prenom_t = new JTextField("Bernadette");
		this.add(prenom_l);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO Lire zone de texte lorsque bouton pressé puis envoyer à client.demarrer(message);
		//TODO Puis fermer la fenetre
	}

}
