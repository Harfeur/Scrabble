package fr.scrabble.menu.vues;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JTextField;

import fr.scrabble.menu.Menu;

@SuppressWarnings("serial")
public class CommentJouer extends JFrame {
	
	public CommentJouer(Menu menu) {
		super();
		ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", menu.getLocale());
		this.setTitle(strings.getString("commentjouer"));
		
		JTextField jText = new JTextField();
		
		this.add(jText);
		
		this.pack();

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);

		this.setLocation(x, y);
		this.setVisible(true);
	}
	
}
