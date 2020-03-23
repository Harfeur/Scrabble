package fr.scrabble.vues;

import java.awt.Button;
import java.awt.Panel;

import fr.scrabble.controleurs.ControleurBouton;
import fr.scrabble.controleurs.ControleurPlay;

@SuppressWarnings("serial")
public class VueBouton extends Panel {

	public VueBouton(ControleurBouton cb, ControleurPlay cplay) {
		super();
		Button b = new Button("Valider");
		Button d = new Button("Demarrer");
		b.addActionListener(cb);
		d.addActionListener(cplay);
		this.add(d);
		this.add(b);
	}
	
}
