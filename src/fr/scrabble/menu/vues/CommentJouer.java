package fr.scrabble.menu.vues;

import java.util.ResourceBundle;

import javax.swing.JFrame;

import fr.scrabble.menu.Menu;

@SuppressWarnings("serial")
public class CommentJouer extends JFrame {
	
	public CommentJouer(Menu menu) {
		super();
		ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", menu.getLocale());
		this.setTitle(strings.getString("commentjouer"));
	}
	
}
