package fr.scrabble;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import fr.scrabble.controleurs.ControleurPlateau;
import fr.scrabble.vues.VuePlateau;

public class Scrabble extends Frame {

	public static double SCALE=2.0; 
	
	public Scrabble() {

		Modele m = new Modele();
		
		ControleurPlateau cp = new ControleurPlateau(m);
		
		VuePlateau vuePlateau = new VuePlateau(cp);

		LayoutManager layout = new BorderLayout();
		this.setLayout(layout);

		this.add(vuePlateau, BorderLayout.CENTER);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		this.pack();
		this.setVisible(true);
	}

	
	public static void main(String[] args) {
		new Scrabble();
	}
}
