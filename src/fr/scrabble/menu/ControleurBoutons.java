package fr.scrabble.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class ControleurBoutons implements ActionListener, ItemListener {
	private ArrayList<String> prenom;
	private Menu menu;
	private int nbjoueur=1;
	private String langue;
	
	public ControleurBoutons(Menu menu) {
		super();
		this.menu=menu;
		this.langue = "FR";
		this.prenom = new ArrayList<String>();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand()=="Hors ligne") {
			menu.vueInstructionHorsLigne();
		}
		if (e.getActionCommand()=="En ligne") {
			menu.vueClient();
		}
		if (e.getActionCommand()=="Serveur") {
			menu.vueServeur();
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
	
	public void lancerPartie(int nb, ArrayList<String> l) {
		this.nbjoueur=nb;
		this.prenom=l;
		menu.vueHorsLigne(this.nbjoueur, this.langue,prenom);
	}
	
	public void NombreJoueur(int nbjoueur) {
		this.nbjoueur=nbjoueur;
		menu.vueNomJoueurHorsLigne(this.nbjoueur);
	}
}
