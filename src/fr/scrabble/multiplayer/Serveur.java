package fr.scrabble.multiplayer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import fr.scrabble.Modele;

@SuppressWarnings("serial")
public class Serveur extends ArrayList<UserThread> {
	
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
			/*
			boolean attente = true;
			while (attente) {
				Socket connection = this.serverSoc.accept();
				String ip = connection.getRemoteSocketAddress().toString();
				ip = ip.substring(ip.indexOf("/") + 1);
				ip = ip.substring(0, ip.indexOf(":"));
				PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String message = in.readLine();
				System.out.println(message);
				if (message.equals("gameStart")) attente = false;
				else {
					if (ips.size() == 4) out.println("full");
					else {
						this.joueurs.add(message);
						this.ips.add(ip);
						this.outs.add(out);
						out.println("ok");
						System.out.println(ip + " : " + message);
					}
				}
			}
			for (PrintWriter out : this.outs) {
				out.println("starting");
			}
			*/
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
		this.modele.nouvellePartie(this.size(), "FR");
		for (int i = 0; i < this.size(); ++i) {
			UserThread user = this.get(i);
			user.envoyer("starting");
			user.envoyer(this.modele.plateau);
			user.envoyer(this.modele.sac);
			user.envoyer(this.modele.score);
			user.envoyer(this.modele.numChevalet);
			user.envoyer(this.modele.chevalets[this.modele.numChevalet]);
		}
	}
	
}
