package fr.scrabble.menu.vues;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Couleur;

@SuppressWarnings("serial")
public class VueMenu extends JPanel {
	
	Image[] images ;
	Couleur c;
	private Menu menu;
	
	public VueMenu(Couleur c, Menu m) {
		this.menu = m;
		this.c = c;
		this.images = new Image[2];
		Image im1 = null, im2 = null;
		try {
			im1 = ImageIO.read(Menu.class.getResource("/resources/images/scrabble.jpg"));
		} catch (IOException e1) {
			new ErrorFrame("Images manquantes");
		}
		
		try {
			im2 = ImageIO.read(Menu.class.getResource("/resources/images/beauxgosses.jpg"));
		} catch (IOException e1) {
			new ErrorFrame("Images manquantes");
		}
		
		this.images[0] = im1;
		this.images[1] = im2;
		
		this.setBounds(0, 0, (int) (600*Menu.SCALE), (int) (600*Menu.SCALE));
		this.setOpaque(true);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.setBounds(0, 0, this.menu.getWidth(), this.menu.getHeight());
		this.putClientProperty("color", this.c.getCouleur());
		int taille = Math.max(this.menu.getWidth(), this.menu.getHeight());
		g.drawImage(this.images[this.c.getCouleur()], 0, 0, taille, taille, this.getParent());
	}
	/*
	@Override
	public void update(Graphics g) {
		this.setBounds(0, 0, (int) (600*this.menu.zoom()), (int) (600*this.menu.zoom()));
		g.drawImage(this.images[this.c.getCouleur()], 0, 0, (int) (600*this.menu.zoom()), (int) (600*this.menu.zoom()), this.getParent());
	}*/

}