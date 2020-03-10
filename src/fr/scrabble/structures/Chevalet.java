package fr.scrabble.structures;

import java.util.ArrayList;

public class Chevalet extends ArrayList<Lettre>{
	
	public Chevalet() {
		super(7);
	}
	
	public void remplir(Sac sac) {
		while(!sac.estVide() && this.size()<7) {
			this.add(sac.obtenirLettre());
		}
	}
	
	public void supprimerLettre(Lettre lettre) {
		this.remove(lettre);
	}
}
