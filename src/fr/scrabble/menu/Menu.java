package fr.scrabble.menu;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import fr.scrabble.Modele;
import fr.scrabble.controleurs.ControleurBouton;
import fr.scrabble.controleurs.ControleurBoutons;
import fr.scrabble.controleurs.ControleurChevalet;
import fr.scrabble.controleurs.ControleurPlateau;
import fr.scrabble.menu.vues.VueBoutonHorsLigne;
import fr.scrabble.menu.vues.VueBoutonMulti;
import fr.scrabble.menu.vues.VueMenu;
import fr.scrabble.vues.VueBouton;
import fr.scrabble.vues.VueChevalet;
import fr.scrabble.vues.VueColonne;
import fr.scrabble.vues.VueLigne;
import fr.scrabble.vues.VuePlateau;

@SuppressWarnings("serial")
public class Menu extends JFrame implements Observer {

	public static double SCALE=1.5;
	public String langue;
	
	Container containerMenu;
	Container containerHorsLigne;

	Modele modeleHorsLigne;
	
	public Menu () {
		super("Menu");

		this.langue = "FR";
		
		// Création du conteneur du Menu
		this.containerMenu = new JLayeredPane();
		
		ControleurBoutons cplay = new ControleurBoutons(this);

		VueMenu fondMenu = new VueMenu();
		VueBoutonHorsLigne vueBoutonHorsLigne = new VueBoutonHorsLigne(cplay);
		VueBoutonMulti vueBoutonMultijoueur = new VueBoutonMulti(cplay);

		containerMenu.setBounds(0, 0, (int) (600*Menu.SCALE), (int) (600*Menu.SCALE));
		containerMenu.add(fondMenu, 0, 0);
		containerMenu.add(vueBoutonHorsLigne, 1, 0);
		containerMenu.add(vueBoutonMultijoueur, 1, 0);
		
		// Création du conteneur du mode Hors Ligne
		this.modeleHorsLigne = new Modele();

		ControleurPlateau cp = new ControleurPlateau(modeleHorsLigne);
		ControleurChevalet cc = new ControleurChevalet(modeleHorsLigne);
		ControleurBouton cb = new ControleurBouton(modeleHorsLigne);

		VuePlateau vuePlateau = new VuePlateau(cp);
		VueChevalet vueChevalet = new VueChevalet(cc);
		VueBouton vueBouton = new VueBouton(cb);
		VueLigne vueLigne = new VueLigne();
		VueColonne vueColonne = new VueColonne();

		this.modeleHorsLigne.addObserver(vuePlateau);
		this.modeleHorsLigne.addObserver(vueChevalet);
		this.modeleHorsLigne.addObserver(this);

		this.containerHorsLigne = new Container();

		this.containerHorsLigne.setLayout(new BorderLayout());

		this.containerHorsLigne.add(vuePlateau, BorderLayout.CENTER);
		this.containerHorsLigne.add(vueLigne, BorderLayout.NORTH);
		this.containerHorsLigne.add(vueColonne, BorderLayout.WEST);
		this.containerHorsLigne.add(vueChevalet, BorderLayout.SOUTH);
		this.containerHorsLigne.add(vueBouton, BorderLayout.EAST);
		
		// Création et paramétrage de la fenêtre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension((int) (600*Menu.SCALE), (int) (600*Menu.SCALE)));
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.setAutoRequestFocus(false);
		this.setResizable(false);

		this.pack();

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);

		this.setLocation(x, y);
		
		this.vueMenu();
	}
	
	@Override
	public void removeAll() {
		this.remove(this.containerMenu);
		this.remove(this.containerHorsLigne);
	}

	public void vueMenu() {
		this.removeAll();
		
		this.add(containerMenu);
		
		this.setVisible(true);
	}

	public void vueHorsLigne() {
		this.removeAll();
		
		this.add(this.containerHorsLigne);
		
		this.modeleHorsLigne.nouvellePartie(4, this.langue);
		
		this.setVisible(true);
	}

	public void fermer() {
		this.setVisible(false);
		this.dispose();
	}

	public static void main(String[] args) {
		new Menu();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg.getClass() == String.class) {
			String str = (String) arg;
			if (str.equals("cacher")) {
				this.setVisible(false);
			}
			if (str.equals("afficher")) {
				this.setVisible(true);
			}
		}
	}
}


