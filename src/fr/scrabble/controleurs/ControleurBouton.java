package fr.scrabble.controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.scrabble.Modele;

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
