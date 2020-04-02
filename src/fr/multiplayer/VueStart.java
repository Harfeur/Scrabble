package fr.multiplayer;

import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class VueStart extends Panel implements ActionListener {
	
	Client client;
	Frame f;

	public VueStart(Client client) {
		super();
		this.client = client;
		//TODO Afficher deux zones de texte (prénom, ip) et un bouton (Valider)
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO Lire zone de texte lorsque bouton pressé puis envoyer à client.demarrer(message);
		//TODO Puis fermer la fenetre
	}

}
