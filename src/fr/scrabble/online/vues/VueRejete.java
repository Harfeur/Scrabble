package fr.scrabble.online.vues;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import fr.scrabble.menu.Menu;
import fr.scrabble.menu.vues.ErrorFrame;
import fr.scrabble.structures.Couleur;

import java.awt.Color;

@SuppressWarnings("serial")
public class VueRejete extends JPanel implements Observer{

	Image im1, im2;
	Image[] im ;
	Color[] fond = {Color.white, Color.black};
	Color[] lettre = {new Color (179,29,29), new Color (255,0,0)};
	Couleur c;
	JTextArea rejete;
	Menu menu;
	
	public VueRejete (Couleur c,Menu menu) {
		super();
		this.c = c;
		this.im = new Image[2];
		rejete = new JTextArea();
		this.menu = menu;
		
		//image clair
		try {
			this.im1 = ImageIO.read(Menu.class.getResource("/resources/images/triste.jpg"));
		} catch (IOException e) {
			this.menu.dispose();
			new ErrorFrame("Images manquantes");
			e.printStackTrace();
		}	
		//image sombre
		try {
			this.im2 = ImageIO.read(Menu.class.getResource("/resources/images/beauxgosses.jpg"));
		} catch (IOException e) {
			this.menu.dispose();
			new ErrorFrame("Images manquantes");
			e.printStackTrace();
		}
		
		this.im[0] = this.im1;
		this.im[1] = this.im2;
		
		this.setBounds(this.menu.decalageX(),this.menu.decalageY(),(int) (600*this.menu.zoom()),(int) (600*this.menu.zoom()));
		this.setOpaque(false);
		
	}
	
	public void paint(Graphics g) {
		super.paintComponents(g);
		
		this.setBounds(this.menu.decalageX(),this.menu.decalageY(),(int) (600*this.menu.zoom()),(int) (600*this.menu.zoom()));

		ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
		this.rejete.setText(strings.getString("rejete"));
		
		//fond
		g.setColor(fond[this.c.getCouleur()]);
		g.fillRect((int) (90*this.menu.zoom()), (int) (50*this.menu.zoom()), (int) (420*this.menu.zoom()), (int) (480*this.menu.zoom()));
		g.drawImage(this.im[this.c.getCouleur()], (int) (100*this.menu.zoom()), (int) (100*this.menu.zoom()), (int) (400*this.menu.zoom()), (int) (400*this.menu.zoom()), this.getParent());
		
		//texte
		Font font = new Font("Arial",Font.BOLD,(int) (25*this.menu.zoom()));
		g.setFont(font);
		g.setColor(lettre[this.c.getCouleur()]);
		g.drawString(rejete.getText(), (int) (120*this.menu.zoom()), (int) (80*this.menu.zoom()));
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Couleur) {
			this.c = (Couleur) o;
			this.repaint();
		}
	}
}
