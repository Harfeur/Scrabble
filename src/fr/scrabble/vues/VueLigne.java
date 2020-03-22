package fr.scrabble.vues;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import fr.scrabble.Scrabble;

public class VueLigne extends Canvas {

	public static int TAILLE = 25; 
	
	public VueLigne() {
		super();
		this.setPreferredSize(new Dimension((int) (TAILLE*15*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE)));
	}
	
	public void paint(Graphics g) {
		//Fond (case : 0,0)
		g.setColor(new Color(51,108,11));
		g.fillRect(0,0 ,(int) (TAILLE*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE));
		g.setColor(Color.BLACK);
		g.drawRect(0,0,(int) (TAILLE*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE));
		for (int i = 1; i < 16; i++) {
			//Fond
			g.setColor(new Color(51,108,11));
			g.fillRect((int) (i*TAILLE*Scrabble.SCALE),0 ,(int) (TAILLE*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE));
			g.setColor(Color.BLACK);
			g.drawRect((int) (i*TAILLE*Scrabble.SCALE),0,(int) (TAILLE*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE));
			//Chiffre
			Font font_lettre = new Font("Arial",Font.PLAIN,(int)(18*Scrabble.SCALE)) ;
			FontMetrics metrics_lettre = getFontMetrics(font_lettre);
			g.setFont(font_lettre);
			g.setColor(Color.BLACK);
			g.drawString(i+"",(int) (i*TAILLE*Scrabble.SCALE+metrics_lettre.getDescent()),metrics_lettre.getAscent());
		}
	}
}
