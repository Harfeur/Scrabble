package fr.scrabble.menu.vues;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import fr.scrabble.menu.Menu;

@SuppressWarnings("serial")
public class ErrorFrame extends JFrame {
	
	public ErrorFrame(String erreur) {
		super(erreur.getClass().getSimpleName());
		
		JTextPane jText = new JTextPane();
		
		jText.setText(erreur);
		jText.setEditable(false);
		
		StyledDocument doc = jText.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		
		this.add(jText);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setMaximumSize(new Dimension((int) (Menu.SCALE*300), (int) (Menu.SCALE*300)));

		this.pack();

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);

		this.setLocation(x, y);
		this.setVisible(true);
	}
	
}
