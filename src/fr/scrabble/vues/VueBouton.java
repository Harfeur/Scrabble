package fr.scrabble.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.*;

import fr.scrabble.menu.Menu;

@SuppressWarnings("serial")
public class VueBouton extends JPanel {

	public VueBouton(ActionListener cb) {
		super();
		JButton b = new JButton("Valider");
		JButton p = new JButton("Passer");
		b.addActionListener(cb);
		p.addActionListener(cb);
		
		b.setContentAreaFilled(false);
		
		p.setContentAreaFilled(false);
		
		this.setPreferredSize(new Dimension((int) (b.getWidth()*2.5),(int) (b.getHeight()*2.5)));
		this.setBounds((int) (VueColonne.TAILLE*Menu.SCALE+VuePlateau.TAILLE*15*Menu.SCALE), 0, 100, 100);
		this.add(b);
		this.add(p);
	}
	
}
