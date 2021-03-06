package fr.scrabble.menu.vues;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ResourceBundle;

import javax.swing.JEditorPane;
import javax.swing.JFrame;

import fr.scrabble.menu.Menu;

@SuppressWarnings("serial")
public class APropos extends JFrame{
	Menu menu;
	public APropos(Menu menu) {
		super();
		ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", menu.getLocale());
		this.setTitle(strings.getString("asavoir"));
		
		JEditorPane jText = new JEditorPane("text/html", strings.getString("pourquoi"));
		jText.setEditable(false);
		
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
