package fr.scrabble.structures;

import java.awt.Color;
import java.io.Serializable;

public class Case implements Serializable{
	private static final long serialVersionUID=1L;
	public enum Multiplicateur {
		
		SIMPLE (new Color(81,138,11), "S"),
		LETTRE_DOUBLE (new Color(131,198,235), "LD"),
		MOT_DOUBLE (new Color(209,134,194), "MD"),
		LETTRE_TRIPLE (new Color(42,103,209), "LT"),
		MOT_TRIPLE (new Color(186,60,60), "MT");
		
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
		if (this.lettre != null)
			c.lettre = this.lettre.clone();
		return c;
	}
	
}
