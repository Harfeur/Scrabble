package fr.scrabble;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	
	public Client() {
		// TODO Auto-generated constructor stub
	}
	
	private void envoyerMessage(String msg) {
		Socket connection;
		try {
			connection = new Socket("127.0.0.1", 8080);
	        PrintWriter out = new PrintWriter(connection.getOutputStream(), true);
	        out.println(msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		client.envoyerMessage("Coucou");
	}

}
