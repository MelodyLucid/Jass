package main;

import main.Card.CardSuit;

public class Player {

	private Hand hand;	// TODO hand immutable, server => proxy
	
	public Player() {
		
	}
	
	public void giveHand(Hand hand) {
		this.hand = hand;
	}
	
	public void setUp(int turn) {

	}
	
	// TODO
	// schieber = true means you may pass
	public CardSuit chooseTrumpSuit(boolean schieber) {
//		System.out.println("Your hand is: " + hand + "\nYou may choose a trump suit");
//		Scanner sc = new Scanner(System.in);
//		CardSuit cs = CardSuit.valueOf(sc.nextLine());
//		
//		sc.close();
		return CardSuit.DIAMONDS;
	}
	
	public Card playCard(Card[] fold) {
		if (fold[0] != null) {
			Card c = playNextInSuit(fold[0].getSuit());
			if (c != null) {
				return c;
			}
		}
		for (int i = 0; i < CardSuit.values().length; i++) {
			Card c = playNextInSuit(CardSuit.values()[i]);
			if (c != null) {
				return c;
			}
		}
		return null;
	}
	
	public Card playNextInSuit(CardSuit suit) {
		Card[] suitedCards = hand.pickSuit(suit);
		if (suitedCards.length > 0) {
			Card c = suitedCards[0];
			if (hand.pickCard(c)) {
				return c;
			}
		}
		return null;
	}
	
	public Hand getHand() {
		return hand;
	}
}
