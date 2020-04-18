package fr.scrabble.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

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
	public void changerLangue(int index) {
		switch (index) {
		case 0:
			this.langue = "FR";
			break;
			
		case 1:
			this.langue = "FR";
			break;

		default:
			this.langue = "FR";
			break;
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		@SuppressWarnings("unchecked")
		JComboBox<String> jcb = (JComboBox<String>) e.getSource();
		this.changerLangue(jcb.getSelectedIndex());
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
