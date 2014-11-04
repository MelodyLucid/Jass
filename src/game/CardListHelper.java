package game;

import java.util.ArrayList;
import java.util.Collections;

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
	
	public static CardList[] dealCards(CardList deck) {
		CardList[] hands = new CardList[4];
		for (int i = 0; i < hands.length; i++) {
			hands[i] = new CardList();
		}
		
		if (deck.size() > 11) {
			int i = 0, j = 0;
			for (; j < deck.size()-2; j += 3) {
				CardList threeCards = deck.take(j, j + 3);
				hands[i] = hands[i].added(threeCards);
				i = (i + 1) % 4;
			}
			for (; j < deck.size(); j++) {
				hands[i] = hands[i].added(deck.get(j));
				i = (i + 1) % 4;
			}
			return hands;
		}
		if (deck.size() < 8) {
			int i = 0;
			for (Card card : deck) {
				hands[i] = hands[i].added(card);
				i = (i + 1) % 4;
			}
			
			return hands;
		}
		int i = 0, j = 0;
		for (; j < deck.size()-1; j += 2) {
			CardList twoCards = deck.take(j, j + 2);
			hands[i] = hands[i].added(twoCards);
			i = (i + 1) % 4;
		}
		hands[i] = hands[i].added(deck.get(j));
		return hands;
	}
}
