package game;

import java.util.Arrays;

import game.Card.Suit;

public class Game {

	final private int[] totalTeamPoints;
	final private Match currentMatch;
	final private int turn;
	// TODO Stats, History
	
	public Game(int[] points, Match match, int turn) {
		this.totalTeamPoints = points;
		this.currentMatch = match;
		this.turn = turn;
	}
	
	public static Game newGame() {
		Match m = Match.createFirstMatch();
		return new Game(new int[]{0, 0}, m, m.getTurn());
	}
	
	public Game setUp(Suit trumpSuit) {
		Match nextMatch = currentMatch.setUp(trumpSuit);
		return new Game(totalTeamPoints, nextMatch, turn);
	}
	
	public Game update(Card cardPlayed) {
		Match nextMatch = currentMatch.update(cardPlayed);
		int nextTurn = turn;
		if (nextMatch.isOver()) {
			for (int i = 0; i < totalTeamPoints.length; i++) {
				totalTeamPoints[i] += nextMatch.getTeamPoints(i);
			}
			nextTurn = (nextTurn + 1) % 4;
			nextMatch = new Match(Suit.UNKNOWN, nextTurn, CardList.getBasic().shuffled().deal());
		}
		return new Game(totalTeamPoints, nextMatch, nextTurn);
	}
	
	public Match getMatch() {
		return currentMatch;
	}
	
	@Override
	public String toString() {
		String gameStr = "Game\n";
		
		String totalPointsStr = "\nTotal teampoints: " + Arrays.toString(totalTeamPoints);
		String currentTurnStr = "\nCurrent turn: " + turn;
		String currentMatchStr = "\n\n" +  currentMatch.toString();
		return gameStr + totalPointsStr + currentTurnStr + currentMatchStr;
	}
}
