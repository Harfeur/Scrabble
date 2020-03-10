package fr.scrabble.structures;

import java.io.Serializable;

public class Lettre implements Serializable {
	private static final long serialVersionUID=1L;
	String lettre;
	int valeur;
	
	public Lettre(String l, int v) {
		this.lettre=l;
		this.valeur=v;
	}

	@Override
	public String toString() {
		return lettre+"("+valeur+")";
	}
	
	
	
}
