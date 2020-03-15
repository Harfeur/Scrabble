package fr.scrabble;
import java.io.File;

import fr.scrabble.structures.*;

public class Modele {
	Sac sac;
	File fichier;
	Plateau plateau;
	Chevalet[] chevalets;
	
	public Modele() {
	}
	
	public void nouvellePartie(int nbJoueur) {
		this.sac = new Sac("FR");
		System.out.print(this.sac);
		
		this.plateau= new Plateau();
		
		for (int i=0; i<nbJoueur; i++) {
			this.chevalets[i]=new Chevalet();
			this.chevalets[i].remplir(this.sac);
		}
	}
	
	public boolean validerProposition(){
		return true;
		
	}

	public void selectLettre(int num) {
		// TODO On récupére la lettre num du Chevalet et on la sélectionne
		// puis on notifie
		
	}
}
