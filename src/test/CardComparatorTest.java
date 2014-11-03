package test;

import static org.junit.Assert.assertEquals;
import game.Card;
import game.Card.CardSuit;
import game.Card.CardValue;
import game.CardComparator;

import org.junit.Test;

/**
 * I have no idea how to make this properly.
 * 
 * @author Melody Lucid
 *
 */

public class CardComparatorTest {

	private CardComparator cc;
	private final static CardSuit enteringSuit = CardSuit.HEARTS;
	private final static CardSuit trumpSuit = CardSuit.SPADES;
	private final static CardSuit otherSuit = CardSuit.CLUBS;
	private final static CardValue greaterValue = CardValue.KING;
	private final static CardValue lesserValue = CardValue.QUEEN;
	
	public void loadDiffSuitComparator() {
		cc = new CardComparator(enteringSuit, trumpSuit);
	}
	
	public void loadSameSuitComparator(CardSuit suit) {
		cc = new CardComparator(suit, suit);
	}
	
	@Test
	public void caseBothEnteringSuitGreater() {
		Card c1 = new Card(enteringSuit, greaterValue);
		Card c2 = new Card(enteringSuit, lesserValue);
		
		loadDiffSuitComparator();
		assertEquals(cc.compare(c1, c2), +1);
		
		loadSameSuitComparator(enteringSuit);
		assertEquals(cc.compare(c1, c2), +1);
		
		loadSameSuitComparator(trumpSuit);
		assertEquals(cc.compare(c1, c2), 0);
	}
	
	@Test
	public void caseBothEnteringSuitLesser() {
		Card c1 = new Card(enteringSuit, lesserValue);
		Card c2 = new Card(enteringSuit, greaterValue);
		
		loadDiffSuitComparator();
		assertEquals(cc.compare(c1, c2), -1);
		
		loadSameSuitComparator(enteringSuit);
		assertEquals(cc.compare(c1, c2), -1);
		
		loadSameSuitComparator(trumpSuit);
		assertEquals(cc.compare(c1, c2), 0);
	}
	
	@Test
	public void caseBothEnteringSuitEquals() {
		Card c1 = new Card(enteringSuit, greaterValue);
		Card c2 = new Card(enteringSuit, greaterValue);
		
		loadDiffSuitComparator();
		assertEquals(cc.compare(c1, c2), 0);
		
		loadSameSuitComparator(enteringSuit);
		assertEquals(cc.compare(c1, c2), 0);
		
		loadSameSuitComparator(trumpSuit);
		assertEquals(cc.compare(c1, c2), 0);
	}
	
	
	@Test
	public void caseBothTrumpSuitGreater() {
		Card c1 = new Card(trumpSuit, greaterValue);
		Card c2 = new Card(trumpSuit, lesserValue);
		
		loadDiffSuitComparator();
		assertEquals(cc.compare(c1, c2), +1);
		
		loadSameSuitComparator(enteringSuit);
		assertEquals(cc.compare(c1, c2), 0);
		
		loadSameSuitComparator(trumpSuit);
		assertEquals(cc.compare(c1, c2), +1);
	}
	
	@Test
	public void caseBothTrumpSuitLesser() {
		Card c1 = new Card(trumpSuit, lesserValue);
		Card c2 = new Card(trumpSuit, greaterValue);
		
		loadDiffSuitComparator();
		assertEquals(cc.compare(c1, c2), -1);
		
		loadSameSuitComparator(enteringSuit);
		assertEquals(cc.compare(c1, c2), 0);
		
		loadSameSuitComparator(trumpSuit);
		assertEquals(cc.compare(c1, c2), -1);
	}
	
	@Test
	public void caseBothTrumpSuitEquals() {
		Card c1 = new Card(trumpSuit, greaterValue);
		Card c2 = new Card(trumpSuit, greaterValue);
		
		loadDiffSuitComparator();
		assertEquals(cc.compare(c1, c2), 0);
		
		loadSameSuitComparator(enteringSuit);
		assertEquals(cc.compare(c1, c2), 0);
		
		loadSameSuitComparator(trumpSuit);
		assertEquals(cc.compare(c1, c2), 0);
	}
	
	@Test
	public void caseBothOtherSuit() {
		Card c1 = new Card(otherSuit, greaterValue);
		Card c2 = new Card(otherSuit, lesserValue);
		
		loadDiffSuitComparator();
		assertEquals(cc.compare(c1, c2), 0);
		
		loadSameSuitComparator(enteringSuit);
		assertEquals(cc.compare(c1, c2), 0);
		
		loadSameSuitComparator(trumpSuit);
		assertEquals(cc.compare(c1, c2), 0);
	}

	@Test
	public void caseOneEnteringSuitOneTrump() {
		Card c1 = new Card(enteringSuit, greaterValue);
		Card c2 = new Card(trumpSuit, lesserValue);
		
		loadDiffSuitComparator();
		assertEquals(cc.compare(c1, c2), -1);
		
		loadSameSuitComparator(enteringSuit);
		assertEquals(cc.compare(c1, c2), +1);
		
		loadSameSuitComparator(trumpSuit);
		assertEquals(cc.compare(c1, c2), -1);
	}
	
	@Test
	public void caseOneEnteringSuitOneOtherSuit() {
		Card c1 = new Card(enteringSuit, lesserValue);
		Card c2 = new Card(otherSuit, greaterValue);
		
		loadDiffSuitComparator();
		assertEquals(cc.compare(c1, c2), +1);
		
		loadSameSuitComparator(enteringSuit);
		assertEquals(cc.compare(c1, c2), +1);
		
		loadSameSuitComparator(trumpSuit);
		assertEquals(cc.compare(c1, c2), 0);
	}
	
	@Test
	public void caseOneTrumpSuitOneEnteringSuit() {
		Card c1 = new Card(trumpSuit, lesserValue);
		Card c2 = new Card(enteringSuit, greaterValue);
		
		loadDiffSuitComparator();
		assertEquals(cc.compare(c1, c2), +1);
		
		loadSameSuitComparator(enteringSuit);
		assertEquals(cc.compare(c1, c2), -1);
		
		loadSameSuitComparator(trumpSuit);
		assertEquals(cc.compare(c1, c2), +1);
	}
	
	@Test
	public void caseOneTrumpSuitOneOtherSuit() {
		Card c1 = new Card(trumpSuit, lesserValue);
		Card c2 = new Card(otherSuit, greaterValue);
		
		loadDiffSuitComparator();
		assertEquals(cc.compare(c1, c2), +1);
		
		loadSameSuitComparator(enteringSuit);
		assertEquals(cc.compare(c1, c2), 0);
		
		loadSameSuitComparator(trumpSuit);
		assertEquals(cc.compare(c1, c2), +1);
	}
	
	@Test
	public void caseOneOtherSuitOneEnteringSuit() {
		Card c1 = new Card(otherSuit, lesserValue);
		Card c2 = new Card(enteringSuit, greaterValue);
		
		loadDiffSuitComparator();
		assertEquals(cc.compare(c1, c2), -1);
		
		loadSameSuitComparator(enteringSuit);
		assertEquals(cc.compare(c1, c2), -1);
		
		loadSameSuitComparator(trumpSuit);
		assertEquals(cc.compare(c1, c2), 0);
	}
	
	@Test
	public void caseOneOtherSuitOneTrumpSuit() {
		Card c1 = new Card(otherSuit, greaterValue);
		Card c2 = new Card(trumpSuit, lesserValue);
		
		loadDiffSuitComparator();
		assertEquals(cc.compare(c1, c2), -1);
		
		loadSameSuitComparator(enteringSuit);
		assertEquals(cc.compare(c1, c2), 0);
		
		loadSameSuitComparator(trumpSuit);
		assertEquals(cc.compare(c1, c2), -1);
	}
	
	@Test
	public void cornerCases() {
		Card c1 = new Card(trumpSuit, CardValue.JACK);
		
		loadDiffSuitComparator();
		Card c2;
		for (int i = 0; i < 9; i++) {
			if (i == 5) {
				continue; // Buur value
			}
			c2 = new Card(trumpSuit, CardValue.values()[i]);
			assertEquals(cc.compare(c1, c2), +1);
		}
		
		c1 = new Card(trumpSuit, CardValue.NINE);
		
		for (int i = 0; i < 9; i++) {
			if (i == 5 || i == 3) {
				continue; // Buur or nel values
			}
			c2 = new Card(trumpSuit, CardValue.values()[i]);
			assertEquals(cc.compare(c1, c2), +1);
		}
		
		c1 = new Card(trumpSuit, CardValue.ACE);
		
		for (int i = 0; i < 9; i++) {
			if (i == 5 || i == 3 || i == 8) {
				continue; // Buur, nel or ace values
			}
			c2 = new Card(trumpSuit, CardValue.values()[i]);
			assertEquals(cc.compare(c1, c2), +1);
		}
		
		c1 = new Card(trumpSuit, CardValue.SIX);
		
		for (int i = 0; i < 4; i++) {
			if (i == 1) {
				continue;
			}
			for (int j = 0; j < 9; j++) {
				c2 = new Card(CardSuit.values()[i], CardValue.values()[j]);
				assertEquals(cc.compare(c1, c2), +1);
			}
		}
	}
}
