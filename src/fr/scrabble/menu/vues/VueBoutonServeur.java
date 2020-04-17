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
public class VueBoutonServeur extends JPanel implements Observer{
	
	Couleur c;
	JButton b;
	
	public VueBoutonServeur(ActionListener serveur, Couleur c) {
		super();
		this.c = c;
		
		// Creation du Bouton
		this.b = new BoutonServeur("Serveur", this.c);
		this.b.addActionListener(serveur);
		this.b.setBorderPainted(false);
		this.b.setContentAreaFilled(false);
		this.b.setPreferredSize(new Dimension((int) (225*Menu.SCALE), (int) (75*Menu.SCALE)));

		// Creation du Panel
		this.setBackground(Color.GREEN);
        this.setBounds((int) (0*Menu.SCALE), (int) (350*Menu.SCALE), (int) (300*Menu.SCALE), (int) (300*Menu.SCALE));
        this.setOpaque(false);
        this.add(b);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(arg.getClass() == Couleur.class) {
			this.c = (Couleur) arg;
			this.repaint();
		}
	}

}

@SuppressWarnings("serial")
class BoutonServeur extends JButton  {
	
	Couleur c;
	
	public BoutonServeur(String titre, Couleur c) {
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