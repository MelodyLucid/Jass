package game;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final public class CardList extends AbstractList<Card> {

	final private List<Card> cardlist;
	
	public CardList(Card... cards) {
		cardlist = Collections.unmodifiableList(Arrays.asList(cards));
	}
	
	public CardList(List<Card> list) {
		cardlist = Collections.unmodifiableList(new ArrayList<Card>(list));
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
		for (Card nextCard : cardlist) {
			if (!c.equals(nextCard)) {
				tmp.add(c);
			}
		}
		
		return new CardList(tmp);
	}
	
	public CardList addCard(Card c) {
		ArrayList<Card> tmp = new ArrayList<Card>(cardlist);
		tmp.add(c);
		return new CardList(tmp);
	}

}
