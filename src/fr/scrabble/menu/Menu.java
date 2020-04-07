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
import fr.scrabble.multiplayer.Client;
import fr.scrabble.multiplayer.Serveur;
import fr.scrabble.multiplayer.vues.VueStart;
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
	Container containerEnLigne;
	Container containerClient;
	Container containerServeur;

	Modele modeleHorsLigne;
	
	Client client;
	Serveur serveur;
	private Modele modeleEnLigne;
	
	public Menu () {
		super("Menu");

		this.langue = "FR";
		
		this.containerMenu = new JLayeredPane();
		this.containerClient = new Container();
		this.containerHorsLigne = new Container();
		this.containerEnLigne = new Container();
		this.containerServeur = new Container();
				
		// Création du conteneur du Serveur - En ligne
		
		/*
		VueServeur vueServeur = new VueServeur();
		
		this.containerServeur.add(vueServeur);
		*/
		
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
		this.remove(this.containerClient);
		this.remove(this.containerServeur);
		this.remove(this.containerEnLigne);
	}

	public void vueMenu() {
		this.removeAll();
		
		this.containerMenu = new JLayeredPane();

		ControleurBoutons cplay = new ControleurBoutons(this);

		VueMenu fondMenu = new VueMenu();
		VueBoutonHorsLigne vueBoutonHorsLigne = new VueBoutonHorsLigne(cplay);
		VueBoutonMulti vueBoutonMultijoueur = new VueBoutonMulti(cplay);

		containerMenu.setBounds(0, 0, (int) (600*Menu.SCALE), (int) (600*Menu.SCALE));
		containerMenu.add(fondMenu, 0, 0);
		containerMenu.add(vueBoutonHorsLigne, 1, 0);
		containerMenu.add(vueBoutonMultijoueur, 1, 0);
		
		this.add(containerMenu);
		
		this.setVisible(true);
	}

	public void vueHorsLigne() {
		this.removeAll();
		
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
		
		this.add(this.containerHorsLigne);
		
		this.modeleHorsLigne.nouvellePartie(4, this.langue);
		
		this.setVisible(true);
	}
	
	public void vueEnLigne() {
		this.removeAll();
		
		this.modeleEnLigne = new Modele();
		
		ControleurPlateau cp = new ControleurPlateau(modeleEnLigne);
		ControleurChevalet cc = new ControleurChevalet(modeleEnLigne);
		ControleurBouton cb = new ControleurBouton(modeleEnLigne);

		VuePlateau vuePlateau = new VuePlateau(cp);
		VueChevalet vueChevalet = new VueChevalet(cc);
		VueBouton vueBouton = new VueBouton(cb);
		VueLigne vueLigne = new VueLigne();
		VueColonne vueColonne = new VueColonne();

		this.modeleEnLigne.addObserver(vuePlateau);
		this.modeleEnLigne.addObserver(vueChevalet);
		this.modeleEnLigne.addObserver(this);

		this.containerEnLigne = new Container();

		this.containerEnLigne.setLayout(new BorderLayout());

		this.containerEnLigne.add(vuePlateau, BorderLayout.CENTER);
		this.containerEnLigne.add(vueLigne, BorderLayout.NORTH);
		this.containerEnLigne.add(vueColonne, BorderLayout.WEST);
		this.containerEnLigne.add(vueChevalet, BorderLayout.SOUTH);
		this.containerEnLigne.add(vueBouton, BorderLayout.EAST);
		
		this.add(this.containerEnLigne);
		
		this.setVisible(true);
	}
	
	public void vueClient() {
		this.removeAll();
		
		this.containerClient = new Container();
		
		this.client = new Client();
		
		VueStart vs = new VueStart(this.client);
		
		this.containerClient.setLayout(new BorderLayout());
		
		this.containerClient.add(vs, BorderLayout.CENTER);
		
		this.add(this.containerClient);
		
		this.setVisible(true);
	}
	
	public void vueServeur() {
		this.removeAll();
		
		this.containerServeur = new Container();
		
		this.serveur = new Serveur();
		
		this.add(this.containerServeur);
		
		this.serveur.ouvrirConnection();
		
		this.setVisible(true);
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


