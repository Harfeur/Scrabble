package fr.scrabble.game.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import fr.scrabble.menu.Menu;

public class VueInstructionBouton extends JPanel{
	
	public VueInstructionBouton(ActionListener valider) {
		super();
		ComboBox customCombobox = new ComboBox();
		
		ChoixNbJoueur deux = new ChoixNbJoueur("2 joueurs");
		ChoixNbJoueur trois = new ChoixNbJoueur("3 joueurs");
		ChoixNbJoueur quatre = new ChoixNbJoueur("4 joueurs");
		
        JButton valide = new JButton("Commencer");
        
        valide.addActionListener(valider);
        
		ButtonGroup groupe = new ButtonGroup();
		groupe.add(deux);
		groupe.add(trois);
		groupe.add(quatre);
		
        this.setPreferredSize(new Dimension(200,100));
        this.setBounds(100,100,200,200);
        this.setOpaque(false);
        
        this.add(customCombobox);
        this.add(deux);
        this.add(trois);
        this.add(quatre);
        this.add(valide);
	}

}

@SuppressWarnings("serial")
class ChoixNbJoueur extends JRadioButton {
	
	public ChoixNbJoueur(String titre) {
		super(titre);
	}
}

@SuppressWarnings("serial")
class ComboBox extends JComboBox {
	String langue[] = {"Français", "English"};
	public ComboBox() {
		super();

        this.setPreferredSize(new Dimension(150, 50));
        this.setEditable(true);
        for(int i=0; i<langue.length;i++) {
        	this.addItem(langue[i]);
        }
	}
	
	
}
