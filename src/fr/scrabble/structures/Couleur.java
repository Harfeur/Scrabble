package fr.scrabble.structures;

import java.awt.Color;
import java.util.Observable;

public class Couleur extends Observable {

	int couleur;
	Color[] lettre = {Color.black,Color.white};
	Color[] bouton = {new Color(128, 255, 170), new Color(82,150,119)};
	
	public Couleur() {
		this.couleur=0;
	}
	
	public void changeCouleur(String nom) {
		if(nom.equals("Sombre")) {
			this.couleur=1;
		}
		else if (nom.equals("Clair")) {
			this.couleur=0;
		}
		this.setChanged();
		this.notifyObservers();
	}
	
	public int getCouleur() {
		return this.couleur;
	}
	
	public Color getColorLettre() {
		return lettre[couleur];
	}
	
	public Color getColorBouton() {
		return bouton[couleur];
	}
	
}
