package game;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final public class CardList extends AbstractList<Card> {

	final private List<Card> cardlist;

    private CardList(List<Card> cards, int dummy) {
        cardlist = Collections.unmodifiableList(cards);
    }
	
	public CardList(List<Card> list) {
		this(new ArrayList<Card>(list), 0);
	}

    public CardList(Card... cards) {
        this(Arrays.asList(cards));
    }
	
	@Override
	public Card get(int i) {
		return cardlist.get(i);
	}

	@Override
	public int size() {
		return cardlist.size();
	}
	
	public CardList addCard(Card c) {
		ArrayList<Card> tmp = new ArrayList<Card>(cardlist);
		tmp.add(c);
		return new CardList(tmp, 0);
	}
	
	public CardList addCardList(CardList cl) {
		ArrayList<Card> tmp = new ArrayList<Card>(cardlist);
		tmp.addAll(tmp.size(), new ArrayList<Card>(cl.cardlist));
		return new CardList(tmp, 0);
	}
	
	public CardList removeCard(Card c) {
		ArrayList<Card> tmp = new ArrayList<Card>();
		for (Card nextCard : cardlist) {
			if (!c.equals(nextCard)) {
				tmp.add(nextCard);
			}
		}
		
		return new CardList(tmp, 0);
	}
	
	public CardList swapCards(int index1, int index2) {
		if (index1 < 0 || index1 > size() || index2 < 0 || index2 > size()) {
			throw new IndexOutOfBoundsException("Invalid indexes: " + index1 + ", " + index2
					+ " expected range: [0, " + size() +"]");
		}
		if (index1 == index2) {
			return this;
		}
		ArrayList<Card> tmp = new ArrayList<Card>();
		for (int i = 0; i < size(); i++) {
			if (i == index1) {
				tmp.add(get(index2));
			} else if (i == index2) {
				tmp.add(get(index1));
			} else {
				tmp.add(get(i));
			}
		}
		
		return new CardList(tmp, 0);
	}
	
	public CardList hide() {
		int n = size();
		List<Card> tmp = new ArrayList<Card>(n);
		for (int i = 0; i < n; i++) {
			tmp.add(Card.UNKNOWN_CARD);
		}
		return new CardList(tmp, 0);
	}
	
	@Override
	public CardList subList(int fromIndex, int toIndex) {
		ArrayList<Card> tmp = new ArrayList<Card>(cardlist.subList(fromIndex, toIndex));
		return new CardList(tmp, 0);
	}
	
	@Override
	public String toString() {
		if (isEmpty()) {
			return "[]";
		}
		String str = "[";
		for (Card c : cardlist) {
			str += c.toString() + ", ";
		}
		return str.substring(0, str.length()-2) + "]";
	}

}
