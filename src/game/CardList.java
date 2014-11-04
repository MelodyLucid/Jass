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
	
	public boolean contains(Card c) {
		return cardlist.contains(c);
	}
	
	public boolean isEmpty() {
		return cardlist.isEmpty();
	}
	
	public CardList addCard(Card c) {
		ArrayList<Card> tmp = new ArrayList<Card>(cardlist);
		tmp.add(c);
		return new CardList(tmp);
	}
	
	public CardList addCardList(CardList cl) {
		ArrayList<Card> tmp = new ArrayList<Card>(cardlist);
		tmp.addAll(tmp.size(), new ArrayList<Card>(cl.cardlist));
		return new CardList(tmp);
	}
	
	public CardList removeCard(Card c) {
		ArrayList<Card> tmp = new ArrayList<Card>();
		for (Card nextCard : cardlist) {
			if (!c.equals(nextCard)) {
				tmp.add(nextCard);
			}
		}
		
		return new CardList(tmp);
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
		
		return new CardList(tmp);
	}
	
	@Override
	public CardList subList(int fromIndex, int toIndex) {
		ArrayList<Card> tmp = new ArrayList<Card>(cardlist.subList(fromIndex, toIndex));
		return new CardList(tmp);
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
	
	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != this.getClass()) {
			return false;
		}
		
		CardList that = (CardList) o;
		
		if (that.size() != this.size()) {
			return false;
		}
		for (int i = 0; i < size(); i++) {
			Card c1 = this.get(i);
			Card c2 = that.get(i);
			
			if (!c1.equals(c2)) {
				return false;
			}
		}
		
		return true;
	}

}
