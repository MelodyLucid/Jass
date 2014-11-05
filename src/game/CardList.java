package game;

import java.util.*;

final public class CardList extends AbstractList<Card> {

	private static final CardList basic = new CardList(Arrays.asList(Card.values()));

	public static CardList getBasic() {
		return basic;
	}

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
	
	public CardList swapped(int i, int j) {
        ArrayList<Card> tmp = new ArrayList<Card>(cardlist);
        Collections.swap(tmp, i, j);
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
		return new CardList(cardlist.subList(f, t), 0);
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

	public Card last() {
		return cardlist.isEmpty() ? null : cardlist.get(cardlist.size() - 1);
	}

	public CardList init() {
		return take(cardlist.size() - 1);
	}

	public List<CardList> split(int count) {
		if (count <= 1) {
			return Collections.singletonList(this);
		}
		ArrayList<CardList> tmp = new ArrayList<CardList>(count);
		int length = cardlist.size() / count;
		for (int i = 0; i < count; ++i) {
			tmp.add(take(i * length, (i + 1) * length));
		}
		return tmp;
	}

	public CardList withSuit(Card.CardSuit suit) {
		ArrayList<Card> tmp = new ArrayList<Card>(cardlist.size());
		for (Card c : cardlist) {
			if (c.getSuit() == suit) {
				tmp.add(c);
			}
		}
		return new CardList(tmp, 0);
	}

	public Map<Card.CardSuit, CardList> splitBySuit() {
		Map<Card.CardSuit, CardList> tmp = new TreeMap<Card.CardSuit, CardList>();
		for (Card.CardSuit s : Card.CardSuit.values()) {
			tmp.put(s, withSuit(s));
		}
		return tmp;
	}

	public List<CardList> deal() {
		List<CardList> hands = new ArrayList<CardList>(4);
		for (int i = 0; i < 4; i++) {
			hands.add(new CardList());
		}

		if (size() > 11) {
			int i = 0, j = 0;
			for (; j < size() - 2; j += 3) {
				CardList threeCards = take(j, j + 3);
				hands.set(i, hands.get(i).added(threeCards));
				i = (i + 1) % 4;
			}
			for (; j < size(); j++) {
				hands.set(i, hands.get(i).added(get(j)));
				i = (i + 1) % 4;
			}
			return hands;
		}
		if (size() < 8) {
			int i = 0;
			for (Card card : this) {
				hands.set(i, hands.get(i).added(card));
				i = (i + 1) % 4;
			}

			return hands;
		}
		int i = 0, j = 0;
		for (; j < size() - 1; j += 2) {
			CardList twoCards = take(j, j + 2);
			hands.set(i, hands.get(i).added(twoCards));
			i = (i + 1) % 4;
		}
		hands.set(i, hands.get(i).added(get(j)));
		return hands;
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

    public CardList rotated(int n) {
        ArrayList<Card> tmp = new ArrayList<Card>(cardlist);
        Collections.rotate(tmp, n);
        return new CardList(tmp, 0);
    }

	public CardList sorted() {
		ArrayList<Card> tmp = new ArrayList<Card>(cardlist);
		Collections.sort(tmp);
		return new CardList(tmp, 0);
	}

    public Card min() {
        return cardlist.isEmpty() ? null : Collections.min(cardlist);
    }

    public Card max() {
        return cardlist.isEmpty() ? null : Collections.max(cardlist);
    }

    public int frequency(Card c) {
        return Collections.frequency(cardlist, c);
    }

    public CardList distinct() {
        ArrayList<Card> tmp = new ArrayList<Card>(cardlist.size());
        for (Card c : cardlist) {
            if (!tmp.contains(c)) {
                tmp.add(c);
            }
        }
        return new CardList(tmp, 0);
    }

    public CardList union(Collection<Card> col) {
        ArrayList<Card> tmp = new ArrayList<Card>(cardlist.size());
        for (Card c : cardlist) {
            if (!tmp.contains(c)) {
                tmp.add(c);
            }
        }
        for (Card c : col) {
            if (!tmp.contains(c)) {
                tmp.add(c);
            }
        }
        return new CardList(tmp, 0);
    }

    public CardList intersection(Collection<Card> col) {
        ArrayList<Card> tmp = new ArrayList<Card>(cardlist.size());
        for (Card c : cardlist) {
            if (!tmp.contains(c) && col.contains(c)) {
                tmp.add(c);
            }
        }
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
		return str.substring(0, str.length() - 2) + "]";
	}

}
