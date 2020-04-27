package fr.scrabble.menu.vues;

import java.awt.Event;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

import fr.scrabble.game.Modele;
import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Couleur;

@SuppressWarnings("serial")
public class VueMenuBar extends JMenuBar {

	JMenu appli,couleurJM,langue;
	JMenuItem accueil,arreter,sauver,valider;
	JRadioButtonMenuItem jr1,jr2, l0,l1, l2;
	ButtonGroup bg,bgl;
	Menu menu;
	Modele m;
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
		this.sauver = new JMenuItem();
		this.valider=new JMenuItem();
		
		this.arreter.addActionListener(new Quitte());
		this.accueil.addActionListener(new Accueil());
		this.valider.addActionListener(new Validation());
		this.sauver.addActionListener(new Sauvegarde());
		
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
		this.bgl = new ButtonGroup();
		LangueListener ll = new LangueListener();
		
		boolean selected = false;
		
		for (Locale locale : Menu.LOCALES) {
			JRadioButtonMenuItem localeButton = new JRadioButtonMenuItem(locale.toString());
			localeButton.getModel().setActionCommand(locale.toString());
			localeButton.addActionListener(ll);
			
			this.bgl.add(localeButton);
			this.langue.add(localeButton);
			
			if (locale.toString().equals(menu.getLocale().toString())) {
				localeButton.setSelected(true);
				selected = true;
			}
		}
		
		if (!selected) {
			Enumeration<AbstractButton> buttons = bgl.getElements();
			
			while(buttons.hasMoreElements()) {
				AbstractButton button = (AbstractButton) buttons.nextElement();
				
				if (button.getText().equals("fr_FR")) {
					button.setSelected(true);
					break;
				}
			}
		}	
		
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
	
	@SuppressWarnings("deprecation")
	public VueMenuBar (Menu menu, Modele m) {
		super();
		
		this.menu = menu;
		this.m=m;
		this.couleur = menu.couleur;
		
		this.appli = new JMenu();
		this.couleurJM = new JMenu();
		this.langue = new JMenu();
		
		this.accueil = new JMenuItem();
		this.arreter = new JMenuItem();
		this.sauver = new JMenuItem();
		this.valider=new JMenuItem();
		
		this.arreter.addActionListener(new Quitte());
		this.accueil.addActionListener(new Accueil());
		this.sauver.addActionListener(new Sauvegarde());
		this.valider.addActionListener(new Validation());
		
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
		this.bgl = new ButtonGroup();
		LangueListener ll = new LangueListener();
		
		boolean selected = false;
		
		for (Locale locale : Menu.LOCALES) {
			JRadioButtonMenuItem localeButton = new JRadioButtonMenuItem(locale.toString());
			localeButton.getModel().setActionCommand(locale.toString());
			localeButton.addActionListener(ll);
			
			this.bgl.add(localeButton);
			this.langue.add(localeButton);
			
			if (locale.toString().equals(menu.getLocale().toString())) {
				localeButton.setSelected(true);
				selected = true;
			}
		}
		
		if (!selected) {
			Enumeration<AbstractButton> buttons = bgl.getElements();
			
			while(buttons.hasMoreElements()) {
				AbstractButton button = (AbstractButton) buttons.nextElement();
				
				if (button.getText().equals("fr_FR")) {
					button.setSelected(true);
					break;
				}
			}
		}	
		
		//Ajout dans Appli
		this.appli.add(this.accueil);
		this.appli.add(this.couleurJM);
		this.appli.add(this.langue);
		this.appli.addSeparator();
		this.appli.add(this.valider);
		this.appli.add(this.sauver);
		this.appli.addSeparator();
		this.appli.add(this.arreter);
		
		this.arreter.setAccelerator(KeyStroke.getKeyStroke('q'));
		this.sauver.setAccelerator(KeyStroke.getKeyStroke('s'));
		this.valider.setAccelerator(KeyStroke.getKeyStroke(Event.ENTER,0));
		
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
		this.sauver.setText(strings.getString("sauvegarder"));
		this.valider.setText(strings.getString("valider"));
		this.arreter.setText(strings.getString("quitter"));
		
		this.jr1.setText(strings.getString("clair"));
		this.jr2.setText(strings.getString("sombre"));
		
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
			this.sauver.setText(strings.getString("sauvegarder"));
			this.valider.setText(strings.getString("valider"));
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
	
	class Sauvegarde implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			m.enregistrer();
		}	
	}
	
	class Validation implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent e) {
			m.verificationMot();
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
			String[] localeString = bgl.getSelection().getActionCommand().split("_");
			Locale locale = new Locale(localeString[0], localeString[1]);
			menu.setLocale(locale);
		}
	}
}


