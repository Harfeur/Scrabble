package fr.scrabble.game.controleurs;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import fr.scrabble.game.Modele;
import fr.scrabble.game.vues.VueChevalet;
import fr.scrabble.menu.Menu;

public class ControleurChevalet implements MouseInputListener {

	private Modele m;
	
	public ControleurChevalet(Modele m) {
		super();
		this.m = m;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int num = (int) (e.getX() / (VueChevalet.TAILLE*Menu.SCALE));
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

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}

}
