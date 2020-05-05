package fr.scrabble.game.vues;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

import fr.scrabble.game.Modele;
import fr.scrabble.menu.Menu;

@SuppressWarnings("serial")
public class VueConsole extends JPanel implements Observer {

	JTextArea JtxtA;
	Modele modele;
	String txt;
	
	public VueConsole (Modele m) {
		super();
		this.modele = m;
		
		JtxtA = new JTextArea(20,20);
		DefaultCaret caret = (DefaultCaret)JtxtA.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane scrollPane = new JScrollPane(JtxtA);
		JtxtA.setEditable(false);

		this.setPreferredSize(new Dimension((int) (VuePlateau.TAILLE*Menu.SCALE),(int) (VuePlateau.TAILLE*10*Menu.SCALE)));
		this.setBounds((int) (VueColonne.TAILLE*Menu.SCALE+VuePlateau.TAILLE*15*Menu.SCALE),(int) (200*Menu.SCALE), (int) (VuePlateau.TAILLE*7*Menu.SCALE),(int) (VuePlateau.TAILLE*10*Menu.SCALE));
		this.add(scrollPane);
		this.setOpaque(false);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg.getClass() == String.class) {
			this.txt = (String) arg;
			this.JtxtA.append(this.txt);
		}
	}
}
