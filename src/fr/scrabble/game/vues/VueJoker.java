package fr.scrabble.game.vues;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import fr.scrabble.game.Modele;
import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Sac;

@SuppressWarnings("serial")
public class VueJoker extends JFrame implements ListSelectionListener {
	
	JList<String> li;
	Modele modele;
	
	public VueJoker(String langue, Modele m) {
		super("Choix de la valeur");
		this.setPreferredSize(new Dimension((int) (100*Menu.SCALE),(int) (200*Menu.SCALE)));
		this.modele = m;
		
		JPanel panel = new JPanel();
		
		Sac sac = new Sac(langue);
		
    	ArrayList<Image> lettre = new ArrayList<Image>();
		
		for(int i=0;i<sac.size();i++) 
        {
            if (!sac.get(i).lettre.equals("JOKER") && !lettre.contains(sac.get(i).lettre)) {
            	//try {
        			//lettre.add(ImageIO.read(Menu.class.getResource("/resources/images/letter_"+sac.get(i).lettre+".jpg")));
        		//} catch (IOException e1) {
//        			e1.printStackTrace();
  //      		}	
            }          
        }
		
		li = new JList<String>(lettre.toArray(new String[lettre.size()]));
	    JScrollPane liScroll = new JScrollPane(li); //marche pas 
		
	    ListSelectionModel listSelectionModel = li.getSelectionModel();
	    listSelectionModel.addListSelectionListener(this);

	    li.setLayoutOrientation(JList.VERTICAL_WRAP);
	    li.setVisibleRowCount(3);
		
	    
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
