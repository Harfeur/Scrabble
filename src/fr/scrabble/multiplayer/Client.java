package fr.scrabble.multiplayer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import fr.scrabble.menu.Menu;

public class Client {
	
	Menu menu;
	
	Socket connection;
	PrintWriter out;
	BufferedReader in;
	String ip;
	String prenom;
	
	
	public Client(Menu menu) {
		this.menu = menu;
	}
	
	public void rejoindre(String ip, String prenom) {
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
	        	this.menu.vueAttente();
	        	/*
	        	resp = in.readLine();
	        	while (!resp.equals("starting")) {
	        		resp = in.readLine();
	        	}
	        	System.out.println("Le jeu démarre");
	        	menu.vueEnLigne();
	        	*/
	        } else {
	        	this.menu.vueRejete();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void demarrer() {
		out.println("gameStart");
	}

}
