package fr.scrabble.vues;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.scrabble.Modele;
import fr.scrabble.structures.Sac;

@SuppressWarnings("serial")
public class VueJoker extends JFrame implements ListSelectionListener {
	
	JList<String> li;
	Modele modele;
	
	public VueJoker(String langue, Modele m) {
		super("Choix de la valeur");
		
		this.modele = m;
		
		JPanel panel = new JPanel();
		
		Sac sac = new Sac(langue);
		
    	ArrayList<String> strings = new ArrayList<String>();
		
		for(int i=0;i<sac.size();i++) 
        {
            if (!sac.get(i).lettre.equals("JOKER") && !strings.contains(sac.get(i).lettre)) {
            	strings.add(sac.get(i).lettre);
            }          
        }
		
		li = new JList<String>(strings.toArray(new String[strings.size()]));
		ListSelectionModel listSelectionModel = li.getSelectionModel();
	    listSelectionModel.addListSelectionListener(this);
		
		panel.add(li);
		this.add(panel);
	
		this.pack();
		
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    
	    this.setLocation(x, y);
		this.setVisible(true);
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		String lettre = li.getSelectedValue();
		this.modele.lettreJoker(lettre);
		this.setVisible(false);
		this.dispose();
	}
}
