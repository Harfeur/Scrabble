package fr.scrabble.multiplayer.vues;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.scrabble.menu.vues.VueMenu;
import fr.scrabble.multiplayer.Client;
import javafx.scene.paint.Color;

@SuppressWarnings("serial")
public class VueStart extends JPanel implements ActionListener {
	
	Client client;
	JTextField prenom_t,ip_t;
	VueMenu vueMenu;
	JFrame f;

	public VueStart(Client client) {
		super();
		this.client = client;
		vueMenu = new VueMenu();
		
		JLabel prenom_l = new JLabel("Prénom :");
		this.prenom_t = new JTextField("Bernadette",10);
		JLabel ip_l = new JLabel("Adresse IP :");
		this.ip_t = new JTextField("127.0.0.1",10);
		JButton valider = new JButton("Valider");
		
		valider.addActionListener(this);
		
		this.add(prenom_l);
		this.add(prenom_t);
		this.add(ip_l);
		this.add(ip_t);
		this.add(valider);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String prenom = this.prenom_t.getText();
		String ip = this.ip_t.getText();
		client.demarrer(ip, prenom);		
	}
	
	
}
