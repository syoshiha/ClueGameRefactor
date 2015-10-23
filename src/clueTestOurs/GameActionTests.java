package clueTestOurs;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import clueGame.Board;
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
						tempSolution.room = cardB.getCardName();
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
}
