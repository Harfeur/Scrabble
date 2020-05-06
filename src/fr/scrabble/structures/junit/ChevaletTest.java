package fr.scrabble.structures.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.scrabble.structures.Chevalet;
import fr.scrabble.structures.Lettre;
import fr.scrabble.structures.Sac;

class ChevaletTest {

	Chevalet c;
	Sac sac;
	
	@BeforeEach
	void setUp() throws Exception {
		c = new Chevalet();
		sac = new Sac("FR");
		c.remplir(sac);
	}


	@Test
	void testChevaletPlein() {
		assertEquals(c.size(),7);
	}
	
	@Test
	void testAjoutLettre() {
		assertEquals(c.size(),7);
		c.remove(0);
		assertEquals(c.size(),6);
		c.remettreLettre(sac.obtenirLettre());
		assertEquals(c.size(),7);
	}
	
	@Test
	void testLettreSelectionne() {
		assertTrue(c.selectionnerLettre(3));
		assertEquals(c.lettreSelectionee,3);
	}
	
	@Test
	void testLettreObtenu() {
		assertNull(c.obtenirLettre());
		c.remove(0);
		Lettre a = new Lettre("A",1);
		c.add(a);
		c.selectionnerLettre(6);
		assertEquals(c.obtenirLettre(),a);
	}

}
