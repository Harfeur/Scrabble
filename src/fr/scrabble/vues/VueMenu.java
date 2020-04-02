package fr.scrabble.vues;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Button;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.scrabble.Menu;
import fr.scrabble.Scrabble;

public class VueMenu extends Panel{
	Image im;
	public Button b;
	public VueMenu() {
		try {
			im = ImageIO.read(Menu.class.getResource("/resources/scrabble.jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setPreferredSize(new Dimension(400,400));
		b=new Button("Démarrer"); 
		b.setForeground(new Color(0,0,0));
		b.setBackground(Color.LIGHT_GRAY);
		Font f = new Font(Font.SERIF,Font.HANGING_BASELINE,25);
		b.setFont(f);
		this.add(b);
	}
	
	public void paint(Graphics g) {
		g.drawImage(im, 0, 0, 400, 400, 0, 0, 400, 400, getParent());
		g.setColor(new Color(117,82,56));
	}
	}


