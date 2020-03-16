package fr.scrabble;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;

import fr.scrabble.structures.*;

public class Modele extends Observable{
	Sac sac;
	File fichier;
	Plateau plateau, plateauFictif;
	Chevalet[] chevalets;
	int numChevalet;
	ArrayList<MotPlace> motValide;
	MotPlace motEnCours;
	
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

	/* mis a jour de la lettre selectionner*/
	public void selectLettre(int num) {
		if (this.chevalets[this.numChevalet].selectionnerLettre(num)) {
			this.setChanged();
			this.notifyObservers(this.chevalets);
		}
	}

	/* ajoute la lettre choisi sur le chevalet dans le plateau*/
	public void ajoutLettre(int col, int lig) {
		Lettre lettre = this.chevalets[this.numChevalet].obtenirLettre();
		Case c = this.plateauFictif.getCase(lig, col);
		if (c.lettre == null) {
			if (this.motEnCours == null) {
				this.motEnCours= new MotPlace(lettre, c);
				c.ajouterLettre(lettre);
			}
			else if (this.motEnCours.nombreDeLettres() == 1) {
				if ()
			}
		}
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
		this.motEnCours=null;
	}
}
