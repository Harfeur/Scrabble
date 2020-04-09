package fr.scrabble.structures;

import java.io.Serializable;

public class Lettre implements Serializable {
	
	private static final long serialVersionUID = 4138175046747357913L;
	
	public String lettre;
	public int valeur;
	
	public Lettre(String l, int v) {
		this.lettre=l;
		this.valeur=v;
	}

	@Override
	public String toString() {
		return lettre+"("+valeur+")";
	}
	
	@Override
	public Lettre clone() {
		Lettre l = new Lettre(this.lettre, this.valeur);
		return l;
	}
	
}
