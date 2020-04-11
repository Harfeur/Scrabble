package fr.scrabble.online.vues;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import fr.scrabble.menu.Menu;
import java.awt.Color;
import java.awt.Container;

@SuppressWarnings("serial")
public class VueRejete extends JPanel {

	Image im;
	
	public VueRejete () {
		super();
		this.setBounds(0,0,(int) (600*Menu.SCALE),(int) (600*Menu.SCALE));
			
		//fond
		try {
			this.im = ImageIO.read(Menu.class.getResource("/resources/triste.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
	}
	
	public void paint(Graphics g) {
		super.paintComponents(g);
		//fond
		g.setColor(Color.white);
		g.fillRect(0, 0, (int) (600*Menu.SCALE), (int) (600*Menu.SCALE));
		g.drawImage(im, (int) (100*Menu.SCALE), (int) (100*Menu.SCALE), (int) (400*Menu.SCALE), (int) (400*Menu.SCALE), this.getParent());
		
		//texte
		Font font = new Font("Arial",Font.BOLD,(int) (25*Menu.SCALE));
		g.setFont(font);
		g.setColor(new Color (179,29,29));
		g.drawString("Vous n'êtes pas dans la partie", (int) (110*Menu.SCALE), (int) (80*Menu.SCALE));
	}
}
