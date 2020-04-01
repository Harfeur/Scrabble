package fr.scrabble.structures;

public class Score {

	public int score;

	public Score() {
		this.score=0;
	}
	
	public void majScore(Lettre lettre) {
		this.score=this.score+lettre.valeur;
	}
	
	public int nbScore() {
		return score;
	}
}
