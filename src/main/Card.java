package main;

public class Card {

	final private CardSuit suit;
	final private CardValue value;
	
	public Card(CardSuit suit, CardValue value) {
		this.suit = suit;
		this.value = value;
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
		PASS(-1);
		
		private int cardSuit;

		private CardSuit(int suit) {
			this.cardSuit = suit;
		}

		public int getCardValue() {
			return cardSuit;
		}
		
		@Override
		public String toString() {
			switch (cardSuit) {
				case 0: return "♥";
				case 1: return "♠";
				case 2: return "♣";
				case 3: return "♦";
				default: return "?";
			}
		}
	}
	
	public enum CardValue {
		SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
		JACK(11), QUEEN(12), KING(13), ACE(14);

		private int cardValue;

		private CardValue(int value) {
			this.cardValue = value;
		}

		public int getCardValue(boolean trump) {
			if (trump) {
				switch (cardValue) {
					case 11 : return 16;	// Buur
					case 9  : return 15;	// Nel
					default : return cardValue;
				}
			} else {
				return cardValue;
			}
		}
		
		public int getPoints(boolean trump) {
			if (trump) {
				switch (cardValue) {
					case 11 : return 20;	// Buur
					case 9  : return 14;	// Nel
				}
			}
			switch (cardValue) {
				case 14 : return 11;
				case 13 : return 4;
				case 12 : return 3;
				case 11 : return 2;
				case 10 : return 10;
				default : return 0;
			}
			
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
}
