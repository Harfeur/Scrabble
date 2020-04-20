package fr.scrabble.game.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Score;

public class VueScoreFin extends JPanel {
	Score[] score;
	
	public VueScoreFin() {
		super();

		JLabel txt = new JLabel("Partie Terminée");
		txt.setPreferredSize(new Dimension(400,300));
		txt.setBounds(325,100,400,300);
		txt.setFont(new Font("Arial",Font.BOLD,45));
		txt.setForeground(new Color(255,191,0));

		this.setBounds(0, 0, (int) (600*Menu.SCALE), (int) (600*Menu.SCALE));
		this.setBackground(new Color(0,100,0));
		
		this.add(txt);
	}
	public VueScoreFin(Score[] scor) {
		super();
		this.score=scor;
		this.setPreferredSize(new Dimension(400,400));
		this.setBounds(250,250, 400, 400);
		this.setOpaque(false);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(new Color(100,100,100));
		g.drawRect(275, 150,(int) (VuePlateau.TAILLE*7*Menu.SCALE)-1,(int) (VuePlateau.TAILLE*4*Menu.SCALE)-1);
		if(score!=null) {
			int j=0;
			for (int i=0; i<score.length;i++) {
				Font font_score = new Font("Arial",Font.PLAIN,(int)(25*Menu.SCALE)) ;
				FontMetrics metrics_score = getFontMetrics(font_score);
				g.setFont(font_score);
				g.setColor(new Color(255,255,255));
				g.drawString(score[i].prenom + " : "+score[i].getScore(),metrics_score.getDescent()+2,(j+1)*metrics_score.getAscent());
				j=j+2;
			
			}
		}
	}
}
