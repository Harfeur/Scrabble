package fr.scrabble.vues;

import java.awt.Button;
import java.awt.Frame;

import fr.scrabble.controleurs.ControleurBouton;

public class VueBouton extends Frame {

	public VueBouton(ControleurBouton cb) {
		super();
		this.add(new Button("Valider"));
		
	}
	
}
