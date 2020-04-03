package fr.scrabble.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.*;

import fr.scrabble.controleurs.ControleurPlay;
import fr.scrabble.menu.vues.VueBoutonMulti;
import fr.scrabble.menu.vues.VueBoutonSolo;
import fr.scrabble.menu.vues.VueMenu;

@SuppressWarnings("serial")
public class Menu extends JFrame{

    private JLayeredPane lpane = new JLayeredPane();
    private JPanel fond = new VueMenu();
    private JPanel bouton1 = new JPanel();
    private JPanel bouton2 = new JPanel();
    
    public Menu () {
    	super("Menu");
    	
    	ControleurPlay cplay = new ControleurPlay(this);
    	
    	
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(400, 400));
        this.setLayout(new BorderLayout());
        this.setCursor(HAND_CURSOR);
        this.setAutoRequestFocus(false);
        
        this.add(lpane, BorderLayout.CENTER);
        lpane.setBounds(0, 0, 400, 400);
        fond.setBounds(0, 0, 600, 400);
        fond.setOpaque(true);
        
        bouton1.setBackground(Color.GREEN);
        bouton1.setBounds(0, 125, 200, 200);
        bouton1.setOpaque(false);
        bouton1.add(new VueBoutonSolo(cplay));
        
        bouton2.setBackground(Color.GREEN);
        bouton2.setBounds(175, 125, 200, 200);
        bouton2.setOpaque(false);
        bouton2.add(new VueBoutonMulti(cplay));
            
            
        lpane.add(fond, new Integer(0), 0);
        lpane.add(bouton1, new Integer(1), 0);
        lpane.add(bouton2, new Integer(1), 0);
        
		this.pack();
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    
	    this.setLocation(x, y);
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


