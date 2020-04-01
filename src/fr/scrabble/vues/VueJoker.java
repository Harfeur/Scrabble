package fr.scrabble.vues;

import java.awt.List;
import java.awt.Panel;
import java.awt.event.ItemListener;
import java.util.Collections;
import java.util.Iterator;

import fr.scrabble.structures.Lettre;
import fr.scrabble.structures.Sac;

@SuppressWarnings("serial")
public class VueJoker extends Panel {
	
	public VueJoker(String langue, ItemListener il) {
		super();
		List li = new List(10, false);
		li.addItemListener(il);
		Sac sac = new Sac(langue);
		Iterator<Lettre> itr = sac.keySet().iterator();
		while(itr.hasNext()) 
        {
            Lettre key = itr.next();
            if (!key.equals("JOKER")) {
            	li.add(key.lettre); 
            }          
        }
		this.add(li);
	}
	
}
