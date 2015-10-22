package clueGame;

import java.awt.Color;
import java.lang.reflect.Field;

public class Player {
	private String playerName;
	private int row;
	private int column;
	private Color color;
	
	private Card myCards;
	private Card seenCards;

	public Card disproveSuggestion(Solution suggestion) {
		return new Card();
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
}
