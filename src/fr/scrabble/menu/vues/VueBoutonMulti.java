package fr.scrabble.menu.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JPanel;

import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Couleur;

@SuppressWarnings("serial")
public class VueBoutonMulti extends JPanel implements Observer, ActionListener {
	
	Couleur c;
	JButton b;
	Menu menu;
	
	public VueBoutonMulti(Menu menu, Couleur c) {
		super();
		this.menu = menu;
		this.c = c;
		
		// Creation du Bouton
		this.b = new BoutonMulti();
		b.addActionListener(this);
		b.setBorderPainted(false);
		b.setContentAreaFilled(false);
		b.setPreferredSize(new Dimension((int) (225*Menu.SCALE), (int) (75*Menu.SCALE)));

		// Creation du Panel
		this.setBackground(Color.GREEN);
        this.setBounds((int) (260*Menu.SCALE), (int) (190*Menu.SCALE), (int) (300*Menu.SCALE), (int) (300*Menu.SCALE));
        this.setOpaque(false);
        this.add(b);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(o.getClass() == Couleur.class) {
			this.c = (Couleur) o;
			this.repaint();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
		this.b.setText(strings.getString("en_ligne"));
		this.b.setForeground(this.c.getColorLettre()[this.c.getCouleur()]);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		menu.vueClient();
	}
	
	class BoutonMulti extends JButton {
		
		@Override
		protected void paintComponent(Graphics g) { 
			 Font f = new Font(Font.SERIF,Font.CENTER_BASELINE,25);
			 g.setFont(f);
			 g.setColor(c.getColorBouton()[c.getCouleur()]); 
			 g.fillOval(0, 0, this.getSize().width-1, this.getSize().height-1); 
			 super.paintComponent(g);
		}
		
	}

}