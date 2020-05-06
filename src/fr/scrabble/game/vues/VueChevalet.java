package fr.scrabble.game.vues;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import fr.scrabble.menu.Menu;
import fr.scrabble.structures.*;

@SuppressWarnings("serial")
public class VueChevalet extends JPanel implements Observer {

	Chevalet chevalet;
	Sac sac;
	Couleur couleur;
	Menu menu;
	Score score;
	public static int TAILLE=45;
	Integer numchevalet;
	Color[] chevaletC = {new Color(117,82,56),new Color(87,52,26),new Color(117,82,56),new Color(167,114,81)};
	private ArrayList<Image> images;
	JTextArea lettrerest;
	MouseInputListener l;

	public VueChevalet(Menu menu) {
		super();
		this.chevalet =new Chevalet();
		this.sac = new Sac("FR");
		this.menu = menu;
		this.couleur = menu.couleur;
		this.lettrerest = new JTextArea();
		lettrerest.setPreferredSize(new Dimension((int) (20*this.menu.zoom()),(int) (15*this.menu.zoom())));
        lettrerest.setBounds((int) (20*this.menu.zoom()),(int) (210*this.menu.zoom()),(int) (20*this.menu.zoom()),(int) (10*this.menu.zoom()));
        lettrerest.setEditable(false);
        lettrerest.setOpaque(true);
        this.setOpaque(false);
		
		// Chargement des images
		this.images = new ArrayList<Image>();
		MediaTracker mt = new MediaTracker(this);

		for (int i = 0; i < 27; i++) {
			String lettre;
			if (i == 26) {
				lettre = "JOKER";
			} else {
				Character c = (char) ('A'+i);
				lettre = c.toString();
			}
			Image img = Toolkit.getDefaultToolkit().getImage(Lettre.class.getResource("/resources/images/lettre/letter_"+lettre+".png"));
			mt.addImage(img, i);
			this.images.add(img);
		}

		for (int i = 27; i < 54; i++) {
			String lettre;
			if (i == 53) {
				lettre = "JOKER";
			} else {
				Character c = (char) ('A'+i-27);
				lettre = c.toString();
			}
			Image img = Toolkit.getDefaultToolkit().getImage(Lettre.class.getResource("/resources/images/lettreSelectionnee/letter_"+lettre+".png"));
			mt.addImage(img, i);
			this.images.add(img);
		}

		for (int i = 54; i < 81; i++) {
			String lettre;
			if (i == 80) {
				lettre = "JOKER";
			} else {
				Character c = (char) ('A'+i-54);
				lettre = c.toString();
			}
			Image img = Toolkit.getDefaultToolkit().getImage(Lettre.class.getResource("/resources/images/lettreSombre/letter_"+lettre+".png"));
			mt.addImage(img, i);
			this.images.add(img);
		}

		for (int i = 81; i < 108; i++) {
			String lettre;
			if (i == 107) {
				lettre = "JOKER";
			} else {
				Character c = (char) ('A'+i-81);
				lettre = c.toString();
			}
			Image img = Toolkit.getDefaultToolkit().getImage(Lettre.class.getResource("/resources/images/LettreSombreSelectionnee/letter_"+lettre+".png"));
			mt.addImage(img, i);
			this.images.add(img);
		}

		try {
			mt.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		this.putClientProperty("color", this.couleur.getCouleur());

		this.setPreferredSize(new Dimension((int) (VuePlateau.TAILLE*16*this.menu.zoom()),(int) (VuePlateau.TAILLE*3*this.menu.zoom())));
		this.setBounds(this.menu.decalageX(), (int) (VuePlateau.TAILLE*15*this.menu.zoom()+(TAILLE*this.menu.zoom())+this.menu.decalageY()), (int) (VuePlateau.TAILLE*16*this.menu.zoom()), (int) (VuePlateau.TAILLE*3*this.menu.zoom()));
	}

	public void initialiser(MouseInputListener l) {
		this.removeMouseListener(this.l);
		this.l = l;
		this.addMouseListener(l);
		this.chevalet = null;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		this.setPreferredSize(new Dimension((int) (VuePlateau.TAILLE*16*this.menu.zoom()),(int) (VuePlateau.TAILLE*3*this.menu.zoom())));
		this.setBounds(this.menu.decalageX(), (int) (VuePlateau.TAILLE*15*this.menu.zoom()+(TAILLE*this.menu.zoom())+this.menu.decalageY()), (int) (VuePlateau.TAILLE*16*this.menu.zoom()), (int) (VuePlateau.TAILLE*3*this.menu.zoom()));

		if ((int) this.getClientProperty("color") != this.couleur.getCouleur())
			this.putClientProperty("color", this.couleur.getCouleur());

		//Fond chevalet
		g.setColor(this.chevaletC[this.couleur.getCouleur()]);
		int[] xPoints = {0, (int) (this.menu.zoom()*20), (int) ((VuePlateau.TAILLE*16-20)*this.menu.zoom()), (int) (VuePlateau.TAILLE*16*this.menu.zoom())};
		int[] yPoints = {(int) (TAILLE*1.5*this.menu.zoom()), (int) (TAILLE*0.5*this.menu.zoom()), (int) (TAILLE*0.5*this.menu.zoom()), (int) (TAILLE*1.5*this.menu.zoom())};
 		g.fillPolygon(xPoints, yPoints, 4);
		//g.setColor(this.chevaletC[this.couleur.getCouleur()+1]);
		//g.drawRect(115, (int) (TAILLE*Menu.SCALE), (int) (TAILLE*7*Menu.SCALE),(int) (TAILLE*Menu.SCALE));

		//Nom joueur
		if(this.score!=null) {
			Font font_joueur = new Font("Arial",Font.PLAIN,(int)(15*this.menu.zoom())) ;
			//FontMetrics metrics_joueur = getFontMetrics(font_joueur);
			g.setFont(font_joueur);
			g.setColor(Color.WHITE);
			//g.drawString(prenom.getPrenom(),metrics_joueur.getDescent(),metrics_joueur.getAscent());
			g.drawString(score.getPrenom(),(int) (15*this.menu.zoom()),(int) (TAILLE*1.4*this.menu.zoom()));
		}

		//Lettre restante
		Font font_lr = new Font("Arial",Font.PLAIN,(int)(15*this.menu.zoom())) ;
		FontMetrics metrics_lr = getFontMetrics(font_lr);
		lettrerest.setPreferredSize(new Dimension((int) (20*this.menu.zoom()),(int) (15*this.menu.zoom())));
        lettrerest.setBounds((int) (20*this.menu.zoom()),(int) (210*this.menu.zoom()),(int) (20*this.menu.zoom()),(int) (10*this.menu.zoom()));
		this.lettrerest.setFont(font_lr);
		this.lettrerest.setCaretColor(this.couleur.getColorLettre());
		ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
		this.lettrerest.setText(strings.getString("lettres_restantes"));
		g.drawString(this.lettrerest.getText()+this.sac.nbLettre(),(int) ((VuePlateau.TAILLE*16-15)*this.menu.zoom()) - metrics_lr.stringWidth(this.lettrerest.getText()+this.sac.size()),(int) (TAILLE*1.4*this.menu.zoom()));

		
		//Affichage lettre sur chevalet
		int index = 0;
		if (this.chevalet != null)
			for(int i=0; i<this.chevalet.size();i=i+1) {
				if (this.chevalet.get(i).valeur == 0)
					index = 26;
				else {
					char lettre = this.chevalet.get(i).lettre.charAt(0);
					index = (int) lettre;
					index -= 65;
				}
				if (this.chevalet.lettreSelectionee == i)
					index+=27;
				if ((int) this.getClientProperty("color") == 1)
					index+=54;
				g.drawImage(this.images.get(index),(int) ((22+TAILLE*i*1.15)*this.menu.zoom()), 0 ,(int) (TAILLE*this.menu.zoom()),(int) (TAILLE*this.menu.zoom()),null);
			}
	}

	@Override
	public void update(Observable o, Object arg) {
		// Cette fonction change le chevalet selon le modèle
		if (arg.getClass() == Chevalet.class) {
			this.chevalet = (Chevalet) arg;
			this.repaint();
		}
		if (arg.getClass() == SetDeChevalets.class) {
			this.chevalet = ((SetDeChevalets) arg).chevaletEnCours();
			this.repaint();
		}
		//Numero Joueur
		if (arg.getClass() == Integer.class) {
			this.numchevalet = (Integer) arg;
			this.repaint();
		}
		//Nombre lettre restante
		if (arg.getClass() == Sac.class) {
			this.sac = (Sac) arg;
			this.repaint();
		}
		if (arg.getClass() == Score[].class) {
			Score[] scores = (Score[]) arg;
			this.score = scores[this.numchevalet];
			this.repaint();
		}	
	}

}
