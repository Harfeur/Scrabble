package fr.scrabble.game.vues;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputListener;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Case;
import fr.scrabble.structures.Case.Multiplicateur;
import fr.scrabble.structures.Couleur;
import fr.scrabble.structures.Plateau;

@SuppressWarnings("serial")
public class VuePlateau extends JPanel implements Observer {

	Plateau plateau;
	public static int TAILLE = 25; 
	Menu menu;
	Couleur c;
	//Color [clair fill,sombre fill]
	Color[] tuile = {new Color(230,207,207),new Color(200,77,77)};

	
	public VuePlateau(MouseInputListener l, Menu menu) {
		super();
		this.setPreferredSize(new Dimension((int) (VuePlateau.TAILLE*15*Menu.SCALE),(int) (VuePlateau.TAILLE*15*Menu.SCALE)));
		this.plateau = new Plateau();
		this.addMouseListener(l);
		this.menu = menu;
		this.c = menu.couleur;

		// Creation du Panel
		VueColonne colonne = new VueColonne(this.menu);
		VueLigne ligne = new VueLigne(this.menu);
		
		this.setBackground(Color.GREEN);
        this.setBounds((int) (colonne.getWidth()), (int) (ligne.getHeight()), (int) (VuePlateau.TAILLE*15*Menu.SCALE), (int) (VuePlateau.TAILLE*15*Menu.SCALE));
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if(this.plateau!=null) {
			for (int i = 0; i < 15; i++) {
				for (int j = 0; j < 15; j++) {
					Case c = this.plateau.getCase(i,j);	
					if(c.lettre==null) {				
						Multiplicateur m = c.multiplicateur;
						Image im = null;
						try {
							if(i==7 && j==7) {
								im = ImageIO.read(Multiplicateur.class.getResource("/resources/images/plateau/E.png"));
							}
							else {
								im = ImageIO.read(c.multiplicateur.getCouleur());
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						g.drawImage(im,(int) (j*VuePlateau.TAILLE*Menu.SCALE), (int) (i*VuePlateau.TAILLE*Menu.SCALE),(int) (VuePlateau.TAILLE*Menu.SCALE),(int) (VuePlateau.TAILLE*Menu.SCALE),null);
						}
					else {
						Image im = null;
						try {
							im = ImageIO.read(c.lettre.image);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						g.drawImage(im,(int) (j*VuePlateau.TAILLE*Menu.SCALE), (int) (i*VuePlateau.TAILLE*Menu.SCALE),(int) (VuePlateau.TAILLE*Menu.SCALE),(int) (VuePlateau.TAILLE*Menu.SCALE),null);
						}
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
	@Override
	public void update(Graphics g) {
		if ((int) this.getClientProperty("color") != this.c.getCouleur()) {
			this.putClientProperty("color", this.c.getCouleur());
		}
	}
	
}
