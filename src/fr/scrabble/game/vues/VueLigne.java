package fr.scrabble.game.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.*;

import fr.scrabble.menu.Menu;

@SuppressWarnings("serial")
public class VueLigne extends JPanel {

	public static int TAILLE = 25; 
	
	public VueLigne() {
		super();
		this.setPreferredSize(new Dimension((int) (TAILLE*15*Menu.SCALE),(int) (TAILLE*Menu.SCALE)));
		this.setBounds(0,0,(int) (TAILLE*16*Menu.SCALE), (int) (TAILLE*Menu.SCALE));
	}
	
	@Override
	public void paint(Graphics g) {
		//Fond (case : 0,0)
		g.setColor(new Color(51,108,11));
		g.fillRect(0,0 ,(int) (TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE));
		g.setColor(Color.BLACK);
		g.drawRect(0,0,(int) (TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE));
		for (int i = 1; i < 16; i++) {
			//Fond
			g.setColor(new Color(51,108,11));
			g.fillRect((int) (i*TAILLE*Menu.SCALE),0 ,(int) (TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE));
			g.setColor(Color.BLACK);
			g.drawRect((int) (i*TAILLE*Menu.SCALE),0,(int) (TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE));
			//Chiffre
			Font font_lettre = new Font("Arial",Font.PLAIN,(int)(18*Menu.SCALE)) ;
			FontMetrics metrics_lettre = getFontMetrics(font_lettre);
			g.setFont(font_lettre);
			g.setColor(Color.BLACK);
			g.drawString(i+"",(int) (i*TAILLE*Menu.SCALE+metrics_lettre.getDescent()),metrics_lettre.getAscent());
		}
	}
}
