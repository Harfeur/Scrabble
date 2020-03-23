package fr.scrabble.vues;

import java.awt.Button;
import java.awt.Panel;

import fr.scrabble.controleurs.ControleurBouton;

@SuppressWarnings("serial")
public class VueBouton extends Panel {

	public VueBouton(ControleurBouton cb) {
		super();
		Button b = new Button("Valider");
		Button d = new Button("Demarrer");
		b.addActionListener(cb);
		this.add(d);
		this.add(b);
	}
	
}
