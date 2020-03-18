package fr.scrabble.vues;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import fr.scrabble.Scrabble;
import fr.scrabble.structures.Chevalet;

public class VueChevalet extends Canvas implements Observer {

	Chevalet chevalet;
	public static int TAILLE=25;
	
	public VueChevalet() {
		super();
		this.setPreferredSize(new Dimension((int) (VuePlateau.TAILLE*15*Scrabble.SCALE),(int) (VuePlateau.TAILLE*5*Scrabble.SCALE)));
	}
	
	public void paint(Graphics g) {
		if(this.chevalet!=null) {
			if(this.chevalet.lettreSelectionee==-1) {
				//Fond
				//g.setColor(Color.BLUE);
				//g.fillRect((int) (VuePlateau.TAILLE*20*Scrabble.SCALE), (int) (VuePlateau.TAILLE*20*Scrabble.SCALE), (int) (TAILLE*15*Scrabble.SCALE),(int) (TAILLE*5*Scrabble.SCALE));
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	
}
