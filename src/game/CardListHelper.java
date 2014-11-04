package game;

import java.util.ArrayList;
import java.util.Collections;

import game.Card;

public class CardListHelper {

	public static CardList createBasicDeck() {
		ArrayList<Card> deck = new ArrayList<Card>(Card.values().length - 1);
		for (int i = 0; i < Card.values().length - 1; i++) {
			deck.add(Card.values()[i]);
		}
		return new CardList(deck);
	}
	
	public static CardList shuffleCardList(CardList cl) {
		ArrayList<Card> tmp = new ArrayList<Card>(cl);
		Collections.shuffle(tmp);
		return new CardList(tmp);
	}
	
	public static CardList sortCardList(CardList cl) {
		ArrayList<Card> tmp = new ArrayList<Card>(cl);
		Collections.sort(tmp);
		return new CardList(tmp);
	}
}
