package fr.scrabble.structures.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.scrabble.structures.Lettre;

class LettreTest {

	Lettre l;
	
	@BeforeEach
	void setUp() throws Exception {
		l = new Lettre("A",1);
	}

	@Test
	void test() {
		assertEquals("A",l.lettre);
		assertEquals(1,l.valeur);
		assertEquals("A(1)",l.toString());
	}

}
