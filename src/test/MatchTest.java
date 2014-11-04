package test;

import game.Card;
import game.Card.CardSuit;
import game.CardList;
import game.CardListHelper;
import game.Match;

import org.junit.Test;

public class MatchTest {

	@Test
	public void createBasicMatch() {
		
		CardList[] hands = CardListHelper.dealCards(CardListHelper.createBasicDeck());
		
		Match m = new Match(CardSuit.HEARTS, 0, hands);
		m = m.update(Card.SIX_OF_HEARTS);
		m = m.update(Card.JACK_OF_HEARTS);
		m = m.update(Card.QUEEN_OF_HEARTS);
		m = m.update(Card.TEN_OF_CLUBS);
		System.out.println(m);
	}

}
