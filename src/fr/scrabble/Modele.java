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
	Integer numChevalet;
	ArrayList<MotPlace> motValide;
	MotPlace motBas, motDroite;
	ArrayList<Placement> placementEnCours;
	Dictionnaire dico;
	int motbasOk, motdroiteOk;
	boolean Test1, Test2, premierTour;

	public Modele() {

	}

	/*Met le jeu a zero en fonction de nb de joueur*/
	public void nouvellePartie(int nbJoueur) {
		this.sac = new Sac("FR");
		this.dico = new Dictionnaire("FR");
		this.plateau= new Plateau();
		this.plateauFictif= new Plateau();
		this.placementEnCours = new ArrayList<Placement>();
		
		this.premierTour=false;
		
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
		this.notifyObservers(this.numChevalet);

		this.setChanged();
		this.notifyObservers(this.plateau);

		this.setChanged();
		this.notifyObservers(this.sac);

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
			if(lettre.valeur == 0) {
				System.out.println("Veuillez choisir");
				lettre.lettre="A";
			}
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
		int lig=0;
		int col =0;
		int lettrecotecote=1;
		Placement premierLettre = this.placementEnCours.get(0);
		for (Placement elem : this.placementEnCours) {
			lig=lig+elem.getLine();
			col=col+elem.getColumn();
			if(this.premierTour==false && elem.getLine()==7 && elem.getColumn()==7) {
				premierTour=true;
			}
		}
		if(premierLettre.getLine() == lig/this.placementEnCours.size() || premierLettre.getColumn() == col/this.placementEnCours.size()) {
			//Pour un seul ajout
			if(this.placementEnCours.size()==1) {
				//mot bas
				int l=0;
				if(premierLettre.getLine()>0) {
					while(this.plateauFictif.getCase(premierLettre.getLine()+l-1, premierLettre.getColumn()).lettre != null) {
						l--;
						if(premierLettre.getLine()+l-1==-1) {
							break;
						}
					}	
				}
				Case premB = this.plateauFictif.getCase(premierLettre.getLine()+l, premierLettre.getColumn());

				motBas = new MotPlace( premB.lettre, premierLettre.getLine()+l, premierLettre.getColumn());

				if(premierLettre.getLine()<14) {
					while(this.plateauFictif.getCase(premierLettre.getLine()+l+1,premierLettre.getColumn()).lettre != null) {
						premB = this.plateauFictif.getCase(premierLettre.getLine()+l+1, premierLettre.getColumn());
						motBas.ajoutLettre(premB.lettre, premierLettre.getLine()+l+1, premierLettre.getColumn());
						l++;
						if(premierLettre.getLine()+l+1==15) {
							break;
						}
					}
				}

				if(motBas.valideMot(this.dico) || motBas.nombreDeLettres()==1) {
					motbasOk++;
				}

				//mot droite
				int c=0;
				if(premierLettre.getColumn()>0) {
					while(this.plateauFictif.getCase(premierLettre.getLine(), premierLettre.getColumn()+c-1).lettre != null) {
						c--;
						if(premierLettre.getColumn()+c-1==-1) {
							break;
						}
					}	
				}
				Case premD = this.plateauFictif.getCase(premierLettre.getLine(), premierLettre.getColumn()+c);

				motDroite = new MotPlace( premD.lettre, premierLettre.getLine(), premierLettre.getColumn()+c);

				if(premierLettre.getColumn()<14) {
					while(this.plateauFictif.getCase(premierLettre.getLine(),premierLettre.getColumn()+c+1).lettre != null) {
						premD = this.plateauFictif.getCase(premierLettre.getLine(), premierLettre.getColumn()+c+1);
						motDroite.ajoutLettre(premD.lettre, premierLettre.getLine(), premierLettre.getColumn()+c+1);
						c++;
						if(premierLettre.getColumn()+c+1==15) {
							break;
						}
					}
				}
				if(motDroite.valideMot(this.dico) || motDroite.nombreDeLettres()==1) {
					motdroiteOk++;
				}

				if(motbasOk==1 && motdroiteOk==1) {
					Test1=true;
					Test2=true;
				}
			}	
			//plusieurs lettres
			else {
				Placement deuxiemLettre = this.placementEnCours.get(1);
				// Si les lettres sont dans la meme colonne
				if(premierLettre.getColumn()==deuxiemLettre.getColumn()) {
					//mot bas
					int l=0;
					if(premierLettre.getLine()>0) {
						while(this.plateauFictif.getCase(premierLettre.getLine()+l-1, premierLettre.getColumn()).lettre != null) {
							l--;
							if(premierLettre.getLine()+l-1==-1) {
								break;
							}
						}	
					}
					Case premB = this.plateauFictif.getCase(premierLettre.getLine()+l, premierLettre.getColumn());

					motBas = new MotPlace( premB.lettre, premierLettre.getLine()+l, premierLettre.getColumn());

					if(premierLettre.getLine()<14) {
						while(this.plateauFictif.getCase(premierLettre.getLine()+l+1,premierLettre.getColumn()).lettre != null) {
							for(Placement elem: this.placementEnCours) {
								if(elem.getCase()==premB) {
									lettrecotecote++;
								}
							}
							premB = this.plateauFictif.getCase(premierLettre.getLine()+l+1, premierLettre.getColumn());
							motBas.ajoutLettre(premB.lettre, premierLettre.getLine()+l+1, premierLettre.getColumn());
							l++;
							if(premierLettre.getLine()+l+1==15) {
								break;
							}
						}
					}
					
					if(motBas.valideMot(this.dico) || motBas.nombreDeLettres()==1) {
						motbasOk++;
					}

					for(Placement elem : this.placementEnCours) {
						//mot droite
						int c=0;
						if(premierLettre.getColumn()>0) {
							while(this.plateauFictif.getCase(premierLettre.getLine(), premierLettre.getColumn()+c-1).lettre != null) {
								c--;
								if(premierLettre.getColumn()+c-1==-1) {
									break;
								}
							}	
						}
						Case premD = this.plateauFictif.getCase(premierLettre.getLine(), premierLettre.getColumn()+c);

						motDroite = new MotPlace( premD.lettre, premierLettre.getLine(), premierLettre.getColumn()+c);

						if(premierLettre.getColumn()<14) {
							while(this.plateauFictif.getCase(premierLettre.getLine(),premierLettre.getColumn()+c+1).lettre != null) {
								premD = this.plateauFictif.getCase(premierLettre.getLine(), premierLettre.getColumn()+c+1);
								motDroite.ajoutLettre(premD.lettre, premierLettre.getLine(), premierLettre.getColumn()+c+1);
								c++;
								if(premierLettre.getColumn()+c+1==15) {
									break;
								}
							}
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
						//mot bas
						int l=0;
						if(premierLettre.getLine()>0) {
							while(this.plateauFictif.getCase(premierLettre.getLine()+l-1, premierLettre.getColumn()).lettre != null) {
								l--;
								if(premierLettre.getLine()+l-1==-1) {
									break;
								}
							}	
						}
						Case premB = this.plateauFictif.getCase(premierLettre.getLine()+l, premierLettre.getColumn());

						motBas = new MotPlace( premB.lettre, premierLettre.getLine()+l, premierLettre.getColumn());

						if(premierLettre.getLine()<14) {
							while(this.plateauFictif.getCase(premierLettre.getLine()+l+1,premierLettre.getColumn()).lettre != null) {
								premB = this.plateauFictif.getCase(premierLettre.getLine()+l+1, premierLettre.getColumn());
								motBas.ajoutLettre(premB.lettre, premierLettre.getLine()+l+1, premierLettre.getColumn());
								l++;
								if(premierLettre.getLine()+l+1==15) {
									break;
								}
							}
						}

						if(motBas.valideMot(this.dico) || motBas.nombreDeLettres()==1) {
							motbasOk++;
						}
					}

					//mot droite
					int c=0;
					if(premierLettre.getColumn()>0) {
						while(this.plateauFictif.getCase(premierLettre.getLine(), premierLettre.getColumn()+c-1).lettre != null) {
							c--;
							if(premierLettre.getColumn()+c-1==-1) {
								break;
							}
						}	
					}
					Case premD = this.plateauFictif.getCase(premierLettre.getLine(), premierLettre.getColumn()+c);

					motDroite = new MotPlace( premD.lettre, premierLettre.getLine(), premierLettre.getColumn()+c);

					if(premierLettre.getColumn()<14) {
						while(this.plateauFictif.getCase(premierLettre.getLine(),premierLettre.getColumn()+c+1).lettre != null) {
							for(Placement elem: this.placementEnCours) {
								if(elem.getCase()==premD) {
									lettrecotecote++;
								}
							}
							premD = this.plateauFictif.getCase(premierLettre.getLine(), premierLettre.getColumn()+c+1);
							motDroite.ajoutLettre(premD.lettre, premierLettre.getLine(), premierLettre.getColumn()+c+1);
							c++;
							if(premierLettre.getColumn()+c+1==15) {
								break;
							}
						}
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
			if (this.Test1 && this.Test2 && lettrecotecote == this.placementEnCours.size() && premierTour==true) {
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
		else {
			System.out.println("Les lettres ne sont pas sur la même ligne ou colonne");
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
