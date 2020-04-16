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
import fr.scrabble.online.JTextFieldLimit;

@SuppressWarnings("serial")
public class VueInstructionBouton extends JPanel implements ActionListener{

	ArrayList<String> prenom;
	int nbjoueur=1;
	JTextField j1,j2,j3,j4;
	ChoixNbJoueur deux,trois,quatre;
	ControleurBoutons cb;
	ItemListener it;
	
	public VueInstructionBouton(ControleurBoutons contb) {
		super();
		this.cb= contb;
		this.prenom = new ArrayList<String>();
		this.setLayout(null);
		ComboBox customCombobox = new ComboBox();
		
		deux = new ChoixNbJoueur("2 joueurs");
		trois = new ChoixNbJoueur("3 joueurs");
		quatre = new ChoixNbJoueur("4 joueurs");
		
        JButton valide = new JButton("Commencer");
        
        valide.addActionListener(this);
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
        joueur.setEditable(false);
        joueur.setOpaque(true);
        
        JTextArea langue = new JTextArea("Langue du jeu :");
        Font fl = new Font("Arial",Font.BOLD,(int)(10*Menu.SCALE));
        langue.setFont(fl);
        langue.setBounds(470,315,110,25);
        langue.setBackground(new Color(128, 255, 170));
        langue.setEditable(false);
        langue.setOpaque(true);
        
        JPanel pan2 = new JPanel();
        this.j1 = new JTextField("Joueur 1", 10);
        this.j2 = new JTextField("Joueur 2", 10);
        this.j3= new JTextField("Joueur 3", 10);
        this.j4 = new JTextField("Joueur 4", 10);
        this.j1.setDocument(new JTextFieldLimit(15));
        this.j2.setDocument(new JTextFieldLimit(15));
        this.j3.setDocument(new JTextFieldLimit(15));
        this.j4.setDocument(new JTextFieldLimit(15));
        pan2.add(j1);
        pan2.add(j2);
        pan2.add(j3);
        pan2.add(j4);
        pan2.setOpaque(false);
        pan2.setPreferredSize(new Dimension(300,100));
        pan2.setBounds(295, 140,150, 150);
        
        JTextArea nomjoueur = new JTextArea("Noms des joueurs :");
        nomjoueur.setFont(f);
        nomjoueur.setBounds(300,110,140,25);
        nomjoueur.setBackground(new Color(128, 255, 170));
        nomjoueur.setEditable(false);
        nomjoueur.setOpaque(true);
        
        
        this.add(joueur);
        this.add(nomjoueur);
        this.add(pan);
        this.add(pan1);
        this.add(pan2);
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
			//Boîte du message préventif
			JOptionPane jop2 = new JOptionPane();
			JOptionPane.showMessageDialog(null, "Nombre de joueur non choisi", "Attention", JOptionPane.WARNING_MESSAGE);
		}
		return nbjoueur;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		this.prenom.add(this.j1.getText());
		this.prenom.add(this.j2.getText());
		this.prenom.add(this.j3.getText());
		this.prenom.add(this.j4.getText());
		this.cb.setInstruc(getNbJoueur(), this.prenom);
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

