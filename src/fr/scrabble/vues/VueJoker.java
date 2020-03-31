package fr.scrabble.vues;

import java.awt.List;
import java.awt.Panel;
import java.util.Iterator;

import fr.scrabble.structures.Lettre;
import fr.scrabble.structures.Sac;

public class VueJoker extends Panel {
	
	public VueJoker(String langue) {
		super();
		List li = new List(26,false);
		Sac sac = new Sac(langue);
		Iterator<Lettre> itr = sac.keySet().iterator();
		while(itr.hasNext()) 
        {
            Lettre key = itr.next();
            li.add(key.lettre); 
        }
	}
	
}
