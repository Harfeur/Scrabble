package fr.scrabble.game.controleurs;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;

import fr.scrabble.game.Modele;

public class ControleurBouton implements ActionListener {
	
	Modele m;

	public ControleurBouton(Modele m) {
		super();
		this.m = m;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand()=="Valider") {
			new Thread(new Runnable() {
	            public void run() {
	    			m.verificationMot();
	            }
			}).start();
		}
		if (e.getActionCommand()=="Passer") {
			new Thread(new Runnable() {
	            public void run() {
	    			m.changementJoueur();
	            }
			}).start();
		}
		if (e.getActionCommand()=="Melanger") {
			new Thread(new Runnable() {
	            public void run() {
	    			m.melanger();
	            }
			}).start();
		}
		
	}

}
