package fr.scrabble.game.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Score;

@SuppressWarnings("serial")
public class VueScoreFin extends JPanel {
	
	Score[] score;
	Score scr;
	int[] y= {325,375,425,475};
	
	public VueScoreFin(Score[] score) {
		super();
		this.score=score;
		
		for(int i=this.score.length-1;i>0;i--) {
			int a=1;
			while(i-a!=-1 && this.score[i].getScore()>this.score[i-a].getScore()) {
				scr=this.score[i];
				this.score[i]=this.score[i-a];
				this.score[i-a]=scr;
			}
		}
		this.setPreferredSize(new Dimension(1000,1000));
		this.setBounds(0,0, 1000, 1000);
		this.setOpaque(false);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(new Color(0,100,0));
		g.fillRoundRect(200, 200, 500, 400, 100,100);
		g.setColor(new Color(253,200,72));
		Font font_string = new Font("Arial",Font.BOLD,(int)(30*Menu.SCALE)) ;
		g.setFont(font_string);
		g.drawString("Partie terminée", 285, 250);
		if(score!=null) {
			int j=0;
			for (int i=0; i<score.length;i++) {
				if(i==0) {
					Font font_score = new Font("Arial",Font.PLAIN,(int)(30*Menu.SCALE)) ;
					FontMetrics metrics_score = getFontMetrics(font_score);
					g.setFont(font_score);
					g.setColor(new Color(253,200,72));
					g.drawString(score[i].prenom + "  "+score[i].getScore()+" points",360-10*score[i].prenom.length(),325);
					j=j+2;
				}
				else {
					Font font_score = new Font("Arial",Font.PLAIN,(int)(25*Menu.SCALE)) ;
					g.setFont(font_score);
					g.setColor(new Color(255,255,255));
					g.drawString("  "+score[i].prenom + "  "+score[i].getScore()+" points",360-10*score[i].prenom.length(),y[i]);
				}
			}
		}
	}
}
