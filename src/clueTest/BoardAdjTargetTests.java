package clueTest;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.Board;
import clueGame.BoardCell;

public class BoardAdjTargetTests {

	private static Board board;
	
	@BeforeClass
	public static void setUp() {
		board = new Board("ClueLayout.csv", "ClueLegend.txt");
		board.initialize();
	}
	
	//marked in green on layout
	@Test
	public void testAdjacenciesInsideRooms() { //change these to orange squares in our xlsx
		// Test a corner
		LinkedList<BoardCell> testList = board.getAdjList(24, 20);
		assertEquals(0, testList.size());
		// Test one that has walkway underneath
		testList = board.getAdjList(22, 8);
		assertEquals(0, testList.size());
		// Test one that has walkway above
		testList = board.getAdjList(2, 7);
		assertEquals(0, testList.size());
		// Test one that is in middle of room
		testList = board.getAdjList(2, 3);
		assertEquals(0, testList.size());
		// Test one beside a door
		testList = board.getAdjList(11, 14);
		assertEquals(0, testList.size());
		// Test one in a corner of room
		testList = board.getAdjList(9, 17);
		assertEquals(0, testList.size());
	}
	
	// Ensure that the adjacency list from a doorway is only the
		// walkway. NOTE: This test could be merged with door 
		// direction test. 
		// These tests are PURPLE on the planning spreadsheet
		@Test
		public void testAdjacencyRoomExit() //change to our doors
		{
			// TEST DOORWAY RIGHT 
			LinkedList<BoardCell> testList = board.getAdjList(11, 6);
			assertEquals(1, testList.size());
			assertTrue(testList.contains(board.getCellAt(11, 7)));
			// TEST DOORWAY LEFT 
			testList = board.getAdjList(10, 17);
			assertEquals(1, testList.size());
			assertTrue(testList.contains(board.getCellAt(10, 16)));
			//TEST DOORWAY DOWN
			testList = board.getAdjList(5, 15);
			assertEquals(1, testList.size());
			assertTrue(testList.contains(board.getCellAt(6, 15)));
			//TEST DOORWAY UP
			testList = board.getAdjList(5, 15);
			assertEquals(1, testList.size());
			assertTrue(testList.contains(board.getCellAt(6, 15)));
		}
	
}