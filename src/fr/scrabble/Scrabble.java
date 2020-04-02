package fr.scrabble;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import fr.scrabble.controleurs.*;
import fr.scrabble.vues.*;


@SuppressWarnings("serial")
public class Scrabble extends Frame implements Observer{

	public static double SCALE=1.0;
	public String langue;

	public Scrabble() {
		
		this.langue = "FR";

		Modele m = new Modele();

		ControleurPlateau cp = new ControleurPlateau(m);
		ControleurChevalet cc = new ControleurChevalet(m);
		ControleurBouton cb = new ControleurBouton(m);
		ControleurPlay cplay = new ControleurPlay(m, langue);
		ControleurJoker cj = new ControleurJoker(m);
		
		VuePlateau vuePlateau = new VuePlateau(cp);
		VueChevalet vueChevalet = new VueChevalet(cc);
		VueBouton vueBouton = new VueBouton(cb, cplay);
		VueLigne vueLigne = new VueLigne();
		VueColonne vueColonne = new VueColonne();
		//VueJoker vueJoker = new VueJoker(langue, cj);
		
		m.addObserver(vuePlateau);
		m.addObserver(vueChevalet);
		m.addObserver(this);
		
		LayoutManager layout = new BorderLayout();
		this.setLayout(layout);
		
		this.add(vuePlateau, BorderLayout.CENTER);
		this.add(vueChevalet, BorderLayout.SOUTH);
		this.add(vueBouton, BorderLayout.EAST);
		this.add(vueLigne, BorderLayout.NORTH);
		this.add(vueColonne, BorderLayout.WEST);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	
		this.pack();
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    
	    this.setLocation(x, y);
		this.setVisible(true);
	}

	
	public static void main(String[] args) {
		new Scrabble();
	}


	@Override
	public void update(Observable o, Object arg) {
		if (arg.getClass() == String.class) {
			String str = (String) arg;
			if (str.equals("cacher")) {
				this.setVisible(false);
			}
			if (str.equals("afficher")) {
				this.setVisible(true);
			}
		}
	}
}
