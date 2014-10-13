package main;

import java.util.Arrays;

import main.Card.CardSuit;
import main.Card.CardValue;

public class Test {
	
	private static CardSuit trumpSuit = CardSuit.SPADES;
	private static int[] teamPoints = {0, 0};
	private static Player[] players = new Player[4];

	public static void main(String[] args) {
		
		Deck deck = new Deck();
		deck.shuffle();
		
		System.out.println("===== Deck =====");
		deck.printDeck();
		
		Hand[] hands = deck.dealCards();
		
		System.out.println("\n===== Hands =====");
		
		for (int i = 0; i < hands.length; i++) {
			System.out.println(hands[i]);
			System.out.println("Spades: " + Arrays.toString(hands[i].pickSuit(CardSuit.SPADES)));
			System.out.println("Aces: " + Arrays.toString(hands[i].pickValue(CardValue.ACE)));
			System.out.println();
		}
		
		for (int i = 0; i < players.length; i++) {
			players[i] = new Player();
			players[i].giveHand(hands[i]);
		}
		
		System.out.println("===== Trump suit =====");
		System.out.println("Player 1, please choose a trump suit.");
		trumpSuit = players[0].chooseTrumpSuit(true);
		if (trumpSuit == CardSuit.PASS) {
			System.out.println("Player 1 schiebered.\n\nPlayer 3, please choose a trump suit");
			trumpSuit = players[2].chooseTrumpSuit(false);
		}
		System.out.println(trumpSuit.name() + " is now the trump suit\n");
		
		System.out.println("===== Match =====");
		int winner = 0;
		for (int i = 0; i < 9; i++) {
			winner = playersGonnaPlay(winner, players);
			System.out.println("P1 cards: " + players[0].getHand() + "\n\n");
		}
		
		teamPoints[(winner == 0)  || (winner == 2) ? 0 : 1] += 5;
		
		if (teamPoints[0] > teamPoints[1]) {
			System.out.println("Player 1 and 3 win the match ! (" + teamPoints[0] + " vs " + teamPoints[1] +")");
		} else {
			System.out.println("Player 2 and 4 win the match ! (" + teamPoints[0] + " vs " + teamPoints[1] +")");
		}
	}
	
	public static int playersGonnaPlay(int start, Player[] players) {
		Card[] fold = new Card[4];
		for (int i = 0; i < players.length; i++) {
			Card next = players[(i + start) % players.length].playCard(fold); // TODO fold immutable
			fold[i] = next;
			System.out.println("Player " + ((i + start) % players.length + 1) + " plays the " + next.fullName());
		}
		
		int winner = (winner(fold) + start) % players.length;
		
		int points = points(fold);
		teamPoints[(winner == 0)  || (winner == 2) ? 0 : 1] += points;
		
		System.out.println("\nWinner: Player " + (winner + 1) + " (" + fold[winner(fold)].fullName() + ")");
		System.out.println("\tfor a fold worth " + points + " points.\n");
		
		return winner;
	}
	
	public static int winner(Card[] fold) {
		CardSuit winningSuit = fold[0].getSuit();
		int maxValue = fold[0].getValue().getCardValue(winningSuit == trumpSuit);
		int winner = 0;
		
		for (int i = 1; i < fold.length; i++) {
			if (fold[i].getSuit() == winningSuit) {
				int val = fold[i].getValue().getCardValue(winningSuit == trumpSuit);
				if (val > maxValue) {
					maxValue = val;
					winner = i;
				}
			} else {
				if (fold[i].getSuit() == trumpSuit) {
					winningSuit = trumpSuit;
					maxValue = fold[i].getValue().getCardValue(true);
					winner = i;
				}
			}
		}
		
		return winner;
	}
	
	public static int points(Card[] fold) {
		int points = 0;
		
		for (int i = 0; i < fold.length; i++) {
			points += fold[i].getValue().getPoints(fold[i].getSuit() == trumpSuit);
		}
		
		return points;
	}
}
