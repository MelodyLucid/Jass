package game;

final public class Card implements Comparable<Card> {

	final private CardSuit suit;
	final private CardValue value;
	
	public Card(CardSuit suit, CardValue value) {
		this.suit = suit;
		this.value = value;
	}
	
	public int getPoints(boolean trump) {
		if (trump) {
			switch (value) {
				case JACK : return 20;	// Buur
				case NINE : return 14;	// Nel
				default:	// do nothing
			}
		}
		switch (value) {
			case ACE   : return 11;
			case KING  : return 4;
			case QUEEN : return 3;
			case JACK  : return 2;
			case TEN   : return 10;
			default    : return 0;
		}
	}
	
	public int getCardValue(boolean trump) {
		if (trump) {
			switch (value) {
				case JACK : return 16;	// Buur
				case NINE : return 15;	// Nel
				default   : return value.cardValue;
			}
		} else {
			return value.cardValue;
		}
	}
	
	public CardValue getValue() {
		return value;
	}
	
	public CardSuit getSuit() {
		return suit;
	}
	
	public String fullName() {
		return value.name() + " of " + suit.name();
	}
	
	
	public enum CardSuit {
		HEARTS(0), SPADES(1), CLUBS(2), DIAMONDS(3),
		UNKNOWN(-1);
		
		private int cardSuit;

		private CardSuit(int suit) {
			this.cardSuit = suit;
		}

		public int getCardValue() {
			return cardSuit;
		}
		
		public java.awt.Color getColor() {
			switch (cardSuit) {
				case 0:
				case 3: return java.awt.Color.RED;
				default: return java.awt.Color.BLACK;
			}
		}
		
		@Override
		public String toString() {
			switch (cardSuit) {
				case 0: return "\u2665";
				case 1: return "\u2660";
				case 2: return "\u2663";
				case 3: return "\u2666";
				default: return "?";
			}
		}
	}
	
	public enum CardValue {
		SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
		JACK(11), QUEEN(12), KING(13), ACE(14),
		UNKNOWN(-1);

		private int cardValue;

		private CardValue(int value) {
			this.cardValue = value;
		}
		
		@Override
		public String toString() {
			switch (cardValue) {
				case 11 : return "J";
				case 12 : return "Q";
				case 13 : return "K";
				case 14 : return "A";
				default: return Integer.toString(cardValue);
			}
		}
	}
	
	@Override
	public String toString() {
		return value.toString() + " " + suit.toString().charAt(0);
	}
	
	@Override
	public Card clone() {
		return new Card(this.suit, this.value);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null || o.getClass() != this.getClass()) {
			return false;
		}
		
		return ((Card) o).suit == this.suit && ((Card) o).value == this.value;
	}
	
	@Override
	public int compareTo(Card c) {
		if (c == null || this.value.cardValue > c.value.cardValue) {
			return 1;
		}
		if (this.value.cardValue < c.value.cardValue) {
			return -1;
		}
		return 0;
	}
}
