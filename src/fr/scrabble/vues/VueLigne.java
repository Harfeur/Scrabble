package fr.scrabble.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.*;

import fr.scrabble.Solo;

@SuppressWarnings("serial")
public class VueLigne extends JPanel {

	public static int TAILLE = 25; 
	
	public VueLigne() {
		super();
		this.setPreferredSize(new Dimension((int) (TAILLE*15*Solo.SCALE),(int) (TAILLE*Solo.SCALE)));
	}
	
	@Override
	public void paint(Graphics g) {
		//Fond (case : 0,0)
		g.setColor(new Color(51,108,11));
		g.fillRect(0,0 ,(int) (TAILLE*Solo.SCALE),(int) (TAILLE*Solo.SCALE));
		g.setColor(Color.BLACK);
		g.drawRect(0,0,(int) (TAILLE*Solo.SCALE),(int) (TAILLE*Solo.SCALE));
		for (int i = 1; i < 16; i++) {
			//Fond
			g.setColor(new Color(51,108,11));
			g.fillRect((int) (i*TAILLE*Solo.SCALE),0 ,(int) (TAILLE*Solo.SCALE),(int) (TAILLE*Solo.SCALE));
			g.setColor(Color.BLACK);
			g.drawRect((int) (i*TAILLE*Solo.SCALE),0,(int) (TAILLE*Solo.SCALE),(int) (TAILLE*Solo.SCALE));
			//Chiffre
			Font font_lettre = new Font("Arial",Font.PLAIN,(int)(18*Solo.SCALE)) ;
			FontMetrics metrics_lettre = getFontMetrics(font_lettre);
			g.setFont(font_lettre);
			g.setColor(Color.BLACK);
			g.drawString(i+"",(int) (i*TAILLE*Solo.SCALE+metrics_lettre.getDescent()),metrics_lettre.getAscent());
		}
	}
}
