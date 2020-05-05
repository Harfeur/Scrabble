package fr.scrabble.online;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import fr.scrabble.game.Modele;
import fr.scrabble.menu.Menu.Vues;
import fr.scrabble.menu.vues.ErrorFrame;
import fr.scrabble.structures.Chevalet;
import fr.scrabble.structures.Score;
import fr.scrabble.structures.SetDeChevalets;

@SuppressWarnings("serial")
public class Serveur extends ArrayList<UserThread> implements Observer, Runnable {

	// Valeurs à partgaer
	Modele modele;
	Integer joueurEnCours;

	// Stats de partie
	boolean gameStarted;
	ArrayList<String> joueurs;

	HashMap<String, Integer> joueursEtID;
	private ServerSocket serverSoc;
	private boolean fin;


	public Serveur(Modele modele) {
		super();
		this.modele = modele;
		this.gameStarted = false;
		this.joueurs = new ArrayList<String>();
		this.joueursEtID = new HashMap<String, Integer>();
		this.fin = false;
	}

	public void ouvrirConnection() {
		try {
			this.serverSoc = new ServerSocket(8080);
			UserThread user;
			while (true) {
				user = new UserThread(serverSoc.accept(), this);
				user.start();
				this.add(user);
			}
		} catch (IOException e) {
			new ErrorFrame(e.getLocalizedMessage());
			e.printStackTrace();
		}
	}

	public boolean ajouterJoueur(String username) {
		if (!this.gameStarted && !this.joueurs.contains(username) && this.joueurs.size() < 4) {
			int id = this.joueurs.size();
			this.joueurs.add(username);
			this.joueursEtID.put(username, id);
			return true;
		} else if (this.gameStarted && this.joueurs.contains(username)) {
			UserThread user = this.remove(this.size()-1);
			this.remove((int) this.joueursEtID.get(username));
			this.add(this.joueursEtID.get(username), user);
			return true;
		}
		return false;
	}

	public void disconnected(String username, UserThread userThread) {
		if (!gameStarted) {
			this.joueurs.remove(username);
			this.joueursEtID.remove(username);
			this.remove(userThread);
		} else if (this.contains(userThread)) {
			int index = this.indexOf(userThread);
			this.remove(index);
			this.add(index, null);
			this.update(null, username + " s'est déconnecté\n");
		}
	}

	public void demarrer() {
		this.joueurEnCours = 0;
		this.modele.addObserver(this);
		this.gameStarted = true;
		for (int i = 0; i < this.size(); ++i) {
			UserThread user = this.get(i);
			user.envoyer("starting");
		}
		this.modele.nouvellePartie(this.size(), "FR", this.joueurs, 0);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg.getClass() == Chevalet.class) {
			UserThread user = this.get(this.joueurEnCours);
			if (user != null)
				user.envoyer(this.modele.chevalets.chevaletEnCours());
		} else if (arg.getClass() == SetDeChevalets.class) {
			SetDeChevalets sdc = (SetDeChevalets) arg;
			for (int i = 0; i < this.size(); ++i) {
				UserThread user = this.get(i);
				if (user != null)
					user.envoyer(sdc.get(i));
			}
		} else if (arg.getClass() == Integer.class) {
			this.joueurEnCours = (Integer) arg;
			for (int i = 0; i < this.size(); ++i) {
				UserThread user = this.get(i);
				if (user != null) {
					user.envoyer(i);
					if (i == joueurEnCours) {
						user.envoyer("your_turn");
					}
				}
			}
		} else if (arg.getClass() == String.class) {
			for (int i = 0; i < this.size(); ++i) {
				UserThread user = this.get(i);
				if (user != null)
					user.envoyer(arg);
			}
		} else if (arg.getClass() == Vues.class) {
			Vues vue = (Vues) arg;
			UserThread user;
			switch (vue) {
			case JOKER:
				user = this.get(this.joueurEnCours);
				if (user != null)
					user.envoyer(Vues.JOKER);
				break;
			case AFFICHER:
				user = this.get(this.joueurEnCours);
				if (user != null)
					user.envoyer(Vues.AFFICHER);
				break;
			case FINALE:
				for (int i = 0; i < this.size(); ++i) {
					user = this.get(i);
					if (user != null)
						user.envoyer(Vues.FINALE);
				}
				this.fin=true;
				break;
			}
		} else {
			for (int i = 0; i < this.size(); ++i) {
				UserThread user = this.get(i);
				if (user != null)
					user.envoyer(arg);
			}
			if (arg.getClass()==Score[].class && fin) {
				try {
					serverSoc.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					System.exit(0);
				}
			}
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

	@Override
	public void run() {
		this.ouvrirConnection();
	}

	public void getData(String username) {
		int id = this.joueursEtID.get(username);
		UserThread user = this.get(id);
		user.envoyer(modele.chevalets.get(id));
		user.envoyer(modele.plateauFictif);
		user.envoyer(id);
		user.envoyer(modele.score);
		user.envoyer(modele.sac);
	}

}
