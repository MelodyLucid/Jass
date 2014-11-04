package test;

import static org.junit.Assert.*;
import game.Card;
import game.Card.CardSuit;
import game.Card.CardValue;
import game.CardList;
import game.CardListHelper;

import java.util.Collections;
import java.util.Random;

import org.junit.Test;

public class CardListTest {

	/**
	 * Checks a correct handle of an empty CardList.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void cardListEmptyInstantiation() {
		CardList cl = new CardList();
		assertEquals(0, cl.size());
		assertTrue(cl.isEmpty());
		cl.get(0);
	}
	
	/**
	 * Checks get(index) method.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void cardListGetMethod() {
		CardList cl = new CardList(Card.ACE_OF_HEARTS, Card.ACE_OF_CLUBS, Card.ACE_OF_SPADES, Card.ACE_OF_DIAMONDS);
		assertEquals(Card.ACE_OF_HEARTS, cl.get(0));
		assertEquals(Card.ACE_OF_CLUBS, cl.get(1));
		assertEquals(Card.ACE_OF_SPADES, cl.get(2));
		assertEquals(Card.ACE_OF_DIAMONDS, cl.get(3));
		assertEquals(null, cl.get(4));
	}
	
	/**
	 * Checks contains(Card) method.
	 */
	
	@Test
	public void cardListContainsMethod() {
		CardList cl = new CardList(Card.ACE_OF_CLUBS, Card.SEVEN_OF_DIAMONDS, Card.JACK_OF_HEARTS, Card.NINE_OF_HEARTS);
		
		assertTrue(cl.contains(Card.ACE_OF_CLUBS));
		assertTrue(cl.contains(Card.SEVEN_OF_DIAMONDS));
		assertTrue(cl.contains(Card.JACK_OF_HEARTS));
		assertTrue(cl.contains(Card.NINE_OF_HEARTS));
		
		// The set does not contain any spade
		int n = CardValue.NUMBERS_OF_VALUES;
		for (int i = 0; i < n; i++) {
			assertFalse(cl.contains(Card.values()[i+n*CardSuit.SPADES.getIntValue()]));
		}
		
		assertFalse(cl.contains(Card.ACE_OF_HEARTS));
		assertFalse(cl.contains(Card.JACK_OF_CLUBS));
		assertFalse(cl.contains(Card.TEN_OF_HEARTS));
		assertFalse(cl.contains(Card.ACE_OF_DIAMONDS));
	}
	
	/**
	 * Checks added(Card) method.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void cardListAddMethod() {
		CardList cl = new CardList(Card.ACE_OF_CLUBS, Card.KING_OF_CLUBS, Card.QUEEN_OF_CLUBS);
		
		cl = cl.added(Card.SIX_OF_CLUBS);
		
		assertTrue(cl.contains(Card.SIX_OF_CLUBS));
		cl.add(Card.SIX_OF_DIAMONDS);	// should not be supported
	}
	
	/**
	 * Checks added(Card) method.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void cardListAddAllMethod() {
		CardList cl1 = new CardList(Card.ACE_OF_CLUBS, Card.KING_OF_CLUBS, Card.QUEEN_OF_CLUBS);
		CardList cl2 = new CardList(Card.ACE_OF_DIAMONDS, Card.KING_OF_DIAMONDS, Card.QUEEN_OF_DIAMONDS);
		
		CardList cl = cl1.added(cl2);
		
		assertTrue(cl.containsAll(cl1));
		assertTrue(cl.containsAll(cl2));
		cl.addAll(cl2);	// should not be supported
	}
	
	/**
	 * Checks removeCard(Card) method.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void cardListRemoveMethod() {
		CardList cl = new CardList(Card.ACE_OF_CLUBS, Card.KING_OF_CLUBS, Card.QUEEN_OF_CLUBS);
		
		cl = cl.removed(Card.QUEEN_OF_CLUBS);
		assertFalse(cl.contains(Card.QUEEN_OF_CLUBS));
		
		cl = cl.removed(Card.NINE_OF_DIAMONDS);
		assertFalse(cl.contains(Card.NINE_OF_DIAMONDS));
		
		cl.removed(Card.ACE_OF_CLUBS);	// should not be supported
	}
	
	/**
	 * Checks isEmpty() method.
	 */
	@Test
	public void cardListIsEmptyMethod() {
		Card c = Card.ACE_OF_HEARTS;
		
		CardList cl = new CardList();
		assertTrue(cl.isEmpty());
		
		cl = cl.added(c);
		assertFalse(cl.isEmpty());
		
		cl = cl.removed(c);
		assertTrue(cl.isEmpty());
	}
	
	/**
	 * Checks toString() method, prints the beautiful string rep.
	 */
	
	@Test
	public void cardListToStringMethod() {
		CardList cl = CardListHelper.createBasicDeck();
		System.out.println("=== Ordered version ===");
		System.out.println(cl);
		
		assertEquals(cl.toString(), "[6 \u2665, 7 \u2665, 8 \u2665, 9 \u2665, 10\u2665, J \u2665, Q \u2665, K \u2665, A \u2665," +
				" 6 \u2660, 7 \u2660, 8 \u2660, 9 \u2660, 10\u2660, J \u2660, Q \u2660, K \u2660, A \u2660," +
				" 6 \u2663, 7 \u2663, 8 \u2663, 9 \u2663, 10\u2663, J \u2663, Q \u2663, K \u2663, A \u2663," +
				" 6 \u2666, 7 \u2666, 8 \u2666, 9 \u2666, 10\u2666, J \u2666, Q \u2666, K \u2666, A \u2666]");
		
		cl = CardListHelper.shuffleCardList(cl);
		System.out.println("\n=== Shuffled version ===");
		System.out.println(cl);
	}
	
	/**
	 * Checks that equals work for two separate identical cardlists, but not for
	 * two different ones.
	 */
	
	@Test
	public void cardListEqualsMethod() {
		CardList cl1 = CardListHelper.createBasicDeck();
		CardList cl2 = CardListHelper.createBasicDeck();
		
		assertTrue(cl1.equals(cl2));
		
		cl1 = CardListHelper.shuffleCardList(cl1);
		cl2 = CardListHelper.shuffleCardList(cl2);
		
		assertFalse(cl1.equals(cl2));
	}
	
	/**
	 * Checks that the subList method retrieves a proper sub list.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void cardListSubListMethod() {
		CardList cl = new CardList(Card.SIX_OF_HEARTS, Card.SEVEN_OF_HEARTS, Card.EIGHT_OF_HEARTS);
		CardList subList = cl.take(1, 3);
		
		assertTrue(subList.size() == 2);
		assertEquals(cl.get(1), subList.get(0));
		assertEquals(cl.get(2), subList.get(1));
		
		cl.subList(0, 4);
	}
	
	/**
	 * Checks that swap methods indeed swaps two cards, and that if indexes
	 * are the same, no card is being swapped.
	 */
	@Test
	public void cardListSwapCardsMethod() {
		CardList cl = CardListHelper.createBasicDeck();
		Random rdn = new Random();
		int index1 = rdn.nextInt(cl.size());
		int index2 = rdn.nextInt(cl.size());
		
		Card c1 = cl.get(index1);
		Card c2 = cl.get(index2);
		
		cl = cl.swapped(index1, index2);
		assertEquals(cl.get(index1), c2);
		assertEquals(cl.get(index2), c1);
		
		int index = rdn.nextInt(cl.size());
		
		Card c = cl.get(index);
		
		cl = cl.swapped(index, index);
		assertEquals(cl.get(index), c);
	}
	
	/**
	 * Checks hidden() method.
	 */
	@Test
	public void cardListHideMethod() {
		CardList deck = CardListHelper.createBasicDeck();
		CardList hiddenDeck = deck.hidden();
		
		for (Card card : hiddenDeck) {
			assertEquals(card, Card.UNKNOWN_CARD);
		}
	}
	
	/**
	 * Checks that sort method returns a sorted CardList.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void cardListHelperSortCardList() {
		CardList deck = CardListHelper.createBasicDeck();
		CardList shuffle = CardListHelper.shuffleCardList(deck);
		
		CardList sorted = CardListHelper.sortCardList(shuffle);
		assertEquals(deck, sorted);
		
		Collections.sort(shuffle);	// should not be supported
	}
	
	/**
	 * Checks that each card issued from a basic deck is ordered.
	 */
	@Test
	public void cardListHelperCreateDeck() {
		CardList deck = CardListHelper.createBasicDeck();
		assertEquals(36, deck.size());
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 9; j++) {
				Card card = deck.get(i*9 + j);
				assertEquals(j+6, card.getValue().getIntValue());
			}
		}
	}
	
	/**
	 * Checks that the cards are not ordered.
	 */
	@Test
	public void cardListHelperShuffle() {
		CardList deck = CardListHelper.createBasicDeck();
		deck = CardListHelper.shuffleCardList(deck);
		assertEquals(36, deck.size());
		int count = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 9; j++) {
				Card card = deck.get(i*9 + j);
				if (card.getValue().getIntValue() == j+6) {
					count++;
				}
			}
		}
		if (count > 18) {
			fail("Half of the cards are ordered.");
		}
	}
	
	/**
	 * Checks that the hands are well dealt.
	 */
	@Test
	public void cardListHelperDealHands() {
		CardList deck = CardListHelper.createBasicDeck();
		CardList[] hands = CardListHelper.dealCards(deck);
		
		for (CardList hand : hands) {
			System.out.println(hand);
		}
		
		System.out.println();
		
		CardList nineCardsDeck = deck.take(0, 9);
		hands = CardListHelper.dealCards(nineCardsDeck);
		
		for (CardList hand : hands) {
			System.out.println(hand);
		}
		System.out.println();
		
		CardList sevenCardsDeck = deck.take(9, 16);
		hands = CardListHelper.dealCards(sevenCardsDeck);
		
		for (CardList hand : hands) {
			System.out.println(hand);
		}
	}
}
