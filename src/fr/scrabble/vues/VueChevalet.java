package fr.scrabble.vues;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import fr.scrabble.Scrabble;
import fr.scrabble.controleurs.ControleurChevalet;
import fr.scrabble.structures.*;

public class VueChevalet extends Canvas implements Observer {

	Chevalet chevalet;
	Sac sac;
	public static int TAILLE=35;
	Integer numchevalet;
	Score score;
	
	//essaie de push
	
	public VueChevalet(ControleurChevalet cc) {
		super();
		this.chevalet =new Chevalet();
		this.sac = new Sac("FR");
		this.setPreferredSize(new Dimension((int) (VuePlateau.TAILLE*15*Scrabble.SCALE),(int) (VuePlateau.TAILLE*3*Scrabble.SCALE)));
		this.addMouseListener(cc);
	}
	
	public void paint(Graphics g) {
		//Fond
		g.setColor(new Color(117,82,56));
		g.fillRect(0, (int) (TAILLE*Scrabble.SCALE), (int) (TAILLE*7*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE));
		g.setColor(new Color(87,52,26));
		g.drawRect(0, (int) (TAILLE*Scrabble.SCALE), (int) (TAILLE*7*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE));
		//Numero joueur
		if(numchevalet!=null) {
			Font font_joueur = new Font("Arial",Font.PLAIN,(int)(15*Scrabble.SCALE)) ;
			FontMetrics metrics_joueur = getFontMetrics(font_joueur);
			g.setFont(font_joueur);
			g.setColor(Color.BLACK);
			g.drawString("Joueur "+(numchevalet+1),metrics_joueur.getDescent(),metrics_joueur.getAscent());
		}
		if(score!=null) {
			Font font_score = new Font("Arial",Font.PLAIN,(int)(15*Scrabble.SCALE)) ;
			FontMetrics metrics_score = getFontMetrics(font_score);
			g.setFont(font_score);
			g.setColor(Color.BLACK);
			g.drawString("Score :"+score.getScore(),metrics_score.getDescent(),(int) (16*Scrabble.SCALE+metrics_score.getAscent()));
		}
		//Lettre restante
		Font font_lr = new Font("Arial",Font.PLAIN,(int)(15*Scrabble.SCALE)) ;
		FontMetrics metrics_lr = getFontMetrics(font_lr);
		g.setFont(font_lr);
		g.setColor(Color.BLACK);
		g.drawString("Lettre restante (sac) : "+this.sac.nombreDeLettres,(int) (4*TAILLE*Scrabble.SCALE+metrics_lr.getDescent()),metrics_lr.getAscent());
		//Affichage lettre sur chevalet
		if(this.chevalet.size()>0) {
			for(int i=0; i<this.chevalet.size();i=i+1) {
				//Fond
				g.setColor(new Color(230,207,207));
				g.fillRect((int) (i*TAILLE*Scrabble.SCALE), (int) (TAILLE*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE));
				g.setColor(new Color(200,77,77));
				g.drawRect((int) (i*TAILLE*Scrabble.SCALE), (int) (TAILLE*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE));
				//Lettre
				if(this.chevalet.get(i).valeur!=0) {
					Font font_lettre = new Font("Arial",Font.PLAIN,(int)(this.TAILLE*Scrabble.SCALE)) ;
					FontMetrics metrics_lettre = getFontMetrics(font_lettre);
					g.setFont(font_lettre);
					g.setColor(Color.BLACK);
					g.drawString(this.chevalet.get(i).lettre,(int) (i*TAILLE*Scrabble.SCALE+metrics_lettre.getDescent()),(int) (TAILLE*Scrabble.SCALE+metrics_lettre.getAscent()));
				}
				//Valeur
				Font font_valeur = new Font("Arial",Font.PLAIN,(int)(5*Scrabble.SCALE)) ;
				FontMetrics metrics_valeur = getFontMetrics(font_valeur);
				g.setFont(font_valeur);
				g.setColor(Color.BLACK);
				g.drawString(this.chevalet.get(i).valeur+"",(int) (i*TAILLE*Scrabble.SCALE+metrics_valeur.getDescent()),(int) (TAILLE*Scrabble.SCALE+metrics_valeur.getAscent()));
			}
		}		
	}
	
	@Override
	public void update(Graphics g) {
		super.update(g);
		//TODO C'est ici que l'on écrit les lettres et qu'on les dessines. En gros tout le code de paint() est ici.
		for(int i=0 ; i<this.chevalet.size() ;i=i+1) {
			if(this.chevalet.lettreSelectionee==i) {
				//Fond
				g.setColor(new Color(219,212,4));
				g.fillRect((int) (i*TAILLE*Scrabble.SCALE), (int) (TAILLE*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE));
				g.setColor(new Color(200,77,77));
				g.drawRect((int) (i*TAILLE*Scrabble.SCALE), (int) (TAILLE*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE));
				//Lettre
				Font font_lettre = new Font("Arial",Font.PLAIN,(int)(this.TAILLE*Scrabble.SCALE)) ;
				FontMetrics metrics_lettre = getFontMetrics(font_lettre);
				g.setFont(font_lettre);
				g.setColor(Color.BLACK);
				g.drawString(this.chevalet.get(i).lettre,(int) (i*TAILLE*Scrabble.SCALE+metrics_lettre.getDescent()),(int) (TAILLE*Scrabble.SCALE+metrics_lettre.getAscent()));
				//Valeur
				Font font_valeur = new Font("Arial",Font.PLAIN,(int)(5*Scrabble.SCALE)) ;
				FontMetrics metrics_valeur = getFontMetrics(font_valeur);
				g.setFont(font_valeur);
				g.setColor(Color.BLACK);
				g.drawString(this.chevalet.get(i).valeur+"",(int) (i*TAILLE*Scrabble.SCALE+metrics_valeur.getDescent()),(int) (TAILLE*Scrabble.SCALE+metrics_valeur.getAscent()));
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// Cette fonction change le chevalet selon le modèle
		if (arg.getClass() == Chevalet.class) {
			this.chevalet = (Chevalet) arg;
			this.repaint(0, (int) (TAILLE*Scrabble.SCALE), (int) (TAILLE*7*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE));
		}
		if (arg.getClass() == Integer.class) {
			this.numchevalet = (Integer) arg;
			this.repaint(0,0,(int) (TAILLE*3*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE));
		}
		if(arg.getClass() == Score.class) {
			this.score = (Score) arg;
			this.repaint(0,0,(int) (TAILLE*3*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE));
		}
		if (arg.getClass() == Sac.class) {
			this.sac = (Sac) arg;
			this.repaint((int) (TAILLE*9*Scrabble.SCALE), 0, (int) (TAILLE*2*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE));
		}
	}
	
	
}
