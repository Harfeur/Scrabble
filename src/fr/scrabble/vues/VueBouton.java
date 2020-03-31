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
		Button p = new Button("Passer");
		b.addActionListener(cb);
		d.addActionListener(cplay);
		p.addActionListener(cb);
		this.add(d);
		this.add(b);
		this.add(p);
	}
	
}
