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
	private Menu menu;
	private JLabel txt;
	
	public VueScoreFin(Menu menu) {
		super();
		
		this.menu = menu;

		this.txt = new JLabel("Partie Terminée");
		txt.setPreferredSize(new Dimension((int) (266*this.menu.zoom()),(int) (200*this.menu.zoom())));
		txt.setBounds((int) (216*this.menu.zoom()),(int) (66*this.menu.zoom()),(int) (266*this.menu.zoom()),(int) (200*this.menu.zoom()));
		txt.setFont(new Font("Arial",Font.BOLD,(int) (30*this.menu.zoom())));
		txt.setForeground(new Color(255,191,0));
		
		this.setBounds(this.menu.decalageX(), this.menu.decalageY(), (int) (600*this.menu.zoom()), (int) (600*this.menu.zoom()));
		this.setBackground(new Color(0,100,0));
		this.add(txt);
	}
	
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
		this.setPreferredSize(new Dimension((int) (266*this.menu.zoom()), (int) (266*this.menu.zoom())));
		this.setBounds((int) (200*this.menu.zoom()),(int) (166*this.menu.zoom()), (int) (266*this.menu.zoom()), (int) (266*this.menu.zoom()));
		this.setOpaque(false);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		if(score!=null) {
			this.setPreferredSize(new Dimension((int) (266*this.menu.zoom()), (int) (266*this.menu.zoom())));
			this.setBounds((int) (200*this.menu.zoom()),(int) (166*this.menu.zoom()), (int) (266*this.menu.zoom()), (int) (266*this.menu.zoom()));

			int j=0;
			for (int i=0; i<score.length;i++) {
				if(i==0) {
					Font font_score = new Font("Arial",Font.PLAIN,(int)(30*this.menu.zoom())) ;
					FontMetrics metrics_score = getFontMetrics(font_score);
					g.setFont(font_score);
					g.setColor(new Color(253,200,72));
					g.drawString(score[i].prenom + "  "+score[i].getScore()+" points",metrics_score.getDescent()+2,(j+1)*metrics_score.getAscent());
					j=j+2;
				}
				else {
					Font font_score = new Font("Arial",Font.PLAIN,(int)(25*this.menu.zoom())) ;
					FontMetrics metrics_score = getFontMetrics(font_score);
					g.setFont(font_score);
					g.setColor(new Color(255,255,255));
					g.drawString("  "+score[i].prenom + "  "+score[i].getScore()+" points",metrics_score.getDescent()+2,(j+1)*metrics_score.getAscent());
					j=j+2;
				}
			}
		} else {
			this.setBounds(this.menu.decalageX(), this.menu.decalageY(), (int) (600*this.menu.zoom()), (int) (600*this.menu.zoom()));
			this.setBackground(new Color(0,100,0));

			txt.setPreferredSize(new Dimension((int) (266*this.menu.zoom()),(int) (200*this.menu.zoom())));
			txt.setBounds((int) (216*this.menu.zoom()),(int) (66*this.menu.zoom()),(int) (266*this.menu.zoom()),(int) (200*this.menu.zoom()));
			txt.setFont(new Font("Arial",Font.BOLD,(int) (30*this.menu.zoom())));

		}
	}
}
