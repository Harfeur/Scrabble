package fr.scrabble.menu.vues;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import fr.scrabble.menu.Menu;

@SuppressWarnings("serial")
public class VueMenu extends JPanel{
	Image im;
	
	public VueMenu() {
		try {
			im = ImageIO.read(Menu.class.getResource("/resources/scrabble.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		this.setBounds(0, 0, (int) (600*Menu.SCALE), (int) (600*Menu.SCALE));
		this.setOpaque(true);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paintComponents(g);
		g.drawImage(im, 0, 0, (int) (600*Menu.SCALE), (int) (600*Menu.SCALE), this.getParent());
	}

}