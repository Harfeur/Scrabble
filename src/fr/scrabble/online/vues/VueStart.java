package fr.scrabble.online.vues;

import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.scrabble.menu.Menu;
import fr.scrabble.online.Client;
import fr.scrabble.online.JTextFieldLimit;
import fr.scrabble.structures.Couleur;

@SuppressWarnings("serial")
public class VueStart extends JPanel implements ActionListener, Observer {
	
	Client client;
	JTextField prenom_t,ip_t;
	JLabel prenom_l,ip_l;
	JButton valider;
	Couleur c;

	public VueStart(Client client, Couleur c) {
		super();
		this.client = client;
		this.c = c;
		
		Font font = new Font("Arial",Font.BOLD,(int) (15*Menu.SCALE));
		
		prenom_l = new JLabel("Prénom : ");
		ip_l = new JLabel("Adresse IP : ");	
		
		this.prenom_t = new JTextField("Bernadette",10);	
		this.ip_t = new JTextField("127.0.0.1",10);
		
		valider = new JButton("Valider");
		
		prenom_l.setFont(font);
		this.prenom_t.setFont(font);
		ip_l.setFont(font);	
		this.ip_t.setFont(font);
		valider.setFont(font);
		
		prenom_t.setDocument(new JTextFieldLimit(15));
		
		valider.addActionListener(this);
		
        this.setBounds((int) (240*Menu.SCALE),(int) (240*Menu.SCALE),(int) (150*Menu.SCALE),(int) (120*Menu.SCALE));
		
		this.add(prenom_l);
		this.add(prenom_t);
		this.add(ip_l);
		this.add(ip_t);
		this.add(valider);
		
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//Mode sombre
		this.setBackground(c.getColorBoutonVert()[c.getCouleur()]);
		
		this.prenom_l.setForeground(this.c.getColorLettre()[this.c.getCouleur()]);
		this.ip_l.setForeground(this.c.getColorLettre()[this.c.getCouleur()]);
		
		this.valider.setBackground(c.getColorBoutonVert()[c.getCouleur()]);
		this.valider.setForeground(this.c.getColorLettre()[this.c.getCouleur()]);		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String prenom = this.prenom_t.getText();
		String ip = this.ip_t.getText();
		client.rejoindre(ip, prenom);		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(o.getClass() == Couleur.class) {
			this.c = (Couleur) o;
			this.repaint();
		}
	}
	
	
}
