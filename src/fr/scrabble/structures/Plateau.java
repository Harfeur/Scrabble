package fr.scrabble.structures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.util.Arrays;

import fr.scrabble.menu.vues.ErrorFrame;
import fr.scrabble.structures.Case.Multiplicateur;

public class Plateau implements Serializable {

	private static final long serialVersionUID = -8869397609730203863L;
	
	Case[][] plateau;

	public Plateau() {
		super();
		this.plateau = new Case[15][15];
		URL fichier=Plateau.class.getResource("/resources/plateau.csv");
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(fichier.openStream()));
			String strCurrentLine;
			int ligne = 0;
			while ((strCurrentLine = reader.readLine()) != null) {
				String[] tab = strCurrentLine.split(",");
				for (int col = 0; col < 15; col++) {
					Multiplicateur m;
					if (tab.length > col) {
						switch (tab[col]) {
							case "2W": m = Multiplicateur.MOT_DOUBLE; break;
							case "3W": m = Multiplicateur.MOT_TRIPLE; break;
							case "2L": m = Multiplicateur.LETTRE_DOUBLE; break;
							case "3L": m = Multiplicateur.LETTRE_TRIPLE; break;
							default: m = Multiplicateur.SIMPLE;
						}
						this.plateau[ligne][col] = new Case(m);
					} else {
						this.plateau[ligne][col] = new Case(Multiplicateur.SIMPLE);
					}
				}
				ligne++;
			}
			reader.close();
		} catch(IOException e1) {
			new ErrorFrame("Fichiers manquants");
		}
	}

	public Case getCase(int ligne, int colonne) {
		return this.plateau[ligne][colonne];
	}
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		for (Case[] cases : plateau) {
			for (Case case1 : cases) {
				if (case1.lettre != null)
					buf.append(case1.lettre.lettre);
				else
					buf.append('-');
			}
		}
		return buf.toString();
	}
	
	@Override
	public Plateau clone() {
		Plateau p = new Plateau();
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				p.plateau[i][j] = this.plateau[i][j].clone();
			}
		}
		return p;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(plateau);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Plateau other = (Plateau) obj;
		if (!Arrays.deepEquals(plateau, other.plateau))
			return false;
		return true;
	}
}
