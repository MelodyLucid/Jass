package main;


import java.util.Random;

import main.Card.CardSuit;
import main.Card.CardValue;

public class Deck {

	public static final int NUMBER_OF_PLAYERS = 4;
	private Card[] deck;
	
	
	
	public Deck() {
		deck = new Card[36];
		
		for (int i = 0; i < CardSuit.values().length - 1; i++) {
			CardSuit suit = CardSuit.values()[i];
			
			for (int j = 0; j < CardValue.values().length; j++) {
				CardValue val = CardValue.values()[j];
				
				int index = j + i*CardValue.values().length;
				deck[index] = new Card(suit, val);
			}
		}
	}
	
	public void printDeck() {
		for (int i = 0; i < deck.length; i++) {
			System.out.println(deck[i].fullName());
		}
	}
	
	 // Fisher–Yates shuffle
	public void shuffle() {
		Random rnd = new Random(0);	// TODO random seed
		Card tmp;
		for (int i = deck.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			
			tmp = deck[index];
			deck[index] = deck[i];
			deck[i] = tmp;
		}
	}
	
	public Hand[] dealCards() {
		Hand[] hands = new Hand[NUMBER_OF_PLAYERS];
		
		for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
			Card[] hand = new Card[Hand.CARDS_PER_HAND];
			
			for (int j = 0; j < Hand.CARDS_PER_HAND; j++) {
				int index = j + i*Hand.CARDS_PER_HAND;
				hand[j] = deck[index];
			}
			
			hands[i] = new Hand(hand);
		}
		
		return hands;
	}
}
