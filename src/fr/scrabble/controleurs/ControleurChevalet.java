package fr.scrabble.controleurs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import fr.scrabble.Modele;
import fr.scrabble.Scrabble;

public class ControleurChevalet implements MouseListener {

	private Modele m;
	
	public ControleurChevalet(Modele m) {
		super();
		this.m = m;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int num = (int) (e.getX() / (20*Scrabble.SCALE));
		m.selectLettre(num);
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
