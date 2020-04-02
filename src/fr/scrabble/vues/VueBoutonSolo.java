package fr.scrabble.vues;


import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class VueBoutonSolo  extends JButton{

	public VueBoutonSolo(ActionListener cplay) {
		super("Solo");
		this.addActionListener(cplay);
	}
}
