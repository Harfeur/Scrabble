package fr.scrabble;

import javax.imageio.ImageIO;
import javax.swing.*;

import fr.scrabble.vues.VueBoutonSolo;
import fr.scrabble.vues.VueMenu;

import java.awt.*;


public class Menu{
	private Image img = null;
	VueBoutonSolo bouton;
	   
    public Menu () {
    	JFrame frame = new JFrame("Menu Scrabble" );
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	bouton = new VueBoutonSolo();
        frame.getContentPane().add(new VueMenu());
        frame.add(bouton);
        frame.setSize(400, 400);
        frame.setVisible(true);
    	
    }
	 
	public static void main(String[] args) {
		new Menu();
	}
}
