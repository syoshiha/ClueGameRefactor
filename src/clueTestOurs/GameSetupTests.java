package clueTestOurs;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import clueGame.Board;
import clueGame.Card;
import clueGame.CardType;
import clueGame.ComputerPlayer;
import clueGame.Player;

public class GameSetupTests {
	
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
	public void testPeopleLoading() {
		
		// The first person in the file (John Smith) should be
		// the human player. The remaining 5 people will be
		// computer players.
		
		// Check human player
		assertEquals(board.getHumanPlayer().getName(), "John Smith");
		assertEquals(board.getHumanPlayer().getColor(), Color.red);
		assertEquals(board.getHumanPlayer().getRow(), 3);
		assertEquals(board.getHumanPlayer().getCol(), 9);
		
		// Exhaustively check each computer player to make sure
		// they have the correct name, color, and starting location
		Set<ComputerPlayer> compPlayers = board.getCompPlayers();
		assertEquals(compPlayers.size(), 5);
		
		for (ComputerPlayer player : compPlayers) {
			switch (player.getName()) {
			case "Joe Brown":
				assertEquals(player.getColor(), Color.blue);
				assertEquals(player.getRow(), 8);
				assertEquals(player.getCol(), 17);
				break;
			case "Bill Adams":
				assertEquals(player.getColor(), Color.cyan);
				assertEquals(player.getRow(), 19);
				assertEquals(player.getCol(), 19);
				break;
			case "Jake Williams":
				assertEquals(player.getColor(), Color.green);
				assertEquals(player.getRow(), 14);
				assertEquals(player.getCol(), 7);
				break;
			case "Connor Davis":
				assertEquals(player.getColor(), Color.yellow);
				assertEquals(player.getRow(), 10);
				assertEquals(player.getCol(), 7);
				break;
			case "Grant Jones":
				assertEquals(player.getColor(), Color.orange);
				assertEquals(player.getRow(), 12);
				assertEquals(player.getCol(), 2);
				break;
			default:
				// The name should be one of the names listed above. If it
				// is not, the test fails.
				assertEquals(1, 0);
			}
		}
	}
	
	@Test
	public void testCardLoading() {
		Set<Card> deck = board.getDeck();
		
		// Count the number of people, rooms, and weapons
		int numPeople = 0;
		int numRooms = 0;
		int numWeapons = 0;

		for (Card card : deck) {
			if (card.getCardType() == CardType.PERSON) {
				numPeople++;
			} else if (card.getCardType() == CardType.ROOM) {
				numRooms++;
			} else if (card.getCardType() == CardType.WEAPON) {
				numWeapons++;
			} else {
				// The card type should be one of the types listed above,
				// otherwise the test should fail.
				assertEquals(0, 1);
			}
		}
		
		// The deck should have 9 rooms, 6 people, and 6 weapons = 21 total
		assertEquals(numPeople, 6);
		assertEquals(numRooms, 9);
		assertEquals(numWeapons, 6);
		assertEquals(deck.size(), 21);
		
		// Check that the deck contains "Hammer", "John Smith", and "Game Room"
		// This is to ensure the cards are named correctly
		boolean personFound = false;
		boolean weaponFound = false;
		boolean roomFound = false;
		
		for (Card card : deck) {
			if (card.getCardType().equals(CardType.PERSON) &&
				card.getCardName().equals("John Smith")) {
				personFound = true;
			}
			if (card.getCardType().equals(CardType.WEAPON) &&
				card.getCardName().equals("Hammer")) {
				weaponFound = true;
			}
			if (card.getCardType().equals(CardType.ROOM) &&
				card.getCardName().equals("Game room")) {
				roomFound = true;
			}
		}
		
		assertTrue(personFound);
		assertTrue(weaponFound);
		assertTrue(roomFound);
	}
	
	@Test
	public void testCardDeal() {
		Set<Player> allPlayers = new HashSet<Player>();
		allPlayers.add(board.getHumanPlayer());
		allPlayers.addAll(board.getCompPlayers());
		
		// Check that for each pair of players, the sets
		// of cards they have are disjoint. This is to ensure
		// that each card was only dealt once.
		for (Player playerA : allPlayers) {
			for (Player playerB : allPlayers) {
				
				// Skip this iteration if playerA and playerB are the same player
				if (playerA.getName().equals(playerB.getName())) {
					continue;
				}
				
				// Check that playerA and playerB don't have any of the same cards
				assertTrue(Collections.disjoint(playerA.getMyCards(), playerB.getMyCards()));
			}
		}
		
		
		// Check that every card was dealt
		Set<Card> dealtCards = new HashSet<Card>();
		for (Player player : allPlayers) {
			dealtCards.addAll(player.getMyCards());
		}
		assertTrue(board.getDeck().equals(dealtCards));
		
		
		// Check that each player has about the same number of cards.
		// Each player should have either x or x-1 cards.
		Set<Integer> numCardsPerPlayer = new HashSet<Integer>();
		for (Player player : allPlayers) {
			numCardsPerPlayer.add(player.getMyCards().size());
		}
		assertEquals(numCardsPerPlayer.size(), 2);
		for (Integer i : numCardsPerPlayer) {
			assertTrue(i == Collections.max(numCardsPerPlayer) ||
					   i == Collections.max(numCardsPerPlayer) - 1);
		}
	}

}
