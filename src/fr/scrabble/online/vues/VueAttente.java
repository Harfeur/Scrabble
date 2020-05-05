package fr.scrabble.online.vues;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.scrabble.menu.Menu;
import fr.scrabble.online.Client;
import fr.scrabble.structures.Couleur;

@SuppressWarnings("serial")
public class VueAttente extends JPanel implements ActionListener, Observer{

	Client client;
	Couleur c;
	JLabel txt;
	JButton lancerPartie;
	Menu menu;
	
	public VueAttente(Client client, Couleur c, Menu menu) {
		super();
		this.client = client;  
		this.c = c;
		this.menu = menu;
		
		this.txt = new JLabel();
		this.lancerPartie = new JButton();
		
		Font font = new Font("Arial",Font.BOLD,(int) (15*Menu.SCALE));
		txt.setFont(font);
		lancerPartie.setFont(font);		
		
		lancerPartie.addActionListener(this);
		
        this.setBounds((int) (230*Menu.SCALE),(int) (240*Menu.SCALE),(int) (150*Menu.SCALE),(int) (60*Menu.SCALE));
		
		this.add(txt);
		this.add(lancerPartie);
		
		
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", this.menu.getLocale());
		this.txt.setText(strings.getString("attente"));
		this.lancerPartie.setText(strings.getString("lancerPartie"));
		
		this.txt.setForeground(this.c.getColorLettre());
		this.lancerPartie.setForeground(this.c.getColorLettre());
		this.lancerPartie.setBackground(c.getColorBouton());
		this.setBackground(c.getColorBouton());
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		client.demarrer();
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Couleur) {
			this.c = (Couleur) o;
			this.repaint();
		}
	}

}