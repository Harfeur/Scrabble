package fr.scrabble.vues;

import java.awt.Button;
import java.awt.Frame;
import java.awt.Panel;

import fr.scrabble.controleurs.ControleurBouton;

public class VueBouton extends Panel {

	public VueBouton(ControleurBouton cb) {
		super();
		this.add(new Button("Valider"));
		
	}
	
}
