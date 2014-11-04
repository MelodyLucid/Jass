package game;

public enum Card {

	SIX_OF_HEARTS(CardSuit.HEARTS, CardValue.SIX),
	SEVEN_OF_HEARTS(CardSuit.HEARTS, CardValue.SEVEN),
	EIGHT_OF_HEARTS(CardSuit.HEARTS, CardValue.EIGHT),
	NINE_OF_HEARTS(CardSuit.HEARTS, CardValue.NINE),
	TEN_OF_HEARTS(CardSuit.HEARTS, CardValue.TEN),
	JACK_OF_HEARTS(CardSuit.HEARTS, CardValue.JACK),
	QUEEN_OF_HEARTS(CardSuit.HEARTS, CardValue.QUEEN),
	KING_OF_HEARTS(CardSuit.HEARTS, CardValue.KING),
	ACE_OF_HEARTS(CardSuit.HEARTS, CardValue.ACE),
	
	SIX_OF_SPADES(CardSuit.SPADES, CardValue.SIX),
	SEVEN_OF_SPADES(CardSuit.SPADES, CardValue.SEVEN),
	EIGHT_OF_SPADES(CardSuit.SPADES, CardValue.EIGHT),
	NINE_OF_SPADES(CardSuit.SPADES, CardValue.NINE),
	TEN_OF_SPADES(CardSuit.SPADES, CardValue.TEN),
	JACK_OF_SPADES(CardSuit.SPADES, CardValue.JACK),
	QUEEN_OF_SPADES(CardSuit.SPADES, CardValue.QUEEN),
	KING_OF_SPADES(CardSuit.SPADES, CardValue.KING),
	ACE_OF_SPADES(CardSuit.SPADES, CardValue.ACE),
	
	SIX_OF_CLUBS(CardSuit.CLUBS, CardValue.SIX),
	SEVEN_OF_CLUBS(CardSuit.CLUBS, CardValue.SEVEN),
	EIGHT_OF_CLUBS(CardSuit.CLUBS, CardValue.EIGHT),
	NINE_OF_CLUBS(CardSuit.CLUBS, CardValue.NINE),
	TEN_OF_CLUBS(CardSuit.CLUBS, CardValue.TEN),
	JACK_OF_CLUBS(CardSuit.CLUBS, CardValue.JACK),
	QUEEN_OF_CLUBS(CardSuit.CLUBS, CardValue.QUEEN),
	KING_OF_CLUBS(CardSuit.CLUBS, CardValue.KING),
	ACE_OF_CLUBS(CardSuit.CLUBS, CardValue.ACE),
	
	SIX_OF_DIAMONDS(CardSuit.DIAMONDS, CardValue.SIX),
	SEVEN_OF_DIAMONDS(CardSuit.DIAMONDS, CardValue.SEVEN),
	EIGHT_OF_DIAMONDS(CardSuit.DIAMONDS, CardValue.EIGHT),
	NINE_OF_DIAMONDS(CardSuit.DIAMONDS, CardValue.NINE),
	TEN_OF_DIAMONDS(CardSuit.DIAMONDS, CardValue.TEN),
	JACK_OF_DIAMONDS(CardSuit.DIAMONDS, CardValue.JACK),
	QUEEN_OF_DIAMONDS(CardSuit.DIAMONDS, CardValue.QUEEN),
	KING_OF_DIAMONDS(CardSuit.DIAMONDS, CardValue.KING),
	ACE_OF_DIAMONDS(CardSuit.DIAMONDS, CardValue.ACE),
	
	UNKNOWN_CARD(CardSuit.UNKNOWN, CardValue.UNKNOWN);
	
	final public static int TOTAL_NUMBER = values().length - 1;
	final private CardSuit suit;
	final private CardValue value;
	
	private Card(CardSuit suit, CardValue value) {
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
	
	public CardValue getValue() {
		return value;
	}
	
	public CardSuit getSuit() {
		return suit;
	}
	
	public String fullName() {
		return value.name() + " of " + suit.name();
	}
	
	public static Card getCard(CardSuit suit, CardValue value) {
		for (int i = 0; i < values().length; i++) {
			if (values()[i].getSuit() == suit && values()[i].getValue() == value) {
				return values()[i];
			}
		}
		return UNKNOWN_CARD;
	}
	
	
	public enum CardSuit {
		HEARTS(0), SPADES(1), CLUBS(2), DIAMONDS(3),
		UNKNOWN(-1);
		
		final public static int NUMBERS_OF_SUIT = values().length - 1;
		final private int cardSuit;

		private CardSuit(int suit) {
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
	
	public enum CardValue {
		SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
		JACK(11), QUEEN(12), KING(13), ACE(14),
		UNKNOWN(-1);
		
		final public static int NUMBERS_OF_VALUES = values().length - 1;
		final private int cardValue;
		
		private CardValue(int value) {
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
	
	@Override
	public String toString() {
		return value.toString() + suit.toString().charAt(0);
	}
}
