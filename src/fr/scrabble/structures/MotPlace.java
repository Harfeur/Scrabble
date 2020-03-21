package fr.scrabble.structures;

import java.util.ArrayList;

/**
 * MotPlace représente le placement d'un mot sur une grille de Scrabble.
 * 
 */
public class MotPlace {

	enum Sens {DROITE, BAS};
	
	ArrayList<Lettre> mot;
	int lig, col;
	Sens sens;

	public MotPlace(Lettre l, int lig, int col) {
		super();
		this.mot = new ArrayList<Lettre>();
		this.mot.add(l);
		this.lig = lig;
		this.col = col;
	}
	
	/**
	 * Ajoute une lettre dans le mot, et renvoie vrai ou faux si la case
	 * se trouve à côté. N'ajoute pas la lettre si case pas à côté
	 * @param l La lettre à ajouter
	 * @param lig La ligne de la lettre
	 * @param col La colonne de la lettre
	 * @return boolean vrai si placement possible et fait
	 */
	public boolean ajoutLettre(Lettre l, int lig, int col) {
		this.mot.add(l);
		return true;
	}
	
	public int nombreDeLettres() {
		return this.mot.size();
	}
	
	public boolean valideMot(Dictionnaire dico) {
		return dico.contient(this.toString());
	}
	
	@Override
	public String toString() {
		String str = "";
		for (Lettre lettre : mot) {
			str += lettre.lettre;
		}
		return str;
	}
}
