package fr.scrabble.structures;

public class Case {
	enum Multiplicateur {SIMPLE,LETTRE_DOUBLE,MOT_DOUBLE,LETTRE_TRIPLE,MOT_TRIPLE};
	Multiplicateur multiplicateur;
	Lettre lettre;
	
	public Case(Multiplicateur multi) {
		this.multiplicateur=multi;
	}
	
	public void ajouterLettre(Lettre lettre) {
		this.lettre =lettre;
		// TODO: Exception
	}
}
