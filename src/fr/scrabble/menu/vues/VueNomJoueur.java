package fr.scrabble.menu.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.scrabble.menu.ControleurBoutons;
import fr.scrabble.menu.Menu;
import fr.scrabble.online.JTextFieldLimit;

public class VueNomJoueur extends JPanel implements ActionListener{

	ArrayList<String> prenom;
	JTextField j1,j2,j3,j4;
	int nbjoueur=1;
	ControleurBoutons cb;
	
	public VueNomJoueur(int nbjoueur, ControleurBoutons cb) {
		super();
		this.prenom = new ArrayList<String>();
		this.nbjoueur=nbjoueur;
		this.cb=cb;
		
		this.setPreferredSize(new Dimension((int) (600*Menu.SCALE),(int) (600*Menu.SCALE)));
		this.setBounds((int) (250*Menu.SCALE), (int) (200*Menu.SCALE), (int) (100*Menu.SCALE), (int) (200*Menu.SCALE));
		this.setOpaque(false);
		
		Font f = new Font("Arial",Font.BOLD,(int)(10*Menu.SCALE));
		
		//label
		JPanel p = new JPanel();
		JLabel label = new JLabel("Noms des Joueurs : ");
		label.setFont(f);
		label.setBounds(0, 0, 100, 100);
		p.add(label);
		p.setBackground(new Color(128, 255, 170));
		p.setOpaque(true);
		this.add(p);
		
		//Creation zone de texte
		this.j1 = new JTextField("Joueur 1", 10);
	    this.j2 = new JTextField("Joueur 2", 10);
	    this.j3= new JTextField("Joueur 3", 10);
	    this.j4 = new JTextField("Joueur 4", 10);
	    
	    //Limite de 15 caracteres
	    this.j1.setDocument(new JTextFieldLimit(15));
	    this.j2.setDocument(new JTextFieldLimit(15));
	    this.j3.setDocument(new JTextFieldLimit(15));
	    this.j4.setDocument(new JTextFieldLimit(15));
	    
	    //Ajout police
	    this.j1.setFont(f);
	    this.j2.setFont(f);
	    this.j3.setFont(f);
	    this.j4.setFont(f);
		
	    //Ajout jtextfield au JPanel en fonction nbjoueur
	    this.add(j1);
	    if(nbjoueur == 2) {
	    	this.add(j2);
	    }
	    else if (nbjoueur == 3) {
	    	this.add(j2);
	    	this.add(j3);
	    }
	    else if (nbjoueur == 4) {
	    	this.add(j2);
	    	this.add(j3);
	    	this.add(j4);
	    }
	    
	    //Bouton validation
	    JButton b = new JButton("Jouer");
	    b.setFont(f);
	    b.addActionListener(this);
	    b.setBounds(200, 200, 100, 10);
	    this.add(b);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg) {
		// TODO Auto-generated method stub
		this.prenom.add(this.j1.getText());
		this.prenom.add(this.j2.getText());
		this.prenom.add(this.j3.getText());
		this.prenom.add(this.j4.getText());
		this.cb.lancerPartie(this.nbjoueur, this.prenom);
	}
	
}
