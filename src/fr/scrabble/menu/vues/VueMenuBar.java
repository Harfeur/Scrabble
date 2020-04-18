package fr.scrabble.menu.vues;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
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
public class VueMenuBar extends JMenuBar {

	JMenu appli,couleurJM,langue;
	JMenuItem accueil,arreter;
	JRadioButtonMenuItem jr1,jr2, l0,l1, l2;
	ButtonGroup bg,bgl;
	Menu menu;
	Couleur couleur;
	
	public VueMenuBar (Menu menu) {
		super();
		
		this.menu = menu;
		this.couleur = menu.couleur;
		
		this.appli = new JMenu();
		this.couleurJM = new JMenu();
		this.langue = new JMenu();
		
		this.accueil = new JMenuItem();
		this.arreter = new JMenuItem();
		
		this.arreter.addActionListener(new Quitte());
		this.accueil.addActionListener(new Accueil());
		
		//RadioButton Couleur
		this.jr1 = new JRadioButtonMenuItem();
		this.jr2 = new JRadioButtonMenuItem();

		this.jr1.getModel().setActionCommand("Clair");
		this.jr2.getModel().setActionCommand("Sombre");
		
		this.bg = new ButtonGroup();
		bg.add(this.jr1);
		bg.add(this.jr2);
		this.jr1.setSelected(true);
		
		//RadioButton couleur ajout Listener
		CouleurListener cl = new CouleurListener();
		this.jr1.addActionListener(cl);
		this.jr2.addActionListener(cl);
		
		//Ajout RadioButton couleur dans JMenu couleur
		this.couleurJM.add(this.jr1);		
		this.couleurJM.add(this.jr2);	
		
		//RadioButton Langue
		this.l0 = new JRadioButtonMenuItem();
		this.l1 = new JRadioButtonMenuItem();
		this.l2 = new JRadioButtonMenuItem();

		this.l0.getModel().setActionCommand(Locale.getDefault().toString());
		this.l1.getModel().setActionCommand(menu.locales[0].toString());
		this.l2.getModel().setActionCommand(menu.locales[1].toString());
				
		this.bgl = new ButtonGroup();
		bgl.add(this.l0);				
		bgl.add(this.l1);
		bgl.add(this.l2);
		this.l0.setSelected(true);
				
		//RadioButton langue ajout Listener
		LangueListener ll = new LangueListener();
		this.l0.addActionListener(ll);
		this.l1.addActionListener(ll);
		this.l2.addActionListener(ll);
				
		//Ajout RadioButton langue dans JMenu langue
		this.langue.add(this.l0);		
		this.langue.add(this.l1);		
		this.langue.add(this.l2);	
		
		//Ajout dans Appli
		this.appli.add(this.accueil);
		this.appli.add(this.couleurJM);
		this.appli.add(this.langue);
		this.appli.addSeparator();
		this.appli.add(this.arreter);
		
		this.arreter.setAccelerator(KeyStroke.getKeyStroke('q'));
		
		this.add(appli);
		this.setVisible(true);
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.setLocale(menu.getLocale());
		this.putClientProperty("color", this.couleur.getCouleur());
		
		//Changement de langue
		ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
		this.appli.setText(strings.getString("application"));
		this.couleurJM.setText(strings.getString("couleur"));
		this.langue.setText(strings.getString("langue"));
		
		this.accueil.setText(strings.getString("accueil"));
		this.arreter.setText(strings.getString("quitter"));
		
		this.jr1.setText(strings.getString("clair"));
		this.jr2.setText(strings.getString("sombre"));
		
		this.l0.setText(Locale.getDefault().toString());
		this.l1.setText(strings.getString(menu.locales[0].toString()));
		this.l2.setText(strings.getString(menu.locales[1].toString()));
		
		//Mode sombre
		this.setBackground(this.couleur.getColorBouton());
	}
	
	@Override
	public void update(Graphics g) {
		if (menu.getLocale()!=this.getLocale()) {
			this.setLocale(menu.getLocale());
			
			ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
			this.appli.setText(strings.getString("application"));
			this.couleurJM.setText(strings.getString("couleur"));
			
			this.accueil.setText(strings.getString("accueil"));
			this.arreter.setText(strings.getString("quitter"));
			
			this.jr1.setText(strings.getString("clair"));
			this.jr2.setText(strings.getString("sombre"));
		}
		if ((int) this.getClientProperty("color") != this.couleur.getCouleur()) {
			this.putClientProperty("color", this.couleur.getCouleur());
			
			this.setBackground(this.couleur.getColorBouton());
		}
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
	
	class LangueListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(bgl.getSelection().getActionCommand());
			
		}
	}
}


