package fr.scrabble;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import fr.scrabble.vues.VueMenu;


public class Menu extends Frame{
	 VueMenu menu;
	 
	public Menu(){
		
		menu = new VueMenu();
		this.add(menu, BorderLayout.PAGE_START);
		 
		this.pack();  
		setVisible(true);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
	}
	
	public static void main(String[] args) {
		new Menu();
	}
}
