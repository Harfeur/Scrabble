package fr.scrabble.game.vues;

import java.awt.Dimension;
import java.io.PrintStream;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fr.scrabble.game.Modele;
import fr.scrabble.game.utils.CustomOutputStream;
import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Plateau;

@SuppressWarnings("serial")
public class VueConsole extends JPanel implements Observer {

	JTextArea JtxtA;
	Modele modele;
	String txt;
	
	public VueConsole (Modele m) {
		super();
		this.modele = m;
		
		JtxtA = new JTextArea(20,20);
		JScrollPane scrollPane = new JScrollPane(JtxtA);
		JtxtA.setEditable(false);

		PrintStream print = new PrintStream(new CustomOutputStream(JtxtA));
		System.setOut(print);
		System.setErr(print);

		this.setPreferredSize(new Dimension((int) (VuePlateau.TAILLE*Menu.SCALE),(int) (VuePlateau.TAILLE*10*Menu.SCALE)));
		this.setBounds((int) (VueColonne.TAILLE*Menu.SCALE+VuePlateau.TAILLE*15*Menu.SCALE),(int) (200*Menu.SCALE), (int) (VuePlateau.TAILLE*7*Menu.SCALE),(int) (VuePlateau.TAILLE*10*Menu.SCALE));
		this.add(scrollPane);
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(arg.getClass() == String.class) {
			this.txt = (String) arg;
			this.JtxtA.append(this.txt);
		}
	}
}
