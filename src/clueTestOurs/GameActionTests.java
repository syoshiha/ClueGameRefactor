package clueTestOurs;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.HumanPlayer;
import clueGame.Player;
import clueGame.Solution;

public class GameActionTests {
	
	Board board;
	Card gunCard;
	Card swordCard;
	Card smithCard;
	Card brownCard;
	Card kitchenCard;
	Card poolCard;
	Card bowCard;
	Card maceCard;
	
	@Before
	public void setup() {
		board = new Board();
		
		// initialize() loads the board, rooms, people and weapons.
		// As these things are loaded, the deck of cards is filled
		// with the data from the config files.
		// This function also calls dealCards() to deal the cards.
		board.initialize();
		
		// Cards to use for disproveSuggestion tests:
		gunCard = new Card("Gun", CardType.WEAPON);
		swordCard = new Card("Sword", CardType.WEAPON);
		smithCard = new Card("John Smith", CardType.PERSON);
		brownCard = new Card("Joe Brown", CardType.PERSON);
		kitchenCard = new Card("Kitchen", CardType.ROOM);
		poolCard = new Card("Pool", CardType.ROOM);
		bowCard = new Card("Crossbow", CardType.WEAPON);
		maceCard = new Card("Mace", CardType.WEAPON);
	}
	
	@Test
	public void testAccusationMaking() {
		
		// Because the solution is generated randomly, we will
		// check 10 different random solutions
		int numCorrectGuesses = 0;
		for (int i=0; i<10; i++) {
			board.initialize();
			numCorrectGuesses = 0;
			
			// Exhaustively guess every possible combination of
			// cards. Make sure that only ONE of the guesses is correct.
			// For the guess to be correct, it needs to contain a person,
			// weapon, and room, and it also needs to match the solution
			// in the Board class.
			Set<Card> allCards = new HashSet<Card>();
			allCards.addAll(board.getDeck());
			assertTrue(board.getAnswerCards().size() == 3);
			
			for (Card cardA : allCards) {
				for (Card cardB : allCards) {
					for (Card cardC : allCards) {
						Solution tempSolution = new Solution();
						tempSolution.person = cardA.getCardName();
						tempSolution.weapon = cardB.getCardName();
						tempSolution.room = cardC.getCardName();
						if (board.checkAccusation(tempSolution)) {
							numCorrectGuesses++;
						}
					}
				}
			}

			// Make sure that only the correct solution was considered
			// correct, and all other solutions were considered wrong.
			assertEquals(numCorrectGuesses, 1);
			
			// Show the computer players all the cards besides the
			// solution cards to make sure they can make the
			// correct accusation
			Set<ComputerPlayer> compPlayers = board.getCompPlayers();
			allCards.removeAll(board.getAnswerCards());
			for (ComputerPlayer player : compPlayers) {
				for (Card card : allCards) {
					player.showCard(card);
				}
				assertTrue(board.checkAccusation(player.makeAccusation()));
			}
		}
	}
	
	@Test
	// This test ensures that:
	// - If a room is a target it is chosen.
	// - If a room is a target that we visited recently, a target is chosen randomly.
	// - That random selection is in fact random.
	public void testTargetSelection() {
		// Ensure that a room is chosen if it is in the list of targets and 
		// was not the last visited room. The following test places the player
		// close to a room, and makes sure that the player enters the room. 
		board.calcTargets(17, 6, 3);
		ComputerPlayer testPlayer = new ComputerPlayer();
		for (int i=0; i<20; i++) { // Make sure the room was not entered by luck.
			testPlayer = new ComputerPlayer();
			testPlayer.setRow(17);
			testPlayer.setCol(6);
			assertTrue(testPlayer.pickLocation(board.getTargets()).getInitial() == 'S');
		}
		
		// Now that this room been visited, repeat the same test as above,
		// but make sure that ALL targets are chosen randomly.
		int[] timesHitEachTarget = new int[5];
		for (int i=0; i<5; i++) {
			timesHitEachTarget[i] = 0;
		}
		
		for (int i=0; i<500; i++) {
			testPlayer.setRow(17);
			testPlayer.setCol(6);
			BoardCell chosenTarget = testPlayer.pickLocation(board.getTargets());
			
			// Test 5 of the possible targets to make sure they are all visited.
			if (chosenTarget.getRow() == 14 && chosenTarget.getCol() == 6) {
				timesHitEachTarget[0]++;
			}
			if (chosenTarget.getRow() == 18 && chosenTarget.getCol() == 8) {
				timesHitEachTarget[1]++;
			}
			if (chosenTarget.getRow() == 17 && chosenTarget.getCol() == 4) {
				timesHitEachTarget[2]++;
			}
			if (chosenTarget.getRow() == 17 && chosenTarget.getCol() == 7) {
				timesHitEachTarget[3]++;
			}
			if (chosenTarget.getRow() == 19 && chosenTarget.getCol() == 5) {
				timesHitEachTarget[4]++;
			}
		}
		
		// Make sure each target was hit at least 5 times.
		for (int i=0; i<5; i++) {
			assertTrue(timesHitEachTarget[i] >= 5);
		}
	}
	
	@Test
	public void testDisprovingASuggestion() {
		Player testPlayer = new Player();
		testPlayer.giveCard(gunCard);
		testPlayer.giveCard(swordCard);
		testPlayer.giveCard(smithCard);
		testPlayer.giveCard(brownCard);
		testPlayer.giveCard(kitchenCard);
		testPlayer.giveCard(poolCard);
		
		// Test disproving a suggestion with a person
		assertEquals(testPlayer.disproveSuggestion(new Solution("John Smith", "Restroom", "Mace")).getCardType(), CardType.PERSON);
		assertEquals(testPlayer.disproveSuggestion(new Solution("John Smith", "Restroom", "Mace")).getCardName(), "John Smith");
		
		// Test disproving a suggestion with a room
		assertEquals(testPlayer.disproveSuggestion(new Solution("Jake Williams", "Pool", "Hammer")).getCardType(), CardType.ROOM);
		assertEquals(testPlayer.disproveSuggestion(new Solution("Jake Williams", "Pool", "Hammer")).getCardName(), "Pool");
		
		// Test disproving a suggestion with a weapon
		assertEquals(testPlayer.disproveSuggestion(new Solution("Connor Davis", "Study", "Gun")).getCardType(), CardType.WEAPON);
		assertEquals(testPlayer.disproveSuggestion(new Solution("Connor Davis", "Study", "Gun")).getCardName(), "Gun");
		
		// Test inability to disprove a suggestion
		assertEquals(testPlayer.disproveSuggestion(new Solution("Connor Davis", "Study", "Axe")), null);
		assertEquals(testPlayer.disproveSuggestion(new Solution("Connor Davis", "Study", "Axe")), null);
		
		// Test disproving a solution when two possible cards could be used. Make sure
		// that each card is used at least five times after 500 calls to disproveSuggestion().
		int numTimesDisprovedWithSmith = 0;
		int numTimesDisprovedWithKitchen = 0;
		for (int i=0; i<500; i++) {
			Card cardUsed = testPlayer.disproveSuggestion(new Solution("John Smith", "Kitchen", "Crossbow"));
			if (cardUsed.getCardName() == "John Smith") {
				numTimesDisprovedWithSmith++;
			} else if (cardUsed.getCardName() == "Kitchen") {
				numTimesDisprovedWithKitchen++;
			} else {
				// Only the cards above can disprove the solution
				assertTrue(false);
			}
		}
		assertTrue(numTimesDisprovedWithSmith >= 5);
		assertTrue(numTimesDisprovedWithKitchen >= 5);
		
		
		// Test that the handleSuggestion function works correctly, and that all
		// players are queried. A human player and set of comp players are created
		// to test the function with.
		HumanPlayer hp = new HumanPlayer();
		hp.giveCard(gunCard);
		hp.giveCard(swordCard);
		hp.setName("Human Player");
		
		ArrayList<ComputerPlayer> cps = new ArrayList<ComputerPlayer>();
		ComputerPlayer tempCP = new ComputerPlayer();
		tempCP.giveCard(smithCard);
		tempCP.giveCard(brownCard);
		tempCP.setName("Comp 1");
		cps.add(tempCP);
		
		tempCP = new ComputerPlayer();
		tempCP.giveCard(poolCard);
		tempCP.giveCard(kitchenCard);
		tempCP.setName("Comp 2");
		cps.add(tempCP);
		
		tempCP = new ComputerPlayer();
		tempCP.giveCard(bowCard);
		tempCP.giveCard(maceCard);
		tempCP.setName("Comp 3");
		cps.add(tempCP);
		
		board.setHumanPlayer(hp);
		board.setCompPlayer(cps);
		
		// Test a suggestion that no players can disprove
		assertEquals(board.handleSuggestion(new Solution("Grant Jones", "Balcony", "Axe"), hp.getName()), null);
		
		// Test a suggestion that only the human can disprove
		assertEquals(board.handleSuggestion(new Solution("Grant Jones", "Balcony", "Gun"), cps.get(0).getName()).getCardName(), "Gun");
		
		// Test a suggestion that only the accuser can disprove (human)
		assertEquals(board.handleSuggestion(new Solution("Grant Jones", "Balcony", "Sword"), hp.getName()), null);
		
		// Test a suggestion that only the accuser can disprove (comp 3)
		assertEquals(board.handleSuggestion(new Solution("Grant Jones", "Balcony", "Mace"), cps.get(2).getName()), null);
		
		
		// Make sure the players are queried in the proper order. The proper order/cycle is:
		// 1. The human player
		// 2. The computer players in order of their name
		// The computer players go in order of their name because they are stored in a TreeSet.
		hp = new HumanPlayer();
		hp.giveCard(gunCard);
		hp.setName("CCD");
		
		cps = new ArrayList<ComputerPlayer>();
		tempCP = new ComputerPlayer();
		tempCP.giveCard(smithCard);
		tempCP.setName("CDD");
		cps.add(tempCP);
		
		tempCP = new ComputerPlayer();
		tempCP.giveCard(poolCard);
		tempCP.setName("CCC");
		cps.add(tempCP);
		
		tempCP = new ComputerPlayer();
		tempCP.giveCard(bowCard);
		tempCP.setName("ABB");
		cps.add(tempCP);
		
		board.setHumanPlayer(hp);
		board.setCompPlayer(cps);
		
		// Test suggestion that multiple players can disprove. Ensure first alphabetical
		// player is the one to disprove
		assertEquals(board.handleSuggestion(new Solution("John Smith", "Pool", "Crossbow"), hp.getName()).getCardName(), "Crossbow");
		
		// Ensure the entire "cycle" of players are queried by making the
		// last computer player the accuser, and the second to last computer player
		// the disprover.
		assertEquals(board.handleSuggestion(new Solution("Connor Davis", "Pool", "Sword"), cps.get(0).getName()).getCardName(), "Pool");
		
	}
	
	@Test
	// Test that the computer player makes an appropriate suggestion. An appropriate suggestion:
	// - Has the room the player is currently in
	// - Does not have a weapon or person that the player has already seen (unless the
	//   the player has seen ALL of the weapons or ALL of the people
	// - 
	public void testSuggestionMaking() {
		
		// Put the player on the balcony, and show them a set of cards
		ComputerPlayer testPlayer = new ComputerPlayer();
		testPlayer.showCard(new Card("Kitchen", CardType.ROOM));
		testPlayer.showCard(new Card("Pool", CardType.ROOM));
		testPlayer.showCard(new Card("Balcony", CardType.ROOM));
		testPlayer.showCard(new Card("John Smith", CardType.PERSON));
		testPlayer.showCard(new Card("Joe Brown", CardType.PERSON));
		testPlayer.showCard(new Card("Bill Adams", CardType.PERSON));
		testPlayer.showCard(new Card("Gun", CardType.WEAPON));
		testPlayer.showCard(new Card("Sword", CardType.WEAPON));
		testPlayer.showCard(new Card("Crossbow", CardType.WEAPON));
		testPlayer.setRow(11); // Balcony
		testPlayer.setCol(23);
		
		// Because a suggestion is made randomly, we must check it many times
		for (int i=0; i<500; i++) {
			Solution suggestion = testPlayer.makeSuggestion(board, board.getCellAt(testPlayer.getRow(), testPlayer.getCol()));
			
			// Suggestion can only be made from the current room
			assertTrue(suggestion.room.equals("Balcony"));
			
			// Suggestion should only contain cards that have not been seen
			if (suggestion.room.equals("Kitchen") ||
				suggestion.room.equals("Pool") ||
				suggestion.person.equals("John Smith") ||
				suggestion.person.equals("Joe Brown") ||
				suggestion.person.equals("Bill Adams") ||
				suggestion.weapon.equals("Gun") ||
				suggestion.weapon.equals("Sword") ||
				suggestion.weapon.equals("Crossbow")) {
					assertTrue(false);
			}
		}
		
		// Show the computer all the cards but three, so that only one suggestion is possible
		// the player will not see: 'Grant Jones', 'Hammer'
		// We need not show them all the rooms, because only one room is possible anyways.
		testPlayer.showCard(new Card("Jake Williams", CardType.PERSON));
		testPlayer.showCard(new Card("Connor Davis", CardType.PERSON));
		testPlayer.showCard(new Card("Mace", CardType.WEAPON));
		testPlayer.showCard(new Card("Axe", CardType.WEAPON));
		
		// Because makeSuggestion is random, test multiple times
		for (int i=0; i<100; i++) {
			
			Solution suggestion = testPlayer.makeSuggestion(board, board.getCellAt(testPlayer.getRow(), testPlayer.getCol()));
			
			// Suggestion can only be made from current room
			assertTrue(suggestion.room.equals("Balcony"));
			assertTrue(suggestion.person.equals("Grant Jones"));
			assertTrue(suggestion.weapon.equals("Hammer"));
		}
	}
}
