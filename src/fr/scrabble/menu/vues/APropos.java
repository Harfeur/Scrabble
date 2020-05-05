package fr.scrabble.menu.vues;

import java.util.ResourceBundle;

import javax.swing.JFrame;

import fr.scrabble.menu.Menu;

public class APropos extends JFrame{
	Menu menu;
	public APropos(Menu menu) {
		super();
		ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", menu.getLocale());
		this.setTitle(strings.getString("asavoir"));
		
		this.pack();
		this.setVisible(true);
	}
}
