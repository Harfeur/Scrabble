package fr.scrabble.controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.scrabble.Modele;

public class ControleurPlay implements ActionListener {
	
	private Modele m;

	public ControleurPlay(Modele m) {
		super();
		this.m = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		m.nouvellePartie(4);
	}

}
