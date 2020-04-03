package fr.scrabble.menu.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class VueBoutonHorsLigne  extends JPanel {

	public VueBoutonHorsLigne(ActionListener solo) {
		super();
		
		// Creation du Bouton
		JButton b = new BoutonHorsLigne("Hors ligne");
		b.addActionListener(solo);
		b.setBorderPainted(false);
		b.setContentAreaFilled(false);
		b.setPreferredSize(new Dimension(150, 50));
	
		// Creation du Panel
		this.setBackground(Color.GREEN);
		this.setBounds(0, 125, 200, 200);
		this.setOpaque(false);
		this.add(b);
	}
	
	 
}

@SuppressWarnings("serial")
class BoutonHorsLigne extends JButton {
	
	public BoutonHorsLigne(String titre) {
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