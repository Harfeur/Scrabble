package fr.scrabble.game.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ResourceBundle;

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
import fr.scrabble.structures.Couleur;
import fr.scrabble.structures.Sac;

@SuppressWarnings("serial")
public class VueInstructionBouton extends JPanel implements ActionListener{

	int nbjoueur=1;
	Sac sac;
	JTextField j1,j2,j3,j4;
	JRadioButton un,deux,trois,quatre;
	JButton valide, sauver;
	JTextArea joueur;
	JTextArea langue;
	JComboBox<String> liste_langues;
	ControleurBoutons cb;
	Menu menu;
	Couleur c;
	
	public VueInstructionBouton(Menu menu, ControleurBoutons contb) {
		super();
		this.cb = contb;
		this.menu = menu;
		this.c = menu.couleur;
		this.setLayout(null);
		
		this.liste_langues = new JComboBox<String>();
		liste_langues.setPreferredSize(new Dimension(150, 50));
		liste_langues.setEditable(false);
		liste_langues.addItemListener(cb);
		
		this.un = new JRadioButton();
		this.deux = new JRadioButton();
		this.trois = new JRadioButton();
		this.quatre = new JRadioButton();
		
        this.valide = new JButton();
        this.sauver = new JButton();
        
        this.valide.getModel().setActionCommand("Commencer");
        this.sauver.getModel().setActionCommand("Continuer");
        
        valide.addActionListener(this);
        sauver.addActionListener(this);
        
		ButtonGroup groupe = new ButtonGroup();
		this.un.setSelected(true);
		groupe.add(this.un);
		groupe.add(this.deux);
		groupe.add(this.trois);
		groupe.add(this.quatre);
        this.setPreferredSize(new Dimension(600,600));
        this.setBounds(70, 50, (int) (500*Menu.SCALE), (int) (500*Menu.SCALE));
        this.setOpaque(false);
        
        JPanel pan1 = new JPanel();
        pan1.add(liste_langues);
        pan1.setOpaque(false);
        pan1.setPreferredSize(new Dimension(150,50));
        pan1.setBounds(450, 350, 150, 50);
        
        JPanel pan = new JPanel();
        pan.add(this.un);
        pan.add(this.deux);
        pan.add(this.trois);
        pan.add(this.quatre);
        pan.setOpaque(false);
        pan.setPreferredSize(new Dimension(100,100));
        pan.setBounds(150, 350,100, 150);
        
        this.joueur = new JTextArea();
        Font f = new Font("Arial",Font.BOLD,(int)(10*Menu.SCALE));
        joueur.setFont(f);
        joueur.setBounds(140,315,this.joueur.getText().length()*8,25);
        joueur.setEditable(false);
        joueur.setOpaque(true);
        
        this.langue = new JTextArea();
        Font fl = new Font("Arial",Font.BOLD,(int)(10*Menu.SCALE));
        langue.setFont(fl);
        langue.setBounds(470,315,this.langue.getText().length()*9,25);
        langue.setEditable(false);
        langue.setOpaque(true);
        
        
        this.add(joueur);
        this.add(pan);
        this.add(pan1);
        this.add(langue);
        
        if(testSauvegarde()) {
            valide.setBounds(400, 550, 150,50);
            
            sauver.setBounds(200, 550, 150,50);
            
        	this.add(valide);
        	this.add(sauver);
        }
        else {
        	valide.setBounds(300, 550, 150,50);
            
        	this.add(valide);
        }
        
        
	}
	
	public boolean testSauvegarde() {
		try {
			FileInputStream fis = new FileInputStream(new File("Sac.dat"));
			ObjectInputStream ois = new ObjectInputStream(fis);
			this.sac = (Sac)ois.readObject();
			ois.close();
			fis.close();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
		return true;
	}
	
	public int getNbJoueur() {
		if(un.isSelected()==true) {
			this.nbjoueur=1;
		}else if (deux.isSelected()==true) {
			this.nbjoueur=2;
		}else if (trois.isSelected()==true) {
			this.nbjoueur=3;
		}else if (quatre.isSelected()==true) {
			this.nbjoueur=4;
		}
		return nbjoueur;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Continuer")) {
			this.menu.vueHorsLigne();
		}
		if(e.getActionCommand().equals("Commencer")){
			this.cb.NombreJoueur(getNbJoueur());
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", menu.getLocale());
		this.un.setText(String.format(strings.getString("x_joueurs"), 1));
		this.deux.setText(String.format(strings.getString("x_joueurs"), 2));
		this.trois.setText(String.format(strings.getString("x_joueurs"), 3));
		this.quatre.setText(String.format(strings.getString("x_joueurs"), 4));
		this.sauver.setText(strings.getString("continuer"));
		this.valide.setText(strings.getString("commencer"));
		this.joueur.setText(strings.getString("nb_joueurs"));
		this.langue.setText(strings.getString("langue_jeu"));

		String langues[] = {strings.getString("francais"), strings.getString("anglais")};
		if (this.liste_langues.getItemCount() == 0 || !this.liste_langues.getItemAt(0).equals(langues[0])) {
			this.liste_langues.removeAllItems();
	        for(String langue : langues) {
	        	this.liste_langues.addItem(langue);
	        }
		}
		
		this.valide.setBorderPainted(false);
		this.sauver.setBorderPainted(false);
		
		//Couleur
        this.joueur.setBackground(this.c.getColorBouton());
        this.langue.setBackground(this.c.getColorBouton());
        this.valide.setBackground(this.c.getColorBouton());
        this.sauver.setBackground(this.c.getColorBouton());
        
        this.joueur.setForeground(this.c.getColorLettre());
        this.langue.setForeground(this.c.getColorLettre());
        this.valide.setForeground(this.c.getColorLettre());
        this.sauver.setForeground(this.c.getColorLettre());
        
        langue.setBounds(470,315,this.langue.getText().length()*9,25);
        joueur.setBounds(140,315,this.joueur.getText().length()*8,25);
	}
}



