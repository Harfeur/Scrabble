package fr.scrabble.menu.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fr.scrabble.game.utils.JTextFieldLimit;
import fr.scrabble.menu.ControleurBoutons;
import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Couleur;

@SuppressWarnings("serial")
public class VueNomJoueur extends JPanel implements ActionListener, Observer{

	ArrayList<String> prenom;
	JRadioButton pc,pc1,pc2,pc3;
	JTextField j1,j2,j3,j4;
	int nbjoueur=1, nbpc=0;
	ControleurBoutons cb;
	JLabel label,label_diff;
	JComboBox<String> liste_difficulte;
	Menu menu;
	JButton b;
	Couleur c;
	
	public VueNomJoueur(Menu menu, int nbjoueur, ControleurBoutons cb, Couleur c) {
		super();
		this.prenom = new ArrayList<String>();
		this.nbjoueur=nbjoueur;
		this.cb=cb;
		this.menu = menu;
		this.c = c;
		this.setLayout(null);
		
        this.setPreferredSize(new Dimension(600,600));
        this.setBounds(70, 50, (int) (500*Menu.SCALE), (int) (500*Menu.SCALE));
        this.setOpaque(false);
		
		Font f = new Font("Arial",Font.BOLD,(int)(10*Menu.SCALE));
		
		
		//Liste diff		
		this.liste_difficulte = new JComboBox<String>();
		liste_difficulte.setPreferredSize(new Dimension(150, 50));
		liste_difficulte.setEditable(false);


        JPanel pan1 = new JPanel();
        pan1.add(liste_difficulte);
        pan1.setOpaque(false);
        pan1.setPreferredSize(new Dimension(150,50));
        pan1.setBounds(450, 350, 150, 50);
		
		//Creation zone de texte
		this.j1 = new JTextField("Joueur 1", 10);
	    this.j2 = new JTextField("Joueur 2", 10);
	    this.j3= new JTextField("Joueur 3", 10);
	    this.j4 = new JTextField("Joueur 4", 10);
	    
	    //Limite de 15 caracteres
	    this.j1.setDocument(new JTextFieldLimit(15));
	    this.j2.setDocument(new JTextFieldLimit(15));
	    this.j3.setDocument(new JTextFieldLimit(15));
	    this.j4.setDocument(new JTextFieldLimit(15));
	    
	    //Creation JRadioButton ordi
	    this.pc = new JRadioButton();	    
	    this.pc1 = new JRadioButton();
	    this.pc2 = new JRadioButton();
	    this.pc3 = new JRadioButton();
	    
	    ButtonGroup groupe = new ButtonGroup();
		this.pc.setSelected(true);
		groupe.add(this.pc);
		groupe.add(this.pc1);
		groupe.add(this.pc2);
		groupe.add(this.pc3);
	    
	    //Ajout police
	    this.j1.setFont(f);
	    this.j2.setFont(f);
	    this.j3.setFont(f);
	    this.j4.setFont(f);
	    this.pc.setFont(f);
	    this.pc1.setFont(f);
	    this.pc2.setFont(f);
	    this.pc3.setFont(f);
	    
	    
	    JPanel pan = new JPanel();
        pan.setOpaque(false);
        pan.setPreferredSize(new Dimension(100,100));
        pan.setBounds(100, 350,200, 300);
	    //Ajout jtextfield au JPanel en fonction nbjoueur
	    if(nbjoueur == 1) {
	    	this.pc1.setSelected(true);
	    	this.pc.setSelected(false);
	    	pan.add(j1);
	    	pan.add(pc1);
	    	pan.add(pc2);
	    	pan.add(pc3);
	    }
	    else if (nbjoueur == 2) {
	    	pan.add(j1);
	    	pan.add(j2);
	    	pan.add(pc);
	    	pan.add(pc1);
	    	pan.add(pc2);
	    }
	    else if (nbjoueur == 3) {
	    	pan.add(j1);
	    	pan.add(j2);
	    	pan.add(j3);
	    	pan.add(pc);
	    	pan.add(pc1);
	    }
	    else if (nbjoueur == 4) {
	    	pan.add(j1);
	    	pan.add(j2);
	    	pan.add(j3);
	    	pan.add(j4);
	    }
	    
	    //Bouton validation
	    this.b = new JButton();
	    this.b.setFont(f);
	    this.b.setBackground(this.c.getColorBouton());
	    this.b.setForeground(this.c.getColorLettre());
	    this.b.addActionListener(this);
	    this.b.setBounds(300, 550, 150,50);
	    this.add(b);
	    
	    this.label = new JLabel();
        label.setFont(f);
        label.setBounds(140,315,140,25);
        label.setOpaque(true);
        
        this.label_diff = new JLabel();
        label_diff.setFont(f);
        label_diff.setBounds(470,315,110,25);
        label_diff.setOpaque(true);
	    
	    this.add(label);
        this.add(pan);
        this.add(pan1);
        this.add(label_diff);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg) {
		//Ajout prenom joueur
		if(nbjoueur == 1) {
			this.prenom.add(this.j1.getText());
	    }
	    else if (nbjoueur == 2) {
			this.prenom.add(this.j1.getText());
			this.prenom.add(this.j2.getText());
	    }
	    else if (nbjoueur == 3) {
			this.prenom.add(this.j1.getText());
			this.prenom.add(this.j2.getText());
			this.prenom.add(this.j3.getText());
	    }
	    else if (nbjoueur == 4) {
			this.prenom.add(this.j1.getText());
			this.prenom.add(this.j2.getText());
			this.prenom.add(this.j3.getText());
			this.prenom.add(this.j4.getText());
	    }
		
		//Determination nombre pc
		if(pc.isSelected()==true) {
			this.nbpc=0;
		}else if (pc1.isSelected()==true) {
			this.nbpc=1;
		}else if (pc2.isSelected()==true) {
			this.nbpc=2;
		}else if (pc3.isSelected()==true) {
			this.nbpc=3;
		}
		//Ajout prenom PC
		for(int i = 0 ; i < this.nbpc ; i++) {
			this.prenom.add("PC");
		}
		
		
		this.nbjoueur=this.nbjoueur+this.nbpc;	
		this.cb.lancerPartie(this.nbjoueur, this.prenom,liste_difficulte.getSelectedIndex());
	}
	
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", menu.getLocale());
		this.label.setText(strings.getString("nom_joueurs"));
		this.label_diff.setText(strings.getString("difficulte"));
		this.b.setText(strings.getString("jouer"));
		this.pc.setText(String.format(strings.getString("x_ordinateurs"), 0));
		this.pc1.setText(String.format(strings.getString("x_ordinateurs"), 1));
		this.pc2.setText(String.format(strings.getString("x_ordinateurs"), 2));
		this.pc3.setText(String.format(strings.getString("x_ordinateurs"), 3));
		
		String diffs[] = {strings.getString("facile"), strings.getString("moyen"), strings.getString("difficile")};
		if (this.liste_difficulte.getItemCount() == 0 || !this.liste_difficulte.getItemAt(0).equals(diffs[0])) {
			this.liste_difficulte.removeAllItems();
	        for(String diff : diffs) {
	        	this.liste_difficulte.addItem(diff);
	        }
		}
		
		//Sombre button
	    this.b.setBackground(this.c.getColorBouton());
	    this.b.setForeground(this.c.getColorLettre());
	    //Sombre label
		this.label.setForeground(this.c.getColorLettre());
		this.label_diff.setForeground(this.c.getColorLettre());

		this.label.setBackground(this.c.getColorBouton());
		this.label_diff.setBackground(this.c.getColorBouton());

	}

	@Override
	public void update(Observable o, Object arg1) {
		// TODO Auto-generated method stub
		if(o.getClass() == Couleur.class) {
			this.c = (Couleur) o;
			this.repaint();
		}
	}
	
}
