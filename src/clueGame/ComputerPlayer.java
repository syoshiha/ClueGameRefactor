package clueGame;

import java.util.Set;

public class ComputerPlayer extends Player {
	
	public void pickLocation(Set <BoardCell> targets) {
		
	}
	
	public Solution makeAccusation() {
		
		Set<Card> cardsNotSeen = Board.getDeck();
		cardsNotSeen.removeAll(seenCards);
		
		// The computer doesn't make a guess if it doesn't have enough information
		if (cardsNotSeen.size() != 3) {
			return null;
		}
		
		Solution accusation = new Solution();
		for (Card card : cardsNotSeen) {
			if (card.getCardType() == CardType.PERSON) {
				accusation.person = card.getCardName();
			} else if (card.getCardType() == CardType.ROOM) {
				accusation.room = card.getCardName();
			} else {
				accusation.weapon = card.getCardName();
			}
		}
		
		return accusation;
	}
	
	public void makeSuggestion(Board board, BoardCell location) {
		
	}
	
	public ComputerPlayer() {
		super();
	}
 
}
