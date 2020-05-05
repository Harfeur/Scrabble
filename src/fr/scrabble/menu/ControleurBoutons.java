package fr.scrabble.menu;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JComboBox;

public class ControleurBoutons implements ItemListener {
	private ArrayList<String> prenom;
	private Menu menu;
	private int nbjoueur=1, diff=0;
	private String langue;

	public ControleurBoutons(Menu menu) {
		super();
		this.menu=menu;
		this.langue = "FR";
		this.prenom = new ArrayList<String>();
	}

	public void changerLangue(int index) {
		switch (index) {
		case 0:
			this.langue = "FR";
			break;
			
		case 1:
			this.langue = "EN";
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

	public void lancerPartie(int nb, ArrayList<String> l, int nbdiff) {
		this.nbjoueur=nb;
		this.prenom=l;
		this.diff=nbdiff;
		menu.vueHorsLigne(this.nbjoueur, this.langue,prenom,this.diff);
	}

	public void nombreJoueur(int nbjoueur) {
		this.nbjoueur=nbjoueur;
		menu.vueNomJoueurHorsLigne(this.nbjoueur);
	}
}
