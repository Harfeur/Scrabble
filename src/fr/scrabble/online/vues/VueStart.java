package fr.scrabble.online.vues;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.scrabble.menu.Menu;
import fr.scrabble.online.Client;

@SuppressWarnings("serial")
public class VueStart extends JPanel implements ActionListener {
	
	Client client;
	JTextField prenom_t,ip_t;

	public VueStart(Client client) {
		super();
		this.client = client;
		
		Font font = new Font("Arial",Font.BOLD,(int) (15*Menu.SCALE));
		
		JLabel prenom_l = new JLabel("Prénom : ");
		JLabel ip_l = new JLabel("Adresse IP : ");	
		
		this.prenom_t = new JTextField("Bernadette",10);	
		this.ip_t = new JTextField("127.0.0.1",10);
		
		JButton valider = new JButton("Valider");
		
		prenom_l.setFont(font);
		this.prenom_t.setFont(font);
		ip_l.setFont(font);	
		this.ip_t.setFont(font);
		valider.setFont(font);
		
		valider.addActionListener(this);
		
		this.setBackground(new Color(128, 255, 170));
        this.setBounds((int) (240*Menu.SCALE),(int) (240*Menu.SCALE),(int) (150*Menu.SCALE),(int) (120*Menu.SCALE));
		
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
		client.rejoindre(ip, prenom);		
	}
	
	
}
