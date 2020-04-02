package fr.scrabble;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import fr.scrabble.controleurs.*;
import fr.scrabble.vues.*;


@SuppressWarnings("serial")
public class Solo extends JFrame implements Observer{

	public static double SCALE=1.5;
	public String langue;

	public Solo() {
		
		this.langue = "FR";

		Modele m = new Modele();

		ControleurPlateau cp = new ControleurPlateau(m);
		ControleurChevalet cc = new ControleurChevalet(m);
		ControleurBouton cb = new ControleurBouton(m);
		
		VuePlateau vuePlateau = new VuePlateau(cp);
		VueChevalet vueChevalet = new VueChevalet(cc);
		VueBouton vueBouton = new VueBouton(cb);
		VueLigne vueLigne = new VueLigne();
		VueColonne vueColonne = new VueColonne();
		
		m.addObserver(vuePlateau);
		m.addObserver(vueChevalet);
		m.addObserver(this);
		
		Container contentPane = this.getContentPane();
		
		contentPane.setLayout(new BorderLayout());
		
		contentPane.add(vuePlateau, BorderLayout.CENTER);
		contentPane.add(vueChevalet, BorderLayout.SOUTH);
		contentPane.add(vueBouton, BorderLayout.EAST);
		contentPane.add(vueLigne, BorderLayout.NORTH);
		contentPane.add(vueColonne, BorderLayout.WEST);
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		m.nouvellePartie(4, this.langue);
	
		this.pack();
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    
	    this.setLocation(x, y);
		this.setVisible(true);
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