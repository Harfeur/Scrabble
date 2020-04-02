package fr.scrabble.vues;


import java.awt.*;

@SuppressWarnings("serial")
public class VueBoutonSolo  extends Button{

	public VueBoutonSolo() {
		Button b=new Button("Solo"); 
		b.setForeground(new Color(0,0,0));
		b.setBackground(Color.LIGHT_GRAY);
		Font f = new Font(Font.SERIF,Font.HANGING_BASELINE,25);
		b.setFont(f);
		b.setLocation(100, 30);
		
		Button m=new Button("Multijoueur"); 
		m.setForeground(new Color(0,0,0));
		m.setBackground(Color.LIGHT_GRAY);
		Font fo = new Font(Font.SERIF,Font.HANGING_BASELINE,25);
		m.setFont(fo);
		m.setLocation(30, 100);
		
	}
}
