package fr.scrabble.menu.vues;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Couleur;

@SuppressWarnings("serial")
public class VueMenu extends JPanel implements Observer{
	
	Image im1, im2;
	Image[] im ;
	Couleur c;
	
	public VueMenu(Couleur c) {
		this.c = c;
		this.im = new Image[2];
		try {
			this.im1 = ImageIO.read(Menu.class.getResource("/resources/images/scrabble.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		try {
			this.im2 = ImageIO.read(Menu.class.getResource("/resources/images/beauxgosses.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		this.im[0] = this.im1;
		this.im[1] = this.im2;
		
		this.setBounds(0, 0, (int) (600*Menu.SCALE), (int) (600*Menu.SCALE));
		this.setOpaque(true);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(o.getClass() == Couleur.class) {
			this.c = (Couleur) o;
			this.repaint();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paintComponents(g);
		g.drawImage(this.im[this.c.getCouleur()], 0, 0, (int) (600*Menu.SCALE), (int) (600*Menu.SCALE), this.getParent());
	}

}