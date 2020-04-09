package fr.scrabble.controleurs;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import fr.scrabble.Modele;
import fr.scrabble.menu.Menu;
import fr.scrabble.vues.VuePlateau;

public class ControleurPlateau implements MouseInputListener {
	
	private Modele m;
	
	public ControleurPlateau(Modele m) {
		super();
		this.m = m;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int col = (int) (e.getX() / (VuePlateau.TAILLE*Menu.SCALE));
		int lig = (int) (e.getY() / (VuePlateau.TAILLE*Menu.SCALE));
		if (lig < 15 && col < 15)
			m.ajoutLettre(col, lig);
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

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

}
