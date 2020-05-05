package fr.scrabble.menu.vues;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ResourceBundle;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import fr.scrabble.menu.Menu;

@SuppressWarnings("serial")
public class CommentJouer extends JFrame {
	
	public CommentJouer(Menu menu) {
		super();
		
		ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", menu.getLocale());
		this.setTitle(strings.getString("commentjouer"));
		
		JEditorPane jText = new JEditorPane("text/html", strings.getString("tutoriel"));
		
		this.add(jText);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.setPreferredSize(new Dimension((int) (Menu.SCALE*500), (int) (Menu.SCALE*500)));

		this.pack();

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);

		this.setLocation(x, y);
		this.setVisible(true);
	}
	
}
