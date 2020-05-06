package fr.scrabble.structures.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.scrabble.structures.Dictionnaire;
import fr.scrabble.structures.Lettre;
import fr.scrabble.structures.MotPlace;

class MotPlaceTest {

	MotPlace mp;
	Lettre M,E;
	Dictionnaire d;
	
	@BeforeEach
	void setUp() throws Exception {
		M = new Lettre("M",1);
		E= new Lettre("E",1);
		mp = new MotPlace(M,2,2);
		d = new Dictionnaire("FR");
	}

	@Test
	void testAjoutLettre() {
		assertEquals(1,mp.nombreDeLettres());
		assertFalse(mp.ajoutLettre(M, 0, 0));
		assertEquals(1,mp.nombreDeLettres());
		assertTrue(mp.ajoutLettre(E, 2, 3));
		assertEquals(2,mp.nombreDeLettres());
	}

	@Test
	void testMotValide() {
		assertFalse(mp.valideMot(d));
		mp.ajoutLettre(E, 2, 3);
		assertTrue(mp.valideMot(d));
	}
}
