package test;

import game.Match;
import game.Card.CardSuit;
import org.junit.Test;

public class MatchTest {

	@Test
	public void createBasicMatch() {
		
		Match m = new Match(CardSuit.HEARTS, 0, null);
	
	}

}
