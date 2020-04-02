package fr.scrabble;

import javax.swing.*;

import fr.scrabble.vues.VueBoutonSolo;
import fr.scrabble.vues.VueMenu;

import java.awt.*;


public class Menu extends JFrame{

    private JLayeredPane lpane = new JLayeredPane();
    private JPanel panelBlue = new VueMenu();
    private JPanel panelGreen = new JPanel();
    
    public Menu () {
    	super("Menu Scrabble");
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(400, 400));
        this.setLayout(new BorderLayout());
        this.add(lpane, BorderLayout.CENTER);
        lpane.setBounds(0, 0, 400, 400);

        panelBlue.setBounds(0, 0, 600, 400);
        panelBlue.setOpaque(true);
            
        panelGreen.setBackground(Color.GREEN);
        panelGreen.setBounds(50, 100, 100, 100);
        panelGreen.setOpaque(false);
        panelGreen.add(new VueBoutonSolo());
            
            
        lpane.add(panelBlue, new Integer(0), 0);
        lpane.add(panelGreen, new Integer(1), 0);
        this.pack();
        this.setVisible(true);
    	
    }
	 
	public static void main(String[] args) {
		new Menu();
	}
}


