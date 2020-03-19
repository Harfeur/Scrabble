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
						String score_l="";
						String score_d="";
						switch(m.toString()) {
							case "LD": score_l="LETTRE";score_d="DOUBLE";break;
							case "LT": score_l="LETTRE";score_d="TRIPLE";break;
							case "MD": score_l="MOT";score_d="DOUBLE";break;
							case "MT": score_l="MOT";score_d="TRIPLE";break;
						} 
						Font font_mot_score = new Font("Arial",Font.BOLD, (int)(5*Scrabble.SCALE));
							FontMetrics metrics_mot_score = getFontMetrics(font_mot_score);
							g.setFont(font_mot_score);
							g.setColor(Color.BLACK);
							g.drawString(score_l,(int) (Scrabble.SCALE*2+j*this.TAILLE*Scrabble.SCALE),(int) (Scrabble.SCALE*5+i*this.TAILLE*Scrabble.SCALE+metrics_mot_score.getHeight()));
							g.setColor(Color.BLACK);
							g.drawString(score_d,(int) (Scrabble.SCALE*2+j*this.TAILLE*Scrabble.SCALE+metrics_mot_score.getDescent()),(int) (Scrabble.SCALE*10+i*this.TAILLE*Scrabble.SCALE+metrics_mot_score.getHeight()));
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
	public void update(Observable o, Object arg) {
		if(arg.getClass() == Plateau.class) {
			this.plateau = (Plateau) arg;
		}
		this.repaint();
	}
	
}
