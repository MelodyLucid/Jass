package game;

import java.util.*;

final public class CardList extends AbstractList<Card> {

	final private List<Card> cardlist;

    private CardList(List<Card> cards, int dummy) {
        cardlist = Collections.unmodifiableList(cards);
    }
	
	public CardList(Collection<Card> list) {
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
	
	public CardList added(Card c) {
		ArrayList<Card> tmp = new ArrayList<Card>(cardlist.size() + 1);
        tmp.addAll(cardlist);
		tmp.add(c);
		return new CardList(tmp, 0);
	}
	
	public CardList added(Collection<Card> col) {
		ArrayList<Card> tmp = new ArrayList<Card>(cardlist.size() + col.size());
        tmp.addAll(cardlist);
		tmp.addAll(col);
		return new CardList(tmp, 0);
	}
	
	public CardList removed(Card c) {
		ArrayList<Card> tmp = new ArrayList<Card>(cardlist);
        tmp.remove(c);
		return new CardList(tmp, 0);
	}

    public CardList removed(Collection<Card> col) {
        ArrayList<Card> tmp = new ArrayList<Card>(cardlist);
        tmp.removeAll(col);
        return new CardList(tmp, 0);
    }
	
	public CardList swapped(int index1, int index2) {
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
	
	public CardList hidden() {
		int n = size();
		List<Card> tmp = new ArrayList<Card>(n);
		for (int i = 0; i < n; i++) {
			tmp.add(Card.UNKNOWN_CARD);
		}
		return new CardList(tmp, 0);
	}

	public CardList take(int fromIndex, int toIndex) {
        int f = Math.max(Math.min(fromIndex, toIndex), 0);
        int t = Math.min(Math.max(fromIndex, toIndex), cardlist.size());
		ArrayList<Card> tmp = new ArrayList<Card>(cardlist.subList(f, t));
		return new CardList(tmp, 0);
	}

    public CardList take(int count) {
        return take(0, count);
    }

    public CardList drop(int count) {
        return take(count, cardlist.size());
    }

    public Card head() {
        return cardlist.isEmpty() ? null : cardlist.get(0);
    }

    public CardList tail() {
        return drop(1);
    }

    public List<CardList> split(int count) {
        if (count <= 1)
            return Collections.singletonList(this);
        ArrayList<CardList> tmp = new ArrayList<CardList>(count);
        int length = cardlist.size() / count;
        for (int i = 0; i < count; ++i)
            tmp.add(take(i * length, (i + 1) * length));
        return tmp;
    }

    public CardList withSuit(Card.CardSuit suit) {
        ArrayList<Card> tmp = new ArrayList<Card>(cardlist.size());
        for (Card c : cardlist)
            if (c.getSuit() == suit)
                tmp.add(c);
        return new CardList(tmp, 0);
    }

    public Map<Card.CardSuit, CardList> splitBySuit() {
        Map<Card.CardSuit, CardList> tmp = new TreeMap<Card.CardSuit, CardList>();
        for (Card.CardSuit s : Card.CardSuit.values())
            tmp.put(s, withSuit(s));
        return tmp;
    }

    public CardList shuffled() {
        ArrayList<Card> tmp = new ArrayList<Card>(cardlist);
        Collections.shuffle(tmp);
        return new CardList(tmp, 0);
    }

    public CardList reversed() {
        ArrayList<Card> tmp = new ArrayList<Card>(cardlist);
        Collections.reverse(tmp);
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
