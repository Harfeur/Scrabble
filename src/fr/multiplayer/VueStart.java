package fr.multiplayer;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



@SuppressWarnings("serial")
public class VueStart extends JPanel implements ActionListener {
	
	Client client;
	JFrame f;
	String prenom,ip;

	public VueStart(Client client) {
		super();
		this.client = client;
		f = new JFrame("Start"); 
		
		JLabel prenom_l = new JLabel("Prénom");
		JTextField prenom_t = new JTextField("Bernadette");
		JLabel ip_l = new JLabel("Adresse IP");
		JTextField ip_t = new JTextField("###.##.##.##.##");
		JButton valider = new JButton("Valider");
		
		valider.addActionListener(this);
			
		this.add(prenom_l);
		this.add(prenom_t);
		this.add(ip_l);
		this.add(ip_t);
		this.add(valider);
		
		this.prenom=prenom_t.getText();
		this.ip=ip_t.getText();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO Puis fermer la fenetre
		client.demarrer(ip, prenom);
	}
	
	
}
