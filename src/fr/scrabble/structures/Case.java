package fr.scrabble.structures;

import java.awt.Color;
import java.io.Serializable;

public class Case implements Serializable{
	
	private static final long serialVersionUID = -482796625632545523L;

	static Color[] simple = {Color.green, new Color(81,138,11)};
	static Color[] ld = {Color.cyan, new Color(131,198,235)};
	static Color[] md = {Color.pink, new Color(209,134,194)};
	static Color[] lt = {Color.blue, new Color(42,103,209)};
	static Color[] mt = {Color.red, new Color(186,60,60)};
	
	public enum Multiplicateur implements Serializable{
		
		SIMPLE (simple, "S"),
		LETTRE_DOUBLE (ld, "LD"),
		MOT_DOUBLE (md, "MD"),
		LETTRE_TRIPLE (lt, "LT"),
		MOT_TRIPLE (mt, "MT");
		
		private Color[] couleur;
		private String libelle;
		
		private static final long serialVersionUID = 315206622012546523L;
		
		private Multiplicateur(Color[] c, String l) {
			this.couleur = c;
			this.libelle = l;
		}
		
		public String toString() { return this.libelle; }
		public Color[] getCouleur() { return this.couleur; }
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
