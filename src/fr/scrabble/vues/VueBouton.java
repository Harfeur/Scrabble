package fr.scrabble.vues;

import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class VueBouton extends JPanel {

	public VueBouton(ActionListener cb) {
		super();
		JButton b = new JButton("Valider");
		JButton p = new JButton("Passer");
		b.addActionListener(cb);
		p.addActionListener(cb);
		this.add(b);
		this.add(p);
	}
	
}
