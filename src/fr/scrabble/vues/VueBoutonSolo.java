package fr.scrabble.vues;


import javax.swing.JButton;

@SuppressWarnings("serial")
public class VueBoutonSolo  extends JButton{

	public VueBoutonSolo() {
		JButton bouton = new JButton("Solo");
		bouton.setLocation(100,100);
		bouton.setSize(50, 50);
		bouton.setOpaque(false);
		this.add(bouton);

		
	}
}
