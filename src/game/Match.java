package game;

import game.Card.Suit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final public class Match {

	// TODO real names
	private final List<CardList> hands;
	private final CardList fold;
	private final Suit trumpSuit;
	private final int foldPoints;
	private final int[] teamPoints;
	private final int turn;
	private final int starter;
	// TODO hasSchibered, history
	
	public Match(Suit trumpSuit, int turn, List<CardList> hands) {
		this(hands, new CardList(), trumpSuit, 0, new int[] {0, 0}, turn, turn);
	}
	
	public Match(List<CardList> hands, CardList fold, Suit trumpSuit, int foldPoints, int[] teamPoints, int turn, int starter) {
		if (hands == null || fold == null || teamPoints == null) {
			throw new NullPointerException();
		}
		if (hands.size() != 4) {
			throw new IllegalArgumentException("Invalid hands size: " + hands.size() + ", expected: 4.");
		}
		if (teamPoints.length != 2) {
			throw new IllegalArgumentException("Invalid teamPoints length: " + teamPoints.length + ", expected: 2.");
		}
		if (starter < 0 || starter > 3) {
			throw new IllegalArgumentException("Invalid starter: " + starter + ", range expected: [0, 3]" );
		}
		if (turn < 0 || turn > 3) {
			throw new IllegalArgumentException("Invalid turn: " + turn + ", range expected: [0, 3]");
		}
		if (foldPoints < 0 || foldPoints > 56) {
			throw new IllegalArgumentException("Invalid foldPoints: " + foldPoints + ", range expected: [0, 56]");
		}
		
		this.hands = Collections.unmodifiableList(new ArrayList<CardList>(hands));
		this.fold = fold;
		this.trumpSuit = trumpSuit;
		this.foldPoints = foldPoints;
		this.teamPoints = Arrays.copyOf(teamPoints, 2);
		this.turn = turn;
		this.starter = starter;
	}
	
	public static Match createFirstMatch() {
		List<CardList> hands = CardList.getBasic().deal();
		int starter = 0;
		for (; starter < hands.size(); starter++) {
			if (hands.get(starter).contains(Card.SEVEN_OF_DIAMONDS)) {
				break;
			}
		}
		
		return new Match(Suit.UNKNOWN, starter, hands);
	}
	
	public Match setUp(Suit trumpSuit) {
		if (this.trumpSuit != Suit.UNKNOWN) {
			throw new IllegalStateException("Match already has trump suit: " + this.trumpSuit);
		}
		return new Match(trumpSuit, starter, hands);
	}
	
	public Match update(Card cardPlayed) {
		if (isOver()) {
			return this;
		}
		if (trumpSuit == Suit.UNKNOWN) {
			throw new IllegalStateException("Trump suit has not been chosen.");
		}
		if (cardPlayed == null) {
			throw new NullPointerException();
		}
		if (!isLegal(cardPlayed)) {
			throw new IllegalArgumentException("Player " + turn + " cannot play card " + cardPlayed);
		}
		CardList playerHand = hands.get(turn);
		playerHand = playerHand.removed(cardPlayed);
		
		List<CardList> updatedHands = new ArrayList<CardList>(hands);
		updatedHands.remove(turn);
		updatedHands.add(turn, playerHand);
		
		CardList updatedFold = fold.added(cardPlayed);
		int updatedFoldPoints = foldPoints + cardPlayed.getPoints(trumpSuit == cardPlayed.getSuit());
		
		if (updatedFold.size() == 4) {
			CardComparator cc = getCardComparator(updatedFold.get(0).getSuit());
			
			Card best = updatedFold.get(0);
			int winner = 0;
			for (int i = 0; i < updatedFold.size(); i++) {
				Card card = updatedFold.get(i);
				if (cc.compare(best, card) < 0) {
					best = card;
					winner = i;
				}
			}
			winner = (winner + starter) % 4;
			
			if (playerHand.isEmpty()) {
				updatedFoldPoints += 5;
			}
			
			int[] updatedTeamPoints = Arrays.copyOf(teamPoints, 2);
			updatedTeamPoints[(winner == 0)  || (winner == 2) ? 0 : 1] += updatedFoldPoints;
			
			updatedFold = new CardList();
			updatedFoldPoints = 0;
			int updatedTurn = winner;
			int updatedStarter = winner;
			
			return new Match(updatedHands, updatedFold, trumpSuit, updatedFoldPoints, updatedTeamPoints, updatedTurn, updatedStarter);
		}
		
		return new Match(updatedHands, updatedFold, trumpSuit, updatedFoldPoints, teamPoints, (turn+1) % 4, starter);
	}
	
	// Rules :
	//  1. You can only play a card that you possess
	//  2. You must follow the leading suit
	//   2a. But, you can break with a trump at anytime
	//    2a-i.   But, you can't break under a higher-trump
	//    2a-ii.  Unless it's the leading suit
	//    2a-iii. Unless you don't have any other card
	//  3. If you can't, you can discard any other non-trump card
	
	public boolean isLegal(Card cardPlayed) {
		CardList playerHand = hands.get(turn);
		if (!playerHand.contains(cardPlayed)) {
			return false;
		}
		
		if (!fold.isEmpty()) {
			Suit leadingSuit = fold.get(0).getSuit();
			if (cardPlayed.getSuit() == leadingSuit) {
				return true;
			}
			if (cardPlayed.getSuit() == trumpSuit) {
				Card bestTrump = fold.max(trumpSuit);
				if (bestTrump != null && bestTrump.compareTo(cardPlayed) < 1) {
					// the player must not contain any other suit
					// he does not contain any greater card
					if (playerHand.withSuit(trumpSuit).equals(playerHand) && bestTrump.compareTo(playerHand.max(trumpSuit)) < 1) {
						return false;
					}
				}
			}
			if (playerHand.contains(leadingSuit)) {
				return false;
			}
		}
		
		return true;
	}
	
	public Match getInstanceFor(int player) {
		List<CardList> visibleHands = new ArrayList<CardList>(4);
		for (int i = 0; i < hands.size(); i++) {
			if (i == player) {
				visibleHands.add(hands.get(i));
			} else {
				visibleHands.add(hands.get(i).hidden());
			}
		}
		return new Match(visibleHands, fold, trumpSuit, foldPoints, teamPoints, turn, starter);
	}
	
	public CardList getHand(int player) {
		if (player < 0 || player > 3) {
			return null;
		}
		return hands.get(player);
	}
	
	public CardList getFold() {
		return fold;
	}
	
	public int getFoldPoints() {
		return foldPoints;
	}
	
	public Suit getTrumpSuit() {
		return trumpSuit;
	}
	
	public int getTeamPoints(int team) {
		if (team < 0 || team > 2) {
			return -1;
		}
		return teamPoints[team];
	}
	
	public int getTurn() {
		return turn;
	}
	
	public boolean isOver() {
		for (CardList cardlist : hands) {
			if (!cardlist.isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	public CardComparator getCardComparator(Suit enteringSuit) {
		return new CardComparator(enteringSuit, trumpSuit);
	}
	
	@Override
	public String toString() {
		String handsStr = "\n=== Hands ===\n";
		for (int i = 0; i < hands.size(); i++) {
			CardList h = hands.get(i);
			handsStr += "Player " + i + ": " + h.toString() + "\n";
		}
		String foldStr = "\nFold: " + fold.toString();
		String teamPointsStr = "\nTeam points: " + Arrays.toString(teamPoints);
		
		String matchStr = "Match " + (isOver() ? "over" : "current") + "\n"
				+ teamPointsStr
				+ handsStr
				+ foldStr
				+ "\nTrumpSuit: " + trumpSuit.name()
				+ "\nTurn: Player " + turn
				+ "\nStarter: Player " + starter;
		
		return matchStr;
	}
}
