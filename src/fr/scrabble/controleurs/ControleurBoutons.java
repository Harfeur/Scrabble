package fr.scrabble.controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.scrabble.menu.Menu;
import fr.scrabble.multiplayer.Client;

public class ControleurBoutons implements ActionListener {
	
	private Menu menu;
	
	public ControleurBoutons(Menu menu) {
		super();
		this.menu=menu;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand()=="Hors ligne") {
			menu.vueHorsLigne();
		}
		if (e.getActionCommand()=="En ligne") {
			menu.vueClient();
		}
		if (e.getActionCommand()=="Serveur") {
			menu.vueServeur();
		}
		
	}
}
