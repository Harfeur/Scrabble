package fr.scrabble;
import java.io.File;

import fr.scrabble.structures.*;

public class Modele {
	Sac sac;
	File fichier;
	Plateau plateau, plateauFictif;
	Chevalet[] chevalets;
	
	public Modele() {
	}
	
	public void nouvellePartie(int nbJoueur) {
		this.sac = new Sac("FR");
		System.out.print(this.sac);
		
		this.plateau= new Plateau();
		this.plateauFictif= new Plateau();
		
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

	/* ajoute la lettre choisi sur le chevalet dans le plateau*/
	public void ajoutLettre(int col, int lig, int numChevalet) {
		// TODO Si une lettre est sélectionnée, on l'ajoute dans un Plateau temporaire qui servira à la verification
		// puis on notifie
		Lettre lettre = this.chevalets[numChevalet].obtenirLettre();
		Case c = this.plateauFictif.getCase(lig, col);
		c.ajouterLettre(lettre);
	}
}
