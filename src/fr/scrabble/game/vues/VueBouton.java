package fr.scrabble.game.vues;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.*;

import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Couleur;

@SuppressWarnings("serial")
public class VueBouton extends JPanel{
	JButton b,p,m;
	Menu menu;
	Couleur c;
	
	public VueBouton(ActionListener cb, Menu menu) {
		super();
		this.b = new JButton();
		this.p = new JButton();
		this.m = new JButton();
		
		this.menu=menu;
		this.c = menu.couleur;
		
		b.getModel().setActionCommand("Valider");
		p.getModel().setActionCommand("Passer");
		m.getModel().setActionCommand("Melanger");
		
		b.addActionListener(cb);
		p.addActionListener(cb);
		m.addActionListener(cb);
		
		b.setContentAreaFilled(false);
		p.setContentAreaFilled(false);
		m.setContentAreaFilled(false);
		
		b.setOpaque(true);
		p.setOpaque(true);
		m.setOpaque(true);
		
		this.setPreferredSize(new Dimension((int) (b.getWidth()*2.5),(int) (b.getHeight()*2.5)));
		this.setBounds((int) (VueColonne.TAILLE*Menu.SCALE+VuePlateau.TAILLE*15*Menu.SCALE), 0, 100, 100);
		this.add(b);
		this.add(p);
		this.add(m);
		this.setOpaque(false);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
		this.b.setText(strings.getString("valider"));
		this.p.setText(strings.getString("passer"));
		this.m.setText(strings.getString("melanger"));
		
		this.b.setBackground(this.c.getColorBouton());
		this.p.setBackground(this.c.getColorBouton());
		this.m.setBackground(this.c.getColorBouton());
	}
	
}
