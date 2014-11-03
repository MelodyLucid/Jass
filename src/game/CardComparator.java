package game;

import java.util.Comparator;

final public class CardComparator implements Comparator<Card> {

	final private Card.CardSuit enteringSuit;
	final private Card.CardSuit trumpSuit;
	
	public CardComparator(Card.CardSuit enteringSuit, Card.CardSuit trumpSuit) {
		this.enteringSuit = enteringSuit;
		this.trumpSuit = trumpSuit;
	}
	
	/**
	 * Compares two cards, depending on the trump suit and their value.
	 * <p>
	 * If someone comes up with a better algorithm, I'm interested.
	 */
	@Override
	public int compare(Card c1, Card c2) {
		if (trumpSuit != enteringSuit) {
			if (c1.getSuit() == enteringSuit) {
				if (c2.getSuit() == trumpSuit) {
					return -1;
				}
				if (c2.getSuit() == enteringSuit) {
					return c1.compareTo(c2);
				}
				return 1;
			}
			if (c1.getSuit() == trumpSuit) {
				if (c2.getSuit() == trumpSuit) {
					if (c1.getCardValue(true) > c2.getCardValue(true)) {
						return 1;
					}
					if (c1.getCardValue(true) < c2.getCardValue(true)) {
						return -1;
					}
					return 0;
				}
				return 1;
			}
			if (c2.getSuit() == enteringSuit) {
				return -1;
			}
			if (c2.getSuit() == trumpSuit) {
				return -1;
			}
		} else {
			if (c1.getSuit() == trumpSuit) {
				if (c2.getSuit() == trumpSuit) {
					if (c1.getCardValue(true) > c2.getCardValue(true)) {
						return 1;
					}
					if (c1.getCardValue(true) < c2.getCardValue(true)) {
						return -1;
					}
					return 0;
				}
				return 1;
			}
			if (c2.getSuit() == trumpSuit) {
				return -1;
			}
		}
		
		return 0;
	}

}
