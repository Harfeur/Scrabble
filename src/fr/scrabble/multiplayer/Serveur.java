package fr.scrabble.multiplayer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
	ArrayList<String> joueurs, ips;
	Integer joueurEnCours;

	ArrayList<PrintWriter> outs;
	ServerSocket serverSoc;
	
	public Serveur() {
		super();
		this.joueurs = new ArrayList<String>();
		this.ips = new ArrayList<String>();
		this.outs = new ArrayList<PrintWriter>();
		try {
			this.serverSoc = new ServerSocket(8080);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void ouvrirConnection() {
		try {
			boolean attente = true;
			while (attente) {
				Socket connection = this.serverSoc.accept();
				String ip = connection.getRemoteSocketAddress().toString();
				PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String message = in.readLine();
				System.out.println(ip);
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
 
    public static void main(String[] args) {
		Serveur s = new Serveur();
		s.ouvrirConnection();
	}
	
}
