package fr.scrabble;
import java.io.File;
import java.util.Observable;

import fr.scrabble.structures.*;

public class Modele extends Observable{
	Sac sac;
	File fichier;
	Plateau plateau, plateauFictif;
	Chevalet[] chevalets;
	int numChevalet;
	
	public Modele() {
	}
	
	/*Met le jeu a zero en fonction de nb de joueur*/
	public void nouvellePartie(int nbJoueur) {
		this.sac = new Sac("FR");
		System.out.print(this.sac);
		
		this.plateau= new Plateau();
		this.plateauFictif= new Plateau();
		
		for (int i=0; i<nbJoueur; i++) {
			this.chevalets[i]=new Chevalet();
			this.chevalets[i].remplir(this.sac);
		}
		this.numChevalet=0;
	}
	
	public boolean validerProposition(){
		return true;
		
	}

	public void selectLettre(int num) {
		// TODO On récupére la lettre num du Chevalet et on la sélectionne
		// puis on notifie
		this.chevalets[this.numChevalet].selectionnerLettre(num);
		this.setChanged();
		this.notifyObservers(this.plateauFictif);
	}

	/* ajoute la lettre choisi sur le chevalet dans le plateau*/
	public void ajoutLettre(int col, int lig) {
		// TODO Si une lettre est sélectionnée, on l'ajoute dans un Plateau temporaire qui servira à la verification
		// puis on notifie
		Lettre lettre = this.chevalets[this.numChevalet].obtenirLettre();
		Case c = this.plateauFictif.getCase(lig, col);
		c.ajouterLettre(lettre);
		this.setChanged();
		this.notifyObservers(this.plateauFictif);
	}
	
	public void verificationMot() {
	}
	
	/*met a jour les changements de joueur*/
	public void changementJoueur() {
		if (this.numChevalet+1 == this.chevalets.length) {
			this.numChevalet=0;
		}
		else {
			this.numChevalet++;
		}
		this.plateauFictif = this.plateau.clone();
	}
}
