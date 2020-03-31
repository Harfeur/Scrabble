package fr.scrabble.controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.scrabble.Modele;

public class ControleurPlay implements ActionListener {
	
	private Modele m;
	private String langue;

	public ControleurPlay(Modele m, String langue) {
		super();
		this.m = m;
		this.langue = langue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		m.nouvellePartie(4,langue);
	}
}
