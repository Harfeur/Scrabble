package fr.scrabble;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import fr.scrabble.controleurs.ControleurChevalet;
import fr.scrabble.controleurs.ControleurPlateau;
import fr.scrabble.vues.VueChevalet;
import fr.scrabble.vues.VuePlateau;

public class Scrabble extends Frame {

	public static double SCALE=1.0; 
	
	@SuppressWarnings("deprecation")
	public Scrabble() {

		Modele m = new Modele();

		ControleurPlateau cp = new ControleurPlateau(m);
		ControleurChevalet cc = new ControleurChevalet(m); // On ajoute le controleur
		
		VuePlateau vuePlateau = new VuePlateau(cp);
		VueChevalet vueChevalet = new VueChevalet(cc);
		
		m.addObserver(vuePlateau);
		m.addObserver(vueChevalet);
		
		LayoutManager layout = new BorderLayout();
		this.setLayout(layout);

		this.add(vuePlateau, BorderLayout.CENTER);
		this.add(vueChevalet, BorderLayout.PAGE_END);
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		m.nouvellePartie(4); // On cree une partie
		
		this.pack();
		this.setVisible(true);
	}

	
	public static void main(String[] args) {
		new Scrabble();
	}
}
