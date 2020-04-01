package fr.scrabble.structures;

public class Score {

	public int score;

	public Score() {
		this.score=0;
	}
	
	public void majScore(int valeur) {
		this.score=this.score+valeur;
	}
	
	public int getScore() {
		return score;
	}
}
