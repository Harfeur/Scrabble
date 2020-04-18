package fr.scrabble.game.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Couleur;

@SuppressWarnings("serial")
public class VueColonne extends JPanel {

	public static int TAILLE = 25; 
	String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O"};
	Color[] fond = {Color.green,new Color(51,108,11)};
	Couleur c;
	Menu menu;
	
	public VueColonne(Menu menu) {
		super();
		this.menu = menu;
		this.c = menu.couleur;
		this.setPreferredSize(new Dimension((int) (TAILLE*Menu.SCALE),(int) (TAILLE*15*Menu.SCALE)));
		this.setBounds(0,(int) (TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE), (int) (TAILLE*15*Menu.SCALE));
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < 15; i++) {
			//Fond
			g.setColor(fond[this.c.getCouleur()]);
			g.fillRect(0,(int) (i*TAILLE*Menu.SCALE) ,(int) (TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE));
			g.setColor(this.c.getColorLettre());
			g.drawRect(0,(int) (i*TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE));
			//Chiffre
			Font font_lettre = new Font("Arial",Font.PLAIN,(int)(18*Menu.SCALE)) ;
			FontMetrics metrics_lettre = getFontMetrics(font_lettre);
			g.setFont(font_lettre);
			g.setColor(this.c.getColorLettre());
			g.drawString(alphabet[i],metrics_lettre.getDescent(),(int) (i*TAILLE*Menu.SCALE+metrics_lettre.getAscent()));
		}
	}
	
	@Override
	public void update(Graphics g) {
		if ((int) this.getClientProperty("color") != this.c.getCouleur()) {
			this.putClientProperty("color", this.c.getCouleur());
		}
	}


}
