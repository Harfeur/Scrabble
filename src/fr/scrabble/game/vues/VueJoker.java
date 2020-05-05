package fr.scrabble.game.vues;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Couleur;
import fr.scrabble.structures.Sac;

@SuppressWarnings("serial")
public class VueJoker extends JFrame implements ListSelectionListener, ActionListener {
	
	JList<String> li;
	ArrayList<String> lettre ;
	JButton annuler;
	JPanel panel;
	Menu menu;
	Couleur c;
	
	public VueJoker(String langue, Menu m) {
		super("Choix de la valeur");
		this.menu = m;
		this.c = m.couleur;
		this.panel = new JPanel();
		
		Sac sac = new Sac(langue);
		
    	this.lettre = new ArrayList<String>();
		
		for(int i=0;i<sac.size();i++) 
        {
            if (!sac.get(i).lettre.equals("JOKER") && !lettre.contains(sac.get(i).lettre)) {
            	lettre.add(sac.get(i).lettre);
            }          
        }
		
		li = new JList<String>(lettre.toArray(new String[lettre.size()]));
	    
	    ListSelectionModel listSelectionModel = li.getSelectionModel();
	    listSelectionModel.addListSelectionListener(this);

	    li.setCellRenderer(new JokerList());
	    li.setLayoutOrientation(JList.VERTICAL_WRAP);
	    li.setVisibleRowCount(2);
		
	    annuler = new JButton("Annuler");
	    annuler.addActionListener(this);
	    
		panel.add(li);
		panel.add(annuler);
		this.add(panel);
	
		this.pack();
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    
	    this.setLocation(x, y);
		this.setVisible(true);

		
		
	}
	public void paint(Graphics g) {
		super.paint(g);
		ResourceBundle strings = ResourceBundle.getBundle("resources/i18n/strings", menu.getLocale());
		this.annuler.setText(String.format(strings.getString("annuler"), 1));
        this.panel.setBackground(this.c.getColorBouton());
        this.li.setBackground(this.c.getColorBouton());
        this.annuler.setForeground(this.c.getColorLettre());
        this.annuler.setBackground(this.c.getColorBouton());
        
	}


	@Override
	public void valueChanged(ListSelectionEvent e) {
		String lettre = li.getSelectedValue();
		System.out.println(lettre);
		this.menu.lettreJoker(lettre);
		this.setVisible(false);
		this.dispose();
	}

	
	class JokerList extends DefaultListCellRenderer{
		
		@SuppressWarnings("rawtypes")
		@Override
		public Component getListCellRendererComponent(JList list, Object value, int index,boolean isSelected, boolean cellHasFocus) {
			JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);
			for(int i=0;i<lettre.size();i++) {
	        	if(value.equals(lettre.get(i))) {
	        		Image im = null;
	        		if (c.getCouleur() == 0) {
						im = new ImageIcon(getClass().getResource("/resources/images/lettre/letter_"+lettre.get(i)+".png")).getImage().getScaledInstance((int)(VuePlateau.TAILLE*Menu.SCALE), (int) (VuePlateau.TAILLE*Menu.SCALE), Image.SCALE_DEFAULT);

	        		}
	        		else {
						im = new ImageIcon(getClass().getResource("/resources/images/lettreSombre/letter_"+lettre.get(i)+".png")).getImage().getScaledInstance((int)(VuePlateau.TAILLE*Menu.SCALE), (int) (VuePlateau.TAILLE*Menu.SCALE), Image.SCALE_DEFAULT);
	        		}
	        		label.setIcon(new ImageIcon(im));
	        		label.setText("");
	        	}
	        }
            return label;
		}			
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.menu.lettreJoker("non");
		this.setVisible(false);
		this.dispose();
	}	
	
}

