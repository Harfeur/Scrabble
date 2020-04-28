package fr.scrabble.menu;

import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import fr.scrabble.game.Modele;
import fr.scrabble.game.controleurs.*;
import fr.scrabble.game.vues.*;
import fr.scrabble.menu.vues.*;
import fr.scrabble.online.*;
import fr.scrabble.online.vues.*;
import fr.scrabble.structures.Couleur;
import fr.scrabble.structures.Score;

@SuppressWarnings("serial")
public class Menu extends JFrame implements Observer {

	public static double SCALE = 1.5;
	public static Locale[] LOCALES = {new Locale("fr", "FR"), new Locale("en", "US"), new Locale("es", "MX")};
	
	public enum Vues { AFFICHER, MASQUER, FINALE }

	Container containerChargement, containerMenu, containerHorsLigne, containerNomJoueurHorsLigne, containerInstructionHorsLigne,
	containerEnLigne, containerClient, containerServeur, containerAttente, containerRejete, containerScore;

	Modele modeleHorsLigne;

	// Vues à charger au début
	JPanel fondMenu, vueBoutonHorsLigne, vueBoutonMultijoueur, vueBoutonServeur,
	vueLigne, vueColonne, vueConsole, vueRejete;
	VueScore vueScore;

	boolean vueHorsLigneSombre=true, fin=false;

	Client client;
	Serveur serveur;
	ModeleEnLigne modeleEnLigne;
	
	public Couleur couleur;
	

	public Menu () {
		super("Scrabble");

		this.setLocale(Locale.getDefault());

		this.couleur = new Couleur();
		this.couleur.addObserver(this);

		// Initialisation des Containers
		this.containerChargement = new Container();
		this.containerMenu = new JLayeredPane();
		this.containerClient = new Container();
		this.containerHorsLigne = new Container();
		this.containerNomJoueurHorsLigne = new Container();
		this.containerInstructionHorsLigne = new Container();
		this.containerEnLigne = new Container();
		this.containerServeur = new Container();
		this.containerAttente = new Container();
		this.containerRejete = new Container();
		this.containerScore =  new Container();

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

		JProgressBar loading = new JProgressBar(0, 9);
		
		this.vueChargement(loading);

		// Barre de menu
		VueMenuBar vueMenuBar = new VueMenuBar(this);
		this.setJMenuBar(vueMenuBar);

		// Chargement des Vues
		this.fondMenu = new VueMenu(this.couleur);
		loading.setValue(1);
		
		this.vueBoutonHorsLigne = new VueBoutonHorsLigne(this, this.couleur);
		loading.setValue(2);
		
		this.vueBoutonMultijoueur = new VueBoutonMulti(this, this.couleur);
		loading.setValue(3);
		
		this.vueBoutonServeur = new VueBoutonServeur(this, this.couleur);
		loading.setValue(4);
		
		this.vueLigne = new VueLigne(this);
		loading.setValue(5);
		
		this.vueColonne = new VueColonne(this);
		loading.setValue(6);
		
		this.vueScore = new VueScore(this);
		loading.setValue(7);
		
		this.vueRejete = new VueRejete(this.couleur);
		loading.setValue(8);

		this.vueMenu();
	}

	@Override
	public void removeAll() {
		this.remove(this.containerChargement);
		this.remove(this.containerMenu);
		this.remove(this.containerHorsLigne);
		this.remove(this.containerClient);
		this.remove(this.containerServeur);
		this.remove(this.containerEnLigne);
		this.remove(this.containerAttente);
		this.remove(this.containerRejete);
		this.remove(this.containerInstructionHorsLigne);
		this.remove(this.containerNomJoueurHorsLigne);
		this.remove(this.containerScore);
	}

	public void vueChargement(JProgressBar loading) {
		this.removeAll();
		
		this.containerChargement = new Container();

		VueChargement vueChargement = new VueChargement(this, loading);

		containerChargement.add(vueChargement);

		this.add(containerChargement);

		this.setVisible(true);
	}

	public void vueMenu() {
		this.removeAll();
		
		this.containerMenu = new JLayeredPane();

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

		this.modeleHorsLigne = new Modele(this);
		
		VueMenuBar vueMenuBar = new VueMenuBar(this,this.modeleHorsLigne);
		this.setJMenuBar(vueMenuBar);

		ControleurPlateau cp = new ControleurPlateau(modeleHorsLigne);
		ControleurChevalet cc = new ControleurChevalet(modeleHorsLigne);
		ControleurBouton cb = new ControleurBouton(modeleHorsLigne);

		VuePlateau vuePlateau = new VuePlateau(cp,this);
		VueChevalet vueChevalet = new VueChevalet(cc, this);
		VueBouton vueBouton = new VueBouton(cb,this);
		VueConsole vueConsole = new VueConsole(modeleHorsLigne);

		this.modeleHorsLigne.addObserver(vuePlateau);
		this.modeleHorsLigne.addObserver(vueChevalet);
		this.modeleHorsLigne.addObserver(vueConsole);
		this.modeleHorsLigne.addObserver(vueScore);
		this.modeleHorsLigne.addObserver(this);

		this.containerHorsLigne = new JLayeredPane();


		this.containerHorsLigne.add(vuePlateau);
		this.containerHorsLigne.add(vueLigne);
		this.containerHorsLigne.add(vueColonne);
		this.containerHorsLigne.add(vueChevalet);
		this.containerHorsLigne.add(vueBouton);
		this.containerHorsLigne.add(vueScore);
		this.containerHorsLigne.add(vueConsole,1);

		this.add(this.containerHorsLigne);

		this.modeleHorsLigne.nouvellePartie(nb, l, prenoms);

		this.setVisible(true);
	}
	
	public void vueHorsLigne() {
		this.removeAll();

		this.modeleHorsLigne = new Modele(this);
		
		VueMenuBar vueMenuBar = new VueMenuBar(this,this.modeleHorsLigne);
		this.setJMenuBar(vueMenuBar);

		ControleurPlateau cp = new ControleurPlateau(modeleHorsLigne);
		ControleurChevalet cc = new ControleurChevalet(modeleHorsLigne);
		ControleurBouton cb = new ControleurBouton(modeleHorsLigne);

		VuePlateau vuePlateau = new VuePlateau(cp,this);
		VueChevalet vueChevalet = new VueChevalet(cc, this);
		VueBouton vueBouton = new VueBouton(cb,this);
		VueConsole vueConsole = new VueConsole(modeleHorsLigne);

		this.modeleHorsLigne.addObserver(vuePlateau);
		this.modeleHorsLigne.addObserver(vueChevalet);
		this.modeleHorsLigne.addObserver(vueConsole);
		this.modeleHorsLigne.addObserver(vueScore);
		this.modeleHorsLigne.addObserver(this);

		this.containerHorsLigne = new JLayeredPane();


		this.containerHorsLigne.add(vuePlateau);
		this.containerHorsLigne.add(vueLigne);
		this.containerHorsLigne.add(vueColonne);
		this.containerHorsLigne.add(vueChevalet);
		this.containerHorsLigne.add(vueBouton);
		this.containerHorsLigne.add(vueScore);
		this.containerHorsLigne.add(vueConsole,1);

		this.add(this.containerHorsLigne);

		this.modeleHorsLigne.reprise();

		this.setVisible(true);
	}

	public void vueNomJoueurHorsLigne(int nbJoueur) {
		this.removeAll();

		this.containerNomJoueurHorsLigne = new JLayeredPane();
		
		ControleurBoutons cplay = new ControleurBoutons(this);

		VueNomJoueur vNJ = new VueNomJoueur(this, nbJoueur,cplay, this.couleur);

		containerNomJoueurHorsLigne.setBounds(0, 0, (int) (600*Menu.SCALE), (int) (600*Menu.SCALE));
		containerNomJoueurHorsLigne.add(fondMenu, 0, 0);
		containerNomJoueurHorsLigne.add(vNJ, 1, 0);

		this.add(containerNomJoueurHorsLigne);

		this.setVisible(true);
	}


	public void vueInstructionHorsLigne() {
		this.removeAll();

		this.containerInstructionHorsLigne = new JLayeredPane();
		
		ControleurBoutons cplay = new ControleurBoutons(this);

		VueInstructionBouton vueInstru = new VueInstructionBouton(this, cplay);

		containerInstructionHorsLigne.setBounds(0, 0, (int) (600*Menu.SCALE), (int) (600*Menu.SCALE));
		containerInstructionHorsLigne.add(fondMenu, 0, 0);
		containerInstructionHorsLigne.add(vueInstru, 1, 0);

		this.add(containerInstructionHorsLigne);

		this.setVisible(true);
	}
	public void vueEnLigne() {
		this.removeAll();

		this.modeleEnLigne = new ModeleEnLigne(this.client, this);

		ControleurPlateau cp = new ControleurPlateau(modeleEnLigne);
		ControleurChevalet cc = new ControleurChevalet(modeleEnLigne);
		ControleurBouton cb = new ControleurBouton(modeleEnLigne);

		VuePlateau vuePlateau = new VuePlateau(cp,this);
		VueChevalet vueChevalet = new VueChevalet(cc, this);
		VueBouton vueBouton = new VueBouton(cb,this);
		VueConsole vueConsole = new VueConsole(modeleHorsLigne);

		this.client.addObserver(vuePlateau);
		this.client.addObserver(vueChevalet);
		this.client.addObserver(vueConsole);
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

		VueStart vs = new VueStart(this.client, this.couleur);

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

		VueAttente va = new VueAttente(this.client, this.couleur);

		this.containerAttente = new JLayeredPane();

		this.containerAttente.setBounds(0, 0, (int) (600*Menu.SCALE), (int) (600*Menu.SCALE));
		this.containerAttente.add(fondMenu, 0, 0);
		this.containerAttente.add(va, 1, 0);

		this.add(this.containerAttente);
		this.setVisible(true);
	}

	public void vueRejete() {
		this.removeAll();

		this.containerRejete = new JLayeredPane();

		this.containerRejete.setBounds(0, 0, (int) (600*Menu.SCALE), (int) (600*Menu.SCALE));
		this.containerRejete.add(fondMenu, 0, 0);
		this.containerRejete.add(vueRejete, 1, 0);

		this.add(this.containerRejete);
		this.setVisible(true);
	}

	public void vueServeur() {
		this.removeAll();
		
		this.modeleHorsLigne = new Modele(this);

		this.serveur = new Serveur(this.modeleHorsLigne);
		
		VuePlateau vuePlateau = new VuePlateau(null,this);
		VueScore vueScore = new VueScore(this);

		this.modeleHorsLigne.addObserver(vuePlateau);
		this.modeleHorsLigne.addObserver(vueScore);
		
		this.containerServeur = new JLayeredPane();

		this.containerServeur.add(vuePlateau);
		this.containerServeur.add(vueLigne);
		this.containerServeur.add(vueColonne);
		this.containerServeur.add(vueScore,1);

		this.add(this.containerServeur);

		new Thread(this.serveur).start();

		this.setVisible(true);
	}
	
	public void vueFinale(Score[] score) {
		this.removeAll();
		
		this.containerScore =  new JLayeredPane();
		
		this.modeleHorsLigne.suppFile();
		
		
		containerScore.add(new VueScoreFin(),0,0);
		containerScore.add(new VueScoreFin(score),1,0);
		this.add(containerScore);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Menu();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg != null && arg.getClass() == Vues.class) {
			Vues vue = (Vues) arg;
			if (vue.equals(Vues.MASQUER)) {
				this.setVisible(false);
			}
			if (vue.equals(Vues.AFFICHER)) {
				this.setVisible(true);
			}
			if (vue.equals(Vues.FINALE)) {
				this.fin=true;
			}
		}
		if (arg != null) {
			if (arg.getClass() == Score[].class && this.fin) {
				System.out.println("Partie terminée !");
					this.vueFinale((Score[]) arg);
			}
		}
		else {
			if(o.getClass() == Couleur.class) {
				this.repaint();
			}
			
		}
			
	}

	@Override
	public void setLocale(Locale l) {
		super.setLocale(l);
		this.repaint();
	}
}


