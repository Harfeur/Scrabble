package fr.scrabble.game.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.scrabble.menu.ControleurBoutons;
import fr.scrabble.menu.Menu;

@SuppressWarnings("serial")
public class VueInstructionBouton extends JPanel implements ActionListener{

	int nbjoueur=1;
	JTextField j1,j2,j3,j4;
	ChoixNbJoueur deux,trois,quatre;
	ControleurBoutons cb;
	ItemListener it;
	
	public VueInstructionBouton(ControleurBoutons contb) {
		super();
		this.cb= contb;
		this.setLayout(null);
		ComboBox customCombobox = new ComboBox();
		
		this.deux = new ChoixNbJoueur("2 joueurs");
		this.trois = new ChoixNbJoueur("3 joueurs");
		this.quatre = new ChoixNbJoueur("4 joueurs");
		
        JButton valide = new JButton("Commencer");
        
        valide.addActionListener(this);
        valide.setBounds(300, 550, 150,50);
        
		ButtonGroup groupe = new ButtonGroup();
		this.deux.setSelected(true);
		groupe.add(this.deux);
		groupe.add(this.trois);
		groupe.add(this.quatre);
        this.setPreferredSize(new Dimension(600,600));
        this.setBounds(70, 50, (int) (500*Menu.SCALE), (int) (500*Menu.SCALE));
        this.setOpaque(false);
        
        JPanel pan1 = new JPanel();
        pan1.add(customCombobox);
        pan1.setOpaque(false);
        pan1.setPreferredSize(new Dimension(150,50));
        pan1.setBounds(450, 350, 150, 50);
        
        JPanel pan = new JPanel();
        pan.add(this.deux);
        pan.add(this.trois);
        pan.add(this.quatre);
        pan.setOpaque(false);
        pan.setPreferredSize(new Dimension(100,100));
        pan.setBounds(150, 350,100, 100);
        
        JTextArea joueur = new JTextArea("Nombre de joueur :");
        Font f = new Font("Arial",Font.BOLD,(int)(10*Menu.SCALE));
        joueur.setFont(f);
        joueur.setBounds(140,315,140,25);
        joueur.setBackground(new Color(128, 255, 170));
        joueur.setEditable(false);
        joueur.setOpaque(true);
        
        JTextArea langue = new JTextArea("Langue du jeu :");
        Font fl = new Font("Arial",Font.BOLD,(int)(10*Menu.SCALE));
        langue.setFont(fl);
        langue.setBounds(470,315,110,25);
        langue.setBackground(new Color(128, 255, 170));
        langue.setEditable(false);
        langue.setOpaque(true);
        
        
        this.add(joueur);
        this.add(pan);
        this.add(pan1);
        this.add(langue);
        this.add(valide);       
        
	}
	
	public int getNbJoueur() {
		if(deux.isSelected()==true) {
			this.nbjoueur=2;
		}else if (trois.isSelected()==true) {
			this.nbjoueur=3;
		}else if (quatre.isSelected()==true) {
			this.nbjoueur=4;
		}else {
			//TODO : On garde ou pas ? Car 2 joueurs est sélectionner par défaut 
			//Boîte du message préventif
			JOptionPane jop2 = new JOptionPane();
			JOptionPane.showMessageDialog(null, "Nombre de joueur non choisi", "Attention", JOptionPane.WARNING_MESSAGE);
		}
		return nbjoueur;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		this.cb.NombreJoueur(getNbJoueur());
	}
	
	
}

@SuppressWarnings("serial")
class ChoixNbJoueur extends JRadioButton {
	
	public ChoixNbJoueur(String titre) {
		super(titre);
	}
}

@SuppressWarnings({ "serial", "rawtypes" })
class ComboBox extends JComboBox {
	String langue[] = {"Français", "English"};
	@SuppressWarnings("unchecked")
	public ComboBox() {
		super();

        this.setPreferredSize(new Dimension(150, 50));
        this.setEditable(true);
        for(int i=0; i<langue.length;i++) {
        	this.addItem(langue[i]);
        }
	}
	
}

