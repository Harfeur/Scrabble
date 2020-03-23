package fr.scrabble.structures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Hashtable;
import java.util.Random;
import java.util.Map.Entry;

@SuppressWarnings("serial")
public class Sac extends Hashtable<Lettre, Integer> {
	
	int nombreDeLettres;

	public Sac(String langue) {
		super();
		this.nombreDeLettres = 0;
		URL fichier = Sac.class.getResource("/resources/sacs/"+langue+".csv");
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(fichier.openStream()));
			String strCurrentLine;
		    while ((strCurrentLine = reader.readLine()) != null) {
		    	String[] tab=strCurrentLine.split(",");
		    	String lettre = tab[0];
		    	int valeur= Integer.parseInt(tab[1]);
		    	Lettre nouvLettre= new Lettre(lettre, valeur);
		    	int nombre = Integer.parseInt(tab[2]);
		    	this.nombreDeLettres= this.nombreDeLettres+nombre;
		    	
		    	this.ajouterLettre(nouvLettre, nombre);
		    }
		    reader.close();

		} catch(IOException e1) {
			System.out.print("Erreur");
			System.exit(0);
		}

	}

	public void ajouterLettre(Lettre lettre, int nombre) {
		this.put(lettre, nombre);
	}

	public Lettre obtenirLettre() {
		if(this.estVide()==false) {
			Random r = new Random();
			int nombreAleatoire = r.nextInt(this.nombreDeLettres);
			int compteur=0;
			this.nombreDeLettres--;

			for (Entry<Lettre, Integer> element : this.entrySet()) {
				compteur = compteur + element.getValue();
				if (compteur>=nombreAleatoire) {
					return element.getKey();
				}
			}
		}
		return null;
	}

	public boolean estVide() {
		return this.nombreDeLettres==0;
	}

}
