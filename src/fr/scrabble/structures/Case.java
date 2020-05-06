package fr.scrabble.structures;

import java.io.Serializable;
import java.net.URL;

public class Case implements Serializable{
	
	private static final long serialVersionUID = -482796625632545523L;
	public static URL simple, ld,md,lt,mt;
	
	public enum Multiplicateur implements Serializable{
		
		SIMPLE ("S"),
		LETTRE_DOUBLE ("LD"),
		MOT_DOUBLE ("MD"),
		LETTRE_TRIPLE ("LT"),
		MOT_TRIPLE ("MT");
		
		private String libelle;
		
		private static final long serialVersionUID = 315206622012546523L;
		
		private Multiplicateur(String l) {
			this.libelle = l;
		}
		
		public String toString() {
			return this.libelle;
		}
	};
	
	
	public Multiplicateur multiplicateur;
	public Lettre lettre;
	
	public Case(Multiplicateur multi) {
		this.multiplicateur=multi;
	}
	
	public void ajouterLettre(Lettre lettre) {
		this.lettre =lettre;
	}

	@Override
	protected Case clone() {
		Case c = new Case(this.multiplicateur);
		if (this.lettre != null)
			c.lettre = this.lettre.clone();
		return c;
	}
	
}
