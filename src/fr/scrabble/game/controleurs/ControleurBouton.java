package fr.scrabble.game.controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.scrabble.game.Modele;

public class ControleurBouton implements ActionListener {
	
	Modele m;

	public ControleurBouton(Modele m) {
		super();
		this.m = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand()=="Valider") {
			m.verificationMot();
		}
		if (e.getActionCommand()=="Passer") {
			m.changementJoueur();
		}
		
	}

}
