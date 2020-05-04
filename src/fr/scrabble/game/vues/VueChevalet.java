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
	Score prenom;
	public static int TAILLE=35;
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
		lettrerest.setPreferredSize(new Dimension(30,15));
        lettrerest.setBounds(30,315,30,15);
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

		for (int i = 27; i < 53; i++) {
			String lettre;
			if (i == 52) {
				lettre = "JOKER";
			} else {
				Character c = (char) ('A'+i-27);
				lettre = c.toString();
			}
			Image img = Toolkit.getDefaultToolkit().getImage(Lettre.class.getResource("/resources/images/lettreSelectionnee/letter_"+lettre+".png"));
			mt.addImage(img, i);
			this.images.add(img);
		}

		for (int i = 53; i < 80; i++) {
			String lettre;
			if (i == 79) {
				lettre = "JOKER";
			} else {
				Character c = (char) ('A'+i-53);
				lettre = c.toString();
			}
			Image img = Toolkit.getDefaultToolkit().getImage(Lettre.class.getResource("/resources/images/lettreSombre/letter_"+lettre+".png"));
			mt.addImage(img, i);
			this.images.add(img);
		}

		for (int i = 80; i < 106; i++) {
			String lettre;
			if (i == 105) {
				lettre = "JOKER";
			} else {
				Character c = (char) ('A'+i-80);
				lettre = c.toString();
			}
			Image img = Toolkit.getDefaultToolkit().getImage(Lettre.class.getResource("/resources/images/lettreSombreSelectionnee/letter_"+lettre+".png"));
			mt.addImage(img, i);
			this.images.add(img);
		}

		try {
			mt.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		this.putClientProperty("color", this.couleur.getCouleur());

		this.setPreferredSize(new Dimension((int) (VuePlateau.TAILLE*15*Menu.SCALE),(int) (VuePlateau.TAILLE*3*Menu.SCALE)));
		this.setBounds(0, (int) (VuePlateau.TAILLE*15*Menu.SCALE+(TAILLE*Menu.SCALE)), (int) (VuePlateau.TAILLE*15*Menu.SCALE), (int) (VuePlateau.TAILLE*3*Menu.SCALE));
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

		if ((int) this.getClientProperty("color") != this.couleur.getCouleur())
			this.putClientProperty("color", this.couleur.getCouleur());

		//Fond chevalet
		g.setColor(this.chevaletC[this.couleur.getCouleur()]);
		g.fillRect(0, (int) (TAILLE*Menu.SCALE), (int) (TAILLE*7*Menu.SCALE),(int) (TAILLE*Menu.SCALE));
		g.setColor(this.chevaletC[this.couleur.getCouleur()+1]);
		g.drawRect(0, (int) (TAILLE*Menu.SCALE), (int) (TAILLE*7*Menu.SCALE),(int) (TAILLE*Menu.SCALE));

		//Nom joueur
		if(numchevalet!=null) {
			Font font_joueur = new Font("Arial",Font.PLAIN,(int)(15*Menu.SCALE)) ;
			FontMetrics metrics_joueur = getFontMetrics(font_joueur);
			g.setFont(font_joueur);
			g.setColor(this.couleur.getColorLettre());
			g.drawString(prenom.getPrenom(),metrics_joueur.getDescent(),metrics_joueur.getAscent());
		}

		//Lettre restante
		Font font_lr = new Font("Arial",Font.PLAIN,(int)(15*Menu.SCALE)) ;
		FontMetrics metrics_lr = getFontMetrics(font_lr);
		this.lettrerest.setFont(font_lr);
		this.lettrerest.setCaretColor(this.couleur.getColorLettre());
		ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
		this.lettrerest.setText(strings.getString("lettres_restantes"));
		g.drawString(this.lettrerest.getText()+this.sac.nombreDeLettres,(int) (4*TAILLE*Menu.SCALE+metrics_lr.getDescent()),metrics_lr.getAscent());

		
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
					index+=53;
				g.drawImage(this.images.get(index),(int) (i*TAILLE*Menu.SCALE), (int) (TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE),(int) (TAILLE*Menu.SCALE),null);
			}
	}

	@Override
	public void update(Observable o, Object arg) {
		// Cette fonction change le chevalet selon le modèle
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
		if (arg.getClass() == Score.class) {
			this.prenom = (Score) arg;
			this.repaint((int) (TAILLE*5*Menu.SCALE), 0, (int) (TAILLE*5*Menu.SCALE),(int) (TAILLE*Menu.SCALE));
		}	
	}

}
