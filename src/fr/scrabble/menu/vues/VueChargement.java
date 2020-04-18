package fr.scrabble.menu.vues;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ResourceBundle;

import javax.swing.JPanel;

import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Couleur;

public class VueChargement extends JPanel {
	
	Menu menu;
	Couleur c;
	
	public VueChargement(Menu menu) {
		super();
		this.menu = menu;
		this.c = menu.couleur;
		
		this.setBounds(0,0,(int) (600*Menu.SCALE),(int) (600*Menu.SCALE));
		this.setOpaque(false);
	}

	
	public void paint(Graphics g) {
		super.paint(g);
		Font font = new Font("Arial",Font.BOLD,(int) (25*Menu.SCALE));
		g.setFont(font);
		g.setColor(this.c.getColorLettre());
		g.drawString("Chargement", (int) (230*Menu.SCALE), (int) (250*Menu.SCALE));
	}

	public void update(Graphics g) {
		if ((int) this.getClientProperty("color") != this.c.getCouleur()) {
			this.putClientProperty("color", this.c.getCouleur());
			
		}
	}
	
}

