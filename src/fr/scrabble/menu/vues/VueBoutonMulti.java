package fr.scrabble.menu.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JPanel;

import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Couleur;

@SuppressWarnings("serial")
public class VueBoutonMulti extends JPanel implements Observer {
	
	Couleur c;
	
	public VueBoutonMulti(ActionListener multi,Couleur c) {
		super();
		this.c = c;
		
		// Creation du Bouton
		JButton b = new BoutonMulti("En ligne", this.c);
		b.addActionListener(multi);
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
		// TODO Auto-generated method stub
		if(o.getClass() == Couleur.class) {
			this.c = (Couleur) o;
			this.repaint();
		}
	}

}

@SuppressWarnings("serial")
class BoutonMulti extends JButton {
	
	Couleur c;
	
	public BoutonMulti(String titre, Couleur c) {
		super(titre);
		this.c = c;
	}
	
	@Override
	protected void paintComponent(Graphics g) { 
		 Font f = new Font(Font.SERIF,Font.CENTER_BASELINE,25);
		 g.setFont(f);
		 g.setColor(c.getColorBoutonVert()[c.getCouleur()]); 
		 g.fillOval(0, 0, this.getSize().width-1, this.getSize().height-1); 
		 super.paintComponent(g);
	}
	
}
