package fr.scrabble.menu.vues;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

@SuppressWarnings("serial")
public class VueMenuBar extends JMenuBar {

	JMenu appli,couleur;
	JMenuItem accueil,arreter;
	JRadioButtonMenuItem jr1,jr2;
	
	public VueMenuBar (ActionListener accueil) {
		super();
		
		this.appli = new JMenu("Application");
		this.couleur = new JMenu("Couleur");
		
		this.accueil = new JMenuItem("Accueil");
		this.arreter = new JMenuItem("Arrêter");
		
		this.arreter.addActionListener(new Quitte());
		this.accueil.addActionListener(accueil);
		
		//RadioButton
		this.jr1 = new JRadioButtonMenuItem("Clair");
		this.jr2 = new JRadioButtonMenuItem("Sombre");
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(this.jr1);
		bg.add(this.jr2);
		this.jr1.setSelected(true);
		
		//Ajout RadioButton dans couleur
		this.couleur.add(this.jr1);		
		this.couleur.add(this.jr2);	
		
		//Ajout dans Appli
		this.appli.add(this.accueil);
		this.appli.add(this.couleur);
		this.appli.addSeparator();
		this.appli.add(this.arreter);
		
		
		this.add(appli);
		
		this.setBackground(new Color(128, 255, 170));
		this.setVisible(true);
		
	}	
}

class Quitte implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
	}
	
}
