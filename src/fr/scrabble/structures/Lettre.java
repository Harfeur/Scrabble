package fr.scrabble.structures;

import java.io.Serializable;
import java.net.URL;

public class Lettre implements Serializable {
	
	private static final long serialVersionUID = 4138175046747357913L;
	
	public String lettre;
	public int valeur;
	public URL image, imageSelectionnee;
			
	
	public Lettre(String l, int v) {
		this.lettre=l;
		this.valeur=v;
		this.image=Lettre.class.getResource("/resources/images/lettre/letter_"+lettre+".png");
		this.imageSelectionnee= Lettre.class.getResource("/resources/images/lettreSelectionnee/letter_"+lettre+".png");
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
