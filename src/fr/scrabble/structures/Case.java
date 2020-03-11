package fr.scrabble.structures;

public class Case {
	public enum Multiplicateur {SIMPLE,LETTRE_DOUBLE,MOT_DOUBLE,LETTRE_TRIPLE,MOT_TRIPLE};
	public Multiplicateur multiplicateur;
	public Lettre lettre;
	
	public Case(Multiplicateur multi) {
		this.multiplicateur=multi;
	}
	
	public void ajouterLettre(Lettre lettre) {
		this.lettre =lettre;
		// TODO: Exception
	}
}
