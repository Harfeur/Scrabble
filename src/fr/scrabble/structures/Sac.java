package fr.scrabble.structures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("serial")
public class Sac extends ArrayList<Lettre> implements Serializable {
	private static final long serialVersionUID=1L;
	
	public Sac() {
		super();
	}
	
	public void ajouterLettre(Lettre lettre) {
		this.add(lettre);
	}
	
	public Lettre obtenirLettre() {
		Random r = new Random();
		return this.remove(r.nextInt(this.size()));
	}
	
	public boolean estVide() {
		return this.size() == 0;
	}
	
}
