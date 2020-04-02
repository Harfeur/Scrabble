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
	VueBoutonSolo b;
	public VueMenu() {
		try {
			im = ImageIO.read(Menu.class.getResource("/resources/scrabble.jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setPreferredSize(new Dimension(400,400));
		b = new VueBoutonSolo();
		this.add(b);
	}
	
	public void paint(Graphics g) {
		g.drawImage(im, 0, 0, 400, 400, 0, 0, 400, 400, getParent());
		super.paint(g);
	}
	}


