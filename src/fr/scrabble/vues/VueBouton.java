package fr.scrabble.vues;

import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class VueBouton extends JPanel {

	public VueBouton(ActionListener cb, ActionListener cplay) {
		super();
		JButton b = new JButton("Valider");
		JButton d = new JButton("Demarrer");
		JButton p = new JButton("Passer");
		b.addActionListener(cb);
		d.addActionListener(cplay);
		p.addActionListener(cb);
		this.add(d);
		this.add(b);
		this.add(p);
	}
	
}
