package fr.scrabble.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import fr.scrabble.online.Client;

public class ControleurBoutons implements ActionListener, ItemListener {
	private ArrayList<String> prenom;
	private Menu menu;
	private int nbjoueur=1;
	private String langue;
	private JComboBox<String> c;
	public ControleurBoutons(Menu menu) {
		super();
		this.menu=menu;
		this.langue = "FR";
		this.prenom = new ArrayList<String>();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand()=="2 joueurs") {
			this.nbjoueur=2;
		}
		if (e.getActionCommand()=="3 joueurs") {
			this.nbjoueur=3;
		}
		if (e.getActionCommand()=="4 joueurs") {
			this.nbjoueur=4;
		}
		if (e.getActionCommand()=="Hors ligne") {
			menu.vueInstructionHorsLigne();
		}
		if (e.getActionCommand()=="En ligne") {
			menu.vueClient();
		}
		if (e.getActionCommand()=="Serveur") {
			menu.vueServeur();
		}
		if (e.getActionCommand()=="Commencer") {
			if(this.nbjoueur<2) {
				//Boîte du message préventif
				JOptionPane jop2 = new JOptionPane();
				JOptionPane.showMessageDialog(null, "Nombre de joueur non choisi", "Attention", JOptionPane.WARNING_MESSAGE);
			}
			else {
				prenom.add("Joueur 1");
				prenom.add("Joueur 2");
				prenom.add("Joueur 3");
				prenom.add("Joueur 4");
				menu.vueHorsLigne(this.nbjoueur, this.langue,prenom);
			}
		}
		if (e.getActionCommand()=="Accueil") {
			menu.vueMenu();
		}
	}
	public void changerLangue(String l) {
		if(l=="English") {
			this.langue="EN";
		}
		else {
			this.langue="FR";
		}
	}
//selectedItemReminder
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
	    this.changerLangue((String) e.getItem());
	}
}
