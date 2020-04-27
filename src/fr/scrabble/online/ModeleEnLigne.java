package fr.scrabble.online;

import fr.scrabble.game.Modele;
import fr.scrabble.menu.Menu;

public class ModeleEnLigne extends Modele {
	
	private Client client;
	
	public ModeleEnLigne(Client client, Menu menu) {
		super(menu);
		this.client =  client;
	}
	
	@Override
	public void verificationMot() {
		this.client.message("verifMot");
	}
	
	@Override
	public void changementJoueur() {
		this.client.message("changeJoueur");
	}
	
	@Override
	public void selectLettre(int num) {
		this.client.message("selectLettre");
		this.client.message(String.valueOf(num));
	}
	
	@Override
	public void ajoutLettre(int col, int lig) {
		this.client.message("ajoutLettre");
		this.client.message(String.valueOf(col));
		this.client.message(String.valueOf(lig));
	}
}
