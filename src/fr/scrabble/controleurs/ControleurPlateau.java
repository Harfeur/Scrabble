package fr.scrabble.controleurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fr.scrabble.Modele;
import fr.scrabble.Scrabble;
import fr.scrabble.vues.VuePlateau;

public class ControleurPlateau implements MouseListener {
	
	private Modele m;
	
	public ControleurPlateau(Modele m) {
		super();
		this.m = m;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int col = (int) (e.getX() / (VuePlateau.TAILLE*Scrabble.SCALE));
		int lig = (int) (e.getY() / (VuePlateau.TAILLE*Scrabble.SCALE));
		//TODO ajouter le numéro du chevalet qui fait l'action m.ajoutLettre(col,lig);
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

}
