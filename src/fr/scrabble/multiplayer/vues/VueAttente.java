package fr.scrabble.multiplayer.vues;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.scrabble.menu.Menu;
import fr.scrabble.multiplayer.Client;
import javafx.scene.paint.Color;

public class VueAttente extends JPanel implements ActionListener{

	Client client;
	Menu menu = new Menu();
	
	public VueAttente(Client client) {
		super(new BorderLayout());
		this.client = client;
		
		JLabel txt = new JLabel("En attente");
		txt.setFont(new Font("Arial",Font.BOLD,(int) (25*menu.SCALE)));
		
		JButton lancerPartie = new JButton("Lancer la partie");
		
		lancerPartie.addActionListener(this);
		
		this.add(txt, BorderLayout.CENTER);
		this.add(lancerPartie, BorderLayout.SOUTH);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//client.demarrer();
		System.out.println("coucou");
	}

}
