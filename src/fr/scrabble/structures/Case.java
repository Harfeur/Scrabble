package fr.scrabble.structures;

public class Case {
	enum Multiplicateur {LETTRE_DOUBLE,MOT_DOUBLE,LETTRE_TRIPLE,MOT_TRIPLE}; 
	char ligne;
	int colonne;
	Multiplicateur multiplicateur;
	Lettre lettre;
	
	public Case(char lig, int col, Multiplicateur multi) {
		this.ligne=lig;
		this.colonne=col;
		this.multiplicateur=multi;
	}
	
	public void ajouterLettre(Lettre lettre) {
		this.lettre =lettre;
		// TODO: Exception
	}
}
