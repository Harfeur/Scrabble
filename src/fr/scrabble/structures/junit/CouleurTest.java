package fr.scrabble.structures.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.scrabble.structures.Couleur;

class CouleurTest {

	Couleur c;
	
	@BeforeEach
	void setUp() throws Exception {
		c = new Couleur();
	}

	@Test
	void testChangerCouleur() {
		assertEquals(c.getCouleur(),0);
		c.changeCouleur("Sombre");
		assertEquals(c.getCouleur(),1);
	}

	
}
