package fr.scrabble.online.vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.scrabble.menu.Menu;
import fr.scrabble.online.Client;
import fr.scrabble.structures.Couleur;

public class VueAttente extends JPanel implements ActionListener, Observer{

	Client client;
	Couleur c;
	JLabel txt;
	JButton lancerPartie;
	
	public VueAttente(Client client, Couleur c) {
		super();
		this.client = client;  
		this.c = c;
		
		this.txt = new JLabel("En attente");
		this.lancerPartie = new JButton("Lancer la partie");
		
		Font font = new Font("Arial",Font.BOLD,(int) (15*Menu.SCALE));
		txt.setFont(font);
		lancerPartie.setFont(font);		
		
		lancerPartie.addActionListener(this);
		
        this.setBounds((int) (230*Menu.SCALE),(int) (240*Menu.SCALE),(int) (150*Menu.SCALE),(int) (60*Menu.SCALE));
		
		this.add(txt);
		this.add(lancerPartie);
		
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.txt.setForeground(this.c.getColorLettre()[this.c.getCouleur()]);
		this.lancerPartie.setForeground(this.c.getColorLettre()[this.c.getCouleur()]);
		this.lancerPartie.setBackground(c.getColorBoutonVert()[c.getCouleur()]);
		this.setBackground(c.getColorBoutonVert()[c.getCouleur()]);
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		client.demarrer();
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