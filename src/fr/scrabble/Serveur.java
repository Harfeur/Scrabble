package fr.scrabble;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import fr.scrabble.structures.Plateau;
import fr.scrabble.structures.Sac;
import fr.scrabble.structures.Score;

public class Serveur {
	
	Plateau plateau;
	Sac sac;
	ArrayList<Score> scores;
	ArrayList<String> joueurs;
	Integer joueurEnCours;
	
	public Serveur() {
		super();
		this.joueurs = new ArrayList<String>();
	}

	private void ouvrirConnection() {
		ServerSocket serverSoc;
		try {
			serverSoc = new ServerSocket(8080);
			boolean attente = true;
			while (attente) {
				Socket connection = serverSoc.accept();
				String ip = connection.getLocalAddress().getHostAddress();
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				System.out.println(ip + " : " + in.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
    public static void main(String[] args) {
		Serveur s = new Serveur();
		s.ouvrirConnection();
	}
	
}
