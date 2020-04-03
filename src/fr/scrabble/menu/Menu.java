package fr.scrabble.menu;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.*;

import fr.scrabble.controleurs.ControleurBoutons;
import fr.scrabble.menu.vues.VueBoutonMulti;
import fr.scrabble.menu.vues.VueBoutonHorsLigne;
import fr.scrabble.menu.vues.VueMenu;

@SuppressWarnings("serial")
public class Menu extends JFrame{
	
	// Panels et Layouts
    private JLayeredPane panelMenu;
    
    // Vues
    private JPanel fondMenu;
    private JPanel vueBoutonHorsLigne;
	private JPanel vueBoutonMultijoueur;
    
	//Controleurs
    private ActionListener cplay;
    
    public Menu () {
    	super("Menu");
    	
    	this.panelMenu = new JLayeredPane();
    	
    	this.cplay = new ControleurBoutons(this);

    	this.fondMenu = new VueMenu();
    	this.vueBoutonHorsLigne = new VueBoutonHorsLigne(cplay);
    	this.vueBoutonMultijoueur = new VueBoutonMulti(cplay);
    	
    	
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(400, 400));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setAutoRequestFocus(false);
        
		this.pack();
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    
	    this.setLocation(x, y);
		this.setVisible(true);
    }
    
    public void vueMenu() {
    	
    	this.add(this.panelMenu);
    	this.panelMenu.setBounds(0, 0, 400, 400);
            
    	this.panelMenu.add(this.fondMenu, 0, 0);
    	this.panelMenu.add(this.vueBoutonHorsLigne, 1, 0);
    	this.panelMenu.add(this.vueBoutonMultijoueur, 1, 0);
    }
    
    public void fermer() {
    	this.setVisible(false);
    	this.dispose();
    }
	 
	public static void main(String[] args) {
		Menu m = new Menu();
		m.vueMenu();
	}
}


