package fr.scrabble;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import fr.scrabble.structures.Lettre;
import fr.scrabble.structures.Sac;

public class CreationBD {
	Sac sac;
	File fichier;

	public CreationBD() {
		this.sac=new Sac();
		Lettre lettre= new Lettre("A", 1);
		for(int i=0; i<9;i++) {
			this.sac.ajouterLettre(lettre);
		}

		lettre= new Lettre("B", 3);
		for(int i=0; i<2;i++) {
			this.sac.ajouterLettre(lettre);
		}

		lettre= new Lettre("C", 3);
		for(int i=0; i<2;i++) {
			this.sac.ajouterLettre(lettre);
		}

		lettre= new Lettre("D", 2);
		for(int i=0; i<3;i++) {
			this.sac.ajouterLettre(lettre);
		}

		lettre= new Lettre("E", 1);
		for(int i=0; i<15;i++) {
			this.sac.ajouterLettre(lettre);
		}

		lettre= new Lettre("F", 4);
		for(int i=0; i<2;i++) {
			this.sac.ajouterLettre(lettre);
		}

		lettre= new Lettre("G", 2);
		for(int i=0; i<2;i++) {
			this.sac.ajouterLettre(lettre);
		}

		lettre= new Lettre("H", 4);
		for(int i=0; i<2;i++) {
			this.sac.ajouterLettre(lettre);
		}

		lettre= new Lettre("I", 1);
		for(int i=0; i<8;i++) {
			this.sac.ajouterLettre(lettre);
		}

		lettre= new Lettre("J", 8);
		for(int i=0; i<1;i++) {
			this.sac.ajouterLettre(lettre);
		}

		lettre= new Lettre("K", 10);
		for(int i=0; i<1;i++) {
			this.sac.ajouterLettre(lettre);
		}

		lettre= new Lettre("L", 1);
		for(int i=0; i<5;i++) {
			this.sac.ajouterLettre(lettre);
		}

		lettre= new Lettre("M", 2);
		for(int i=0; i<3;i++) {
			this.sac.ajouterLettre(lettre);
		}

		lettre= new Lettre("N", 1);
		for(int i=0; i<6;i++) {
			this.sac.ajouterLettre(lettre);
		}

		lettre= new Lettre("O", 1);
		for(int i=0; i<6;i++) {
			this.sac.ajouterLettre(lettre);
		}

		lettre= new Lettre("P", 3);
		for(int i=0; i<2;i++) {
			this.sac.ajouterLettre(lettre);
		}

		lettre= new Lettre("Q", 8);
		for(int i=0; i<1;i++) {
			this.sac.ajouterLettre(lettre);
		}

		lettre= new Lettre("R", 1);
		for(int i=0; i<6;i++) {
			this.sac.ajouterLettre(lettre);
		}

		lettre= new Lettre("S", 1);
		for(int i=0; i<6;i++) {
			this.sac.ajouterLettre(lettre);
		}

		lettre= new Lettre("T", 1);
		for(int i=0; i<6;i++) {
			this.sac.ajouterLettre(lettre);
		}

		lettre= new Lettre("U", 1);
		for(int i=0; i<6;i++) {
			this.sac.ajouterLettre(lettre);
		}

		lettre= new Lettre("V", 4);
		for(int i=0; i<2;i++) {
			this.sac.ajouterLettre(lettre);
		}

		lettre= new Lettre("W", 10);
		for(int i=0; i<1;i++) {
			this.sac.ajouterLettre(lettre);
		}

		lettre= new Lettre("X", 10);
		for(int i=0; i<1;i++) {
			this.sac.ajouterLettre(lettre);
		}

		lettre= new Lettre("Y", 10);
		for(int i=0; i<1;i++) {
			this.sac.ajouterLettre(lettre);
		}

		lettre= new Lettre("Z", 10);
		for(int i=0; i<1;i++) {
			this.sac.ajouterLettre(lettre);
		}

		lettre= new Lettre("JOKER", 0);
		for(int i=0; i<2;i++) {
			this.sac.ajouterLettre(lettre);
		}
		
		this.fichier=new File("assets/sacs/FR.dat");
		System.out.print(this.sac);
		try{
			FileOutputStream fos = new FileOutputStream(fichier);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(this.sac);
			
			oos.close();
			fos.close();
		}
		catch(IOException e1) {
			throw new RuntimeException("Lecture des données impossibles ou données corrompus");
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new CreationBD();
	}

}
