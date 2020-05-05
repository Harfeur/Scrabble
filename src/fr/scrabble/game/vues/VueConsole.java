package fr.scrabble.game.vues;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
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
	private Menu menu;
	
	public VueConsole (Modele m, Menu menu) {
		super();
		this.modele = m;
		this.menu = menu;
		
		JtxtA = new JTextArea(20,20);
		DefaultCaret caret = (DefaultCaret)JtxtA.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane scrollPane = new JScrollPane(JtxtA);
		JtxtA.setEditable(false);
		this.JtxtA.setFont(new Font("Arial",Font.PLAIN,(int)(10*this.menu.zoom())));

		this.setPreferredSize(new Dimension((int) (VuePlateau.TAILLE*this.menu.zoom()),(int) (VuePlateau.TAILLE*10*this.menu.zoom())));
		this.setBounds((int) (VueColonne.TAILLE*this.menu.zoom()+VuePlateau.TAILLE*15*this.menu.zoom()+this.menu.decalageX()),(int) (200*this.menu.zoom()+this.menu.decalageY()), (int) (VuePlateau.TAILLE*7*this.menu.zoom()),(int) (VuePlateau.TAILLE*10*this.menu.zoom()));
		this.add(scrollPane);
		this.setOpaque(false);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		this.JtxtA.setFont(new Font("Arial",Font.PLAIN,(int)(10*this.menu.zoom())));
		
		this.setPreferredSize(new Dimension((int) (VuePlateau.TAILLE*this.menu.zoom()),(int) (VuePlateau.TAILLE*10*this.menu.zoom())));
		this.setBounds((int) (VueColonne.TAILLE*this.menu.zoom()+VuePlateau.TAILLE*15*this.menu.zoom()+this.menu.decalageX()),(int) (200*this.menu.zoom()+this.menu.decalageY()), (int) (VuePlateau.TAILLE*7*this.menu.zoom()),(int) (VuePlateau.TAILLE*10*this.menu.zoom()));

	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg.getClass() == String.class) {
			this.txt = (String) arg;
			this.JtxtA.append(this.txt);
		}
	}
}
