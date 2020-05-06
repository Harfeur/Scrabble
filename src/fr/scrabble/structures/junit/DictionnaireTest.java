package fr.scrabble.structures.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.scrabble.structures.Dictionnaire;

class DictionnaireTest {

	Dictionnaire d;
	
	@BeforeEach
	void setUp() throws Exception {
		d = new Dictionnaire("FR");
	}

	@Test
	void testContient() {
		assertTrue(d.contient("MOT"));
	}

}
