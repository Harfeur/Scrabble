package fr.scrabble.online.vues;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Couleur;

import java.awt.Color;
import java.awt.Container;

@SuppressWarnings("serial")
public class VueRejete extends JPanel implements Observer{

	Image im1, im2;
	Image[] im ;
	Color[] fond = {Color.white, Color.black};
	Color[] lettre = {new Color (179,29,29), new Color (255,0,0)};
	Couleur c;
	
	public VueRejete (Couleur c) {
		super();
		this.c = c;
		this.im = new Image[2];
		
		//image clair
		try {
			this.im1 = ImageIO.read(Menu.class.getResource("/resources/images/triste.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
		//image sombre
		try {
			this.im2 = ImageIO.read(Menu.class.getResource("/resources/images/beauxgosses.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		this.im[0] = this.im1;
		this.im[1] = this.im2;
		
		this.setBounds(0,0,(int) (600*Menu.SCALE),(int) (600*Menu.SCALE));
		this.setOpaque(false);
		
	}
	
	public void paint(Graphics g) {
		super.paintComponents(g);
		//fond
		g.setColor(fond[this.c.getCouleur()]);
		g.fillRect((int) (90*Menu.SCALE), (int) (50*Menu.SCALE), (int) (420*Menu.SCALE), (int) (480*Menu.SCALE));
		g.drawImage(this.im[this.c.getCouleur()], (int) (100*Menu.SCALE), (int) (100*Menu.SCALE), (int) (400*Menu.SCALE), (int) (400*Menu.SCALE), this.getParent());
		
		//texte
		Font font = new Font("Arial",Font.BOLD,(int) (25*Menu.SCALE));
		g.setFont(font);
		g.setColor(lettre[this.c.getCouleur()]);
		g.drawString("Vous n'êtes pas dans la partie", (int) (120*Menu.SCALE), (int) (80*Menu.SCALE));
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(o.getClass() == Couleur.class) {
			this.c = (Couleur) o;
			this.repaint();
		}
	}
}
