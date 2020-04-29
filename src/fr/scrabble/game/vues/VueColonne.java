package fr.scrabble.game.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;

import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Couleur;
import fr.scrabble.structures.Case.Multiplicateur;

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
			Image im = null;
			try {
				if(this.c.getCouleur()==0) {
					im = ImageIO.read(Multiplicateur.class.getResource("/resources/images/plateau/"+alphabet[i]+".png"));
				}
				else {
					im = ImageIO.read(Multiplicateur.class.getResource("/resources/images/plateau/"+alphabet[i]+"S.png"));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g.drawImage(im,0,(int) (i*TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE),null);
			}
	}
	
	@Override
	public void update(Graphics g) {
		if ((int) this.getClientProperty("color") != this.c.getCouleur()) {
			this.putClientProperty("color", this.c.getCouleur());
		}
	}


}
