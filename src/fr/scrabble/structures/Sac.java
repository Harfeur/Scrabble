package fr.scrabble.structures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import fr.scrabble.menu.vues.ErrorFrame;

public class Sac extends ArrayList<Lettre> implements Serializable {
	
	private static final long serialVersionUID = 8678217022591767923L;
	

	public Sac(String langue) {
		super();
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
		    	
		    	this.ajouterLettre(nouvLettre, nombre);
		    }
		    reader.close();

		} catch(IOException e1) {
			new ErrorFrame("Fichiers manquants");
		}

	}

	public void ajouterLettre(Lettre lettre, int nombre) {
		for (int i = 0; i < nombre; i++)
			this.add(lettre.clone());
	}
	
	public void remettreLettre(Lettre lettre) {
		this.add(lettre);
	}

	public Lettre obtenirLettre() {
		if(this.estVide()==false) {
			Random r = new Random();
			int nombreAleatoire = r.nextInt(this.size());

			Lettre l = this.get(nombreAleatoire);
			this.remove(nombreAleatoire);
			return l;
		}
		return null;
	}

	public boolean estVide() {
		return this.size()==0;
	}

	public int nbLettre() {
		return this.size();
	}

}
