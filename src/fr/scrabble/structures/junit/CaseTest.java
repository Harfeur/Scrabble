package fr.scrabble.structures.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.scrabble.structures.Case;
import fr.scrabble.structures.Case.Multiplicateur;
import fr.scrabble.structures.Lettre;

class CaseTest {

	Case c;
	Lettre l;
	Multiplicateur multi;
	
	@BeforeEach
	void setUp() throws Exception {
		c = new Case(multi);
		l = new Lettre("A",1);
	}

	@Test
	void testAjoutLettre() {
		assertNull(c.lettre);
		c.ajouterLettre(l);
		assertEquals(c.lettre.lettre,"A");
		assertEquals(c.lettre.valeur,1);
	}

}
