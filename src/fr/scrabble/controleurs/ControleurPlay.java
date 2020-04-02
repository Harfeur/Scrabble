package fr.scrabble.controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.scrabble.Menu;
import fr.scrabble.Modele;
import fr.scrabble.Scrabble;

public class ControleurPlay implements ActionListener {
	
	private Modele m;
	private String langue;
	private Menu menu;
	
	public ControleurPlay(Modele m, String langue, Menu menu) {
		super();
		this.m = m;
		this.langue = langue;
		this.menu=menu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand()=="Solo") {
			menu.fermer();
			new Scrabble();
		}
		else {
			m.nouvellePartie(4,langue);
		}
		
	}
}
