package fr.scrabble.game.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.*;

import fr.scrabble.menu.Menu;

@SuppressWarnings("serial")
public class VueColonne extends JPanel {

	public static int TAILLE = 25; 
	String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O"};
	
	public VueColonne() {
		super();
		this.setPreferredSize(new Dimension((int) (TAILLE*Menu.SCALE),(int) (TAILLE*15*Menu.SCALE)));
		this.setBounds(0,(int) (TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE), (int) (TAILLE*15*Menu.SCALE));
	}
	
	public void paint(Graphics g) {
		for (int i = 0; i < 15; i++) {
			//Fond
			g.setColor(new Color(51,108,11));
			g.fillRect(0,(int) (i*TAILLE*Menu.SCALE) ,(int) (TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE));
			g.setColor(Color.BLACK);
			g.drawRect(0,(int) (i*TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE));
			//Chiffre
			Font font_lettre = new Font("Arial",Font.PLAIN,(int)(18*Menu.SCALE)) ;
			FontMetrics metrics_lettre = getFontMetrics(font_lettre);
			g.setFont(font_lettre);
			g.setColor(Color.BLACK);
			g.drawString(alphabet[i],metrics_lettre.getDescent(),(int) (i*TAILLE*Menu.SCALE+metrics_lettre.getAscent()));
		}
	}
}
