package fr.scrabble;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import fr.scrabble.structures.*;

public class Modele {
	Sac sac;
	File fichier;
	
	public Modele() {
		this.fichier=new File("assets/sacs/FR.dat");
		try {
			FileInputStream fls = new FileInputStream(fichier);
			ObjectInputStream ols = new ObjectInputStream(fls);
		
			this.sac = (Sac)ols.readObject();
			
			ols.close();
			fls.close();
	
		} catch(IOException | ClassNotFoundException e1) {
			System.out.print("Erreur");
			System.exit(0);
		}
		
		System.out.print(this.sac);
		
	}
}
