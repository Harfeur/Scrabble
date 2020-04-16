package fr.scrabble.online.vues;

import java.awt.Color;
import java.awt.Dimension;
import java.io.PrintStream;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import fr.scrabble.game.vues.VueColonne;
import fr.scrabble.game.vues.VuePlateau;
import fr.scrabble.menu.Menu;
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

		this.setPreferredSize(new Dimension((int) (VuePlateau.TAILLE*Menu.SCALE),(int) (VuePlateau.TAILLE*10*Menu.SCALE)));
		this.setBounds((int) (VueColonne.TAILLE*Menu.SCALE+VuePlateau.TAILLE*15*Menu.SCALE)+5,200, (int) (VuePlateau.TAILLE*7*Menu.SCALE),(int) (VuePlateau.TAILLE*10*Menu.SCALE));
		this.add(txt);
	}
	
}