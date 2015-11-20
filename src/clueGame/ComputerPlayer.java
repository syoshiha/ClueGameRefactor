package clueGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

public class ComputerPlayer extends Player {
	
	ArrayList<Character> lastRoomChosen ;
	public ComputerPlayer() {
		super();
		lastRoomChosen = new ArrayList<Character>();
	}


	// This function simply selects a target and keeps track of the last
	// room chosen. it does not actually MOVE the player to the chosen
	// location.
	public BoardCell pickLocation(Set<BoardCell> targets) {

		// Look for room targets
		for (BoardCell cell : targets) {
			if (cell.isRoom() &&  !lastRoomChosen.contains(cell.getInitial())) {
				lastRoomChosen.add(cell.getInitial());
				return cell;
			}
		}
		
		// Room target not found -> select target randomly
		ArrayList<BoardCell> shuffledTargets = new ArrayList<BoardCell>();
		shuffledTargets.addAll(targets);
		Collections.shuffle(shuffledTargets);
		return shuffledTargets.get(0);
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
	
	public Solution makeSuggestion(Board board, BoardCell location, Map<Character, String> rooms) {
		ArrayList<Card> entireDeck = new ArrayList<Card>();
		ArrayList<Card> cardsNotSeen = new ArrayList<Card>();
		
		entireDeck.addAll(board.getDeck());
		cardsNotSeen.addAll(entireDeck);
		cardsNotSeen.removeAll(seenCards);
		
		Collections.shuffle(entireDeck);
		Collections.shuffle(cardsNotSeen);
		
		// The player must suggest the room they are currently in
		Solution suggestion = new Solution();
		suggestion.room = rooms.get(location.getInitial());
		
		// Randomly make suggestion from entire deck
		for (Card card : entireDeck) {
			if (card.getCardType() == CardType.PERSON) {
				suggestion.person = card.getCardName();
			}
			if (card.getCardType() == CardType.WEAPON) {
				suggestion.weapon = card.getCardName();
			}
		}
		
		// Overwrite previous suggestion with cards that have not been seen.
		// This ensures that if possible, the suggestion will contain cards
		// that have not been seen, but if a player has seen all of one type
		// of card, then they will suggest a card they have seen
		for (Card card : cardsNotSeen) {
			if (card.getCardType() == CardType.PERSON) {
				suggestion.person = card.getCardName();
			}
			if (card.getCardType() == CardType.WEAPON) {
				suggestion.weapon = card.getCardName();
			}
		}
		
		return suggestion;
	}
	

 
}
