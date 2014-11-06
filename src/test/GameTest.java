package test;

import static org.junit.Assert.*;
import game.Card;
import game.CardList;
import game.Game;
import game.Match;
import game.Card.Suit;

import org.junit.Test;

public class GameTest {

	@Test
	public void gameToString() {
		Game g = new Game(new int[]{0,0 }, new Match(Suit.UNKNOWN, 0, CardList.getBasic().deal()), 0);
		g = g.setUp(Suit.HEARTS);
		
		g = g.update(Card.SIX_OF_HEARTS);
		g = g.update(Card.JACK_OF_HEARTS);
		g = g.update(Card.QUEEN_OF_HEARTS);
		g = g.update(Card.TEN_OF_CLUBS);
		
		g = g.update(Card.NINE_OF_HEARTS);
		g = g.update(Card.KING_OF_HEARTS);
		g = g.update(Card.JACK_OF_CLUBS);
		g = g.update(Card.SEVEN_OF_HEARTS);
		
		g = g.update(Card.TEN_OF_HEARTS);
		g = g.update(Card.ACE_OF_HEARTS);
		g = g.update(Card.NINE_OF_CLUBS);
		g = g.update(Card.EIGHT_OF_HEARTS);
		
		g = g.update(Card.SIX_OF_CLUBS);
		g = g.update(Card.SIX_OF_SPADES);
		g = g.update(Card.ACE_OF_CLUBS);
		g = g.update(Card.SIX_OF_DIAMONDS);
		
		g = g.update(Card.KING_OF_CLUBS);
		g = g.update(Card.SEVEN_OF_DIAMONDS);
		g = g.update(Card.SEVEN_OF_CLUBS);
		g = g.update(Card.SEVEN_OF_SPADES);
		
		g = g.update(Card.QUEEN_OF_CLUBS);
		g = g.update(Card.EIGHT_OF_DIAMONDS);
		g = g.update(Card.EIGHT_OF_CLUBS);
		g = g.update(Card.EIGHT_OF_SPADES);
		
		g = g.update(Card.JACK_OF_SPADES);
		g = g.update(Card.ACE_OF_SPADES);
		g = g.update(Card.NINE_OF_DIAMONDS);
		g = g.update(Card.ACE_OF_DIAMONDS);
		
		g = g.update(Card.KING_OF_SPADES);
		g = g.update(Card.JACK_OF_DIAMONDS);
		g = g.update(Card.KING_OF_DIAMONDS);
		g = g.update(Card.NINE_OF_SPADES);
		
		g = g.update(Card.QUEEN_OF_SPADES);
		g = g.update(Card.TEN_OF_DIAMONDS);
		g = g.update(Card.QUEEN_OF_DIAMONDS);
		g = g.update(Card.TEN_OF_SPADES);
		
		System.out.println(g);
		assertTrue(true);
	}

}
