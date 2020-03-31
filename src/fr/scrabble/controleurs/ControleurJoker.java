package fr.scrabble.controleurs;

import java.awt.List;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import fr.scrabble.Modele;

public class ControleurJoker implements ItemListener {
	
	Modele modele;
	
	public ControleurJoker(Modele m) {
		this.modele = m;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			List li = (List) e.getSource();
			String lettre = li.getItem((int) e.getItem());
			this.modele.lettreJoker(lettre);
		}
	}

}
