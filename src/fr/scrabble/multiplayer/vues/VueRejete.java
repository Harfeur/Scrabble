package fr.scrabble.multiplayer.vues;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.scrabble.menu.Menu;
import java.awt.Color;

@SuppressWarnings("serial")
public class VueRejete extends JPanel {

	Image im;
	
	public VueRejete () {
		super();
		
		//fond
		try {
			im = ImageIO.read(Menu.class.getResource("/resources/triste.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		this.setBounds(0, 0, (int) (600*Menu.SCALE), (int) (600*Menu.SCALE));
		this.setOpaque(true);		
		this.setBackground(Color.WHITE);
		
		//Texte
		Font font = new Font("Arial",Font.BOLD,(int) (25*Menu.SCALE));
		
		JLabel txt = new JLabel("Vous n'êtes pas dans la partie");
		txt.setFont(font);
		txt.setForeground(new Color (179,29,29));
		
		this.add(txt);
	
	}
	
	public void paint(Graphics g) {
		super.paintComponents(g);
		g.drawImage(im, 0, (int) (35*Menu.SCALE), (int) (600*Menu.SCALE), (int) (600*Menu.SCALE), this.getParent());
	}
}
