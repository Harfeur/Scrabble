package fr.scrabble.multiplayer;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Observable;

import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Sac;

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
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
				System.out.println(inputObject);
                if (inputObject.equals("starting")) {
                    System.out.println("La partie démarre");
        			this.menu.vueEnLigne();
                } else {
                	this.setChanged();
					this.notifyObservers(inputObject);
				}
            }
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
