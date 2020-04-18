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
import fr.scrabble.structures.*;

@SuppressWarnings("serial")
public class VueChevalet extends JPanel implements Observer {

	Chevalet chevalet;
	Sac sac;
	Couleur couleur;
	public static int TAILLE=35;
	Integer numchevalet;
	//Color [clair fill,sombre fill, clair contour, sombre contour]
	Color[] chevaletC = {new Color(117,82,56),new Color(87,52,26),new Color(117,82,56),new Color(167,114,81)};
	Color[] tuile = {new Color(230,207,207),new Color(200,77,77),new Color(66,50,41),new Color(39,32,24)};
	Color[] select = {new Color(255,255,0),new Color(200,200,0),new Color(200,77,77),new Color(200,140,140)};
	
	//essaie de push
	
	public VueChevalet(MouseInputListener cc, Couleur couleur) {
		super();
		this.chevalet =new Chevalet();
		this.sac = new Sac("FR");
		this.couleur = couleur;
		this.setPreferredSize(new Dimension((int) (VuePlateau.TAILLE*15*Menu.SCALE),(int) (VuePlateau.TAILLE*3*Menu.SCALE)));
		this.addMouseListener(cc);
		this.setBounds(0, (int) (VuePlateau.TAILLE*15*Menu.SCALE+(TAILLE*Menu.SCALE)), (int) (VuePlateau.TAILLE*15*Menu.SCALE), (int) (VuePlateau.TAILLE*3*Menu.SCALE));
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//Fond chevalet
		g.setColor(this.chevaletC[this.couleur.getCouleur()]);
		g.fillRect(0, (int) (TAILLE*Menu.SCALE), (int) (TAILLE*7*Menu.SCALE),(int) (TAILLE*Menu.SCALE));
		g.setColor(this.chevaletC[this.couleur.getCouleur()+1]);
		g.drawRect(0, (int) (TAILLE*Menu.SCALE), (int) (TAILLE*7*Menu.SCALE),(int) (TAILLE*Menu.SCALE));
		//Numero joueur
		if(numchevalet!=null) {
			Font font_joueur = new Font("Arial",Font.PLAIN,(int)(15*Menu.SCALE)) ;
			FontMetrics metrics_joueur = getFontMetrics(font_joueur);
			g.setFont(font_joueur);
			g.setColor(this.couleur.getColorLettre());
			g.drawString("Joueur "+(numchevalet+1),metrics_joueur.getDescent(),metrics_joueur.getAscent());
		}
		
		//Lettre restante
		Font font_lr = new Font("Arial",Font.PLAIN,(int)(15*Menu.SCALE)) ;
		FontMetrics metrics_lr = getFontMetrics(font_lr);
		g.setFont(font_lr);
		g.setColor(this.couleur.getColorLettre());
		g.drawString("Lettre restante (sac) : "+this.sac.nombreDeLettres,(int) (4*TAILLE*Menu.SCALE+metrics_lr.getDescent()),metrics_lr.getAscent());
		//Affichage lettre sur chevalet
		if(this.chevalet.size()>0) {
			for(int i=0; i<this.chevalet.size();i=i+1) {
				//Fond
				g.setColor(this.tuile[this.couleur.getCouleur()]);
				g.fillRect((int) (i*TAILLE*Menu.SCALE), (int) (TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE));
				g.setColor(this.tuile[this.couleur.getCouleur()+1]);
				g.drawRect((int) (i*TAILLE*Menu.SCALE), (int) (TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE));
				//Lettre
				if(this.chevalet.get(i).valeur!=0) {
					Font font_lettre = new Font("Arial",Font.PLAIN,(int)(VueChevalet.TAILLE*Menu.SCALE)) ;
					FontMetrics metrics_lettre = getFontMetrics(font_lettre);
					g.setFont(font_lettre);
					g.setColor(this.couleur.getColorLettre());
					g.drawString(this.chevalet.get(i).lettre,(int) (i*TAILLE*Menu.SCALE+metrics_lettre.getDescent()),(int) (TAILLE*Menu.SCALE+metrics_lettre.getAscent()));
				}
				//Valeur
				Font font_valeur = new Font("Arial",Font.PLAIN,(int)(6*Menu.SCALE)) ;
				FontMetrics metrics_valeur = getFontMetrics(font_valeur);
				g.setFont(font_valeur);
				g.setColor(this.couleur.getColorLettre());
				g.drawString(this.chevalet.get(i).valeur+"",(int) (i*TAILLE*Menu.SCALE+metrics_valeur.getDescent()),(int) (TAILLE*Menu.SCALE+metrics_valeur.getAscent()));
			}
		}
		for(int i=0 ; i<this.chevalet.size() ;i=i+1) {
			if(this.chevalet.lettreSelectionee==i) {
				//Fond
				g.setColor(this.select[this.couleur.getCouleur()]);
				g.fillRect((int) (i*TAILLE*Menu.SCALE), (int) (TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE));
				g.setColor(this.select[this.couleur.getCouleur()+1]);
				g.drawRect((int) (i*TAILLE*Menu.SCALE), (int) (TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE));
				//Lettre
				if(this.chevalet.get(i).valeur!=0) {
					Font font_lettre = new Font("Arial",Font.PLAIN,(int)(VueChevalet.TAILLE*Menu.SCALE)) ;
					FontMetrics metrics_lettre = getFontMetrics(font_lettre);
					g.setFont(font_lettre);
					g.setColor(this.couleur.getColorLettre());
					g.drawString(this.chevalet.get(i).lettre,(int) (i*TAILLE*Menu.SCALE+metrics_lettre.getDescent()),(int) (TAILLE*Menu.SCALE+metrics_lettre.getAscent()));
				}
				//Valeur
				Font font_valeur = new Font("Arial",Font.PLAIN,(int)(6*Menu.SCALE)) ;
				FontMetrics metrics_valeur = getFontMetrics(font_valeur);
				g.setFont(font_valeur);
				g.setColor(this.couleur.getColorLettre());
				g.drawString(this.chevalet.get(i).valeur+"",(int) (i*TAILLE*Menu.SCALE+metrics_valeur.getDescent()),(int) (TAILLE*Menu.SCALE+metrics_valeur.getAscent()));
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		// Cette fonction change le chevalet selon le modèle
		if(arg != null) {
			if (arg.getClass() == Chevalet.class) {
				this.chevalet = (Chevalet) arg;
				this.repaint(0, (int) (TAILLE*Menu.SCALE), (int) (TAILLE*7*Menu.SCALE),(int) (TAILLE*Menu.SCALE));
			}
			if (arg.getClass() == SetDeChevalets.class) {
				this.chevalet = ((SetDeChevalets) arg).chevaletEnCours();
				this.repaint(0, (int) (TAILLE*Menu.SCALE), (int) (TAILLE*7*Menu.SCALE),(int) (TAILLE*Menu.SCALE));
			}
			//Numero Joueur
			if (arg.getClass() == Integer.class) {
				this.numchevalet = (Integer) arg;
				this.repaint(0,0,(int) (TAILLE*3*Menu.SCALE),(int) (TAILLE*Menu.SCALE));
			}
			//Nombre lettre restante
			if (arg.getClass() == Sac.class) {
				this.sac = (Sac) arg;
				this.repaint((int) (TAILLE*5*Menu.SCALE), 0, (int) (TAILLE*5*Menu.SCALE),(int) (TAILLE*Menu.SCALE));
			}		
		}
		else {
			if(o.getClass() == Couleur.class) {
			this.couleur = (Couleur) o;
			this.repaint();
			}
		}
		
	}
	
	
}
