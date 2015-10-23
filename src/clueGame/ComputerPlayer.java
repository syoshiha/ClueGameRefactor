package clueGame;

import java.util.Set;

public class ComputerPlayer extends Player {
	
	public void pickLocation(Set <BoardCell> targets) {
		
	}
	
	// Returns either the accusation, or null if an accusation couldn't be made.
	public Solution makeAccusation() {
		
		Set<Card> cardsNotSeen = Board.getDeck();
		cardsNotSeen.removeAll(seenCards);
		
		// The computer doesn't make an accusation if it doesn't have enough information.
		// The computer will make an accusation once there is only one of each card type
		// left that it has not seen.
		int peopleLeft = 0;
		int roomsLeft = 0;
		int weaponsLeft = 0;
		
		Solution accusation = new Solution();
		for (Card card : cardsNotSeen) {
			if (card.getCardType() == CardType.PERSON) {
				accusation.person = card.getCardName();
				peopleLeft++;
			} else if (card.getCardType() == CardType.ROOM) {
				accusation.room = card.getCardName();
				roomsLeft++;
			} else {
				accusation.weapon = card.getCardName();
				weaponsLeft++;
			}
		}
		
		// Computer couldn't make accusation
		if (peopleLeft > 1 || roomsLeft > 1 || weaponsLeft > 1) {
			return null;
		}
		
		return accusation;
	}
	
	public void makeSuggestion(Board board, BoardCell location) {
		
	}
	
	public ComputerPlayer() {
		super();
	}
 
}
