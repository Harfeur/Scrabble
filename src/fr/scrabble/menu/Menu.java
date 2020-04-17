package fr.scrabble.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import fr.scrabble.game.Modele;
import fr.scrabble.game.controleurs.ControleurBouton;
import fr.scrabble.game.controleurs.ControleurChevalet;
import fr.scrabble.game.controleurs.ControleurPlateau;
import fr.scrabble.game.vues.VueBouton;
import fr.scrabble.game.vues.VueChevalet;
import fr.scrabble.game.vues.VueColonne;
import fr.scrabble.game.vues.VueInstructionBouton;
import fr.scrabble.game.vues.VueLigne;
import fr.scrabble.game.vues.VuePlateau;
import fr.scrabble.game.vues.VueScore;
import fr.scrabble.menu.vues.*;
import fr.scrabble.online.*;
import fr.scrabble.online.vues.*;
import fr.scrabble.structures.Couleur;

@SuppressWarnings("serial")
public class Menu extends JFrame implements Observer {

	public static double SCALE=1.5;
	public String langue;
	public int nbJoueur;
	
	Container containerMenu;
	Container containerHorsLigne;
	Container containerNomJoueurHorsLigne;
	Container containerInstructionHorsLigne;
	Container containerEnLigne;
	Container containerClient;
	Container containerServeur;
	Container containerAttente;
	Container containerRejete;

	Modele modeleHorsLigne;
	
	boolean vueHorsLigneSombre=true;
	
	Client client;
	Serveur serveur;
	ModeleEnLigne modeleEnLigne;
	Couleur couleur;
	
	public Menu () {
		super("Menu");
		this.langue = "FR";
		this.nbJoueur = 4;
		this.couleur = new Couleur();
		
		VueMenuBar vueMenuBar = new VueMenuBar(this, this.couleur);
		this.setJMenuBar(vueMenuBar);
		
		this.containerMenu = new JLayeredPane();
		this.containerClient = new Container();
		this.containerHorsLigne = new Container();
		this.containerNomJoueurHorsLigne = new Container();
		this.containerInstructionHorsLigne = new Container();
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
		this.remove(this.containerInstructionHorsLigne);
		this.remove(this.containerNomJoueurHorsLigne);
	}

	public void vueMenu() {
		this.removeAll();
		
		this.containerMenu = new JLayeredPane();

		ControleurBoutons cplay = new ControleurBoutons(this);

		VueMenu fondMenu = new VueMenu();
		VueBoutonHorsLigne vueBoutonHorsLigne = new VueBoutonHorsLigne(cplay);
		VueBoutonMulti vueBoutonMultijoueur = new VueBoutonMulti(cplay);
		VueBoutonServeur vueBoutonServeur = new VueBoutonServeur(cplay, this.couleur);
		
		this.couleur.addObserver(vueBoutonServeur);
		
		containerMenu.setBounds(0, 0, (int) (600*Menu.SCALE), (int) (600*Menu.SCALE));
		containerMenu.add(fondMenu, 0, 0);
		containerMenu.add(vueBoutonHorsLigne, 1, 0);
		containerMenu.add(vueBoutonMultijoueur, 1, 0);
		containerMenu.add(vueBoutonServeur, 1, 0);
		
		this.add(containerMenu);
		
		this.setVisible(true);
	}

	public void vueHorsLigne(int nb, String l, ArrayList<String> prenoms) {
		this.removeAll();
		this.langue=l;
		this.nbJoueur=nb;
		
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

		
		this.containerHorsLigne.add(vuePlateau);
		this.containerHorsLigne.add(vueLigne);
		this.containerHorsLigne.add(vueColonne);
		this.containerHorsLigne.add(vueChevalet);
		this.containerHorsLigne.add(vueBouton);
		this.containerHorsLigne.add(vueScore);
		
		this.add(this.containerHorsLigne);
		
		this.modeleHorsLigne.nouvellePartie(this.nbJoueur, this.langue, prenoms);
		
		this.setVisible(true);
	}
	public void vueNomJoueurHorsLigne(int nbJoueur) {
		this.removeAll();
		
		ControleurBoutons cplay = new ControleurBoutons(this);
		this.containerNomJoueurHorsLigne = new JLayeredPane();
		
		VueMenu fondMenu = new VueMenu();
		VueNomJoueur vNJ = new VueNomJoueur(nbJoueur,cplay);
		
		containerNomJoueurHorsLigne.setBounds(0, 0, (int) (600*Menu.SCALE), (int) (600*Menu.SCALE));
		containerNomJoueurHorsLigne.add(fondMenu, 0, 0);
		containerNomJoueurHorsLigne.add(vNJ, 1, 0);
		
		this.add(containerNomJoueurHorsLigne);
		
		this.setVisible(true);
	}
	
	
	public void vueInstructionHorsLigne() {
		this.removeAll();
		
		ControleurBoutons cplay = new ControleurBoutons(this);
		this.containerInstructionHorsLigne = new JLayeredPane();
		
		VueMenu fondMenu = new VueMenu();
		VueInstructionBouton vueInstru = new VueInstructionBouton(cplay);
		
		containerInstructionHorsLigne.setBounds(0, 0, (int) (600*Menu.SCALE), (int) (600*Menu.SCALE));
		containerInstructionHorsLigne.add(fondMenu, 0, 0);
		containerInstructionHorsLigne.add(vueInstru, 1, 0);
		
		this.add(containerInstructionHorsLigne);
		
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
		VueConsole vueConsole = new VueConsole();

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
		this.containerEnLigne.add(vueConsole,1);
		
		this.add(this.containerEnLigne);
		
		this.setVisible(true);
	}
	
	public void vueClient() {
		this.removeAll();
		
		this.containerClient = new Container();
		
		this.client = new Client(this);
		
		VueMenu fondMenu = new VueMenu();
		VueStart vs = new VueStart(this.client);
		
		this.containerClient = new JLayeredPane();
		
		this.containerClient.setBounds(0, 0, (int) (600*Menu.SCALE), (int) (600*Menu.SCALE));
		this.containerClient.add(fondMenu, 0, 0);
		this.containerClient.add(vs, 1, 0);
		
		this.add(this.containerClient);
		
		this.setVisible(true);
	}

	public void vueAttente() {
		this.removeAll();
		
		this.containerAttente = new Container();
		
		VueMenu fondMenu = new VueMenu();
		VueAttente va = new VueAttente(this.client);
		
		this.containerAttente = new JLayeredPane();
		
		this.containerAttente.setBounds(0, 0, (int) (600*Menu.SCALE), (int) (600*Menu.SCALE));
		this.containerAttente.add(fondMenu, 0, 0);
		this.containerAttente.add(va, 1, 0);
		
		this.add(this.containerAttente);
		this.setVisible(true);
	}

	public void vueRejete() {
		this.removeAll();
		
		this.containerRejete = new Container();
		ControleurBoutons cplay = new ControleurBoutons(this);
		
		VueMenu fondMenu = new VueMenu();
		VueRejete vr = new VueRejete(cplay);
		
		this.containerRejete = new JLayeredPane();
		
		this.containerRejete.setBounds(0, 0, (int) (600*Menu.SCALE), (int) (600*Menu.SCALE));
		this.containerRejete.add(fondMenu, 0, 0);
		this.containerRejete.add(vr, 1, 0);
		
		this.add(this.containerRejete);
		this.setVisible(true);
	}
	
	public void vueServeur() {
		this.removeAll();
		
		this.containerServeur = new Container();
		this.containerServeur.add(new VueServeur());
		
		this.serveur = new Serveur();
		
		this.add(this.containerServeur);
		
		new Thread(this.serveur).start();
		
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


