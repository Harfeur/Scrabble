package fr.scrabble;

import javax.swing.*;

import fr.scrabble.controleurs.ControleurBouton;
import fr.scrabble.controleurs.ControleurPlay;
import fr.scrabble.vues.VueBoutonMulti;
import fr.scrabble.vues.VueBoutonSolo;
import fr.scrabble.vues.VueMenu;

import java.awt.*;


public class Menu extends JFrame{

    private JLayeredPane lpane = new JLayeredPane();
    private JPanel fond = new VueMenu();
    private JPanel bouton1 = new JPanel();
    private JPanel bouton2 = new JPanel();
    
    
    public Menu () {
    	super("Menu");
    	
    	Modele m = new Modele();
    	
    	ControleurPlay cplay = new ControleurPlay(m, "FR", this);
    	
    	
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(400, 400));
        this.setLayout(new BorderLayout());
        this.add(lpane, BorderLayout.CENTER);
        lpane.setBounds(0, 0, 400, 400);

        fond.setBounds(0, 0, 600, 400);
        fond.setOpaque(true);
            
        bouton1.setBackground(Color.GREEN);
        bouton1.setBounds(50, 100, 100, 100);
        bouton1.setOpaque(false);
        bouton1.add(new VueBoutonSolo(cplay));
        
        bouton2.setBackground(Color.GREEN);
        bouton2.setBounds(200, 100, 100, 100);
        bouton2.setOpaque(false);
        bouton2.add(new VueBoutonMulti());
            
            
        lpane.add(fond, new Integer(0), 0);
        lpane.add(bouton1, new Integer(1), 0);
        lpane.add(bouton2, new Integer(1), 0);
        this.pack();
        this.setVisible(true);
    }
    
    public void fermer() {
    	this.setVisible(false);
    	this.dispose();
    }
	 
	public static void main(String[] args) {
		new Menu();
	}
}


