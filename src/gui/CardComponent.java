package gui;

import old.Card;

final public class CardComponent implements Comparable<CardComponent> {

	final private Card card;
	final private int x;
	final private int y;
	
	public CardComponent(int x, int y, Card card) {
		this.x = x;
		this.y = y;
		this.card = card;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Card getCard() {
		return card;
	}

	@Override
	public int compareTo(CardComponent cc) {
		if (cc == null) {
			return 0;
		}
		if (x == cc.x && y == cc.y) return 0;
		if (x > cc.x) return 1;
		if (y > cc.y) return 1; 
		return -1;
	}
}
