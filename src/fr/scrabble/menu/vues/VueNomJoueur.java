package fr.scrabble.menu.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fr.scrabble.game.utils.JTextFieldLimit;
import fr.scrabble.menu.ControleurBoutons;
import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Couleur;

@SuppressWarnings("serial")
public class VueNomJoueur extends JPanel implements ActionListener, Observer{

	ArrayList<String> prenom;
	JTextField j1,j2,j3,j4;
	int nbjoueur=1;
	ControleurBoutons cb;
	JLabel label;
	Menu menu;
	JButton b;
	Couleur c;
	JPanel p;
	
	public VueNomJoueur(Menu menu, int nbjoueur, ControleurBoutons cb, Couleur c) {
		super();
		this.prenom = new ArrayList<String>();
		this.nbjoueur=nbjoueur;
		this.cb=cb;
		this.menu = menu;
		this.c = c;
		
		this.setPreferredSize(new Dimension((int) (600*Menu.SCALE),(int) (600*Menu.SCALE)));
		this.setBounds((int) (250*Menu.SCALE), (int) (200*Menu.SCALE), (int) (100*Menu.SCALE), (int) (200*Menu.SCALE));
		this.setOpaque(false);
		
		Font f = new Font("Arial",Font.BOLD,(int)(10*Menu.SCALE));
		
		//label
		this.p = new JPanel();
		this.label = new JLabel();
		this.label.setFont(f);
		this.label.setForeground(this.c.getColorLettre());
		this.label.setBounds(0, 0, 100, 100);
		this.p.add(label);
		this.p.setBackground(this.c.getColorBouton());
		this.p.setOpaque(true);
		this.add(this.p);
		
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
	    this.b = new JButton();
	    this.b.setFont(f);
	    this.b.setBackground(this.c.getColorBouton());
	    this.b.setForeground(this.c.getColorLettre());
	    this.b.addActionListener(this);
	    this.b.setBounds(200, 200, 100, 10);
	    this.add(b);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg) {
		this.prenom.add(this.j1.getText());
		this.prenom.add(this.j2.getText());
		this.prenom.add(this.j3.getText());
		this.prenom.add(this.j4.getText());
		this.cb.lancerPartie(this.nbjoueur, this.prenom);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", menu.getLocale());
		this.label.setText(strings.getString("nom_joueurs"));
		this.b.setText(strings.getString("jouer"));
		//Sombre button
	    this.b.setBackground(this.c.getColorBouton());
	    this.b.setForeground(this.c.getColorLettre());
	    //Sombre label
		this.label.setForeground(this.c.getColorLettre());
		
		this.p.setBackground(this.c.getColorBouton());
	}

	@Override
	public void update(Observable o, Object arg1) {
		// TODO Auto-generated method stub
		if(o.getClass() == Couleur.class) {
			this.c = (Couleur) o;
			this.repaint();
		}
	}
	
}
