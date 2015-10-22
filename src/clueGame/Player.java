package clueGame;

import com.sun.prism.paint.Color;

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
	
	
}
