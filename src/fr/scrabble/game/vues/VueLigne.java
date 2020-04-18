package fr.scrabble.game.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.*;

import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Couleur;

@SuppressWarnings("serial")
public class VueLigne extends JPanel {

	public static int TAILLE = 25; 
	Menu menu;
	Couleur c;
	Color[] fond = {Color.green,new Color(51,108,11)};
	
	public VueLigne(Menu menu) {
		super();
		this.menu = menu;
		this.c = menu.couleur;
		this.setPreferredSize(new Dimension((int) (TAILLE*15*Menu.SCALE),(int) (TAILLE*Menu.SCALE)));
		this.setBounds(0,0,(int) (TAILLE*16*Menu.SCALE), (int) (TAILLE*Menu.SCALE));
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//Fond (case : 0,0)
		g.setColor(fond[this.c.getCouleur()]);
		g.fillRect(0,0 ,(int) (TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE));
		g.setColor(this.c.getColorLettre());
		g.drawRect(0,0,(int) (TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE));
		for (int i = 1; i < 16; i++) {
			//Fond
			g.setColor(fond[this.c.getCouleur()]);
			g.fillRect((int) (i*TAILLE*Menu.SCALE),0 ,(int) (TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE));
			g.setColor(this.c.getColorLettre());
			g.drawRect((int) (i*TAILLE*Menu.SCALE),0,(int) (TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE));
			//Chiffre
			Font font_lettre = new Font("Arial",Font.PLAIN,(int)(18*Menu.SCALE)) ;
			FontMetrics metrics_lettre = getFontMetrics(font_lettre);
			g.setFont(font_lettre);
			g.setColor(this.c.getColorLettre());
			g.drawString(i+"",(int) (i*TAILLE*Menu.SCALE+metrics_lettre.getDescent()),metrics_lettre.getAscent());
		}
	}
	
	@Override
	public void update(Graphics g) {
		if ((int) this.getClientProperty("color") != this.c.getCouleur()) {
			this.putClientProperty("color", this.c.getCouleur());
		}
	}
}
