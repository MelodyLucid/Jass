package game;

import java.util.Arrays;
import java.util.List;

public enum Card {

	SIX_OF_HEARTS(Suit.HEARTS, Value.SIX),
	SEVEN_OF_HEARTS(Suit.HEARTS, Value.SEVEN),
	EIGHT_OF_HEARTS(Suit.HEARTS, Value.EIGHT),
	NINE_OF_HEARTS(Suit.HEARTS, Value.NINE),
	TEN_OF_HEARTS(Suit.HEARTS, Value.TEN),
	JACK_OF_HEARTS(Suit.HEARTS, Value.JACK),
	QUEEN_OF_HEARTS(Suit.HEARTS, Value.QUEEN),
	KING_OF_HEARTS(Suit.HEARTS, Value.KING),
	ACE_OF_HEARTS(Suit.HEARTS, Value.ACE),
	
	SIX_OF_SPADES(Suit.SPADES, Value.SIX),
	SEVEN_OF_SPADES(Suit.SPADES, Value.SEVEN),
	EIGHT_OF_SPADES(Suit.SPADES, Value.EIGHT),
	NINE_OF_SPADES(Suit.SPADES, Value.NINE),
	TEN_OF_SPADES(Suit.SPADES, Value.TEN),
	JACK_OF_SPADES(Suit.SPADES, Value.JACK),
	QUEEN_OF_SPADES(Suit.SPADES, Value.QUEEN),
	KING_OF_SPADES(Suit.SPADES, Value.KING),
	ACE_OF_SPADES(Suit.SPADES, Value.ACE),
	
	SIX_OF_CLUBS(Suit.CLUBS, Value.SIX),
	SEVEN_OF_CLUBS(Suit.CLUBS, Value.SEVEN),
	EIGHT_OF_CLUBS(Suit.CLUBS, Value.EIGHT),
	NINE_OF_CLUBS(Suit.CLUBS, Value.NINE),
	TEN_OF_CLUBS(Suit.CLUBS, Value.TEN),
	JACK_OF_CLUBS(Suit.CLUBS, Value.JACK),
	QUEEN_OF_CLUBS(Suit.CLUBS, Value.QUEEN),
	KING_OF_CLUBS(Suit.CLUBS, Value.KING),
	ACE_OF_CLUBS(Suit.CLUBS, Value.ACE),
	
	SIX_OF_DIAMONDS(Suit.DIAMONDS, Value.SIX),
	SEVEN_OF_DIAMONDS(Suit.DIAMONDS, Value.SEVEN),
	EIGHT_OF_DIAMONDS(Suit.DIAMONDS, Value.EIGHT),
	NINE_OF_DIAMONDS(Suit.DIAMONDS, Value.NINE),
	TEN_OF_DIAMONDS(Suit.DIAMONDS, Value.TEN),
	JACK_OF_DIAMONDS(Suit.DIAMONDS, Value.JACK),
	QUEEN_OF_DIAMONDS(Suit.DIAMONDS, Value.QUEEN),
	KING_OF_DIAMONDS(Suit.DIAMONDS, Value.KING),
	ACE_OF_DIAMONDS(Suit.DIAMONDS, Value.ACE),
	
	UNKNOWN_CARD(Suit.UNKNOWN, Value.UNKNOWN);
	
	final public static int TOTAL_NUMBER = values().length - 1;
	final private Suit suit;
	final private Value value;
	
	public static Card getCard(Suit suit, Value value) {
		for (int i = 0; i < values().length; i++) {
			if (values()[i].getSuit() == suit && values()[i].getValue() == value) {
				return values()[i];
			}
		}
		return UNKNOWN_CARD;
	}
	
	public static CardList getValid() {
		List<Card> deck = Arrays.asList(Arrays.copyOf(values(), TOTAL_NUMBER));
		return new CardList(deck);
	}

	private Card(Suit suit, Value value) {
		this.suit = suit;
		this.value = value;
	}
	
	public int getPoints(boolean trump) {
		if (trump) {
			switch (value) {
				case JACK : return 20;	// Buur
				case NINE : return 14;	// Nel
				default:
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
	
	public Value getValue() {
		return value;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public String fullName() {
		return value.name() + " of " + suit.name();
	}
	
	@Override
	public String toString() {
		return value.toString() + suit.toString().charAt(0);
	}

	public enum Suit {
		HEARTS(0), SPADES(1), CLUBS(2), DIAMONDS(3),
		UNKNOWN(-1);
		
		final public static int NUMBERS_OF_SUIT = values().length - 1;
		final private int cardSuit;

		private Suit(int suit) {
			this.cardSuit = suit;
		}

		public int getIntValue() {
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
	
	public enum Value {
		SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
		JACK(11), QUEEN(12), KING(13), ACE(14),
		UNKNOWN(-1);
		
		final public static int NUMBERS_OF_VALUES = values().length - 1;
		final private int cardValue;
		
		private Value(int value) {
			this.cardValue = value;
		}
		
		@Override
		public String toString() {
			switch (cardValue) {
				case 10 : return "10";
				case 11 : return "J ";
				case 12 : return "Q ";
				case 13 : return "K ";
				case 14 : return "A ";
				case -1 : return "??";
				default: return Integer.toString(cardValue) + " ";
			}
		}
		
		public int getIntValue() {
			return cardValue;
		}
	}
}
