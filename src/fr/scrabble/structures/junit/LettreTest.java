package fr.scrabble.structures.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import fr.scrabble.structures.Lettre;

class LettreTest {

	@Test
	void test1() {
		Lettre l = new Lettre("A", 1);
		assertEquals("A(1)", l.toString());
	}
	@Test
	void test2() {
		Lettre l = new Lettre("K", 10);
		assertEquals("K(10)", l.toString());
	}
	@Test
	void test3() {
		Lettre l = new Lettre("C", 11);
		assertEquals("C(11)", l.toString());
	}
	@Test
	void test4() {
		Lettre l = new Lettre("M", 0);
		assertEquals("M(0)", l.toString());
	}
}
