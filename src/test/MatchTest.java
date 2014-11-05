package test;

import static org.junit.Assert.*;
import game.Card;
import game.Card.CardSuit;
import game.CardList;
import game.Match;

import org.junit.Test;

import java.util.List;

public class MatchTest {

	@Test
	public void simulateBasicMatch() {
		
		List<CardList> hands = CardList.getBasic().deal();
		
		Match m = new Match(CardSuit.HEARTS, 0, hands);
		m = m.update(Card.SIX_OF_HEARTS);
		m = m.update(Card.JACK_OF_HEARTS);
		m = m.update(Card.QUEEN_OF_HEARTS);
		m = m.update(Card.TEN_OF_CLUBS);
		
		m = m.update(Card.NINE_OF_HEARTS);
		m = m.update(Card.KING_OF_HEARTS);
		m = m.update(Card.JACK_OF_CLUBS);
		m = m.update(Card.SEVEN_OF_HEARTS);
		
		m = m.update(Card.TEN_OF_HEARTS);
		m = m.update(Card.ACE_OF_HEARTS);
		m = m.update(Card.NINE_OF_CLUBS);
		m = m.update(Card.EIGHT_OF_HEARTS);
		
		m = m.update(Card.SIX_OF_CLUBS);
		m = m.update(Card.SIX_OF_SPADES);
		m = m.update(Card.ACE_OF_CLUBS);
		m = m.update(Card.SIX_OF_DIAMONDS);
		
		m = m.update(Card.KING_OF_CLUBS);
		m = m.update(Card.SEVEN_OF_DIAMONDS);
		m = m.update(Card.SEVEN_OF_CLUBS);
		m = m.update(Card.SEVEN_OF_SPADES);
		
		m = m.update(Card.QUEEN_OF_CLUBS);
		m = m.update(Card.EIGHT_OF_DIAMONDS);
		m = m.update(Card.EIGHT_OF_CLUBS);
		m = m.update(Card.EIGHT_OF_SPADES);
		
		m = m.update(Card.JACK_OF_SPADES);
		m = m.update(Card.ACE_OF_SPADES);
		m = m.update(Card.NINE_OF_DIAMONDS);
		m = m.update(Card.ACE_OF_DIAMONDS);
		
		m = m.update(Card.KING_OF_SPADES);
		m = m.update(Card.JACK_OF_DIAMONDS);
		m = m.update(Card.KING_OF_DIAMONDS);
		m = m.update(Card.NINE_OF_SPADES);
		
		m = m.update(Card.QUEEN_OF_SPADES);
		m = m.update(Card.TEN_OF_DIAMONDS);
		m = m.update(Card.QUEEN_OF_DIAMONDS);
		m = m.update(Card.TEN_OF_SPADES);
		
		System.out.println(m);
	}
	
	@Test
	public void matchInstanceForMethod() {
		List<CardList> hands = CardList.getBasic().deal();
		
		Match m = new Match(CardSuit.HEARTS, 0, hands);
		
		System.out.println("=========== Match.instanceFor(i) ============");
		for (int i = 0; i < hands.size(); i++) {
			Match visible = m.getInstanceFor(i);
			for (int j = 0; j < 4; j++) {
				if (i == j) {
					assertEquals(visible.getHand(j), hands.get(j));
					System.out.println(visible.getHand(j));
				} else {
					CardList otherPlayerHand = visible.getHand(j);
					for (Card card : otherPlayerHand) {
						assertEquals(card, Card.UNKNOWN_CARD);
					}
					System.out.println(otherPlayerHand);
				}
			}
			System.out.println("=============================================");
		}
	}

}
