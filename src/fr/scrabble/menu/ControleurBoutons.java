package fr.scrabble.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

import fr.scrabble.online.Client;

public class ControleurBoutons implements ActionListener, ItemListener {
	private ArrayList prenom;
	private Menu menu;
	private int nbjoueur=1;
	private String langue;
	private JComboBox c;
	public ControleurBoutons(Menu menu) {
		super();
		this.menu=menu;
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
			menu.vueHorsLigne(this.nbjoueur, this.langue,prenom);
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
