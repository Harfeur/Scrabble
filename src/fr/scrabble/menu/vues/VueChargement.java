package fr.scrabble.menu.vues;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ResourceBundle;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Couleur;

@SuppressWarnings("serial")
public class VueChargement extends JPanel {
	
	Menu menu;
	Couleur c;
	JLabel chargement;
	JProgressBar loading;
	
	public VueChargement(Menu menu, JProgressBar loading) {
		super();
		this.menu = menu;
		this.c = menu.couleur;
		chargement = new JLabel();
		this.loading = loading;
		
		this.loading.setStringPainted(true);
		this.loading.setPreferredSize(new Dimension((int) Menu.SCALE*400, (int) Menu.SCALE*50));
		this.add(this.loading);
		
		this.setBounds(0,0,(int) (600*Menu.SCALE),(int) (600*Menu.SCALE));
		this.setOpaque(false);
	}

	
	public void paint(Graphics g) {
		super.paint(g);		
		ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
		this.chargement.setText(strings.getString("chargement"));
		this.loading.setString(strings.getString("chargementVue"));
		
		Font font = new Font("Arial",Font.BOLD,(int) (25*Menu.SCALE));
		g.setFont(font);
		g.setColor(this.c.getColorLettre());
		g.drawString(chargement.getText(), (int) (230*Menu.SCALE), (int) (250*Menu.SCALE));
	}

	public void update(Graphics g) {
		if ((int) this.getClientProperty("color") != this.c.getCouleur()) {
			this.putClientProperty("color", this.c.getCouleur());
			
		}
	}
	
}

