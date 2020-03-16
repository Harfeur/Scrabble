package fr.scrabble.structures;

import java.awt.Color;

public class Case {
	public enum Multiplicateur {
		
		SIMPLE (Color.green, "green"),
		LETTRE_DOUBLE (Color.cyan, "cyan"),
		MOT_DOUBLE (Color.pink, "pink"),
		LETTRE_TRIPLE (Color.blue, "blue"),
		MOT_TRIPLE (Color.red, "red");
		
		private Color couleur;
		private String libelle;
		
		private Multiplicateur(Color c, String l) {
			this.couleur = c;
			this.libelle = l;
		}
		
		public String toString() { return this.libelle; }
		public Color getCouleur() { return this.couleur; }
	};
	
	
	public Multiplicateur multiplicateur;
	public Lettre lettre;
	
	public Case(Multiplicateur multi) {
		this.multiplicateur=multi;
	}
	
	public void ajouterLettre(Lettre lettre) {
		this.lettre =lettre;
		// TODO: Exception
	}

	@Override
	protected Case clone() {
		Case c = new Case(this.multiplicateur);
		c.lettre = this.lettre.clone();
		return c;
	}
	
}
