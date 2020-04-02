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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		/*bouton = new VueBoutonSolo();
		Rectangle r = new Rectangle();
		r.height=50;
		r.width=50;
		r.x=100;
		r.y=100;
        bouton.setBounds(r);
        this.add(bouton);
        this.setSize(50, 50);*/
	}
	
	public void paint(Graphics g) {
		super.paintComponents(g);
		g.drawImage(im, 0, 0, 400, 400, this.getParent());
		
	       
       
        
	}
	}


