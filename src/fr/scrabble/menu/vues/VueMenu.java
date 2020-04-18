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
	
	public VueMenu(Couleur c) {
		this.c = c;
		this.images = new Image[2];
		Image im1 = null, im2 = null;
		try {
			im1 = ImageIO.read(Menu.class.getResource("/resources/images/scrabble.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			im2 = ImageIO.read(Menu.class.getResource("/resources/images/beauxgosses.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		this.images[0] = im1;
		this.images[1] = im2;
		
		this.setBounds(0, 0, (int) (600*Menu.SCALE), (int) (600*Menu.SCALE));
		this.setOpaque(true);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.putClientProperty("color", this.c.getCouleur());
		g.drawImage(this.images[this.c.getCouleur()], 0, 0, (int) (600*Menu.SCALE), (int) (600*Menu.SCALE), this.getParent());
	}
	
	@Override
	public void update(Graphics g) {
		System.out.println("update");
		if ((int) this.getClientProperty("color") != this.c.getCouleur()) {
			this.putClientProperty("color", this.c.getCouleur());
			System.out.println("repaint");
			g.drawImage(this.images[this.c.getCouleur()], 0, 0, (int) (600*Menu.SCALE), (int) (600*Menu.SCALE), this.getParent());
		}
	}

}