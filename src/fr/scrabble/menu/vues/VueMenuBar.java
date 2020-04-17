package fr.scrabble.menu.vues;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Couleur;

@SuppressWarnings("serial")
public class VueMenuBar extends JMenuBar implements Observer {

	JMenu appli,couleurJM;
	JMenuItem accueil,arreter;
	JRadioButtonMenuItem jr1,jr2;
	Menu menu;
	Couleur couleur;
	
	public VueMenuBar (Menu menu, Couleur couleur) {
		super();
		this.menu = menu;
		this.couleur = couleur;
		
		this.appli = new JMenu("Application");
		this.couleurJM = new JMenu("Couleur");
		
		this.accueil = new JMenuItem("Accueil");
		this.arreter = new JMenuItem("Quitter");
		
		this.arreter.addActionListener(new Quitte());
		this.accueil.addActionListener(new Accueil(this.menu));
		
		//RadioButton
		this.jr1 = new JRadioButtonMenuItem("Clair");
		this.jr2 = new JRadioButtonMenuItem("Sombre");
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(this.jr1);
		bg.add(this.jr2);
		this.jr1.setSelected(true);
		
		//RadioButton ajout Listener
		CouleurListener cl = new CouleurListener(this.couleur);
		this.jr1.addActionListener(cl);
		this.jr2.addActionListener(cl);
		
		//Ajout RadioButton dans JMenu couleur
		this.couleurJM.add(this.jr1);		
		this.couleurJM.add(this.jr2);	
		
		//Ajout dans Appli
		this.appli.add(this.accueil);
		this.appli.add(this.couleurJM);
		this.appli.addSeparator();
		this.appli.add(this.arreter);
		
		this.appli.setMnemonic('a');
		this.arreter.setAccelerator(KeyStroke.getKeyStroke('q'));
		
		this.add(appli);
		
		this.setBackground(this.couleur.getColorBoutonVert()[this.couleur.getCouleur()]);
		this.setVisible(true);
		
	}

	@Override
	public void update(Observable o, Object arg1) {
		// TODO Auto-generated method stub
		if(o.getClass() == Couleur.class) {
			this.couleur = (Couleur) o;
			this.repaint();
		}
	}	
}

class Quitte implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.exit(0);
	}	
}

class Accueil implements ActionListener{

	Menu menu;
	
	public Accueil(Menu menu) {
		this.menu = menu ;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.menu.vueMenu();
	}	
}

class CouleurListener implements ActionListener{
	
	Couleur couleur;
	
	public CouleurListener(Couleur couleur) {
		this.couleur = couleur;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.couleur.changeCouleur(((JRadioButtonMenuItem)e.getSource()).getText()); 
	}
	
}
