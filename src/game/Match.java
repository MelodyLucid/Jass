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
		if (hands == null || hands.size() != 4) {
			throw new IllegalArgumentException("Hands argument invalid, expected List of size 4.");
		}
		if (teamPoints == null || teamPoints.length != 2) {
			throw new IllegalArgumentException("TeamPoints argument invalid, expected Array of length 2.");
		}
		if (starter < 0 || starter > 3) {
			throw new IllegalArgumentException("Expected starter between 0 and 3.");
		}
		if (turn < 0 || turn > 3) {
			throw new IllegalArgumentException("Expected starter between 0 and 3.");
		}
		if (foldPoints < 0 || foldPoints > 56) {
			throw new IllegalArgumentException("Expected foldPoints between 0 and 56.");
		}
		this.hands = Collections.unmodifiableList(new ArrayList<CardList>(hands));
		this.fold = fold;
		this.trumpSuit = trumpSuit;
		this.foldPoints = foldPoints;
		this.teamPoints = Arrays.copyOf(teamPoints, 2);
		this.turn = turn;
		this.starter = starter;
	}
	
	public CardList getHand(int player) {
		if (player < 0 || player > 3) {
			throw new IllegalArgumentException("Expected player between 0 and 3.");
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
		return teamPoints[team];
	}
	
	public Match update(Card cardPlayed) {
		CardList updatedFold = fold.addCard(cardPlayed);
		CardList playerHand = hands.get(turn).removeCard(cardPlayed);
		List<CardList> updatedHands = new ArrayList<CardList>(hands);
		updatedHands.add(turn, playerHand);
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
}
