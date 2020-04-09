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
import fr.scrabble.controleurs.*;
import fr.scrabble.menu.vues.*;
import fr.scrabble.multiplayer.*;
import fr.scrabble.multiplayer.vues.*;
import fr.scrabble.vues.*;

@SuppressWarnings("serial")
public class Menu extends JFrame implements Observer {

	public static double SCALE=1.5;
	public String langue;
	
	Container containerMenu;
	Container containerHorsLigne;
	Container containerEnLigne;
	Container containerClient;
	Container containerServeur;
	Container containerAttente;
	Container containerRejete;

	Modele modeleHorsLigne;
	
	Client client;
	Serveur serveur;
	ModeleEnLigne modeleEnLigne;
	
	public Menu () {
		super("Menu");

		this.langue = "FR";
		
		this.containerMenu = new JLayeredPane();
		this.containerClient = new Container();
		this.containerHorsLigne = new Container();
		this.containerEnLigne = new Container();
		this.containerServeur = new Container();
		this.containerAttente = new Container();
		this.containerRejete = new Container();
				
		// Création du conteneur du Serveur - En ligne
		
		VueServeur vueServeur = new VueServeur();
		
		this.containerServeur.add(vueServeur);
		
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
		this.remove(this.containerAttente);
		this.remove(this.containerRejete);
	}

	public void vueMenu() {
		this.removeAll();
		
		this.containerMenu = new JLayeredPane();

		ControleurBoutons cplay = new ControleurBoutons(this);

		VueMenu fondMenu = new VueMenu();
		VueBoutonHorsLigne vueBoutonHorsLigne = new VueBoutonHorsLigne(cplay);
		VueBoutonMulti vueBoutonMultijoueur = new VueBoutonMulti(cplay);
		VueBoutonServeur vueBoutonServeur = new VueBoutonServeur(cplay);

		containerMenu.setBounds(0, 0, (int) (600*Menu.SCALE), (int) (600*Menu.SCALE));
		containerMenu.add(fondMenu, 0, 0);
		containerMenu.add(vueBoutonHorsLigne, 1, 0);
		containerMenu.add(vueBoutonMultijoueur, 1, 0);
		containerMenu.add(vueBoutonServeur, 1, 0);
		
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
		VueScore vueScore = new VueScore();

		this.modeleHorsLigne.addObserver(vuePlateau);
		this.modeleHorsLigne.addObserver(vueChevalet);
		this.modeleHorsLigne.addObserver(vueScore);
		this.modeleHorsLigne.addObserver(this);

		this.containerHorsLigne = new JLayeredPane();

		
		this.containerHorsLigne.add(vuePlateau,0);
		this.containerHorsLigne.add(vueLigne,0);
		this.containerHorsLigne.add(vueColonne,0);
		this.containerHorsLigne.add(vueChevalet,0);
		this.containerHorsLigne.add(vueBouton,0);
		this.containerHorsLigne.add(vueScore,1);
		
		this.add(this.containerHorsLigne);
		
		this.modeleHorsLigne.nouvellePartie(4, this.langue);
		
		this.setVisible(true);
	}
	
	public void vueEnLigne() {
		this.removeAll();
		
		this.modeleEnLigne = new ModeleEnLigne(this.client);
		
		ControleurPlateau cp = new ControleurPlateau(modeleEnLigne);
		ControleurChevalet cc = new ControleurChevalet(modeleEnLigne);
		ControleurBouton cb = new ControleurBouton(modeleEnLigne);

		VuePlateau vuePlateau = new VuePlateau(cp);
		VueChevalet vueChevalet = new VueChevalet(cc);
		VueBouton vueBouton = new VueBouton(cb);
		VueLigne vueLigne = new VueLigne();
		VueColonne vueColonne = new VueColonne();
		VueScore vueScore = new VueScore();

		this.client.addObserver(vuePlateau);
		this.client.addObserver(vueChevalet);
		this.client.addObserver(vueScore);
		this.client.addObserver(this);

		this.containerEnLigne = new JLayeredPane();

		this.containerEnLigne.add(vuePlateau,0);
		this.containerEnLigne.add(vueLigne,0);
		this.containerEnLigne.add(vueColonne,0);
		this.containerEnLigne.add(vueChevalet,0);
		this.containerEnLigne.add(vueBouton,0);
		this.containerEnLigne.add(vueScore,1);
		
		this.add(this.containerEnLigne);
		
		this.setVisible(true);
	}
	
	public void vueClient() {
		this.removeAll();
		
		this.containerClient = new Container();
		
		this.client = new Client(this);
		
		VueStart vs = new VueStart(this.client);
		
		this.containerClient.setLayout(new BorderLayout());
		
		this.containerClient.add(vs, BorderLayout.CENTER);
		
		this.add(this.containerClient);
		
		this.setVisible(true);
	}

	public void vueAttente() {
		this.removeAll();
		
		this.containerAttente = new Container();
		this.containerAttente.setLayout(new BorderLayout());
		this.containerAttente.add(new VueAttente(this.client), BorderLayout.CENTER);
		
		this.add(this.containerAttente);
		this.setVisible(true);
	}

	public void vueRejete() {
		this.removeAll();
		
		this.containerRejete = new Container();
		this.containerRejete.add(new VueRejete());
		
		this.add(this.containerRejete);
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


