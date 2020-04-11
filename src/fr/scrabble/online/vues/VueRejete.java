package fr.scrabble.online.vues;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import fr.scrabble.menu.Menu;
import java.awt.Color;
import java.awt.Container;

@SuppressWarnings("serial")
public class VueRejete extends JPanel {

	Image im;
	
	public VueRejete (ActionListener accueil) {
		super();
		
		//image
		try {
			this.im = ImageIO.read(Menu.class.getResource("/resources/triste.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
		
		//Bouton
		JButton b = new BoutonAccueil("Accueil");
		b.setFont(new Font("Arial",Font.BOLD,(int) (15*Menu.SCALE)));		
		b.addActionListener(accueil);
		
		this.setBounds(0,0,(int) (600*Menu.SCALE),(int) (600*Menu.SCALE));
		this.setOpaque(false);
		
		this.add(b);
	}
	
	public void paint(Graphics g) {
		super.paintComponents(g);
		//fond
		g.setColor(Color.white);
		g.fillRect((int) (90*Menu.SCALE), (int) (50*Menu.SCALE), (int) (420*Menu.SCALE), (int) (480*Menu.SCALE));
		g.drawImage(im, (int) (100*Menu.SCALE), (int) (100*Menu.SCALE), (int) (400*Menu.SCALE), (int) (400*Menu.SCALE), this.getParent());
		
		//texte
		Font font = new Font("Arial",Font.BOLD,(int) (25*Menu.SCALE));
		g.setFont(font);
		g.setColor(new Color (179,29,29));
		g.drawString("Vous n'êtes pas dans la partie", (int) (120*Menu.SCALE), (int) (80*Menu.SCALE));
	}
}
class BoutonAccueil extends JButton {
	public BoutonAccueil(String titre) {
		super(titre);
	}
}
