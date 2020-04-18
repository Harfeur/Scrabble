package fr.scrabble.menu.vues;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

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
	ButtonGroup bg;
	Menu menu;
	Couleur couleur;
	
	public VueMenuBar (Menu menu) {
		super();
		
		this.menu = menu;
		this.couleur = menu.couleur;
		
		this.appli = new JMenu();
		this.couleurJM = new JMenu();
		
		this.accueil = new JMenuItem();
		this.arreter = new JMenuItem();
		
		this.arreter.addActionListener(new Quitte());
		this.accueil.addActionListener(new Accueil());
		
		//RadioButton
		this.jr1 = new JRadioButtonMenuItem();
		this.jr2 = new JRadioButtonMenuItem();

		this.jr1.getModel().setActionCommand("Clair");
		this.jr2.getModel().setActionCommand("Sombre");
		
		this.bg = new ButtonGroup();
		bg.add(this.jr1);
		bg.add(this.jr2);
		this.jr1.setSelected(true);
		
		//RadioButton ajout Listener
		CouleurListener cl = new CouleurListener();
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
		if(o.getClass() == Couleur.class) {
			this.couleur = (Couleur) o;
			this.repaint();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
		this.appli.setText(strings.getString("application"));
		this.couleurJM.setText(strings.getString("couleur"));
		
		this.accueil.setText(strings.getString("accueil"));
		this.arreter.setText(strings.getString("quitter"));
		
		this.jr1.setText(strings.getString("clair"));
		this.jr2.setText(strings.getString("sombre"));
	}
	
	class Quitte implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}	
	}

	class Accueil implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			menu.vueMenu();
		}	
	}

	class CouleurListener implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(bg.getSelection().getActionCommand());
			couleur.changeCouleur(bg.getSelection().getActionCommand());
		}
	}
}


