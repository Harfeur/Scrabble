package fr.scrabble.game.vues;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.io.IOException;
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
	ArrayList<String> lettre ;
	
	public VueJoker(String langue, Modele m) {
		super("Choix de la valeur");
		this.modele = m;
		
		JPanel panel = new JPanel();
		
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
		System.out.println(lettre);
		this.modele.lettreJoker(lettre);
		this.setVisible(false);
		this.dispose();
	}

	
	class JokerList extends DefaultListCellRenderer{
		
		public Component getListCellRendererComponent(JList list, Object value, int index,boolean isSelected, boolean cellHasFocus) {
			JLabel label = (JLabel) super.getListCellRendererComponent(
                    list, value, index, isSelected, cellHasFocus);
			for(int i=0;i<lettre.size();i++) {
	        	if(value.equals(lettre.get(i))) {
	        		Image im = null;
					im = new ImageIcon(getClass().getResource("/resources/images/lettre/letter_"+lettre.get(i)+".png")).getImage().getScaledInstance((int)(VuePlateau.TAILLE*Menu.SCALE), (int) (VuePlateau.TAILLE*Menu.SCALE), Image.SCALE_DEFAULT);
	        		label.setIcon(new ImageIcon(im));
	        		label.setText("");
	        	}
	        }
            return label;
		}			
	}	
	
}

