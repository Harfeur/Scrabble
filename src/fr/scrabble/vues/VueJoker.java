package fr.scrabble.vues;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.List;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collections;
import java.util.Iterator;

import fr.scrabble.structures.Lettre;
import fr.scrabble.structures.Sac;

@SuppressWarnings("serial")
public class VueJoker extends Frame implements ItemListener {
	
	public VueJoker(String langue, ItemListener il) {
		super("Choix de la valeur");
		Panel panel = new Panel();
		
		Sac sac = new Sac(langue);
		
		List li = new List(10, false);
		li.addItemListener(il);
		for(int i=0;i<sac.size();i++) 
        {
            if (!sac.get(i).lettre.equals("JOKER")) {
            	li.add(sac.get(i).lettre); 
            }          
        }
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
	public void itemStateChanged(ItemEvent e) {
		this.setVisible(false);
		this.dispose();
	}
}
