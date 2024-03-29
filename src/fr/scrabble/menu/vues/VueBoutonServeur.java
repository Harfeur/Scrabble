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
public class VueBoutonServeur extends JPanel implements Observer, ActionListener {
	
	Couleur c;
	JButton b;
	Menu menu;
	
	public VueBoutonServeur(Menu menu, Couleur c) {
		super();
		this.c = c;
		this.menu = menu;
		
		// Creation du Bouton
		this.b = new BoutonServeur();
		this.b.addActionListener(this);
		this.b.setBorderPainted(false);
		this.b.setContentAreaFilled(false);
		this.b.setPreferredSize(new Dimension((int) (225*Menu.SCALE), (int) (75*Menu.SCALE)));

		// Creation du Panel
		this.setBackground(Color.GREEN);
        this.setBounds((int) (125*Menu.SCALE), (int) (350*Menu.SCALE), (int) (300*Menu.SCALE), (int) (300*Menu.SCALE));
        this.setOpaque(false);
        this.add(b);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Couleur) {
			this.c = (Couleur) o;
			this.repaint();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		this.setBounds((int) (125*this.menu.zoom()+this.menu.decalageX()), (int) (350*this.menu.zoom()+this.menu.decalageY()), (int) (300*this.menu.zoom()), (int) (300*this.menu.zoom()));

		ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
		this.b.setText(strings.getString("serveur"));
		this.b.setForeground(this.c.getColorLettre());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		menu.vueServeur();
	}

	class BoutonServeur extends JButton  {
		
		@Override
		protected void paintComponent(Graphics g) { 
			 Font f = new Font(Font.SERIF,Font.CENTER_BASELINE,25);
			 g.setFont(f);
			 g.setColor(c.getColorBouton()); 
			 g.fillOval(0, 0, this.getSize().width-1, this.getSize().height-1); 
			 super.paintComponent(g);
		}
	}
}
