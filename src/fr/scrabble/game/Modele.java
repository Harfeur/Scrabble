package fr.scrabble.game;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Observable;

import fr.scrabble.game.vues.VueJoker;
import fr.scrabble.menu.Menu;
import fr.scrabble.structures.*;
import fr.scrabble.structures.Case.Multiplicateur;

public class Modele extends Observable{

	public Sac sac;
	File fichier;
	public Plateau plateau;
	Plateau plateauFictif;
	public SetDeChevalets chevalets;
	public Integer numChevalet;
	public Score[] score;
	public int scoreAv;
	ArrayList<MotPlace> motValide;
	MotPlace motBas, motDroite;
	ArrayList<Placement> placementEnCours;
	Dictionnaire dico;
	String lettreChoisi, langue;
	int motbasOk, motdroiteOk, passe=0;
	boolean Test1, Test2, premierTour, colonne;

	public Modele() {
		super();
	}

	/*Met le jeu a zero en fonction de nb de joueur*/
	public void nouvellePartie(int nbJoueur, String langue, ArrayList<String> prenoms) {

		this.sac = new Sac(langue);
		this.numChevalet=0;
		this.dico = new Dictionnaire(langue);
		this.plateau= new Plateau();
		this.plateauFictif= new Plateau();
		this.placementEnCours = new ArrayList<Placement>();
		this.langue = langue;
		
		this.premierTour=false;
		
		this.chevalets = new SetDeChevalets();
		this.score = new Score[nbJoueur];

		for (int i=0; i<nbJoueur; i++) {
			this.chevalets.ajouterChevalet(new Chevalet());
			this.chevalets.get(i).remplir(sac);
			this.score[i]= new Score(prenoms.get(i));
		}
		// Après avoir cree les elements, on notifie les deux vues

		this.setChanged();
		this.notifyObservers(this.chevalets);
		
		this.setChanged();
		this.notifyObservers(this.score);

		this.setChanged();
		this.notifyObservers(this.numChevalet);

		this.setChanged();
		this.notifyObservers(this.plateau);

		this.setChanged();
		this.notifyObservers(this.sac);
		
		this.setChanged();
		this.notifyObservers(this.score[this.numChevalet]);
	}
	
	public void reprise() {
		try {
			this.charger();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println("echec");
		}
		this.dico = new Dictionnaire(langue);
		this.plateauFictif= this.plateau;
		this.placementEnCours = new ArrayList<Placement>();
		this.motbasOk=0;
		this.motdroiteOk=0;
		
		// Après avoir cree les elements, on notifie les deux vues

		this.setChanged();
		this.notifyObservers(this.chevalets);
		
		this.setChanged();
		this.notifyObservers(this.score);

		this.setChanged();
		this.notifyObservers(this.numChevalet);

		this.setChanged();
		this.notifyObservers(this.plateau);

		this.setChanged();
		this.notifyObservers(this.sac);
		
		this.setChanged();
		this.notifyObservers(this.score[this.numChevalet]);
	}

	/* mis a jour de la lettre selectionner*/
	public void selectLettre(int num) {
		if (this.chevalets.chevaletEnCours().selectionnerLettre(num)) {
			this.setChanged();
			this.notifyObservers(this.chevalets);
			if (this.chevalets.chevaletEnCours().get(num).valeur == 0) {
				new VueJoker(this.langue, this);
				this.setChanged();
				this.notifyObservers("cacher");
			}
		}
	}

	/* ajoute la lettre choisi sur le chevalet dans le plateau*/
	public void ajoutLettre(int col, int lig) {
		Lettre lettre = this.chevalets.chevaletEnCours().obtenirLettre();
		Case c = this.plateauFictif.getCase(lig, col);
		if (lettre == null) {
			// Si aucune lettre n'est selectionee du chevalet, alors on supprime la lettre du plateau
			// que si c'est une lettre qui est en train d'etre placee
			Placement caseCliquee = new Placement(null, c, lig, col);
			if (this.placementEnCours.contains(caseCliquee)) {
				this.chevalets.chevaletEnCours().remettreLettre(c.lettre);
				c.lettre = null;
				this.placementEnCours.remove(caseCliquee);
			}
		} else {
			// Sinon, on ajoute la lettre sur le plateau, et dans notre liste des placments
			// du tour en cours
			if(c.lettre == null) {
				// Si la case est vide
				if(lettre.valeur == 0) {
					lettre.lettre=lettreChoisi;
				}
				this.placementEnCours.add(new Placement(lettre, c, lig, col));
				c.ajouterLettre(lettre);
			}
			else {
				this.chevalets.chevaletEnCours().remettreLettre(lettre);
			}
			
		}
		this.setChanged();
		this.notifyObservers(this.plateauFictif);

		this.setChanged();
		this.notifyObservers(this.chevalets);
	}

	public void verificationMot() {
		Test1=false; //Mot bas
		Test2=false; //Mot droite
		int lig=0;
		int col =0;
		int lettrecotecote=0;
		int autreLettre=0;
		if(this.placementEnCours.size()>0) {
			Placement premierLettre = this.placementEnCours.get(0);	
			for (Placement elem : this.placementEnCours) {
				lig=lig+elem.getLine();
				col=col+elem.getColumn();
				if(this.premierTour==false && elem.getLine()==7 && elem.getColumn()==7 && this.placementEnCours.size()>1) {
					autreLettre=15;
					premierTour=true;
				}
			}
			if(premierLettre.getLine() == lig/this.placementEnCours.size() || premierLettre.getColumn() == col/this.placementEnCours.size()) {
				//Pour un seul ajout
				if(this.placementEnCours.size()==1) {
					lettrecotecote=1;
					System.out.println("Un seul ajout");
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
					System.out.println("Mot bas trouvé : "+ motBas);
					if(motBas.valideMot(this.dico) || motBas.nombreDeLettres()==1) {
						motbasOk++;
					}
					else {
						System.out.print(motBas.toString());
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
					System.out.println("Mot droite trouvé : "+ motDroite);
					if(motDroite.valideMot(this.dico) || motDroite.nombreDeLettres()==1) {
						motdroiteOk++;
					}
					else {
						System.out.print(motDroite.toString());
					}

					if(motbasOk==1 && motdroiteOk==1 && (motBas.nombreDeLettres()!=1 || motDroite.nombreDeLettres()!=1)) {
						Test1=true;
						Test2=true;
						autreLettre=2;
					}
				}	
				//plusieurs lettres
				else {
					System.out.println("Plusieurs ajout");
					Placement deuxiemLettre = this.placementEnCours.get(1);
					// Si les lettres sont dans la meme colonne
					if(premierLettre.getColumn()==deuxiemLettre.getColumn()) {
						colonne=true;
						System.out.println("Même colonne donc 1 mot bas et + mot droite");
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
						boolean present=false;
						for(Placement elem: this.placementEnCours) {
							if(elem.getLine()==premierLettre.getLine()+l && elem.getColumn()==premierLettre.getColumn()) {
								present=true;
							}
						}
						if(present) {
							lettrecotecote++;
						}
						else {
							autreLettre++;
						}
						if(premierLettre.getLine()<14) {
							while(this.plateauFictif.getCase(premierLettre.getLine()+l+1,premierLettre.getColumn()).lettre != null) {
								present=false;
								for(Placement elem: this.placementEnCours) {
									if(elem.getLine()==premierLettre.getLine()+l+1 && elem.getColumn()==premierLettre.getColumn()) {
										present=true;
									}
								}
								if(present) {
									lettrecotecote++;
								}
								else {
									autreLettre++;
								}
								premB = this.plateauFictif.getCase(premierLettre.getLine()+l+1, premierLettre.getColumn());
								motBas.ajoutLettre(premB.lettre, premierLettre.getLine()+l+1, premierLettre.getColumn());
								l++;						

								if(premierLettre.getLine()+l+1==15) {
									break;
								}
							}
						}
						System.out.println("Mot bas trouvé : "+motBas);
						if(motBas.valideMot(this.dico) || motBas.nombreDeLettres()==1) {
							motbasOk++;
						}
						else {
							System.out.print(motBas.toString());
						}
						System.out.println("Mots droite trouvés : ");
						for(Placement elem : this.placementEnCours) {
							//mot droite
							int c=0;
							premierLettre = elem;
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
							System.out.println(motDroite);
							if(motDroite.valideMot(this.dico) || motDroite.nombreDeLettres()==1) {
								motdroiteOk++;
							}
							else {
								System.out.print(motDroite.toString());
							}
						}
						if(motbasOk==1 && motdroiteOk==this.placementEnCours.size()) {
							Test1=true;
							Test2=true;
						}
					}
					//lettre dans la meme ligne
					if(this.placementEnCours.get(0).getLine()==deuxiemLettre.getLine()) {
						colonne=false;
						System.out.println("Même ligne donc + mots bas et 1 mot droite");
						System.out.println("Mots bas trouvés : ");
						for(Placement elem : this.placementEnCours) {
							//mot bas
							int l=0;
							premierLettre = elem;
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

							if(premierLettre.getLine()<=14) {
								while(this.plateauFictif.getCase(premierLettre.getLine()+l+1,premierLettre.getColumn()).lettre != null) {
									premB = this.plateauFictif.getCase(premierLettre.getLine()+l+1, premierLettre.getColumn());
									motBas.ajoutLettre(premB.lettre, premierLettre.getLine()+l+1, premierLettre.getColumn());
									l++;

									if(premierLettre.getLine()+l+1==15) {
										break;
									}
								}
							}
							System.out.println(motBas);
							if(motBas.valideMot(this.dico) || motBas.nombreDeLettres()==1) {
								motbasOk++;
							}
							else {
								System.out.print(motBas.toString());
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
						boolean present=false;
						for(Placement elem: this.placementEnCours) {
							if(elem.getLine()==premierLettre.getLine() && elem.getColumn()==premierLettre.getColumn()+c) {
								present=true;
							}
						}
						if(present) {
							lettrecotecote++;
						}
						else {
							autreLettre++;
						}
						if(premierLettre.getColumn()<15) {
							while(this.plateauFictif.getCase(premierLettre.getLine(),premierLettre.getColumn()+c+1).lettre != null) {
								present=false;
								for(Placement elem: this.placementEnCours) {
									if(elem.getLine()==premierLettre.getLine() && elem.getColumn()==premierLettre.getColumn()+c+1) {
										present=true;
									}
								}
								if(present) {
									lettrecotecote++;
								}
								else {
									autreLettre++;
								}
								premD = this.plateauFictif.getCase(premierLettre.getLine(), premierLettre.getColumn()+c+1);
								motDroite.ajoutLettre(premD.lettre, premierLettre.getLine(), premierLettre.getColumn()+c+1);
								c++;
								if(premierLettre.getColumn()+c+1==15) {
									break;
								}
							}
						}
						System.out.println("Mot droite trouvée : "+motDroite);
						if(motDroite.valideMot(this.dico) || motDroite.nombreDeLettres()==1) {
							motdroiteOk++;
						}
						else {
							System.out.print(motDroite.toString());
						}

						if(motbasOk==this.placementEnCours.size() && motdroiteOk==1) {
							Test1=true;
							Test2=true;
						}
					}
				}
				if (this.Test1 && this.Test2 && lettrecotecote == this.placementEnCours.size() && premierTour==true && autreLettre!=0) {
					System.out.println("Plateau Valide");
					System.out.println(lettrecotecote+" "+autreLettre);
					this.calculerScore();
					this.changementJoueur();
				}
				else {
					System.out.println("Plateau Non Valide");
					if(this.Test1==false) {
						System.out.println("Mot Bas Non Valide");
					}
					if(this.Test2==false) {
						System.out.println("Mot Droite Non Valide");
					}
					if(autreLettre==0){
						System.out.println("Attention "+this.score[this.numChevalet].getPrenom()+" Vous devez toucher les lettres déjà placés sur le plateau");
					}
					if(lettrecotecote != this.placementEnCours.size()) {
						System.out.println(this.score[this.numChevalet].getPrenom()+" les lettres doivent être côte à côte !");
						System.out.println(lettrecotecote);
						System.out.println(this.placementEnCours.size());
					}
					if(premierTour==false) {
						System.out.println("Il faut commencer au milieu et posé plusieurs lettres");

					}
					for(Placement elem: this.placementEnCours) {
						this.chevalets.chevaletEnCours().remettreLettre(elem.getLetter());
						elem.getCase().lettre=null;
					}
					this.motbasOk=0;
					this.motdroiteOk=0;
					this.placementEnCours=new ArrayList<Placement>();
					this.setChanged();
					this.notifyObservers(this.chevalets);
					this.plateauFictif=this.plateau.clone();
					this.setChanged();
					this.notifyObservers(this.plateauFictif);
					System.out.println("Réessaye "+this.score[this.numChevalet].getPrenom());
				} 
			}
			else {
				System.out.println("Les lettres ne sont pas sur la même ligne\\colonne");
			}
		}
	}
	
	
	private void calculerScore() {

		Hashtable<String, Integer> motsVerticaux = new Hashtable<String, Integer>();
		Hashtable<String, Integer> motsHorizontaux = new Hashtable<String, Integer>();
		this.scoreAv=this.score[this.numChevalet].getScore();
		
		for (Placement placement : this.placementEnCours) {
			Lettre lettre = placement.getLetter();
			//On cherche le mot vertical
			int lig = placement.getLine()-1;
			int score = lettre.valeur;
			String mot = lettre.lettre;			
			int multiplicateur = 1;
			
			while (lig >= 0 && this.plateauFictif.getCase(lig, placement.getColumn()).lettre != null) {
				
				mot = this.plateauFictif.getCase(lig, placement.getColumn()).lettre.lettre + mot;
				boolean lettreJoueur = false;
				
				for (Placement placement2 : this.placementEnCours) {
					if (placement2.getLetter().equals(this.plateauFictif.getCase(lig, placement.getColumn()).lettre)) {
						lettreJoueur = true;
						Multiplicateur m = this.plateauFictif.getCase(lig, placement.getColumn()).multiplicateur;
						switch(m.toString()) {				
							case "LD":
								score+=this.plateauFictif.getCase(lig, placement.getColumn()).lettre.valeur*2;
								break;
							case "LT":
								score+=this.plateauFictif.getCase(lig, placement.getColumn()).lettre.valeur*3;
								break;
							case "MD": multiplicateur*=2;
								break;
							case "MT": multiplicateur*=3;
								break;
							default:
								score += this.plateauFictif.getCase(lig, placement.getColumn()).lettre.valeur;
								break;
						}
					}
				}
				
				if(!lettreJoueur) {
					score += this.plateauFictif.getCase(lig, placement.getColumn()).lettre.valeur;
				}
				
				lig--;
			}
			
			lig = placement.getLine()+1;
			
			while (lig < 15 && this.plateauFictif.getCase(lig, placement.getColumn()).lettre != null) {
				
				mot = mot +this.plateauFictif.getCase(lig, placement.getColumn()).lettre.lettre;
				boolean lettreJoueur = false;
				
				for (Placement placement2 : this.placementEnCours) {
					if (placement2.getLetter().equals(this.plateauFictif.getCase(lig, placement.getColumn()).lettre)) {
						lettreJoueur = true;
						Multiplicateur m = this.plateauFictif.getCase(lig, placement.getColumn()).multiplicateur;
						switch(m.toString()) {				
							case "LD":
								score+=this.plateauFictif.getCase(lig, placement.getColumn()).lettre.valeur*2;
								break;
							case "LT":
								score+=this.plateauFictif.getCase(lig, placement.getColumn()).lettre.valeur*3;
								break;
							case "MD": multiplicateur*=2;
								break;
							case "MT": multiplicateur*=3;
								break;
							default:
								score += this.plateauFictif.getCase(lig, placement.getColumn()).lettre.valeur;
								break;
						}
					}
				}
				
				if(!lettreJoueur) {
					score += this.plateauFictif.getCase(lig, placement.getColumn()).lettre.valeur;
				}
				
				lig++;
			}
			score*=multiplicateur;
			
			if (mot.length() > 1 && !motsVerticaux.containsKey(mot)) {
				motsVerticaux.put(mot, score);
				this.setChanged();
				this.notifyObservers(mot);
			}
			
			//On cherche le mot horizontal
			int col = placement.getColumn()-1;
			score = lettre.valeur;
			mot = lettre.lettre;
			multiplicateur = 1;
			
			while (col >= 0 && this.plateauFictif.getCase(placement.getLine(), col).lettre != null) {
				
				mot = this.plateauFictif.getCase(placement.getLine(),col).lettre.lettre + mot;
				boolean lettreJoueur = false;
				
				for (Placement placement2 : this.placementEnCours) {
					if (placement2.getLetter().equals(this.plateauFictif.getCase(placement.getLine(), col).lettre)) {
						lettreJoueur = true;
						Multiplicateur m = this.plateauFictif.getCase(placement.getLine(), col).multiplicateur;
						switch(m.toString()) {				
							case "LD":
								score +=this.plateauFictif.getCase(placement.getLine(), col).lettre.valeur*2;
								break;
							case "LT":
								score +=this.plateauFictif.getCase(placement.getLine(), col).lettre.valeur*3;
								break;
							case "MD": multiplicateur*=2;
								break;
							case "MT": multiplicateur*=3;
								break;
							default:
								score += this.plateauFictif.getCase(placement.getLine(), col).lettre.valeur;
								break;
						}
					}
				}
				
				if(!lettreJoueur) {
					score += this.plateauFictif.getCase(placement.getLine(), col).lettre.valeur;
				}
				col--;
			}
			
			col = placement.getColumn()+1;
			
			while (col < 15 && this.plateauFictif.getCase(placement.getLine(), col).lettre != null) {
				
				mot = mot + this.plateauFictif.getCase(placement.getLine(),col).lettre.lettre;
				boolean lettreJoueur = false;
				
				for (Placement placement2 : this.placementEnCours) {
					if (placement2.getLetter().equals(this.plateauFictif.getCase(placement.getLine(), col).lettre)) {
						lettreJoueur = true;
						Multiplicateur m = this.plateauFictif.getCase(placement.getLine(), col).multiplicateur;
						switch(m.toString()) {				
							case "LD":
								score += this.plateauFictif.getCase(placement.getLine(), col).lettre.valeur*2;
								break;
							case "LT":
								score += this.plateauFictif.getCase(placement.getLine(), col).lettre.valeur*3;
								break;
							case "MD": multiplicateur*=2;
								break;
							case "MT": multiplicateur*=3;
								break;
							default:
								score += this.plateauFictif.getCase(placement.getLine(), col).lettre.valeur;
								break;
						}
					}
				}
				
				if(!lettreJoueur) {
					score += this.plateauFictif.getCase(placement.getLine(), col).lettre.valeur;
				}
				col++;
			}

			score*=multiplicateur;
			
			if (mot.length() > 1 && !motsHorizontaux.containsKey(mot)) {
				motsHorizontaux.put(mot, score);
				this.setChanged();
				this.notifyObservers(mot);
			}
			
		}

		for (Integer score : motsHorizontaux.values()) {
			this.score[this.numChevalet].majScore(score);
		}
		for (Integer score : motsVerticaux.values()) {
			this.score[this.numChevalet].majScore(score);
		}
	}

	/*met a jour les changements de Joueur */
	public void changementJoueur() {
		if(this.chevalets.chevaletEnCours().size()==7) {
			this.passe=passe+1;
			System.out.print(this.score[this.numChevalet].getPrenom()+" a passé son tour.. \n");
		}
		else {
			if(this.chevalets.chevaletEnCours().size()==6) {
				System.out.println(this.score[this.numChevalet].getPrenom()+" a placé la lettre "+this.placementEnCours.get(0).getLetter().lettre);
			}
			else {
				//Console
				if(colonne) {
					System.out.print(this.score[this.numChevalet].getPrenom()+" vient de jouer "+this.motBas+"\n");
				}
				else {
					System.out.print(this.score[this.numChevalet].getPrenom()+" vient de jouer "+this.motDroite+"\n");
				}
			}
			this.chevalets.chevaletEnCours().remplir(sac);
			this.passe=0;
			System.out.print("Son score augmente de "+(this.score[numChevalet].getScore()-this.scoreAv)+" points ! \n");
		}
		if(this.passe==this.chevalets.size()) {
			Menu m = new Menu();
			m.vueFinale(score);
		}
		else {
			if (this.numChevalet+1 == this.chevalets.size()) {
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
			this.notifyObservers(this.chevalets);
			this.setChanged();
			this.notifyObservers(this.score);
			this.setChanged();
			this.notifyObservers(this.score[this.numChevalet]);
			// On initialise à zéro le placement
			this.placementEnCours = new ArrayList<Placement>();
			
			//
			System.out.print("C'est au tour de "+this.score[this.numChevalet].getPrenom()+"\n");
			}
		}

	public void lettreJoker(String lettre) {
		lettreChoisi=lettre;
		this.setChanged();
		this.notifyObservers("afficher");
	}
	
	//Serialisation
	
	public void charger() throws IOException, ClassNotFoundException {
		//Sac
		FileInputStream fis = new FileInputStream(new File("Sac.dat"));
		ObjectInputStream ois = new ObjectInputStream(fis);
		this.sac = (Sac)ois.readObject();
		ois.close();
		fis.close();
		//numChevalet
		FileInputStream fis1 = new FileInputStream(new File("JoueurEnCours.dat"));
		ObjectInputStream ois1 = new ObjectInputStream(fis1);
		this.numChevalet = (int)ois1.readObject();
		ois1.close();
		fis1.close();
		//Score
		FileInputStream fis2 = new FileInputStream(new File("Score.dat"));
		ObjectInputStream ois2 = new ObjectInputStream(fis2);
		this.score = (Score[])ois2.readObject();
		ois2.close();
		fis2.close();
		//Score
		FileInputStream fis3 = new FileInputStream(new File("Chevalet.dat"));
		ObjectInputStream ois3 = new ObjectInputStream(fis3);
		this.chevalets= (SetDeChevalets)ois3.readObject();
		ois3.close();
		fis3.close();
		//Langue
		FileInputStream fis4 = new FileInputStream(new File("Langue.dat"));
		ObjectInputStream ois4 = new ObjectInputStream(fis4);
		this.langue= (String)ois4.readObject();
		ois4.close();
		fis4.close();
		//Plateau
		FileInputStream fis5 = new FileInputStream(new File("Plateau.dat"));
		ObjectInputStream ois5 = new ObjectInputStream(fis5);
		this.plateau= (Plateau)ois5.readObject();
		ois5.close();
		fis5.close();
		//PremierTour
		FileInputStream fis6 = new FileInputStream(new File("PremierTour.dat"));
		ObjectInputStream ois6 = new ObjectInputStream(fis6);
		this.premierTour= (boolean)ois6.readObject();
		ois6.close();
		fis6.close();
	}
	
	public void enregistrer(){
		try {
			FileOutputStream fos =  new FileOutputStream(new File("Sac.dat"));
			ObjectOutputStream oos= new ObjectOutputStream(fos);
			oos.writeObject(this.sac);
			oos.close();
			fos.close();
		} catch (IOException e) {
			throw new RuntimeException("Impossible d'écrire les données du sac");
		}
		try {
			FileOutputStream fos =  new FileOutputStream(new File("JoueurEnCours.dat"));
			ObjectOutputStream oos= new ObjectOutputStream(fos);
			oos.writeObject(this.numChevalet);
			oos.close();
			fos.close();
		} catch (IOException e) {
			throw new RuntimeException("Impossible d'écrire les données du numero de joueur en cours");
		}
		try {
			FileOutputStream fos =  new FileOutputStream(new File("Score.dat"));
			ObjectOutputStream oos= new ObjectOutputStream(fos);
			oos.writeObject(this.score);
			oos.close();
			fos.close();
		} catch (IOException e) {
			throw new RuntimeException("Impossible d'écrire les données du score");
		}
		try {
			FileOutputStream fos =  new FileOutputStream(new File("Chevalet.dat"));
			ObjectOutputStream oos= new ObjectOutputStream(fos);
			oos.writeObject(this.chevalets);
			oos.close();
			fos.close();
		} catch (IOException e) {
			throw new RuntimeException("Impossible d'écrire les données du chevalet");
		}
		try {
			FileOutputStream fos =  new FileOutputStream(new File("Langue.dat"));
			ObjectOutputStream oos= new ObjectOutputStream(fos);
			oos.writeObject(this.langue);
			oos.close();
			fos.close();
		} catch (IOException e) {
			throw new RuntimeException("Impossible d'écrire les données de la langue");
		}
		try {
			FileOutputStream fos =  new FileOutputStream(new File("Plateau.dat"));
			ObjectOutputStream oos= new ObjectOutputStream(fos);
			oos.writeObject(this.plateau);
			oos.close();
			fos.close();
		} catch (IOException e) {
			throw new RuntimeException("Impossible d'écrire les données du plateau");
		}
		try {
			FileOutputStream fos =  new FileOutputStream(new File("PremierTour.dat"));
			ObjectOutputStream oos= new ObjectOutputStream(fos);
			oos.writeObject(this.premierTour);
			oos.close();
			fos.close();
		} catch (IOException e) {
			throw new RuntimeException("Impossible d'écrire les données du premTour");
		}
		System.out.println("La partie a été sauvegardé");
	}
}
