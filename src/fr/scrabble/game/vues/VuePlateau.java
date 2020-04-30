package fr.scrabble.game.vues;

import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Case;
import fr.scrabble.structures.Case.Multiplicateur;
import fr.scrabble.structures.Couleur;
import fr.scrabble.structures.Lettre;
import fr.scrabble.structures.Plateau;

@SuppressWarnings("serial")
public class VuePlateau extends JPanel implements Observer {

	Plateau plateau;
	public static int TAILLE = 25; 
	Menu menu;
	Couleur c;
	ArrayList<Image> images;
	MouseInputListener l;

	public VuePlateau(Menu menu) {
		super();
		this.setPreferredSize(new Dimension((int) (VuePlateau.TAILLE*15*Menu.SCALE),(int) (VuePlateau.TAILLE*15*Menu.SCALE)));
		this.plateau = new Plateau();
		this.menu = menu;
		this.c = menu.couleur;

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

		this.images.add(Toolkit.getDefaultToolkit().getImage(Multiplicateur.class.getResource("/resources/images/plateau/Etoile.png")));
		mt.addImage(this.images.get(this.images.size()-1), 27);
		this.images.add(Toolkit.getDefaultToolkit().getImage(Multiplicateur.class.getResource("/resources/images/plateau/LD.png")));
		mt.addImage(this.images.get(this.images.size()-1), 28);
		this.images.add(Toolkit.getDefaultToolkit().getImage(Multiplicateur.class.getResource("/resources/images/plateau/LT.png")));
		mt.addImage(this.images.get(this.images.size()-1), 29);
		this.images.add(Toolkit.getDefaultToolkit().getImage(Multiplicateur.class.getResource("/resources/images/plateau/MD.png")));
		mt.addImage(this.images.get(this.images.size()-1), 30);
		this.images.add(Toolkit.getDefaultToolkit().getImage(Multiplicateur.class.getResource("/resources/images/plateau/MT.png")));
		mt.addImage(this.images.get(this.images.size()-1), 31);
		this.images.add(Toolkit.getDefaultToolkit().getImage(Multiplicateur.class.getResource("/resources/images/plateau/S.png")));
		mt.addImage(this.images.get(this.images.size()-1), 32);

		for (int i = 33; i < 60; i++) {
			String lettre;
			if (i == 59) {
				lettre = "JOKER";
			} else {
				Character c = (char) ('A'+i-33);
				lettre = c.toString();
			}
			Image img = Toolkit.getDefaultToolkit().getImage(Lettre.class.getResource("/resources/images/lettreSombre/letter_"+lettre+".png"));
			mt.addImage(img, i);
			this.images.add(img);
		}

		try {
			mt.waitForAll();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		this.putClientProperty("color", this.c.getCouleur());

		// Creation du Panel
		VueColonne colonne = new VueColonne(this.menu);
		VueLigne ligne = new VueLigne(this.menu);

		this.setBackground(Color.GRAY);
		this.setBounds((int) (colonne.getWidth()), (int) (ligne.getHeight()), (int) (VuePlateau.TAILLE*15*Menu.SCALE), (int) (VuePlateau.TAILLE*15*Menu.SCALE));
	}

	public void initialiser(MouseInputListener l) {
		this.removeMouseListener(this.l);
		this.l = l;
		this.addMouseListener(l);
		this.plateau = null;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if ((int) this.getClientProperty("color") != this.c.getCouleur()) {
			this.putClientProperty("color", this.c.getCouleur());
		}
		Plateau plateauLocal = this.plateau;
		if (plateauLocal==null) {
			plateauLocal = new Plateau();
		}
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				Case c = plateauLocal.getCase(i,j);	
				if(c.lettre==null) {	
					int index = 25;
					Multiplicateur m = c.multiplicateur;
					switch(m) {				
					case LETTRE_DOUBLE:
						index = 28;
						break;
					case LETTRE_TRIPLE:
						index = 29;
						break;
					case MOT_DOUBLE:
						index = 30;
						break;
					case MOT_TRIPLE:
						index = 31;
						break;
					case SIMPLE:
						index = 32;
					}
					if(i==7 && j==7) {
						index = 27;
					}
					g.drawImage(this.images.get(index),(int) (j*VuePlateau.TAILLE*Menu.SCALE), (int) (i*VuePlateau.TAILLE*Menu.SCALE),(int) (VuePlateau.TAILLE*Menu.SCALE),(int) (VuePlateau.TAILLE*Menu.SCALE),null);

				}
				else {
					int index;
					if (c.lettre.valeur == 0)
						index = 26;
					else {
						char lettre = c.lettre.lettre.charAt(0);
						index = (int) lettre;
						index -= 65;
					}
					if (((int) this.getClientProperty("color")) == 1)
						index+=33;
					g.drawImage(this.images.get(index),(int) (j*VuePlateau.TAILLE*Menu.SCALE), (int) (i*VuePlateau.TAILLE*Menu.SCALE),(int) (VuePlateau.TAILLE*Menu.SCALE),(int) (VuePlateau.TAILLE*Menu.SCALE),null);
				}
			}
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg.getClass() == Plateau.class) {
			this.plateau = (Plateau) arg;
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					this.repaint((int) (j*VuePlateau.TAILLE*Menu.SCALE), (int) (i*VuePlateau.TAILLE*Menu.SCALE),(int) (VuePlateau.TAILLE*Menu.SCALE),(int) (VuePlateau.TAILLE*Menu.SCALE));
				}
			}
		}
	}

}
