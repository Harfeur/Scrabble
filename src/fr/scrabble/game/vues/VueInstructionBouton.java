package fr.scrabble.game.vues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.scrabble.menu.Menu;

public class VueInstructionBouton extends JPanel{
	
	public VueInstructionBouton(ActionListener valider) {
		super();
		this.setLayout(null);
		ComboBox customCombobox = new ComboBox();
		
		ChoixNbJoueur deux = new ChoixNbJoueur("2 joueurs");
		ChoixNbJoueur trois = new ChoixNbJoueur("3 joueurs");
		ChoixNbJoueur quatre = new ChoixNbJoueur("4 joueurs");
		
        JButton valide = new JButton("Commencer");
        
        valide.addActionListener(valider);
        valide.setBounds(300, 550, 150,50);
        
		ButtonGroup groupe = new ButtonGroup();
		groupe.add(deux);
		groupe.add(trois);
		groupe.add(quatre);
		
        this.setPreferredSize(new Dimension(600,600));
        this.setBounds(70, 50, (int) (500*Menu.SCALE), (int) (500*Menu.SCALE));
        this.setOpaque(false);
        
        JPanel pan1 = new JPanel();
        pan1.add(customCombobox);
        pan1.setOpaque(false);
        pan1.setPreferredSize(new Dimension(150,50));
        pan1.setBounds(450, 350, 150, 50);
        
        JPanel pan = new JPanel();
        pan.add(deux);
        pan.add(trois);
        pan.add(quatre);
        pan.setOpaque(false);
        pan.setPreferredSize(new Dimension(100,100));
        pan.setBounds(150, 350,100, 100);
        
        JTextArea joueur = new JTextArea("Nombre de joueur :");
        Font f = new Font("Arial",Font.BOLD,(int)(10*Menu.SCALE));
        joueur.setFont(f);
        joueur.setBounds(140,315,140,25);
        joueur.setBackground(new Color(128, 255, 170));
        joueur.setOpaque(true);
        
        JTextArea langue = new JTextArea("Langue du jeu :");
        Font fl = new Font("Arial",Font.BOLD,(int)(10*Menu.SCALE));
        langue.setFont(fl);
        langue.setBounds(470,315,110,25);
        langue.setBackground(new Color(128, 255, 170));
        langue.setOpaque(true);
        
        this.add(joueur);
        this.add(pan);
        this.add(pan1);
        this.add(langue);
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
