package fr.scrabble.vues;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import fr.scrabble.Scrabble;
import fr.scrabble.structures.Case;
import fr.scrabble.structures.Case.Multiplicateur;
import fr.scrabble.structures.Plateau;

public class VuePlateau extends Canvas implements Observer {

	Plateau plateau;

	
	public VuePlateau() {
		super();
		this.setPreferredSize(new Dimension((int) (25*15*Scrabble.SCALE),(int) (25*15*Scrabble.SCALE)));
		this.plateau = new Plateau();
	}
	
	public void paint(Graphics g) {
		if(this.plateau!=null) {
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					Case c = this.plateau.getCase(i,j);	
					if(c.lettre==null) {				
						Multiplicateur m = c.multiplicateur;
						g.setColor(m.getCouleur());
						g.fillRect((int) (j*25*Scrabble.SCALE), (int) (i*25*Scrabble.SCALE),(int) (25*Scrabble.SCALE),(int) (25*Scrabble.SCALE));
					}
					else {
						g.setColor(Color.WHITE);
						g.fillRect((int) (j*25*Scrabble.SCALE), (int) (i*25*Scrabble.SCALE),(int) (25*Scrabble.SCALE),(int) (25*Scrabble.SCALE));
						g.setFont(new Font("Arial",Font.PLAIN,(int)(25*Scrabble.SCALE)));
						g.setColor(Color.GRAY);
						g.drawString(c.lettre.lettre,(int) (j*25*Scrabble.SCALE),(int) (i*25*Scrabble.SCALE));
						g.setFont(new Font("Arial",Font.PLAIN,(int)(5*Scrabble.SCALE)));
						g.setColor(Color.BLACK);
						g.drawString("1",(int) (j*25*Scrabble.SCALE),(int) (i*25*Scrabble.SCALE));
					}
				}
			}
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}
	
}
