package fr.scrabble.game.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Couleur;
import fr.scrabble.structures.Score;

@SuppressWarnings("serial")
public class VueScore extends JPanel implements Observer {

	Score[] score;
	Menu menu;
	Couleur c;
	Score numJoueur=null;
	
	public VueScore(Menu menu) {
		super();
		this.menu = menu;
		this.c = menu.couleur;
		this.setPreferredSize(new Dimension((int) (VuePlateau.TAILLE*this.menu.zoom()),(int) (VuePlateau.TAILLE*10*this.menu.zoom())));
		this.setBounds((int) (VueColonne.TAILLE*this.menu.zoom()+VuePlateau.TAILLE*15*this.menu.zoom())+5+this.menu.decalageX(),(int) (66*this.menu.zoom()+this.menu.decalageY()), (int) (VuePlateau.TAILLE*7*this.menu.zoom()),(int) (VuePlateau.TAILLE*10*this.menu.zoom()));
		this.setOpaque(false);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		this.setPreferredSize(new Dimension((int) (VuePlateau.TAILLE*this.menu.zoom()),(int) (VuePlateau.TAILLE*10*this.menu.zoom())));
		this.setBounds((int) (VueColonne.TAILLE*this.menu.zoom()+VuePlateau.TAILLE*15*this.menu.zoom())+5+this.menu.decalageX(),(int) (66*this.menu.zoom()+this.menu.decalageY()), (int) (VuePlateau.TAILLE*7*this.menu.zoom()),(int) (VuePlateau.TAILLE*10*this.menu.zoom()));
		
		if(score!=null) {
			int longueur=0;
			for (int i=0; i<score.length;i++) {
				if(score[i].prenom.length()>longueur) {
					longueur=score[i].prenom.length();
				}
			}
			g.setColor(this.c.getColorBouton());
			g.fillRect(0, 0,(int) (longueur*20)+50,(int) (VuePlateau.TAILLE*score.length*this.menu.zoom())+5);
			g.setColor(this.c.getColorLettre());
			g.drawRect(0, 0,(int) (longueur*20)+50,(int) (VuePlateau.TAILLE*score.length*this.menu.zoom())+5);
			int j=0;
			for (int i=0; i<score.length;i++) {	
				Font font_score = new Font("Arial",Font.PLAIN,(int)(15*this.menu.zoom())) ;	
				g.setColor(this.c.getColorLettre());
				if(score[i]==numJoueur || (numJoueur==null && i==0)) {
					font_score = new Font("Arial",Font.BOLD,(int)(15*this.menu.zoom())) ;
					g.setColor(new Color(255,0,0));
				}
				FontMetrics metrics_score = getFontMetrics(font_score);
				g.setFont(font_score);
				g.drawString(score[i].prenom + " : "+score[i].score,metrics_score.getDescent()+2,(j+1)*metrics_score.getAscent());
				j=j+2;
			
			}
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(arg.getClass() == Score[].class) {
			this.score = (Score []) arg;
			this.repaint(0,0,(int) (VuePlateau.TAILLE*4*this.menu.zoom())-1,(int) (VuePlateau.TAILLE*score.length*this.menu.zoom())-1);
		}
		if(arg.getClass() == Score.class) {
			this.numJoueur = (Score) arg;
			this.repaint();
		}
	}
	
	@Override
	public void update(Graphics g) {
		if ((int) this.getClientProperty("color") != this.c.getCouleur()) {
			this.putClientProperty("color", this.c.getCouleur());
		}
	}
}
