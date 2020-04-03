package fr.scrabble.menu.vues;

import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class VueBoutonMulti extends JButton{
	
	public VueBoutonMulti(ActionListener multi) {
		super("Multijoueur");
		this.addActionListener(multi);
	}

}
