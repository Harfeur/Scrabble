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
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

import org.json.JSONArray;
import org.json.JSONObject;

import fr.scrabble.game.vues.VueJoker;
import fr.scrabble.menu.Menu;
import fr.scrabble.menu.Menu.Vues;
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
	int motbasOk, motdroiteOk, passe=0, difficulte;
	boolean Test1, Test2, premierTour, colonne;
	public Menu menu;
	
	public Modele(Menu menu) {
		super();
		this.menu=menu;
	}

	/*Met le jeu a zero en fonction de nb de joueur*/
	public void nouvellePartie(int nbJoueur, String langue, ArrayList<String> prenoms, int difficulte) {

		this.sac = new Sac(langue);
		this.numChevalet=0;
		this.dico = new Dictionnaire(langue);
		this.plateau= new Plateau();
		this.plateauFictif= new Plateau();
		this.placementEnCours = new ArrayList<Placement>();
		this.langue = langue;

		this.premierTour=false;
		this.difficulte = difficulte;

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
			this.setChanged();
			this.notifyObservers("echec");
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
				this.notifyObservers(Vues.MASQUER);
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
		if(this.placementEnCours.size()==0) {
			return;
		}
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
					else {
						ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
						this.setChanged();
						this.notifyObservers(String.format(strings.getString("pas_valide"), motBas.toString())+"\n");
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
					else {
						ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
						this.setChanged();
						this.notifyObservers(String.format(strings.getString("pas_valide"), motDroite.toString())+"\n");
					}

					if(motbasOk==1 && motdroiteOk==1 && (motBas.nombreDeLettres()!=1 || motDroite.nombreDeLettres()!=1)) {
						Test1=true;
						Test2=true;
						autreLettre=2;
					}
				}	
				//plusieurs lettres
				else {
					Placement deuxiemLettre = this.placementEnCours.get(1);
					// Si les lettres sont dans la meme colonne
					if(premierLettre.getColumn()==deuxiemLettre.getColumn()) {
						colonne=true;
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
						if(motBas.valideMot(this.dico) || motBas.nombreDeLettres()==1) {
							motbasOk++;
						}
						else {
							ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
							this.setChanged();
							this.notifyObservers(String.format(strings.getString("pas_valide"), motBas.toString())+"\n");
						}

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
							present=false;
							for(Placement tt: this.placementEnCours) {
								if(tt.getLine()==premierLettre.getLine()+l && tt.getColumn()==premierLettre.getColumn()) {
									present=true;
								}
							}
							if(present) {
							}
							else {
								autreLettre++;
							}
							if(premierLettre.getColumn()<14) {
								while(this.plateauFictif.getCase(premierLettre.getLine(),premierLettre.getColumn()+c+1).lettre != null) {
									present=false;
									for(Placement tt: this.placementEnCours) {
										if(tt.getLine()==premierLettre.getLine()+l && tt.getColumn()==premierLettre.getColumn()) {
											present=true;
										}
									}
									if(present) {
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

							if(motDroite.valideMot(this.dico) || motDroite.nombreDeLettres()==1) {
								motdroiteOk++;
							}
							else {
								ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
								this.setChanged();
								this.notifyObservers(String.format(strings.getString("pas_valide"), motDroite.toString())+"\n");}
						}
						if(motbasOk==1 && motdroiteOk==this.placementEnCours.size()) {
							Test1=true;
							Test2=true;
						}
					}
					//lettre dans la meme ligne
					if(this.placementEnCours.get(0).getLine()==deuxiemLettre.getLine()) {
						colonne=false;
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
							boolean present=false;
							for(Placement tt: this.placementEnCours) {
								if(tt.getLine()==premierLettre.getLine()+l && tt.getColumn()==premierLettre.getColumn()) {
									present=true;
								}
							}
							if(present) {
							}
							else {
								autreLettre++;
							}
							if(premierLettre.getLine()<14) {
								while(this.plateauFictif.getCase(premierLettre.getLine()+l+1,premierLettre.getColumn()).lettre != null) {
									present=false;
									for(Placement tt: this.placementEnCours) {
										if(tt.getLine()==premierLettre.getLine()+l+1 && tt.getColumn()==premierLettre.getColumn()) {
											present=true;
										}
									}
									if(present) {
									}
									else {
										autreLettre++;
									}
									premB = this.plateauFictif.getCase(premierLettre.getLine()+l+1, premierLettre.getColumn());
									motBas.ajoutLettre(premB.lettre, premierLettre.getLine()+l+1, premierLettre.getColumn());
									l++;

									if(premierLettre.getLine()+l+1==15) {
										System.out.println("stop");
										break;
									}
								}
							}
							if(motBas.valideMot(this.dico) || motBas.nombreDeLettres()==1) {
								motbasOk++;
							}
							else {
								ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
								this.setChanged();
								this.notifyObservers(String.format(strings.getString("pas_valide"), motBas.toString())+"\n");}
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
						if(motDroite.valideMot(this.dico) || motDroite.nombreDeLettres()==1) {
							motdroiteOk++;
						}
						else {
							ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
							this.setChanged();
							this.notifyObservers(String.format(strings.getString("pas_valide"), motDroite.toString())+"\n");}

						if(motbasOk==this.placementEnCours.size() && motdroiteOk==1) {
							Test1=true;
							Test2=true;
						}
					}
				}
				if (this.Test1 && this.Test2 && lettrecotecote == this.placementEnCours.size() && premierTour==true && autreLettre!=0) {
					this.calculerScore();
					this.changementJoueur();
				}
				else {
					if(autreLettre==0){
						ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
						this.setChanged();
						this.notifyObservers(String.format(strings.getString("toucher"), this.score[this.numChevalet].getPrenom())+"\n");
					}
					if(lettrecotecote != this.placementEnCours.size()) {
						ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
						this.setChanged();
						this.notifyObservers(String.format(strings.getString("cote_cote"), this.score[this.numChevalet].getPrenom())+"\n");	
					}
					if(premierTour==false) {
						ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
						this.setChanged();
						this.notifyObservers(strings.getString("milieu")+"\n");
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
					ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
					this.setChanged();
					this.notifyObservers(String.format(strings.getString("reessaye"), this.score[this.numChevalet].getPrenom())+"\n");
				} 
			}
			else {
				ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
				this.setChanged();
				this.notifyObservers(strings.getString("memes")+"\n");
			}
		}
	}


	private void calculerScore() {

		Hashtable<String, Integer> motsVerticaux = new Hashtable<String, Integer>();
		Hashtable<String, Integer> motsHorizontaux = new Hashtable<String, Integer>();
		this.scoreAv=this.score[this.numChevalet].getScore();

		for (Placement placement : this.placementEnCours) {
			
			//On cherche le mot vertical
			int lig = placement.getLine();
			int score = 0;
			String mot = "";
			int multiplicateur = 1;

			while (lig >= 0 && this.plateauFictif.getCase(lig, placement.getColumn()).lettre != null) {

				mot = this.plateauFictif.getCase(lig, placement.getColumn()).lettre.lettre + mot;

				if (this.plateau.getCase(lig, placement.getColumn()).lettre == null) {
					Multiplicateur m = this.plateauFictif.getCase(lig, placement.getColumn()).multiplicateur;
					switch(m) {				
					case LETTRE_DOUBLE:
						score+=this.plateauFictif.getCase(lig, placement.getColumn()).lettre.valeur*2;
						break;
					case LETTRE_TRIPLE:
						score+=this.plateauFictif.getCase(lig, placement.getColumn()).lettre.valeur*3;
						break;
					case MOT_DOUBLE:
						score+=this.plateauFictif.getCase(lig, placement.getColumn()).lettre.valeur;
						multiplicateur*=2;
						break;
					case MOT_TRIPLE:
						score+=this.plateauFictif.getCase(lig, placement.getColumn()).lettre.valeur;
						multiplicateur*=3;
						break;
					default:
						score += this.plateauFictif.getCase(lig, placement.getColumn()).lettre.valeur;
						break;
					}
				} else {
					score += this.plateauFictif.getCase(lig, placement.getColumn()).lettre.valeur;
				}

				lig--;
			}

			lig = placement.getLine()+1;

			while (lig < 15 && this.plateauFictif.getCase(lig, placement.getColumn()).lettre != null) {

				mot = mot +this.plateauFictif.getCase(lig, placement.getColumn()).lettre.lettre;

				if (this.plateau.getCase(lig, placement.getColumn()).lettre == null) {
					Multiplicateur m = this.plateauFictif.getCase(lig, placement.getColumn()).multiplicateur;
					switch(m) {				
					case LETTRE_DOUBLE:
						score+=this.plateauFictif.getCase(lig, placement.getColumn()).lettre.valeur*2;
						break;
					case LETTRE_TRIPLE:
						score+=this.plateauFictif.getCase(lig, placement.getColumn()).lettre.valeur*3;
						break;
					case MOT_DOUBLE:
						score+=this.plateauFictif.getCase(lig, placement.getColumn()).lettre.valeur;
						multiplicateur*=2;
						break;
					case MOT_TRIPLE:
						score+=this.plateauFictif.getCase(lig, placement.getColumn()).lettre.valeur;
						multiplicateur*=3;
						break;
					default:
						score += this.plateauFictif.getCase(lig, placement.getColumn()).lettre.valeur;
						break;
					}
				} else {
					score += this.plateauFictif.getCase(lig, placement.getColumn()).lettre.valeur;
				}

				lig++;
			}

			score*=multiplicateur;

			if (mot.length() > 1 && !motsVerticaux.containsKey(mot)) {
				motsVerticaux.put(mot, score);
				ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
				this.setChanged();
				this.notifyObservers(String.format(strings.getString("joue"), this.score[this.numChevalet].getPrenom(),mot)+"\n");
			}

			//On cherche le mot horizontal
			int col = placement.getColumn();
			score = 0;
			mot = "";
			multiplicateur = 1;

			while (col >= 0 && this.plateauFictif.getCase(placement.getLine(), col).lettre != null) {

				mot = this.plateauFictif.getCase(placement.getLine(),col).lettre.lettre + mot;

				if (this.plateau.getCase(placement.getLine(), col).lettre == null) {
					Multiplicateur m = this.plateauFictif.getCase(placement.getLine(), col).multiplicateur;
					switch(m) {				
					case LETTRE_DOUBLE:
						score +=this.plateauFictif.getCase(placement.getLine(), col).lettre.valeur*2;
						break;
					case LETTRE_TRIPLE:
						score +=this.plateauFictif.getCase(placement.getLine(), col).lettre.valeur*3;
						break;
					case MOT_DOUBLE:
						score += this.plateauFictif.getCase(placement.getLine(), col).lettre.valeur;
						multiplicateur*=2;
						break;
					case MOT_TRIPLE:
						score += this.plateauFictif.getCase(placement.getLine(), col).lettre.valeur;
						multiplicateur*=3;
						break;
					default:
						score += this.plateauFictif.getCase(placement.getLine(), col).lettre.valeur;
						break;
					}
				} else {
					score += this.plateauFictif.getCase(placement.getLine(), col).lettre.valeur;
				}
				col--;
			}

			col = placement.getColumn()+1;

			while (col < 15 && this.plateauFictif.getCase(placement.getLine(), col).lettre != null) {

				mot = mot + this.plateauFictif.getCase(placement.getLine(),col).lettre.lettre;

				if (this.plateau.getCase(placement.getLine(), col).lettre == null) {
					Multiplicateur m = this.plateauFictif.getCase(placement.getLine(), col).multiplicateur;
					switch(m) {				
					case LETTRE_DOUBLE:
						score += this.plateauFictif.getCase(placement.getLine(), col).lettre.valeur*2;
						break;
					case LETTRE_TRIPLE:
						score += this.plateauFictif.getCase(placement.getLine(), col).lettre.valeur*3;
						break;
					case MOT_DOUBLE:
						score += this.plateauFictif.getCase(placement.getLine(), col).lettre.valeur;
						multiplicateur*=2;
						break;
					case MOT_TRIPLE:
						score += this.plateauFictif.getCase(placement.getLine(), col).lettre.valeur;
						multiplicateur*=3;
						break;
					default:
						score += this.plateauFictif.getCase(placement.getLine(), col).lettre.valeur;
						break;
					}
				} else {
					score += this.plateauFictif.getCase(placement.getLine(), col).lettre.valeur;
				}
				col++;
			}

			score*=multiplicateur;

			if (mot.length() > 1 && !motsHorizontaux.containsKey(mot)) {
				motsHorizontaux.put(mot, score);
				ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
				this.setChanged();
				this.notifyObservers(String.format(strings.getString("joue"), this.score[this.numChevalet].getPrenom(),mot)+"\n");
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
			ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
			this.setChanged();
			this.notifyObservers(String.format(strings.getString("passe"), this.score[this.numChevalet].getPrenom())+"\n");
		}
		else {
			this.chevalets.chevaletEnCours().remplir(sac);
			this.passe=0;
			ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
			this.setChanged();
			this.notifyObservers(String.format(strings.getString("augmente"), (this.score[numChevalet].getScore()-this.scoreAv))+"\n");
		}
		if(this.passe==this.chevalets.size()) {
			this.setChanged();
			this.notifyObservers(Vues.FINALE);
			this.setChanged();
			this.notifyObservers(this.score);
		}
		else {
			if (this.numChevalet+1 == this.chevalets.size()) {
				this.numChevalet=0;
			}
			else {
				this.numChevalet++;
			}
			this.chevalets.joueurSuivant();
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
			ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
			this.setChanged();
			this.notifyObservers(String.format(strings.getString("tour"),this.score[this.numChevalet].getPrenom())+"\n");
			if (this.score[this.numChevalet].getPrenom().equals("PC")){
				this.jouerPC();
			}
		}
	}
	
	public void jouerPC() {
		JSONObject reponse = (JSONObject) Ordinateur.solutions(plateau, this.chevalets.chevaletEnCours(), this.langue);
		JSONArray liste_solutions = (JSONArray) ((JSONObject) reponse.get("results")).get("result");
		JSONObject choix = (JSONObject) liste_solutions.get(0);
		if (difficulte==0) {
			choix = (JSONObject) liste_solutions.get(0);
		}
		else if(difficulte==1) {
			choix = (JSONObject) liste_solutions.get((int) liste_solutions.length()/2);
		}
		else {
			choix = (JSONObject) liste_solutions.get(liste_solutions.length());
		}
		//x
		int x=Integer.parseInt(choix.getString("x"));
		//y
		int y=Integer.parseInt(choix.getString("y"));
		//value
		int value=Integer.parseInt(choix.getString("value"));
		//direction
		int direction=Integer.parseInt(choix.getString("direction"));
		//word
		String word=choix.getString("word");
		//statue
		String status=reponse.getString("status");
		
		if(status.equals("error")) {
			this.changementJoueur();
		}
		else {
			this.score[this.numChevalet].majScore(value);
			
			for (int i=0;i<word.length();i++) {
				Character l=word.charAt(i);
				String lettre=l.toString();
				if(this.plateau.getCase(y,x).lettre==null) {
					if (lettre.toLowerCase().equals(lettre)){
						for(int t=0;t<this.chevalets.chevaletEnCours().size();t++) {
							if (this.chevalets.chevaletEnCours().get(t).valeur == 0) {
								this.chevalets.chevaletEnCours().selectionnerLettre(t);
								Lettre ajout=this.chevalets.chevaletEnCours().obtenirLettre();
								ajout.lettre=lettre;
								Case c =this.plateauFictif.getCase(y, x);
								c.ajouterLettre(ajout);
							}
						}
					}
					else {
						for(int t=0;t<this.chevalets.chevaletEnCours().size();t++) {
							if (this.chevalets.chevaletEnCours().get(t).lettre.equals(lettre)) {
								this.chevalets.chevaletEnCours().selectionnerLettre(t);
								Lettre ajout=this.chevalets.chevaletEnCours().obtenirLettre();
								Case c =this.plateauFictif.getCase(y, x);
								c.ajouterLettre(ajout);
								break;
							}
						}
					}
				}
				if(direction==0) {
					x=x+1;
				}
				else {
					y=y+1;
				}
			}
			ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
			this.setChanged();
			this.notifyObservers(String.format(strings.getString("joue"), this.score[this.numChevalet].getPrenom(),word.toUpperCase())+"\n");
			this.changementJoueur();
		}	
	}

	public void lettreJoker(String lettre) {
		if (lettre == null) {
			this.chevalets.chevaletEnCours().lettreSelectionee = -1;
		} else {
			lettreChoisi=lettre;
		}
		this.setChanged();
		this.notifyObservers(Vues.AFFICHER);
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
		ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
		this.setChanged();
		this.notifyObservers(strings.getString("sauvegarde")+"\n");
	}

	public void suppFile() {
		//Sac
		File fichier = new File("Sac.dat");
		fichier.delete();
		//numChevalet
		File fichier1 = new File("JoueurEnCours.dat");
		fichier1.delete();
		//Score
		File fichier2 = new File("Score.dat");
		fichier2.delete();
		//Chevalet
		File fichier3 = new File("Chevalet.dat");
		fichier3.delete();
		//Langue
		File fichier6 = new File("Langue.dat");
		fichier6.delete();
		//Plateau
		File fichier4 = new File("Plateau.dat");
		fichier4.delete();
		//PremierTour
		File fichier5 = new File("PremierTour.dat");
		fichier5.delete();
	}
}
