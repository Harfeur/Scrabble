package fr.scrabble.structures;

public class Lettre {
	
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
