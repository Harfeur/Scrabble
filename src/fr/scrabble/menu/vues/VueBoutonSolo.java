package fr.scrabble.menu.vues;


import java.awt.event.ActionListener;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class VueBoutonSolo  extends JButton{

	public VueBoutonSolo(ActionListener solo) {
		super("Solo");
		this.addActionListener(solo);
	}
}
