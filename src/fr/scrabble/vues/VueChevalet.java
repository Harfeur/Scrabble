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
	public static int TAILLE=25;
	
	public VueChevalet(ControleurChevalet cc) {
		super();
		this.chevalet =new Chevalet();
		this.setPreferredSize(new Dimension((int) (VuePlateau.TAILLE*15*Scrabble.SCALE),(int) (VuePlateau.TAILLE*3*Scrabble.SCALE)));
		Sac sac = new Sac("FR");
		this.chevalet = new Chevalet();
		this.chevalet.remplir(sac); //Le chevalet doit être envoyé par le modèle, et non pas créé ici
		this.addMouseListener(cc);
	}
	
	public void paint(Graphics g) {
		//Fond
		g.setColor(new Color(117,82,56));
		g.fillRect((int) (0*Scrabble.SCALE), (int) (TAILLE*Scrabble.SCALE), (int) (TAILLE*7*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE));
		g.setColor(new Color(87,52,26));
		g.drawRect((int) (0*Scrabble.SCALE), (int) (TAILLE*Scrabble.SCALE), (int) (TAILLE*7*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE));
		if(this.chevalet.size()>0) {
			for(int i=0; i<this.chevalet.size();i=i+1) {
				//Fond
				g.setColor(new Color(230,207,207));
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
	public void update(Graphics g) {
		super.update(g);
		//TODO C'est ici que l'on écrit les lettres et qu'on les dessines. En gros tout le code de paint() est ici.
		// + le fond change de couleur pour la lettre selectionee : this.chevalet.lettreSelectionee
		for(int i=0 ; i<this.chevalet.size() ;i=i+1) {
			if(this.chevalet.lettreSelectionee==i) {
				g.setColor(new Color(219,212,4));
				g.fillRect((int) (i*TAILLE*Scrabble.SCALE), (int) (TAILLE*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE));
				g.setColor(new Color(200,77,77));
				g.drawRect((int) (i*TAILLE*Scrabble.SCALE), (int) (TAILLE*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE),(int) (TAILLE*Scrabble.SCALE));
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// Cette fonction change le chevalet selon le modèle
		if (arg.getClass() == Chevalet.class) {
			this.chevalet = (Chevalet) arg;
			this.repaint();
		}
	}
	
	
}
