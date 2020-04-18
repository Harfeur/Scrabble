package fr.scrabble.structures;

import java.io.Serializable;
import java.util.ArrayList;

public class Chevalet extends ArrayList<Lettre> implements Serializable{
	
	private static final long serialVersionUID = 3930204422098162416L;
	public int lettreSelectionee;
	
	public Chevalet() {
		super(7);
		this.lettreSelectionee = -1;
	}
	
	public void remplir(Sac sac) {
		while(!sac.estVide() && this.size()<7) {
			this.add(sac.obtenirLettre());
		}
	}
	
	public void remettreLettre(Lettre l) {
		this.add(l);
	}
	
	/**
	 * Cette methode modifie l'attribut lettreSelectionee
	 * @param num Lettre qui doit etre selectionee
	 * @return boolean true si il existe une lettre à l'emplacement num, false sinon
	 */
	public boolean selectionnerLettre(int num) {
		if (this.size() <= num) return false;
		this.lettreSelectionee = num;
		return true;
	}
	
	/**
	 * Cette méthode renvoie la lettre selectionee
	 * @return Lettre La lettre selectionee
	 */
	public Lettre obtenirLettre() {
		if (this.lettreSelectionee == -1) return null;
		Lettre lettre = this.remove(lettreSelectionee);
		this.lettreSelectionee = -1;
		return lettre;
	}
}
