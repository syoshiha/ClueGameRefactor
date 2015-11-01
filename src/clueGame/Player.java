package clueGame;

import java.awt.Color;
import java.awt.Graphics;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Player implements Comparable<Player> {
	private String playerName;
	private int row;
	private int column;
	private Color color;
	
	// Protected so that the children classes can access them.
	protected Set<Card> myCards;
	protected Set<Card> seenCards;

	public Player() {
		myCards = new HashSet<Card>();
		seenCards = new HashSet<Card>();
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillOval(column*BoardCell.DIMENSIONS, row*BoardCell.DIMENSIONS, BoardCell.DIMENSIONS, BoardCell.DIMENSIONS);
		g.setColor(Color.WHITE);
		g.drawOval(column*BoardCell.DIMENSIONS, row*BoardCell.DIMENSIONS, BoardCell.DIMENSIONS, BoardCell.DIMENSIONS);
	}
	
	public Card disproveSuggestion(Solution suggestion) {
		ArrayList<Card> possibleCards = new ArrayList<Card>();
		for (Card card : myCards) {
			if (card.getCardName() == suggestion.person ||
				card.getCardName() == suggestion.room ||
				card.getCardName() == suggestion.weapon) {
					possibleCards.add(card);
			}
		}
		
		Collections.shuffle(possibleCards);
		if (possibleCards.size() == 0) return null;
		return possibleCards.get(0);
	}
	
	public String getName() {
		return playerName;
	}
	
	public Color getColor() {
		return color;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return column;
	}
	
	public void setName(String playerName) {
		this.playerName = playerName;
	}
	
	public void setColor(String colorString) {
		try {
			Field field = Class.forName("java.awt.Color").getField(colorString.trim());
			color = (Color)field.get(null);
		} catch (Exception e) {
			color = null;
		}
	}
	
	public void setRow(int row) {
		this.row = row;
	}
	
	public void setCol(int col) {
		this.column = col;
	}
	
	public Set<Card> getMyCards() {
		return myCards;
	}
	
	public Set<Card> getSeenCards() {
		return seenCards;
	}
	
	public void giveCard(Card card) {
		myCards.add(card);
	}
	
	public void showCard(Card card) {
		seenCards.add(card);
	}
	
	public int compareTo(Player otherPlayer) {
		return this.playerName.compareTo(otherPlayer.playerName);
	}
}
