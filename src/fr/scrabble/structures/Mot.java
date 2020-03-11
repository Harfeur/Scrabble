package fr.scrabble.structures;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Mot extends ArrayList<Lettre> {

	public Mot() {
		super();
	}

	public void ajoutLettre(Lettre lettre) {
		this.add(lettre);
	}

	public boolean motValide() {
		String[] motCree = new String[this.size()];
		for (int i=0; i<this.size();i++) {
			motCree[i]=this.get(i).lettre;
		}

		File fichier=new File("assets/dictionnaire.txt");
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fichier));
			String strCurrentLine;
			while ((strCurrentLine = reader.readLine()) != null) {
				String[] tab=strCurrentLine.split("");


			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return true;
}
}
