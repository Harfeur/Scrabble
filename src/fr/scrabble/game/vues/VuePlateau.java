package fr.scrabble.game.vues;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Case;
import fr.scrabble.structures.Case.Multiplicateur;
import fr.scrabble.structures.Couleur;
import fr.scrabble.structures.Plateau;

@SuppressWarnings("serial")
public class VuePlateau extends JPanel implements Observer {

	Plateau plateau;
	public static int TAILLE = 25; 
	Menu menu;
	Couleur c;
	//Color [clair fill,sombre fill]
	Color[] tuile = {new Color(230,207,207),new Color(200,77,77)};

	
	public VuePlateau(MouseInputListener l, Menu menu) {
		super();
		this.setPreferredSize(new Dimension((int) (VuePlateau.TAILLE*15*Menu.SCALE),(int) (VuePlateau.TAILLE*15*Menu.SCALE)));
		this.plateau = new Plateau();
		this.addMouseListener(l);
		this.menu = menu;
		this.c = menu.couleur;

		// Creation du Panel
		VueColonne colonne = new VueColonne(this.menu);
		VueLigne ligne = new VueLigne(this.menu);
		
		this.setBackground(Color.GREEN);
        this.setBounds((int) (colonne.getWidth()), (int) (ligne.getHeight()), (int) (VuePlateau.TAILLE*15*Menu.SCALE), (int) (VuePlateau.TAILLE*15*Menu.SCALE));
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(this.plateau!=null) {
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					Case c = this.plateau.getCase(i,j);	
					if(c.lettre==null) {				
						Multiplicateur m = c.multiplicateur;
						//Fond
						g.setColor(m.getCouleur()[this.c.getCouleur()]);
						g.fillRect((int) (j*VuePlateau.TAILLE*Menu.SCALE), (int) (i*VuePlateau.TAILLE*Menu.SCALE),(int) (VuePlateau.TAILLE*Menu.SCALE),(int) (VuePlateau.TAILLE*Menu.SCALE));
						g.setColor(this.c.getColorLettre());
						g.drawRect((int) (j*VuePlateau.TAILLE*Menu.SCALE), (int) (i*VuePlateau.TAILLE*Menu.SCALE),(int) (VuePlateau.TAILLE*Menu.SCALE),(int) (VuePlateau.TAILLE*Menu.SCALE));
						//Mot score
						String score_l="";
						String score_d="";
						switch(m.toString()) {
							case "LD": score_l="LETTRE";score_d="DOUBLE";break;
							case "LT": score_l="LETTRE";score_d="TRIPLE";break;
							case "MD": score_l="MOT";score_d="DOUBLE";break;
							case "MT": score_l="MOT";score_d="TRIPLE";break;
						} 
						Font font_mot_score = new Font("Arial",Font.BOLD, (int)(5*Menu.SCALE));
							FontMetrics metrics_mot_score = getFontMetrics(font_mot_score);
							g.setFont(font_mot_score);
							g.setColor(this.c.getColorLettre());
							g.drawString(score_l,(int) (Menu.SCALE*2+j*VuePlateau.TAILLE*Menu.SCALE),(int) (Menu.SCALE*5+i*VuePlateau.TAILLE*Menu.SCALE+metrics_mot_score.getHeight()));
							g.setColor(this.c.getColorLettre());
							g.drawString(score_d,(int) (Menu.SCALE*2+j*VuePlateau.TAILLE*Menu.SCALE+metrics_mot_score.getDescent()),(int) (Menu.SCALE*10+i*VuePlateau.TAILLE*Menu.SCALE+metrics_mot_score.getHeight()));
						}
					else {
						//Fond
						g.setColor(this.tuile[this.c.getCouleur()]);
						g.fillRect((int) (j*VuePlateau.TAILLE*Menu.SCALE), (int) (i*VuePlateau.TAILLE*Menu.SCALE),(int) (VuePlateau.TAILLE*Menu.SCALE),(int) (VuePlateau.TAILLE*Menu.SCALE));
						g.setColor(this.c.getColorLettre());
						g.drawRect((int) (j*VuePlateau.TAILLE*Menu.SCALE), (int) (i*VuePlateau.TAILLE*Menu.SCALE),(int) (VuePlateau.TAILLE*Menu.SCALE),(int) (VuePlateau.TAILLE*Menu.SCALE));
						//Lettre
						Font font_lettre = new Font("Arial",Font.PLAIN,(int)(VuePlateau.TAILLE*Menu.SCALE)) ;
						FontMetrics metrics_lettre = getFontMetrics(font_lettre);
						g.setFont(font_lettre);
						g.setColor(this.c.getColorLettre());
						g.drawString(c.lettre.lettre,(int) (j*TAILLE*Menu.SCALE+metrics_lettre.getDescent()),(int) (i*TAILLE*Menu.SCALE+metrics_lettre.getAscent()));
						//Valeur
						Font font_valeur = new Font("Arial",Font.PLAIN,(int)(5*Menu.SCALE)) ;
						FontMetrics metrics_valeur = getFontMetrics(font_valeur);
						g.setFont(font_valeur);
						g.setColor(this.c.getColorLettre());
						g.drawString(c.lettre.valeur+"",(int) (j*TAILLE*Menu.SCALE+metrics_valeur.getDescent()),(int) (i*TAILLE*Menu.SCALE+metrics_valeur.getAscent()));
					}
				}
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg.getClass() == Plateau.class) {
			this.plateau = (Plateau) arg;
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					this.repaint((int) (j*VuePlateau.TAILLE*Menu.SCALE), (int) (i*VuePlateau.TAILLE*Menu.SCALE),(int) (VuePlateau.TAILLE*Menu.SCALE),(int) (VuePlateau.TAILLE*Menu.SCALE));
				}
			}
		}
	}
	@Override
	public void update(Graphics g) {
		if ((int) this.getClientProperty("color") != this.c.getCouleur()) {
			this.putClientProperty("color", this.c.getCouleur());
		}
	}
	
}
