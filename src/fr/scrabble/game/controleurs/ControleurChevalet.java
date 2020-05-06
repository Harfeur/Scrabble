package fr.scrabble.game.controleurs;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import fr.scrabble.game.Modele;
import fr.scrabble.game.vues.VueChevalet;
import fr.scrabble.menu.Menu;

public class ControleurChevalet implements MouseInputListener {

	private Modele m;
	private Menu menu;
	
	
	public ControleurChevalet(Modele m, Menu menu) {
		super();
		this.m = m;
		this.menu = menu;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int num = (int) ((e.getX()-(22*this.menu.zoom())) / (VueChevalet.TAILLE*this.menu.zoom()*1.15));
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
