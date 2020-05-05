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
public class VueColonne extends JPanel {

	public static int TAILLE = 25; 
	String[] alphabet = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O"};
	Color[] fond = {Color.green,new Color(51,108,11)};
	Couleur c;
	Menu menu;
	private ArrayList<Image> images;

	public VueColonne(Menu menu) {
		super();
		this.menu = menu;
		this.c = menu.couleur;

		// Chargement des images
		this.images = new ArrayList<Image>();
		MediaTracker mt = new MediaTracker(this);

		for (int i = 0; i < 15; i++) {
			Character c = (char) ('A' + i);
			Image img = Toolkit.getDefaultToolkit().getImage(Lettre.class.getResource("/resources/images/Plateau/"+c+".png"));
			mt.addImage(img, i);
			this.images.add(img);
		}

		for (int i = 15; i < 30; i++) {
			Character c = (char) ('A' + i - 15);
			Image img = Toolkit.getDefaultToolkit().getImage(Lettre.class.getResource("/resources/images/Plateau/"+c+"S.png"));
			mt.addImage(img, i);
			this.images.add(img);
		}

		try {
			mt.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		this.putClientProperty("color", this.c.getCouleur());

		this.setPreferredSize(new Dimension((int) (TAILLE*this.menu.zoom()),(int) (TAILLE*15*this.menu.zoom())));
		this.setBounds(this.menu.decalageX(),(int) (TAILLE*this.menu.zoom()+this.menu.decalageY()),(int) (TAILLE*this.menu.zoom()), (int) (TAILLE*15*this.menu.zoom()));
	}

	public void paint(Graphics g) {
		super.paint(g);
		
		this.setPreferredSize(new Dimension((int) (TAILLE*this.menu.zoom()),(int) (TAILLE*15*this.menu.zoom())));
		this.setBounds(this.menu.decalageX(),(int) (TAILLE*this.menu.zoom()+this.menu.decalageY()),(int) (TAILLE*this.menu.zoom()), (int) (TAILLE*15*this.menu.zoom()));

		if ((int) this.getClientProperty("color") != this.c.getCouleur())
			this.putClientProperty("color", this.c.getCouleur());

		for (int i = 0; i < 15; i++) {
			g.drawImage(this.images.get(i+15*((int) this.getClientProperty("color"))),0,(int) (i*TAILLE*this.menu.zoom()),(int) (TAILLE*this.menu.zoom()),(int) (TAILLE*this.menu.zoom()),null);
		}
	}

}
