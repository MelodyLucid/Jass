package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import main.Card;
import main.Card.CardSuit;
import main.Card.CardValue;

public class Main {

	public static void main(String[] args) {
		JFrame f = new Frame();
		f.setVisible(true);
	}
}

class Frame extends JFrame {
	private static final long serialVersionUID = 1L;

	public Frame() {
		setTitle("Jass");
		setSize(378, 448+33);
		setResizable(false);
		setLocation(10, 200);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		final Panel pan = new Panel();
		getContentPane().add(pan);
	}
}

class Panel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static Set<CardComponent> cardComp;

	public Panel() {
		fillDefaultComponent();
	}
	
	private void fillDefaultComponent() {
		cardComp = new TreeSet<CardComponent>();
		for (int i = 0; i < CardSuit.values().length - 1; i++) {
			CardSuit suit = CardSuit.values()[i];
			
			for (int j = 0; j < CardValue.values().length; j++) {
				CardValue val = CardValue.values()[j];
				
				switch (i) {
					case 0:
						cardComp.add(new CardComponent(91 + 15*j, 10, new Card(suit, val)));
						break;
					case 1:
						cardComp.add(new CardComponent(292, 116 + 15*j, new Card(suit, val)));
						break;
					case 2:
						cardComp.add(new CardComponent(91 + 15*j, 342, new Card(suit, val)));
						break;
					case 3:
						cardComp.add(new CardComponent(10, 116 + 15*j, new Card(suit, val)));
					default:
				}
				
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(new Color(0, 127, 0));
		g.fillRect(0,0,getWidth(),getHeight());
		paintAllCards(g);
		paintCentralCards(g);
	}

	public void paintCard(Graphics g, int x, int y, Card c) {
		char suit = c.getSuit().name().charAt(0);
		String val  = c.getValue().toString();
		
		try {
			g.drawImage(ImageIO.read(new File("img/" + suit + val + ".png")), x, y, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void paintCentralCards(Graphics g) {
		paintCard(g, 81+78, 101+35, new Card(CardSuit.HEARTS, CardValue.ACE));
		paintCard(g, 81+43, 101+67, new Card(CardSuit.DIAMONDS, CardValue.ACE));
		paintCard(g, 81+60, 101+115, new Card(CardSuit.CLUBS, CardValue.ACE));
		paintCard(g, 81+96, 101+83, new Card(CardSuit.SPADES, CardValue.ACE));
	}
	
	private void paintHorizontalCards(Graphics g) {
		for (int i = 0; i < CardSuit.values().length - 1; i++) {
			CardSuit suit = CardSuit.values()[i];
			
			for (int j = 0; j < CardValue.values().length; j++) {
				CardValue val = CardValue.values()[j];
				
				paintCard(g, 10 + 15*j, 10 + 100*i, new Card(suit, val));
			}
		}
	}
	
	private void paintVerticalCards(Graphics g) {
		for (int i = 0; i < CardSuit.values().length - 1; i++) {
			CardSuit suit = CardSuit.values()[i];
			
			for (int j = 0; j < CardValue.values().length; j++) {
				CardValue val = CardValue.values()[j];
				
				paintCard(g, 10 + 100*i, 10 + 15*j, new Card(suit, val));
			}
		}
	}
	
	private void paintAllCards(Graphics g) {
		for (CardComponent card : cardComp) {
			paintCard(g, card.getX(), card.getY(), card.getCard());
		}
	}
}