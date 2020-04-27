package fr.scrabble.game.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Chevalet;
import fr.scrabble.structures.Couleur;
import fr.scrabble.structures.Sac;
import fr.scrabble.structures.Score;

public class VueScore extends JPanel implements Observer {

	Score[] score;
	Menu menu;
	Couleur c;
	
	public VueScore(Menu menu) {
		super();
		this.menu = menu;
		this.c = menu.couleur;
		JLabel score = new JLabel();
		this.setPreferredSize(new Dimension((int) (VuePlateau.TAILLE*Menu.SCALE),(int) (VuePlateau.TAILLE*10*Menu.SCALE)));
		this.setBounds((int) (VueColonne.TAILLE*Menu.SCALE+VuePlateau.TAILLE*15*Menu.SCALE)+5,100, (int) (VuePlateau.TAILLE*7*Menu.SCALE),(int) (VuePlateau.TAILLE*10*Menu.SCALE));
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(this.c.getColorLettre());
		if(score!=null) {
			g.drawRect(0, 0,(int) (VuePlateau.TAILLE*4*Menu.SCALE)-1,(int) (VuePlateau.TAILLE*score.length*Menu.SCALE)-1);
			int j=0;
			for (int i=0; i<score.length;i++) {
				Font font_score = new Font("Arial",Font.PLAIN,(int)(15*Menu.SCALE)) ;
				FontMetrics metrics_score = getFontMetrics(font_score);
				g.setFont(font_score);
				g.setColor(this.c.getColorLettre());
				g.drawString(score[i].prenom + " : "+score[i].getScore(),metrics_score.getDescent()+2,(j+1)*metrics_score.getAscent());
				j=j+2;
			
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void update(Observable o, Object arg) {
		if(arg.getClass() == Score[].class) {
			this.score = (Score []) arg;
			this.repaint(0,0,(int) (VuePlateau.TAILLE*7*Menu.SCALE)-1,(int) (VuePlateau.TAILLE*3*Menu.SCALE)-1);
		}
	}
	
	@Override
	public void update(Graphics g) {
		if ((int) this.getClientProperty("color") != this.c.getCouleur()) {
			this.putClientProperty("color", this.c.getCouleur());
		}
	}
}
