package game;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final public class CardList extends AbstractList<Card> {

	final private List<Card> cardlist;
	
	public CardList(Card... cards) {
		ArrayList<Card> tmp = new ArrayList<Card>(cards.length);
		for (int i = 0; i < cards.length; i++) {
			tmp.add(cards[i]);
		}
		cardlist = Collections.unmodifiableList(tmp);
	}
	
	public CardList(List<Card> list) {
		cardlist = Collections.unmodifiableList(list);
	}
	
	@Override
	public Card get(int i) {
		return cardlist.get(i);
	}

	@Override
	public int size() {
		return cardlist.size();
	}
	
	public CardList removeCard(Card c) {
		ArrayList<Card> tmp = new ArrayList<Card>();
		for (int i = 0; i < cardlist.size(); i++) {
			Card nextCard = cardlist.get(i);
			if (!c.equals(nextCard)) {
				tmp.add(c);
			}
		}
		
		return new CardList(tmp);
	}
	
	public CardList addCard(Card c) {
		List<Card> tmp = cardlist;
		tmp.add(c);
		return new CardList(tmp);
	}

}
