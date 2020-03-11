package fr.scrabble.structures;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import fr.scrabble.structures.Case.Multiplicateur;

public class Plateau {

	Case[][] plateau;

	public Plateau() {
		super();
		this.plateau = new Case[15][15];
		File fichier=new File("assets/plateau.csv");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fichier));
			String strCurrentLine;
			int ligne = 0;
			while ((strCurrentLine = reader.readLine()) != null) {
				String[] tab = strCurrentLine.split(",");
				System.out.println(tab.length);
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
		} catch(IOException e1) {
			System.out.print("Erreur");
			System.exit(0);
		}
		System.out.println();
	}

	public Case getCase(int ligne, int colonne) {
		return this.plateau[ligne][colonne];
	}

}
