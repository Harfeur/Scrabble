package fr.scrabble.structures;

public class Placement {
	
	Lettre l;
	Case c;
	int lig,col;
	
	public Placement(Lettre l, Case c, int lig, int col) {
		super();
		this.l = l;
		this.c = c;
		this.lig = lig;
		this.col = col;
	}
	
}
