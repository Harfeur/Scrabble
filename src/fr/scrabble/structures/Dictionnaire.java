package fr.scrabble.structures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;

@SuppressWarnings("serial")
public class Dictionnaire extends HashSet<String> {
	
	/**
	 * Initialise un Dico avec le fichier langue situé à assets/dicos/
	 * @param langue La langue du fichier
	 */
	public Dictionnaire(String langue) {
		super();
		URL fichier=Dictionnaire.class.getResource("/resources/dicos/"+langue+".txt");
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(fichier.openStream()));
			String strCurrentLine;
		    while ((strCurrentLine = reader.readLine()) != null) {
		    	this.add(strCurrentLine);
		    }
		    System.out.println(this.size() + " mots ajoutés dans le dictionnaire.");
		    reader.close();
		
		} catch(IOException e1) {
			System.out.print("Erreur");
			System.exit(0);
		}
	}
	
	/**
	 * Renvoie vrai ou faux selon si un mot est contenu dans le dico
	 * @param mot Mot a chercher dans le dictionnaire
	 * @return Un booléen vrai si le mot est contenu dans le dico, faux sinon
	 */
	public boolean contient(String mot) {
		return this.contains(mot);
	}
	
}
