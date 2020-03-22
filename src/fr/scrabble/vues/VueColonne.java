package fr.scrabble.vues;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import fr.scrabble.Scrabble;

public class VueColonne extends Canvas {

	public static int TAILLE = 25; 
	String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O"};
	
	public VueColonne() {
		super();
		this.setPreferredSize(new Dimension((int) (TAILLE*Scrabble.SCALE),(int) (TAILLE*15*Scrabble.SCALE)));
	}
	
	public void paint(Graphics g) {
		for (int i = 0; i < 15; i++) {
			//Fond
			g.setColor(new Color(51,108,11));
			g.fillRect(0,(int) (i*TAILLE*Scrabble.SCALE) ,(int) (TAILLE*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE));
			g.setColor(Color.BLACK);
			g.drawRect(0,(int) (i*TAILLE*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE));
			//Chiffre
			Font font_lettre = new Font("Arial",Font.PLAIN,(int)(18*Scrabble.SCALE)) ;
			FontMetrics metrics_lettre = getFontMetrics(font_lettre);
			g.setFont(font_lettre);
			g.setColor(Color.BLACK);
			g.drawString(alphabet[i],metrics_lettre.getDescent(),(int) (i*TAILLE*Scrabble.SCALE+metrics_lettre.getAscent()));
		}
	}
}
