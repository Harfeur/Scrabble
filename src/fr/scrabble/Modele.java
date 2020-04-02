package fr.scrabble;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Observable;

import fr.scrabble.structures.*;
import fr.scrabble.structures.Case.Multiplicateur;
import fr.scrabble.vues.VueJoker;

public class Modele extends Observable{

	Sac sac;
	File fichier;
	Plateau plateau, plateauFictif;
	Chevalet[] chevalets;
	Integer numChevalet;
	Score[] score;
	ArrayList<MotPlace> motValide;
	MotPlace motBas, motDroite;
	ArrayList<Placement> placementEnCours;
	Dictionnaire dico;
	String lettreChoisi;
	int motbasOk, motdroiteOk, passe=0;
	boolean Test1, Test2, premierTour;

	public Modele() {
		super();
	}

	/*Met le jeu a zero en fonction de nb de joueur*/
	public void nouvellePartie(int nbJoueur, String langue) {
		this.sac = new Sac(langue);
		this.dico = new Dictionnaire(langue);
		this.plateau= new Plateau();
		this.plateauFictif= new Plateau();
		this.placementEnCours = new ArrayList<Placement>();
		
		this.premierTour=false;
		
		this.chevalets = new Chevalet[nbJoueur];
		this.score = new Score[nbJoueur];

		for (int i=0; i<nbJoueur; i++) {
			this.chevalets[i]=new Chevalet();
			this.chevalets[i].remplir(this.sac);
			this.score[i]= new Score();
		}
		this.numChevalet=0;
		// Après avoir cree les elements, on notifie les deux vues

		this.setChanged();
		this.notifyObservers(this.chevalets[numChevalet]);
		
		this.setChanged();
		this.notifyObservers(this.score[numChevalet]);

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
			if (this.chevalets[this.numChevalet].get(num).valeur == 0) {
				new VueJoker("FR", this);
				this.setChanged();
				this.notifyObservers("cacher");
			}
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
				lettre.lettre=lettreChoisi;
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
		Test1=false; //Mot bas
		Test2=false; //Mot droite
		int lig=0;
		int col =0;
		int lettrecotecote=1;
		Placement premierLettre = this.placementEnCours.get(0);
		for (Placement elem : this.placementEnCours) {
			lig=lig+elem.getLine();
			col=col+elem.getColumn();
			if(this.premierTour==false && elem.getLine()==7 && elem.getColumn()==7) {
				premierTour=true; // A changer que à la fin du code
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
//					int score_MD=0,score_MT=0;
					while(this.plateauFictif.getCase(premierLettre.getLine()+l+1,premierLettre.getColumn()).lettre != null) {
						premB = this.plateauFictif.getCase(premierLettre.getLine()+l+1, premierLettre.getColumn());
						motBas.ajoutLettre(premB.lettre, premierLettre.getLine()+l+1, premierLettre.getColumn());
						l++;	
/*											
						//score	
						Multiplicateur m = premB.multiplicateur;
						switch(m.toString()) {
							case "S": this.score[this.numChevalet].majScore(premB.lettre.valeur);break;
										
							case "LD": this.score[this.numChevalet].majScore(premB.lettre.valeur*2);break;
										
							case "LT": this.score[this.numChevalet].majScore(premB.lettre.valeur*3);break;
										
							case "MD": score_MD++;break;
										
							case "MT": score_MT++;break;
						} 
						*/
						if(premierLettre.getLine()+l+1==15) {
							break;
						}
					}/*
					if(score_MD>0 || score_MT>0) {
						int score_motBas=0;
						for(int i=0;i<motBas.mot.size();i=i+1) { 
							score_motBas=score_motBas+motBas.mot.get(i).valeur;
						}
						if(score_MD>0) {
							this.score[this.numChevalet].majScore(score_motBas*2);
						}
						if(score_MT>0) {						
							this.score[this.numChevalet].majScore(score_motBas*3);
						}
					}*/
				}

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
//					int score_MD=0,score_MT=0;
					while(this.plateauFictif.getCase(premierLettre.getLine(),premierLettre.getColumn()+c+1).lettre != null) {
						premD = this.plateauFictif.getCase(premierLettre.getLine(), premierLettre.getColumn()+c+1);
						motDroite.ajoutLettre(premD.lettre, premierLettre.getLine(), premierLettre.getColumn()+c+1);
						c++;
/*						
						//score
						Multiplicateur m = premD.multiplicateur;
						switch(m.toString()) {
							case "S": this.score[this.numChevalet].majScore(premD.lettre.valeur);break;
										
							case "LD": this.score[this.numChevalet].majScore(premD.lettre.valeur*2);break;
										
							case "LT": this.score[this.numChevalet].majScore(premD.lettre.valeur*3);break;
										
							case "MD": score_MD++;break;
										
							case "MT": score_MT++;break;
						}*/ 
						
						if(premierLettre.getColumn()+c+1==15) {
							break;
						}
					}/*
					if(score_MD>0 || score_MT>0) {
						int score_motDroite=0;
						for(int i=0;i<motDroite.mot.size();i=i+1) { 
							score_motDroite=score_motDroite+motDroite.mot.get(i).valeur;
						}
						if(score_MD>0) {
							this.score[this.numChevalet].majScore(score_motDroite*2);
						}
						if(score_MT>0) {						
							this.score[this.numChevalet].majScore(score_motDroite*3);
						}
					}*/
				}
				
				if(motDroite.valideMot(this.dico) || motDroite.nombreDeLettres()==1) {
					motdroiteOk++;
				}
				else {
					System.out.print(motDroite.toString());
				}

				if(motbasOk==1 && motdroiteOk==1) {
					Test1=true;
					Test2=true;
				}
				else {
					this.score[this.numChevalet].faux();
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
//						int score_MD=0, score_MT=0;
						while(this.plateauFictif.getCase(premierLettre.getLine()+l+1,premierLettre.getColumn()).lettre != null) {
							for(Placement elem: this.placementEnCours) {
								if(elem.getCase()==premB) {
									lettrecotecote++;
								}
							}
							premB = this.plateauFictif.getCase(premierLettre.getLine()+l+1, premierLettre.getColumn());
							motBas.ajoutLettre(premB.lettre, premierLettre.getLine()+l+1, premierLettre.getColumn());
							l++;
/*							
							//score
							Multiplicateur m = premB.multiplicateur;
							switch(m.toString()) {
								case "S": this.score[this.numChevalet].majScore(premB.lettre.valeur);break;
											
								case "LD": this.score[this.numChevalet].majScore(premB.lettre.valeur*2);break;
											
								case "LT": this.score[this.numChevalet].majScore(premB.lettre.valeur*3);break;
											
								case "MD": score_MD++;break;
											
								case "MT": score_MT++;break;
							} 
							
							if(premierLettre.getLine()+l+1==15) {
								break;
							}*/
						}/*
						if(score_MD>0 || score_MT>0) {
							int score_motBas=0;
							for(int i=0;i<motBas.mot.size();i=i+1) { 
								score_motBas=score_motBas+motBas.mot.get(i).valeur;
							}
							if(score_MD>0) {
								this.score[this.numChevalet].majScore(score_motBas*2);
							}
							if(score_MT>0) {						
								this.score[this.numChevalet].majScore(score_motBas*3);
							}
						}*/
					}
					
					if(motBas.valideMot(this.dico) || motBas.nombreDeLettres()==1) {
						motbasOk++;
					}
					else {
						System.out.print(motBas.toString());
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
//							int score_MD=0, score_MT=0;
							while(this.plateauFictif.getCase(premierLettre.getLine(),premierLettre.getColumn()+c+1).lettre != null) {
								premD = this.plateauFictif.getCase(premierLettre.getLine(), premierLettre.getColumn()+c+1);
								motDroite.ajoutLettre(premD.lettre, premierLettre.getLine(), premierLettre.getColumn()+c+1);
								c++;
/*								
								//score
								Multiplicateur m = premD.multiplicateur;
								switch(m.toString()) {
									case "S": this.score[this.numChevalet].majScore(premD.lettre.valeur);break;
												
									case "LD": this.score[this.numChevalet].majScore(premD.lettre.valeur*2);break;
												
									case "LT": this.score[this.numChevalet].majScore(premD.lettre.valeur*3);break;
												
									case "MD": score_MD++;break;
												
									case "MT": score_MT++;break;
								} */
								
								if(premierLettre.getColumn()+c+1==15) {
									break;
								}
							}/*
							if(score_MD>0 || score_MT>0) {
								int score_motDroite=0;
								for(int i=0;i<motDroite.mot.size();i=i+1) { 
									score_motDroite=score_motDroite+motDroite.mot.get(i).valeur;
								}
								if(score_MD>0) {
									this.score[this.numChevalet].majScore(score_motDroite*2);
								}
								if(score_MT>0) {						
									this.score[this.numChevalet].majScore(score_motDroite*3);
								}
							}*/
						}
						
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
					else {
						this.score[this.numChevalet].faux();
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
//							int score_MD=0, score_MT=0;
							while(this.plateauFictif.getCase(premierLettre.getLine()+l+1,premierLettre.getColumn()).lettre != null) {
								premB = this.plateauFictif.getCase(premierLettre.getLine()+l+1, premierLettre.getColumn());
								motBas.ajoutLettre(premB.lettre, premierLettre.getLine()+l+1, premierLettre.getColumn());
								l++;
/*
								//score
								Multiplicateur m = premB.multiplicateur;
								switch(m.toString()) {
									case "S": this.score[this.numChevalet].majScore(premB.lettre.valeur);break;
												
									case "LD": this.score[this.numChevalet].majScore(premB.lettre.valeur*2);break;
												
									case "LT": this.score[this.numChevalet].majScore(premB.lettre.valeur*3);break;
												
									case "MD": score_MD++;break;
												
									case "MT": score_MT++;break;
								} */
								
								if(premierLettre.getLine()+l+1==15) {
									break;
								}
							}/*
							if(score_MD>0 || score_MT>0) {
								int score_motBas=0;
								for(int i=0;i<motBas.mot.size();i=i+1) { 
									score_motBas=score_motBas+motBas.mot.get(i).valeur;
								}
								if(score_MD>0) {
									this.score[this.numChevalet].majScore(score_motBas*2);
								}
								if(score_MT>0) {						
									this.score[this.numChevalet].majScore(score_motBas*3);
								}
							}*/
						}

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

					if(premierLettre.getColumn()<14) {
//						int score_MD=0, score_MT=0;
						while(this.plateauFictif.getCase(premierLettre.getLine(),premierLettre.getColumn()+c+1).lettre != null) {
							for(Placement elem: this.placementEnCours) {
								if(elem.getCase()==premD) {
									lettrecotecote++;
								}
							}
							premD = this.plateauFictif.getCase(premierLettre.getLine(), premierLettre.getColumn()+c+1);
							motDroite.ajoutLettre(premD.lettre, premierLettre.getLine(), premierLettre.getColumn()+c+1);
							c++;
/*							
							//score
							Multiplicateur m = premD.multiplicateur;
							switch(m.toString()) {
								case "S": this.score[this.numChevalet].majScore(premD.lettre.valeur);break;
											
								case "LD": this.score[this.numChevalet].majScore(premD.lettre.valeur*2);break;
											
								case "LT": this.score[this.numChevalet].majScore(premD.lettre.valeur*3);break;
											
								case "MD": score_MD++;break;
											
								case "MT": score_MT++;break;
							} */
							
							if(premierLettre.getColumn()+c+1==15) {
								break;
							}
						}/*
						if(score_MD>0 || score_MT>0) {
							int score_motDroite=0;
							for(int i=0;i<motDroite.mot.size();i=i+1) { 
								score_motDroite=score_motDroite+motDroite.mot.get(i).valeur;
							}
							if(score_MD>0) {
								this.score[this.numChevalet].majScore(score_motDroite*2);
							}
							if(score_MT>0) {						
								this.score[this.numChevalet].majScore(score_motDroite*3);
							}
						}*/
					}
					
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
					else {
						this.score[this.numChevalet].faux();
					}
				}
			}
			if (this.Test1 && this.Test2 && lettrecotecote == this.placementEnCours.size() && premierTour==true) {
				System.out.println("Plateau Valide");
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
				if(lettrecotecote < this.placementEnCours.size()) {
					System.out.println("lettre pas cote cote");
					System.out.println(lettrecotecote);
					
				}
				if(premierTour==false) {
					System.out.println("commence au milieu");
					
				}
				for(Placement elem: this.placementEnCours) {
					this.chevalets[this.numChevalet].remettreLettre(elem.getLetter());
					elem.getCase().lettre=null;
				}
				this.placementEnCours=new ArrayList<Placement>();
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
	
	
	private void calculerScore() {

		Hashtable<String, Integer> motsVerticaux = new Hashtable<String, Integer>();
		Hashtable<String, Integer> motsHorizontaux = new Hashtable<String, Integer>();
		
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
			
			while (lig <= 15 && this.plateauFictif.getCase(lig, placement.getColumn()).lettre != null) {
				
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
			
			if (mot.length() > 1 && !motsVerticaux.containsKey(mot))
				motsVerticaux.put(mot, score);
			
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
			while (col <= 15 && this.plateauFictif.getCase(placement.getLine(), col).lettre != null) {
				
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
			
			if (mot.length() > 1 && !motsHorizontaux.containsKey(mot))
				motsHorizontaux.put(mot, score);
			
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
		System.out.println("Joueur : "+this.numChevalet+" ---------- Score : "+this.score[this.numChevalet].getScore());
		if(this.chevalets[this.numChevalet].size()==7) {
			this.passe=passe+1;
		}
		else {
			this.chevalets[this.numChevalet].remplir(sac);
			this.passe=0;
		}
		if(this.passe==this.chevalets.length) {
			System.out.println("JEU TERMINE");
		}
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
		this.setChanged();
		this.notifyObservers(this.score[numChevalet]);
		// On initialise à zéro le placement
		this.placementEnCours = new ArrayList<Placement>();
	}

	public void lettreJoker(String lettre) {
		// TODO Auto-generated method stub
		lettreChoisi=lettre;
		this.setChanged();
		this.notifyObservers("afficher");
	}
}
