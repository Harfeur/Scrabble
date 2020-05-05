package fr.scrabble.online;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.Observable;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import fr.scrabble.menu.Menu;
import fr.scrabble.menu.vues.ErrorFrame;

public class Client extends Observable implements Runnable {
	
	Menu menu;
	
	Socket connection;
	PrintWriter out;
	ObjectInputStream in;
	
	Thread data;
	
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
	        this.in = new ObjectInputStream(this.connection.getInputStream());
	        out.println(prenom);
	        String resp = (String) in.readObject();
	        if (resp.equals("gameJoined")) {
	        	System.out.println("Connecté");
	        	this.menu.vueAttente();
	        	this.data = new Thread(this);
	        	this.data.start();
	        } else {
	        	this.menu.vueRejete();
			}
		} catch (IOException e) {
			this.menu.vueRejete();
		} catch (ClassNotFoundException e) {
			this.menu.dispose();
			new ErrorFrame(e.getLocalizedMessage());
		}
	}
	
	public void demarrer() {
		out.println("gameStart");
	}
	
	public void message(String message) {
		out.println(message);
	}

	@Override
	public void run() {
		try {
			Object inputObject;
			while ((inputObject = in.readObject()) != null) {
                if (inputObject.equals("starting")) {
                    System.out.println("La partie démarre");
        			this.menu.vueEnLigne();
                } else if (inputObject.equals("your_turn")) {
                	try {
            			URL url = Client.class.getResource("/resources/sounds/turn.wav");
            			Clip clip = AudioSystem.getClip();
            			AudioInputStream ais = AudioSystem.getAudioInputStream(url);
            			clip.open(ais);
            			clip.loop(0);
            		} catch (Exception e) {
            			e.printStackTrace();
            		}
                } else {
                	this.setChanged();
					this.notifyObservers(inputObject);
				}
            }
		} catch (IOException e) {
			this.menu.dispose();
			new ErrorFrame("La connexion avec le serveur a été perdue.");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			this.menu.dispose();
			new ErrorFrame(e.getLocalizedMessage());
		}
	}

}
