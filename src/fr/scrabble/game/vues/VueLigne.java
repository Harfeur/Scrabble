package fr.scrabble.game.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.*;

import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Couleur;
import fr.scrabble.structures.Lettre;

@SuppressWarnings("serial")
public class VueLigne extends JPanel {

	public static int TAILLE = 25; 
	Menu menu;
	Couleur c;
	Color[] fond = {Color.LIGHT_GRAY};
	private ArrayList<Image> images;

	public VueLigne(Menu menu) {
		super();
		this.menu = menu;
		this.c = menu.couleur;

		// Chargement des images
		this.images = new ArrayList<Image>();
		MediaTracker mt = new MediaTracker(this);

		for (int i = 0; i < 15; i++) {
			Image img = Toolkit.getDefaultToolkit().getImage(Lettre.class.getResource("/resources/images/Plateau/"+(i+1)+".png"));
			mt.addImage(img, i);
			this.images.add(img);
		}

		for (int i = 15; i < 30; i++) {
			Image img = Toolkit.getDefaultToolkit().getImage(Lettre.class.getResource("/resources/images/Plateau/"+(i-14)+"S.png"));
			mt.addImage(img, i);
			this.images.add(img);
		}
		
		try {
			mt.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.putClientProperty("color", this.c.getCouleur());

		this.setOpaque(false);
		this.setPreferredSize(new Dimension((int) (TAILLE*15*Menu.SCALE),(int) (TAILLE*Menu.SCALE)));
		this.setBounds(0,0,(int) (TAILLE*16*Menu.SCALE), (int) (TAILLE*Menu.SCALE));
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		if ((int) this.getClientProperty("color") != this.c.getCouleur())
			this.putClientProperty("color", this.c.getCouleur());

		for (int i = 1; i < 16; i++) {
			g.drawImage(this.images.get(i-1+15*((int) this.getClientProperty("color"))),(int) (i*TAILLE*Menu.SCALE),0,(int) (TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE),null);
		}
	}
}
