package fr.scrabble.online;

import fr.scrabble.game.Modele;

public class ModeleEnLigne extends Modele {
	
	private Client client;

	public ModeleEnLigne(Client client) {
		super();
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
