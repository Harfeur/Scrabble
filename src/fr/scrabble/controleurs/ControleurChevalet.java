package fr.scrabble.controleurs;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

import fr.scrabble.Modele;
import fr.scrabble.Scrabble;
import fr.scrabble.vues.VueChevalet;

public class ControleurChevalet implements MouseInputListener {

	private Modele m;
	
	public ControleurChevalet(Modele m) {
		super();
		this.m = m;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int num = (int) (e.getX() / (VueChevalet.TAILLE*Scrabble.SCALE));
		m.selectLettre(num);
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
