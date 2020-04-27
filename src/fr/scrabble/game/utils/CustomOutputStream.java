package fr.scrabble.game.utils;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

public class CustomOutputStream extends OutputStream {
	
	JTextArea txt;
	
	public CustomOutputStream(JTextArea t) {
		this.txt= t;
		
	}
	
	@Override
	public void write(int b) throws IOException {
		// TODO Auto-generated method stub
		this.txt.append(String.valueOf((char) (b)));
		this.txt.setCaretPosition(this.txt.getDocument().getLength());
	}

}
