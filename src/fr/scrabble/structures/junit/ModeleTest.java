package fr.scrabble.structures.junit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.scrabble.game.Modele;
import fr.scrabble.menu.Menu;

public class ModeleTest {
	Modele m;
	Menu menu;
	ArrayList<String> prenoms;
	@BeforeEach
	void setUp() throws Exception {
		menu = new Menu();
		m= new Modele(menu);
		prenoms.add("a");
		prenoms.add("b");
		prenoms.add("c");
		prenoms.add("d");
		m.nouvellePartie(4, "FR", prenoms, 0);
	}

	@Test
	void testAjoutLettre() {
		assertEquals(m.numChevalet,0);
		assertEquals(m.sac.size(),96);
		assertEquals(m.scoreAv,0);
	}

}
