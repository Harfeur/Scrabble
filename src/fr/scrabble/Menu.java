package fr.scrabble;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.imageio.ImageIO;

import fr.scrabble.vues.VueBoutonSolo;
import fr.scrabble.vues.VueMenu;



public class Menu extends Frame{
	 VueMenu menu;
	 VueBoutonSolo b1, b2;
	 Image im;
	 
	public Menu(){
		
		
		try {
			im = ImageIO.read(Menu.class.getResource("/resources/scrabble.jpg"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		menu= new VueMenu();
		this.setLayout(new FlowLayout());
		
		this.add(menu);
		 
		this.setSize(400,400);  
		setVisible(true);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
	}
	
	public static void main(String[] args) {
		new Menu();
	}
}
