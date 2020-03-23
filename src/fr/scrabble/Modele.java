package fr.scrabble;
import java.io.File;
import java.util.ArrayList;
import java.util.Observable;

import fr.scrabble.structures.*;

public class Modele extends Observable{
	
	Sac sac;
	File fichier;
	Plateau plateau, plateauFictif;
	Chevalet[] chevalets;
	int numChevalet;
	ArrayList<MotPlace> motValide;
	MotPlace motBas, motDroite;
	ArrayList<Placement> placementEnCours;
	Dictionnaire dico;
	int motbasOk, motdroiteOk;
	boolean Test1, Test2;
	
	public Modele() {
		
	}
	
	/*Met le jeu a zero en fonction de nb de joueur*/
	public void nouvellePartie(int nbJoueur) {
		this.sac = new Sac("FR");
		this.dico = new Dictionnaire("FR");
		this.plateau= new Plateau();
		this.plateauFictif= new Plateau();
		this.placementEnCours = new ArrayList<Placement>();
		
		this.chevalets = new Chevalet[nbJoueur];
		
		for (int i=0; i<nbJoueur; i++) {
			this.chevalets[i]=new Chevalet();
			this.chevalets[i].remplir(this.sac);
		}
		this.numChevalet=0;

		// Après avoir cree les elements, on notifie les deux vues
		
		this.setChanged();
		this.notifyObservers(this.chevalets[numChevalet]);
		
		this.setChanged();
		this.notifyObservers(this.plateau);
		
	}

	/* mis a jour de la lettre selectionner*/
	public void selectLettre(int num) {
		if (this.chevalets[this.numChevalet].selectionnerLettre(num)) {
			this.setChanged();
			this.notifyObservers(this.chevalets[this.numChevalet]);
		}
	}

	/* ajoute la lettre choisi sur le chevalet dans le plateau*/
	public void ajoutLettre(int col, int lig) {
		Lettre lettre = this.chevalets[this.numChevalet].obtenirLettre();
		Case c = this.plateauFictif.getCase(lig, col);
		if (lettre == null) {
			// Si aucune lettre n'est selectionee du chevalet, alors on supprime la lettre du plateau
			// que si c'est une lettre qui est en train d'etre placee
			Placement caseCliquee = new Placement(null, c, lig, col);
			if (this.placementEnCours.contains(caseCliquee)) {
				this.chevalets[this.numChevalet].remettreLettre(c.lettre);
				c.lettre = null;
				this.placementEnCours.remove(caseCliquee);
			}
		} else {
			// Sinon, on ajoute la lettre sur le plateau, et dans notre liste des placments
			// du tour en cours
			this.placementEnCours.add(new Placement(lettre, c, lig, col));
			c.ajouterLettre(lettre);
		}
		this.setChanged();
		this.notifyObservers(this.plateauFictif);
		
		this.setChanged();
		this.notifyObservers(this.chevalets[this.numChevalet]);
	}
	
	public void verificationMot() {
		Test1=false;
		Test2=false;
		Placement premierLettre = this.placementEnCours.get(0);
		//Pour un seul ajout
		if(this.placementEnCours.size()==1) {
			int l=1;
			//Vérif bas
			while(this.plateauFictif.getCase(premierLettre.getLine()-l,premierLettre.getColumn()).lettre != null) {
				l++;
			}
			Case premB = this.plateauFictif.getCase(premierLettre.getLine()-l+1, premierLettre.getColumn());
			//mot sens bas
			motBas = new MotPlace( premB.lettre, premierLettre.getLine()-l+1, premierLettre.getColumn());
			while(this.plateauFictif.getCase(premierLettre.getLine()-l+1,premierLettre.getColumn()).lettre != null) {
				premB = this.plateauFictif.getCase(premierLettre.getLine()-l+1, premierLettre.getColumn());
				motBas.ajoutLettre(premB.lettre, premierLettre.getLine()-l+1, premierLettre.getColumn());
				l--;
			}
			
			if(motBas.valideMot(this.dico) || motBas.nombreDeLettres()==1) {
				motbasOk++;
			}

			int c=1;
			//Vérif droite
			while(this.plateauFictif.getCase(premierLettre.getLine(),premierLettre.getColumn()-c).lettre != null) {
				c++;
			}
			Case premD = this.plateauFictif.getCase(premierLettre.getLine(), premierLettre.getColumn()-c+1);
			//mot sens droite
			motDroite = new MotPlace( premD.lettre, premierLettre.getLine(), premierLettre.getColumn()-c+1);
			while(this.plateauFictif.getCase(premierLettre.getLine(),premierLettre.getColumn()-c+1).lettre != null) {
				premD = this.plateauFictif.getCase(premierLettre.getLine(), premierLettre.getColumn()-c+1);
				motDroite.ajoutLettre(premD.lettre, premierLettre.getLine(), premierLettre.getColumn()-c+1);
				c--;
			}
			if(motDroite.valideMot(this.dico) || motDroite.nombreDeLettres()==1) {
				motdroiteOk++;
			}
			
			if(motbasOk==1 && motdroiteOk==1) {
				Test1=true;
				Test2=true;
			}
		}
		// Plusieurs lettres ajoutées
		else {
			Placement deuxiemLettre = this.placementEnCours.get(1);
			// Si les lettres sont dans la meme ligne
			if(premierLettre.getColumn()==deuxiemLettre.getColumn()) {
				int l=1;
				//Vérif bas
				while(this.plateauFictif.getCase(premierLettre.getLine()-l,premierLettre.getColumn()).lettre != null) {
					l++;
				}
				Case premB = this.plateauFictif.getCase(premierLettre.getLine()-l+1, premierLettre.getColumn());
				//mot sens bas
				motBas = new MotPlace( premB.lettre, premierLettre.getLine()-l+1, premierLettre.getColumn());
				while(this.plateauFictif.getCase(premierLettre.getLine()-l+1,premierLettre.getColumn()).lettre != null) {
					premB = this.plateauFictif.getCase(premierLettre.getLine()-l+1, premierLettre.getColumn());
					motBas.ajoutLettre(premB.lettre, premierLettre.getLine()-l+1, premierLettre.getColumn());
					l--;
				}
				
				if(motBas.valideMot(this.dico) || motBas.nombreDeLettres()==1) {
					motbasOk++;
				}
				for(Placement elem : this.placementEnCours) {
					int c=1;
					//Vérif droite
					while(this.plateauFictif.getCase(elem.getLine(),elem.getColumn()-c).lettre != null) {
						c++;
					}
					Case premD = this.plateauFictif.getCase(elem.getLine(), elem.getColumn()-c+1);
					//mot sens droite
					motDroite = new MotPlace( premD.lettre, elem.getLine(), elem.getColumn()-c+1);
					while(this.plateauFictif.getCase(elem.getLine(),elem.getColumn()-c+1).lettre != null) {
						premD = this.plateauFictif.getCase(elem.getLine(), elem.getColumn()-c+1);
						motDroite.ajoutLettre(premD.lettre, elem.getLine(), elem.getColumn()-c+1);
						c--;
					}
					if(motDroite.valideMot(this.dico) || motDroite.nombreDeLettres()==1) {
						motdroiteOk++;
					}
				}
				if(motbasOk==1 && motdroiteOk==this.placementEnCours.size()) {
					Test1=true;
					Test2=true;
				}
			}
			//lettre dans la meme ligne
			if(premierLettre.getLine()==deuxiemLettre.getLine()) {
				for(Placement elem : this.placementEnCours) {
					int l=1;
					//Vérif bas
					while(this.plateauFictif.getCase(elem.getLine()-l,elem.getColumn()).lettre != null) {
						l++;
					}
					Case premB = this.plateauFictif.getCase(elem.getLine()-l+1, elem.getColumn());
					//mot sens bas
					motBas = new MotPlace( premB.lettre, elem.getLine()-l+1, elem.getColumn());
					while(this.plateauFictif.getCase(elem.getLine()-l+1,elem.getColumn()).lettre != null) {
						premB = this.plateauFictif.getCase(elem.getLine()-l+1, elem.getColumn());
						motBas.ajoutLettre(premB.lettre, elem.getLine()-l+1, elem.getColumn());
						l--;
					}
					if(motBas.valideMot(this.dico) || motBas.nombreDeLettres()==1) {
						motbasOk++;
					}
				}
				int c=1;
				//Vérif droite
				while(this.plateauFictif.getCase(premierLettre.getLine(),premierLettre.getColumn()-c).lettre != null) {
					c++;
				}
				Case premD = this.plateauFictif.getCase(premierLettre.getLine(), premierLettre.getColumn()-c+1);
				//mot sens droite
				motDroite = new MotPlace( premD.lettre, premierLettre.getLine(), premierLettre.getColumn()-c+1);
				while(this.plateauFictif.getCase(premierLettre.getLine(),premierLettre.getColumn()-c+1).lettre != null) {
					premD = this.plateauFictif.getCase(premierLettre.getLine(), premierLettre.getColumn()-c+1);
					motDroite.ajoutLettre(premD.lettre, premierLettre.getLine(), premierLettre.getColumn()-c+1);
					c--;
				}
				if(motDroite.valideMot(this.dico) || motDroite.nombreDeLettres()==1) {
					motdroiteOk++;
				}
				
				if(motbasOk==this.placementEnCours.size() && motdroiteOk==1) {
					Test1=true;
					Test2=true;
				}
			}
		}
		if (this.Test1 && this.Test2) {
			System.out.println("Plateau Valide");
			this.chevalets[this.numChevalet].remplir(sac);
			this.changementJoueur();
		}
		else {
			System.out.println("Plateau Non Valide");
			for(Placement elem: this.placementEnCours) {
				this.chevalets[this.numChevalet].remettreLettre(elem.getLetter());
				elem.getCase().lettre=null;
			}
			this.setChanged();
			this.notifyObservers(this.chevalets[this.numChevalet]);
			this.plateauFictif=this.plateau.clone();
			this.setChanged();
			this.notifyObservers(this.plateauFictif);
		} 
	}
	
	/*met a jour les changements de Joueur */
	public void changementJoueur() {
		this.chevalets[this.numChevalet].remplir(sac);
		if (this.numChevalet+1 == this.chevalets.length) {
			this.numChevalet=0;
		}
		else {
			this.numChevalet++;
		}
		this.motbasOk=0;
		this.motdroiteOk=0;
		this.plateau=this.plateauFictif.clone();
		this.setChanged();
		this.notifyObservers(this.sac);
		this.setChanged();
		this.notifyObservers(this.plateau);
		this.setChanged();
		this.notifyObservers(this.numChevalet);
		this.setChanged();
		this.notifyObservers(this.chevalets[this.numChevalet]);
		// On initialise à zéro le placement
		this.placementEnCours = new ArrayList<Placement>();
	}
}
