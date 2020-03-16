package fr.scrabble.structures;

import java.util.ArrayList;

public class MotPlace {

	enum Sens {DROITE, BAS};
	
	ArrayList<Lettre> mot;
	Case depart;
	Sens sens;

	public MotPlace(Lettre l, Case c) {
		super();
		this.mot = new ArrayList<Lettre>();
		this.mot.add(l);
		this.depart = c;
	}
	
	public void ajoutLettre(Lettre l) {
		this.mot.add(l);
	}
	
	public int nombreDeLettres() {
		return this.mot.size();
	}
}
