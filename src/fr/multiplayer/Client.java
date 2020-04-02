package fr.multiplayer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Client extends JFrame {
	
	Socket connection;
	PrintWriter out;
	BufferedReader in;
	String ip;
	String prenom;
	
	//Vues
	VueStart vs;
	
	
	public Client() {
		super("Scrabble Multijoueur");
		
		// On initialise les vues que l'on va utiliser
		VueStart vs = new VueStart(this);
		//VueAttente
		//VueRejeté
		
		// On affiche que celle qu'on a besoin
		Container contentPane = this.getContentPane();
		
		contentPane.setLayout(new BorderLayout());
		
		contentPane.add(vs);
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.pack();
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    
	    this.setLocation(x, y);
		this.setVisible(true);
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
	
	public static void main(String[] args) {
		new Client();
	}

}
