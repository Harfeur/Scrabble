package fr.scrabble.multiplayer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class UserThread extends Thread {

	private Serveur serveur;
	private Socket clientSocket;
	private BufferedReader in;
	private ObjectOutputStream out;

	String username;

	public UserThread(Socket socket, Serveur serveur) {
		this.clientSocket = socket;
		this.serveur = serveur;
	}
	
	public void envoyer(Object obj) {
		try {
			this.out.writeObject(obj);
			this.out.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			out = new ObjectOutputStream(this.clientSocket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			this.username = in.readLine();

			String inputLine;
			if (this.serveur.ajouterJoueur(this.username)) {
				out.writeObject("gameJoined");
				while ((inputLine = in.readLine()) != null) {
					System.out.println(this.username + " : " + inputLine);
					switch (inputLine) {
					case "gameStart":
						this.serveur.demarrer();
						break;
					case "verifMot":
						this.serveur.verificationMot(this.username);
						break;
					case "changeJoueur":
						this.serveur.changementJoueur(this.username);
						break;
					case "selectLettre":
						this.serveur.selectLettre(this.username, Integer.parseInt(in.readLine()));
						break;
					case "ajoutLettre":
						this.serveur.ajoutLettre(this.username, Integer.parseInt(in.readLine()), Integer.parseInt(in.readLine()));
						break;
					default:
						break;
					}
				}
			}
			else {
				out.writeObject("full");
			}
			in.close();
			out.close();
			clientSocket.close();
		} catch (SocketException e) {
			if (this.username != null) System.out.print(this.username + " : ");
			System.out.println("Le joueur a quitté la partie");
		}
		catch (IOException e) {
			e.printStackTrace();
		} finally {
			this.serveur.remove(this);
		}
	}

}
