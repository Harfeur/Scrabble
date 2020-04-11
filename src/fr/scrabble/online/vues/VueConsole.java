package fr.scrabble.online.vues;

import java.awt.Color;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import fr.scrabble.online.CustomOutputStream;

public class VueConsole extends JPanel {

	JTextArea txt;
	
	public VueConsole () {
		super();
		txt = new JTextArea(20,20);
		txt.setEditable(false);
		PrintStream print = new PrintStream(new CustomOutputStream(txt));
		System.setOut(print);
		System.setErr(print);

		this.setBackground(new Color(128, 255, 170));
		this.add(txt);
	}
	
}
