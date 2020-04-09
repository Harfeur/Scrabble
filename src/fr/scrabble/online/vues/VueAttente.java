package fr.scrabble.online.vues;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.scrabble.menu.Menu;
import fr.scrabble.online.Client;
import javafx.scene.paint.Color;

public class VueAttente extends JPanel implements ActionListener{

	Client client;
	
	public VueAttente(Client client) {
		super(new BorderLayout());
		this.client = client;
		
		JLabel txt = new JLabel("En attente");
		txt.setFont(new Font("Arial",Font.BOLD,(int) (25*Menu.SCALE)));
		
		JButton lancerPartie = new JButton("Lancer la partie");
		
		lancerPartie.addActionListener(this);
		
		this.add(txt, BorderLayout.CENTER);
		this.add(lancerPartie, BorderLayout.SOUTH);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		client.demarrer();
	}

}
