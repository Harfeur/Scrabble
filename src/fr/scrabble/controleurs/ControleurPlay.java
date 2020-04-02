package fr.scrabble.controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.scrabble.Solo;
import fr.scrabble.menu.Menu;
import fr.scrabble.multiplayer.Client;

public class ControleurPlay implements ActionListener {
	
	private Menu menu;
	
	public ControleurPlay(Menu menu) {
		super();
		this.menu=menu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand()=="Solo") {
			menu.fermer();
			new Solo();
		}
		if (e.getActionCommand()=="Multijoueur") {
			menu.fermer();
			new Client();
		}
		
	}
}
