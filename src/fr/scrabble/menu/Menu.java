package fr.scrabble.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import fr.scrabble.Modele;
import fr.scrabble.controleurs.*;
import fr.scrabble.menu.vues.*;
import fr.scrabble.vues.*;

@SuppressWarnings("serial")
public class Menu extends JFrame implements Observer {

	public static double SCALE=1.5;
	public String langue;
	
	JLayeredPane panelMenu;

	public Menu () {
		super("Menu");
		
		this.panelMenu = new JLayeredPane();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setPreferredSize(new Dimension((int) (600*Menu.SCALE), (int) (600*Menu.SCALE)));
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.setAutoRequestFocus(false);
		this.setResizable(false);

		this.pack();
		
		this.vueMenu();

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
		int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);

		this.setLocation(x, y);
		this.setVisible(true);
	}

	public void vueMenu() {
		this.remove(panelMenu);

		this.panelMenu = new JLayeredPane();

		ControleurBoutons cplay = new ControleurBoutons(this);

		VueMenu fondMenu = new VueMenu();
		VueBoutonHorsLigne vueBoutonHorsLigne = new VueBoutonHorsLigne(cplay);
		VueBoutonMulti vueBoutonMultijoueur = new VueBoutonMulti(cplay);

		this.add(panelMenu);

		panelMenu.setBounds(0, 0, (int) (600*Menu.SCALE), (int) (600*Menu.SCALE));

		panelMenu.add(fondMenu, 0, 0);
		panelMenu.add(vueBoutonHorsLigne, 1, 0);
		panelMenu.add(vueBoutonMultijoueur, 1, 0);
	}

	public void vueHorsLigne() {
		this.remove(panelMenu);

		this.langue = "FR";

		Modele m = new Modele();

		ControleurPlateau cp = new ControleurPlateau(m);
		ControleurChevalet cc = new ControleurChevalet(m);
		ControleurBouton cb = new ControleurBouton(m);

		VuePlateau vuePlateau = new VuePlateau(cp);
		VueChevalet vueChevalet = new VueChevalet(cc);
		VueBouton vueBouton = new VueBouton(cb);
		VueLigne vueLigne = new VueLigne();
		VueColonne vueColonne = new VueColonne();

		m.addObserver(vuePlateau);
		m.addObserver(vueChevalet);
		m.addObserver(this);

		Container contentPane = new Container();
		
		this.add(contentPane);

		contentPane.setLayout(new BorderLayout());

		contentPane.add(vuePlateau, BorderLayout.CENTER);
		contentPane.add(vueLigne, BorderLayout.NORTH);
		contentPane.add(vueColonne, BorderLayout.WEST);
		contentPane.add(vueChevalet, BorderLayout.SOUTH);
		contentPane.add(vueBouton, BorderLayout.EAST);

		m.nouvellePartie(4, this.langue);
		
		this.setVisible(true);
	}

	public void fermer() {
		this.setVisible(false);
		this.dispose();
	}

	public static void main(String[] args) {
		new Menu();
	}

	@Override
	public void update(Observable o, Object arg) {
		if (arg.getClass() == String.class) {
			String str = (String) arg;
			if (str.equals("cacher")) {
				this.setVisible(false);
			}
			if (str.equals("afficher")) {
				this.setVisible(true);
			}
		}
	}
}


