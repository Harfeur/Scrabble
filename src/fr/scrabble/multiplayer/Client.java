package fr.scrabble.multiplayer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	
	Socket connection;
	PrintWriter out;
	BufferedReader in;
	String ip;
	String prenom;
	
	
	public Client() {
		
	}
	
	public void demarrer(String ip, String prenom) {
		try {
			this.ip = ip;
			this.prenom = prenom;
			this.connection = new Socket(this.ip, 8080);
	        this.out = new PrintWriter(this.connection.getOutputStream(), true);
	        this.in = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
	        out.println(prenom);
	        String resp = in.readLine();
	        if (resp.equals("ok")) {
	        	System.out.println("Connecté");
	        	//TODO On change la fenetre pour afficher le texte "En attente du lancement" ainsi qu'un bouton "Lancer maintenant"
	        	/*
	        	 * this.remove(this.vs);
	        	 * this.add(this.va);
	        	 * this.pack();
	        	 */
	        } else {
				//TODO Fin du programme, on affiche "Partie injoignable (pleine ou serveur fermé)" puis on laisse l'utilisateur fermer la fenetre
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
