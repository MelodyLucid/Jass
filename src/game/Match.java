package game;

import game.Card.CardSuit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

final public class Match {

	private final List<CardList> hands;
	private final CardList fold;
	private final CardSuit trumpSuit;
	private final int foldPoints;
	private final int[] teamPoints;
	private final int turn;
	private final int starter;
	
	public Match(CardSuit trumpSuit, int turn, CardList[] hands) {
		this(Arrays.asList(hands), new CardList(), trumpSuit, 0, new int[] {0, 0}, turn, turn);
	}
	
	public Match(List<CardList> hands, CardList fold, CardSuit trumpSuit, int foldPoints, int[] teamPoints, int turn, int starter) {
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
	
	public Match update(Card cardPlayed) {
		if (isOver()) {
			return this;
		}
		if (cardPlayed == null) {
			throw new NullPointerException();
		}
		CardList playerHand = hands.get(turn);
		if (!playerHand.contains(cardPlayed)) {
			throw new IllegalArgumentException("Player " + turn + " does not have card " + cardPlayed);
		}
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
	
	public int getTeamPoints(int team) {
		if (team < 0 || team > 2) {
			return -1;
		}
		return teamPoints[team];
	}
	
	public boolean isOver() {
		for (CardList cardlist : hands) {
			if (!cardlist.isEmpty()) {
				return false;
			}
		}
		return true;
	}
	
	public CardComparator getCardComparator(CardSuit enteringSuit) {
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
