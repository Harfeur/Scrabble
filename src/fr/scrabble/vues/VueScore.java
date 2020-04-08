package fr.scrabble.vues;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class VueScore extends JPanel {

	public VueScore() {
		super();
		JLabel score = new JLabel("TEST");
		this.setPreferredSize(new Dimension(20,20));
		this.setBounds(0, 100, 20, 0);
		this.setBackground(Color.BLACK);
		this.add(score);
	}
}
