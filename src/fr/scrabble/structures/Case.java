package fr.scrabble.structures;

import java.awt.Color;
import java.io.Serializable;
import java.net.URL;

public class Case implements Serializable{
	
	private static final long serialVersionUID = -482796625632545523L;
	public static URL simple, ld,md,lt,mt;
	
	public enum Multiplicateur implements Serializable{
		
		SIMPLE (simple, "S"),
		LETTRE_DOUBLE (ld, "LD"),
		MOT_DOUBLE (md, "MD"),
		LETTRE_TRIPLE (lt, "LT"),
		MOT_TRIPLE (mt, "MT");
		
		private URL couleur;
		private String libelle;
		
		private static final long serialVersionUID = 315206622012546523L;
		
		private Multiplicateur(URL simple2, String l) {
			this.couleur = simple2;
			this.libelle = l;
		}
		
		public String toString() { return this.libelle; }
		public URL getCouleur() { return Multiplicateur.class.getResource("/resources/images/plateau/"+this.libelle+".png"); }
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
