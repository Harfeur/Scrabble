package fr.scrabble.structures;

import java.io.Serializable;

public class Score implements Serializable {

	private static final long serialVersionUID = 8065766459083283414L;
	
	public int score;
	public String prenom;
	
	public Score(String prenom) {
		this.score=0;
		this.prenom = prenom;
	}
	
	public void majScore(int valeur) {
		this.score=this.score+valeur;
	}
	
	public int getScore() {
		return score;
	}
	
	public String getPrenom() {
		return prenom;
	}
	public void faux() {
		this.score=0;
	}
}
