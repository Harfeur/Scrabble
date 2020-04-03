package fr.scrabble.menu.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import fr.scrabble.menu.Menu;

@SuppressWarnings("serial")
public class VueBoutonMulti extends JPanel {
	
	public VueBoutonMulti(ActionListener multi) {
		super();
		
		// Creation du Bouton
		JButton b = new BoutonMulti("En ligne");
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
	
	 

}

@SuppressWarnings("serial")
class BoutonMulti extends JButton {
	
	public BoutonMulti(String titre) {
		super(titre);
	}
	
	@Override
	protected void paintComponent(Graphics g) { 
		 Font f = new Font(Font.SERIF,Font.CENTER_BASELINE,25);
		 g.setFont(f);
		 g.setColor(new Color(128, 255, 170)); 
		 g.fillOval(0, 0, this.getSize().width-1, this.getSize().height-1); 
		 super.paintComponent(g);
	}
	
}
