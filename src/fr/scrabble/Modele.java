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
	ArrayList<Placement> placementEnCours;
	int ligA, colA;
	
	public Modele() {
		
	}
	
	/*Met le jeu a zero en fonction de nb de joueur*/
	public void nouvellePartie(int nbJoueur) {
		this.sac = new Sac("FR");
		
		this.plateau= new Plateau();
		this.plateauFictif= new Plateau();
		this.placementEnCours = new ArrayList<Placement>();
		
		this.chevalets = new Chevalet[nbJoueur];
		
		for (int i=0; i<nbJoueur; i++) {
			this.chevalets[i]=new Chevalet();
			this.chevalets[i].remplir(this.sac);
		}
		this.numChevalet=0;

		// Après avoir cree les elements, on notifie les deux vues
		
		this.setChanged();
		this.notifyObservers(this.chevalets[numChevalet]);
		
		this.setChanged();
		this.notifyObservers(this.plateau);
		
		System.out.println(this.chevalets[numChevalet]);
	}

	/* mis a jour de la lettre selectionner*/
	public void selectLettre(int num) {
		if (this.chevalets[this.numChevalet].selectionnerLettre(num)) {
			this.setChanged();
			this.notifyObservers(this.chevalets[this.numChevalet]);
		}
	}

	/* ajoute la lettre choisi sur le chevalet dans le plateau*/
	public void ajoutLettre(int col, int lig) {
		Lettre lettre = this.chevalets[this.numChevalet].obtenirLettre();
		Case c = this.plateauFictif.getCase(lig, col);
		if (lettre == null) {
			// Si aucune lettre n'est selectionee du chevalet, alors on supprime la lettre du plateau
			// que si c'est une lettre qui est en train d'etre placee
			Placement caseCliquee = new Placement(null, c, lig, col);
			if (this.placementEnCours.contains(caseCliquee)) {
				c.lettre = null;
				this.placementEnCours.remove(caseCliquee);
			}
		} else {
			// Sinon, on ajoute la lettre sur le plateau, et dans notre liste des placments
			// du tour en cours
			this.placementEnCours.add(new Placement(lettre, c, lig, col));
			c.ajouterLettre(lettre);
		}
		this.setChanged();
		this.notifyObservers(this.plateauFictif);
		
		this.setChanged();
		this.notifyObservers(this.chevalets[this.numChevalet]);
	}
	
	public void verificationMot() {
		// On vérifie que toutes les lettres de this.placementEnCours sont soient verticales soient horizontales
		// dans le cas où on a une size() >= 2
		
		// Puis, on crée un this.motEnCours selon l'ordre des lettres et les lettres déjà présentes (si on allonge un mot par exemple)
		
		
		/*
		if (this.motEnCours.valideMot()) {
			this.plateau = this.plateauFictif.clone();
			this.chevalets[this.numChevalet].remplir(sac);
			this.changementJoueur();
		}
		else {
			this.plateauFictif = this.plateau.clone();
		} */
	}
	
	/*met a jour les changements de joueur*/
	public void changementJoueur() {
		if (this.numChevalet+1 == this.chevalets.length) {
			this.numChevalet=0;
		}
		else {
			this.numChevalet++;
		}
		// On initialise à zéro le placement
		this.placementEnCours = new ArrayList<Placement>();
	}
}
