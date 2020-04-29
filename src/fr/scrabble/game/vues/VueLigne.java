package fr.scrabble.game.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Couleur;
import fr.scrabble.structures.Case.Multiplicateur;

@SuppressWarnings("serial")
public class VueLigne extends JPanel {

	public static int TAILLE = 25; 
	Menu menu;
	Couleur c;
	Color[] fond = {Color.LIGHT_GRAY};
	
	public VueLigne(Menu menu) {
		super();
		this.menu = menu;
		this.c = menu.couleur;
		this.setOpaque(false);
		this.setPreferredSize(new Dimension((int) (TAILLE*15*Menu.SCALE),(int) (TAILLE*Menu.SCALE)));
		this.setBounds(0,0,(int) (TAILLE*16*Menu.SCALE), (int) (TAILLE*Menu.SCALE));
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 1; i < 16; i++) {
			Image im = null;
			try {
				if(this.c.getCouleur()==0) {
					im = ImageIO.read(Multiplicateur.class.getResource("/resources/images/plateau/"+i+".png"));
				}
				else {
					im = ImageIO.read(Multiplicateur.class.getResource("/resources/images/plateau/"+i+"S.png"));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.drawImage(im,(int) (i*TAILLE*Menu.SCALE),0,(int) (TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE),null);
			}
	}
	
	@Override
	public void update(Graphics g) {
		if ((int) this.getClientProperty("color") != this.c.getCouleur()) {
			this.putClientProperty("color", this.c.getCouleur());
		}
	}
}
