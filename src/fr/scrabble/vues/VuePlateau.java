package fr.scrabble.vues;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import fr.scrabble.Scrabble;
import fr.scrabble.structures.Case;
import fr.scrabble.structures.Case.Multiplicateur;
import fr.scrabble.structures.Plateau;

public class VuePlateau extends Canvas implements Observer {

	Plateau plateau;
	public static int TAILLE = 25; 
	
	public VuePlateau(MouseListener l) {
		super();
		this.setPreferredSize(new Dimension((int) (VuePlateau.TAILLE*15*Scrabble.SCALE),(int) (VuePlateau.TAILLE*15*Scrabble.SCALE)));
		this.plateau = new Plateau();
		this.addMouseListener(l);
	}
	
	public void paint(Graphics g) {
		if(this.plateau!=null) {
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					Case c = this.plateau.getCase(i,j);	
					if(c.lettre==null) {				
						Multiplicateur m = c.multiplicateur;
						//Fond
						g.setColor(m.getCouleur());
						g.fillRect((int) (j*VuePlateau.TAILLE*Scrabble.SCALE), (int) (i*VuePlateau.TAILLE*Scrabble.SCALE),(int) (VuePlateau.TAILLE*Scrabble.SCALE),(int) (VuePlateau.TAILLE*Scrabble.SCALE));
						g.setColor(Color.BLACK);
						g.drawRect((int) (j*VuePlateau.TAILLE*Scrabble.SCALE), (int) (i*VuePlateau.TAILLE*Scrabble.SCALE),(int) (VuePlateau.TAILLE*Scrabble.SCALE),(int) (VuePlateau.TAILLE*Scrabble.SCALE));
						//Mot score
						if(m.toString()!="Simple") {
							Font font_mot_score = new Font("Arial",Font.PLAIN, (int)(14*Scrabble.SCALE));
							FontMetrics metrics_mot_score = getFontMetrics(font_mot_score);
							g.setFont(font_mot_score);
							g.setColor(Color.DARK_GRAY);
							g.drawString(m.toString(),(int) (j*this.TAILLE*Scrabble.SCALE+metrics_mot_score.getDescent()),(int) (i*this.TAILLE*Scrabble.SCALE+metrics_mot_score.getHeight()));
						}
					}
					else {
						//Fond
						g.setColor(new Color(230,207,207));
						g.fillRect((int) (j*VuePlateau.TAILLE*Scrabble.SCALE), (int) (i*VuePlateau.TAILLE*Scrabble.SCALE),(int) (VuePlateau.TAILLE*Scrabble.SCALE),(int) (VuePlateau.TAILLE*Scrabble.SCALE));
						g.setColor(Color.BLACK);
						g.drawRect((int) (j*VuePlateau.TAILLE*Scrabble.SCALE), (int) (i*VuePlateau.TAILLE*Scrabble.SCALE),(int) (VuePlateau.TAILLE*Scrabble.SCALE),(int) (VuePlateau.TAILLE*Scrabble.SCALE));
						//Lettre
						Font font_lettre = new Font("Arial",Font.PLAIN,(int)(this.TAILLE*Scrabble.SCALE)) ;
						FontMetrics metrics_lettre = getFontMetrics(font_lettre);
						g.setFont(font_lettre);
						g.setColor(Color.BLACK);
						g.drawString(c.lettre.lettre,(int) (j*TAILLE*Scrabble.SCALE+metrics_lettre.getDescent()),(int) (i*TAILLE*Scrabble.SCALE+metrics_lettre.getAscent()));
						//Valeur
						Font font_valeur = new Font("Arial",Font.PLAIN,(int)(5*Scrabble.SCALE)) ;
						FontMetrics metrics_valeur = getFontMetrics(font_valeur);
						g.setFont(font_valeur);
						g.setColor(Color.BLACK);
						g.drawString(c.lettre.valeur+"",(int) (j*TAILLE*Scrabble.SCALE+metrics_valeur.getDescent()),(int) (i*TAILLE*Scrabble.SCALE+metrics_valeur.getAscent()));
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
