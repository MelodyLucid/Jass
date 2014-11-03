package old;


import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import old.Card.CardSuit;
import old.Card.CardValue;


public class Hand {

	public static final int CARDS_PER_HAND = 9;
	private Card[] hand;

	public Hand(Card[] hand) {
		assert (hand.length == CARDS_PER_HAND);
		this.hand = Arrays.copyOf(hand, CARDS_PER_HAND);
	}

	public Card[] pickSuit(CardSuit suit) {
		int n = 0;
		Card[] picked = new Card[9];
		for (int i = 0; i < hand.length; i++) {
			if (hand[i].getSuit() == suit) {
				picked[n++] = hand[i];
			}
		}

		return Arrays.copyOf(picked, n);
	}

	public Card[] pickValue(CardValue value) {
		int n = 0;
		Card[] picked = new Card[9];
		for (int i = 0; i < hand.length; i++) {
			if (hand[i].getValue() == value) {
				picked[n++] = hand[i];
			}
		}

		return Arrays.copyOf(picked, n);
	}

	public boolean pickCard(Card card) {
		for (int i = 0; i < hand.length; i++) {
			if (hand[i].equals(card)) {
				List<Card> result = new LinkedList<Card>();
				
				for (Card item : hand) {
					if (!hand[i].equals(item)) {
						result.add(item);
					}
				}
				
				Card[] newHand = new Card[hand.length - 1];
				newHand = result.toArray(newHand);
				hand = newHand;
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return Arrays.toString(hand);
	}
}
