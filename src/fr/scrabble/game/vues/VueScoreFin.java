package fr.scrabble.game.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Score;

@SuppressWarnings("serial")
public class VueScoreFin extends JPanel {
	
	Score[] score;
	Score scr;
	private Menu menu;
	int[] y= {325,375,425,475};
	
	public VueScoreFin(Score[] score, Menu menu) {
		super();
		this.score=score;
		this.menu = menu;
		
		for(int i=this.score.length-1;i>0;i--) {
			int a=1;
			while(i-a!=-1 && this.score[i].getScore()>this.score[i-a].getScore()) {
				scr=this.score[i];
				this.score[i]=this.score[i-a];
				this.score[i-a]=scr;
			}
		}
		this.setPreferredSize(new Dimension((int) (666*this.menu.zoom()), (int) (666*this.menu.zoom())));
		this.setBounds(0,0, (int) (666*this.menu.zoom()), (int) (666*this.menu.zoom()));
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
			this.setPreferredSize(new Dimension((int) (266*this.menu.zoom()), (int) (266*this.menu.zoom())));
			this.setBounds((int) (200*this.menu.zoom()),(int) (166*this.menu.zoom()), (int) (266*this.menu.zoom()), (int) (266*this.menu.zoom()));

			int j=0;
			for (int i=0; i<score.length;i++) {
				if(i==0) {
					Font font_score = new Font("Arial",Font.PLAIN,(int)(30*this.menu.zoom())) ;
					g.setFont(font_score);
					g.setColor(new Color(253,200,72));
					g.drawString(score[i].prenom + "  "+score[i].getScore()+" points",360-10*score[i].prenom.length(),325);
					j=j+2;
				}
				else {
					Font font_score = new Font("Arial",Font.PLAIN,(int)(25*this.menu.zoom())) ;
					g.setFont(font_score);
					g.setColor(new Color(255,255,255));
					g.drawString("  "+score[i].prenom + "  "+score[i].getScore()+" points",360-10*score[i].prenom.length(),y[i]);
				}
			}
		}
	}
}
