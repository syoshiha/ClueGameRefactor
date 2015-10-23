package clueTestOurs;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;
import clueGame.Card;
import clueGame.ComputerPlayer;
import clueGame.Solution;

public class GameActionTests {
	
Board board;
	
	@Before
	public void setup() {
		board = new Board();
		
		// initialize() loads the board, rooms, people and weapons.
		// As these things are loaded, the deck of cards is filled
		// with the data from the config files.
		// This function also calls dealCards() to deal the cards.
		board.initialize();
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
		for (int i=0; i<20; i++) { // Make sure the room was not entered by luck.
			ComputerPlayer testPlayer = new ComputerPlayer();
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
			ComputerPlayer testPlayer = new ComputerPlayer();
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
}
