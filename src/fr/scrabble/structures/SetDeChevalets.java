package fr.scrabble.structures;

import java.util.ArrayList;

public class SetDeChevalets extends ArrayList<Chevalet> {

	private static final long serialVersionUID = -8080944040696744887L;
	private int joueurEnCours;
	
	public SetDeChevalets() {
		super();
		this.joueurEnCours = 0;
	}
	
	public Chevalet chevaletEnCours() {
		return this.get(joueurEnCours);
	}
	
	public void joueurSuivant() {
		++this.joueurEnCours;
		if (this.joueurEnCours == this.size())
			this.joueurEnCours = 0;
	}
	
	public void ajouterChevalet(Chevalet chevalet) {
		this.add(chevalet);
	}
}
