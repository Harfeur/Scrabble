package fr.scrabble.structures.junit;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.scrabble.game.Modele;
import fr.scrabble.menu.Menu;
import fr.scrabble.structures.Case;
import fr.scrabble.structures.Lettre;
import fr.scrabble.structures.Placement;

public class ModeleTest {
	Modele m;
	Menu menu;
	ArrayList<String> prenoms;
	int nbjoueur=2;
	@BeforeEach
	void setUp() throws Exception {
		menu = new Menu();
		m= new Modele(menu);
		prenoms = new ArrayList();
		prenoms.add("a");
		prenoms.add("b");
		prenoms.add("c");
		prenoms.add("d");
		m.nouvellePartie(nbjoueur, "FR", prenoms, 0);
	}

	@Test
	void testNouvellePartie() {
		assertEquals(m.numChevalet,0);
		assertEquals(m.sac.size(),102-(7*nbjoueur));
		assertEquals(m.chevalets.size(),nbjoueur);
		assertEquals(m.scoreAv,0);
		assertEquals(m.score.length,nbjoueur);
	}
	
	@Test
	void testAjoutLettre() {
		Lettre l = m.chevalets.chevaletEnCours().get(0);
		m.selectLettre(0);
		m.ajoutLettre(0, 0);
		//Lettre ajouté sur case vide
		assertEquals(m.plateauFictif.getCase(0, 0).lettre,l);
		m.selectLettre(1);
		m.ajoutLettre(0, 0);
		//Lettre ajouté sur case pleine
		assertEquals(m.plateauFictif.getCase(0, 0).lettre,l);
		assertEquals(m.chevalets.chevaletEnCours().size(),6);
		m.ajoutLettre(0, 0);
		//Reprendre une lettre
		assertEquals(m.plateauFictif.getCase(0, 0).lettre,null);
	}
	
	@Test
	void testVerifMot() {
		Lettre l = m.chevalets.chevaletEnCours().get(0);
		m.selectLettre(0);
		m.ajoutLettre(0, 0);
		m.placementEnCours.add(new Placement(l,m.plateauFictif.getCase(0, 0),0,0));
		m.verificationMot();
		//Debut pas au milieu
		assertEquals(m.plateauFictif.getCase(0, 0).lettre,null);
		
		l = m.chevalets.chevaletEnCours().get(0);
		m.selectLettre(0);
		m.ajoutLettre(7, 7);
		assertEquals(m.placementEnCours.size(),1);
		m.verificationMot();
		//Debut au milieu
		assertEquals(m.plateauFictif.getCase(7, 7).lettre,null);
		
		l = new Lettre("L",1);
		m.plateauFictif.getCase(7, 7).lettre=l;
		m.placementEnCours.add(new Placement(l,m.plateauFictif.getCase(7, 7),7,7));
		l=new Lettre("A",1);
		m.plateauFictif.getCase(7, 8).lettre=l;
		m.placementEnCours.add(new Placement(l,m.plateauFictif.getCase(7, 8),7,8));
		assertEquals(m.placementEnCours.size(),2);
		m.verificationMot();
		//Lettre au milieu + valide
		assertEquals(m.plateauFictif.getCase(7, 7).lettre.lettre,"L");
		
		l=new Lettre("A",1);
		m.plateauFictif.getCase(3, 2).lettre=l;
		m.placementEnCours.add(new Placement(l,m.plateauFictif.getCase(3, 2),3,2));
		assertEquals(m.placementEnCours.size(),1);
		int num=m.numChevalet;
		m.verificationMot();
		//Lettre pas collé a lettre existante
		assertEquals(m.plateauFictif.getCase(3, 2).lettre,null);
		assertEquals(m.numChevalet,num);
		
		l = new Lettre("L",1);
		m.plateauFictif.getCase(4, 2).lettre=l;
		m.placementEnCours.add(new Placement(l,m.plateauFictif.getCase(9, 7),9,7));
		l=new Lettre("A",1);
		m.plateauFictif.getCase(3, 2).lettre=l;
		m.placementEnCours.add(new Placement(l,m.plateauFictif.getCase(3, 2),3,2));
		assertEquals(m.placementEnCours.size(),2);
		num=m.numChevalet;
		m.verificationMot();
		//Lettre pas collé a lettre existante
		assertEquals(m.plateauFictif.getCase(3, 2).lettre,null);
		assertEquals(m.numChevalet,num);
		
		l = new Lettre("L",1);
		m.plateauFictif.getCase(9, 7).lettre=l;
		m.placementEnCours.add(new Placement(l,m.plateauFictif.getCase(9, 7),9,7));
		l=new Lettre("A",1);
		m.plateauFictif.getCase(3, 2).lettre=l;
		m.placementEnCours.add(new Placement(l,m.plateauFictif.getCase(3, 2),3,2));
		assertEquals(m.placementEnCours.size(),2);
		num=m.numChevalet;
		m.verificationMot();
		//Lettre pas meme ligne/colonne
		assertEquals(m.plateauFictif.getCase(3, 2).lettre,l);
		assertEquals(m.numChevalet,num);	
		
		
	}

}
