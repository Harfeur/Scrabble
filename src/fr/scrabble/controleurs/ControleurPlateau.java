package fr.scrabble.controleurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.event.MouseInputListener;

import fr.scrabble.Modele;
import fr.scrabble.Scrabble;
import fr.scrabble.vues.VuePlateau;

public class ControleurPlateau implements MouseInputListener {
	
	private Modele m;
	
	public ControleurPlateau(Modele m) {
		super();
		this.m = m;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int col = (int) (e.getX() / (VuePlateau.TAILLE*Scrabble.SCALE));
		int lig = (int) (e.getY() / (VuePlateau.TAILLE*Scrabble.SCALE));
		m.ajoutLettre(col, lig);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
