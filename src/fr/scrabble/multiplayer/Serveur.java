package fr.scrabble.multiplayer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import fr.scrabble.Modele;
import fr.scrabble.structures.Chevalet;
import fr.scrabble.structures.Sac;

@SuppressWarnings("serial")
public class Serveur extends ArrayList<UserThread> implements Observer {
	
	// Valeurs à partgaer
	Modele modele;
	Integer joueurEnCours;
	
	// Stats de partie
	boolean gameStarted;
	ArrayList<String> joueurs;
	
	
	public Serveur() {
		super();
		this.gameStarted = false;
		this.joueurs = new ArrayList<String>();
	}

	public void ouvrirConnection() {
		try {
			ServerSocket serverSoc = new ServerSocket(8080);
			UserThread user;
			while (true) {
	            user = new UserThread(serverSoc.accept(), this);
	            user.start();
	            this.add(user);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Serveur s = new Serveur();
		s.ouvrirConnection();
	}

	public boolean ajouterJoueur(String username) {
		if (!this.gameStarted && !this.joueurs.contains(username) && this.joueurs.size() < 4) {
			this.joueurs.add(username);
			return true;
		}
		return false;
	}

	public void demarrer() {
		this.joueurEnCours = 0;
		this.modele = new Modele();
		this.modele.addObserver(this);
		for (int i = 0; i < this.size(); ++i) {
			UserThread user = this.get(i);
			user.envoyer("starting");
		}
		this.modele.nouvellePartie(this.size(), "FR");
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println(arg);
		if (arg.getClass() == Chevalet.class) {
			UserThread user = this.get(this.joueurEnCours);
			user.envoyer(this.modele.chevalets[this.modele.numChevalet]);
		} else if (arg.getClass() == Integer.class) {
			this.joueurEnCours = (Integer) arg;
		} else if (arg.getClass() == String.class) {
			return;
		}
		else {
			for (int i = 0; i < this.size(); ++i) {
				UserThread user = this.get(i);
				user.envoyer(arg);
			}
		}
		if (arg.getClass() == Sac.class) {
			Sac s = (Sac) arg;
			System.out.println(s.size());
		}
	}

	public void verificationMot(String username) {
		if (this.joueurs.get(this.joueurEnCours).equals(username))
			this.modele.verificationMot();
	}

	public void changementJoueur(String username) {
		if (this.joueurs.get(this.joueurEnCours).equals(username))
			this.modele.changementJoueur();
	}

	public void selectLettre(String username, int num) {
		if (this.joueurs.get(this.joueurEnCours).equals(username))
			this.modele.selectLettre(num);
	}
	
	public void ajoutLettre(String username, int col, int lig) {
		if (this.joueurs.get(this.joueurEnCours).equals(username))
			this.modele.ajoutLettre(col, lig);
	}
	
}
