package clueTest;

/*
 * TODO:
 *  Ensure there is the correct # of rooms loaded
 *  Check your work
 *  
 * */

// Doing a static import allows me to write assertEquals rather than
// Assert.assertEquals
import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Map;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import clueGame.BadConfigFormatException;
import clueGame.Board;
import clueGame.BoardCell;
import clueGame.DoorDirection;

public class FileInitTests {
	public static final int NUM_ROOMS = 9;
	public static final int NUM_ROWS = 21;
	public static final int NUM_COLUMNS = 25;

	private static final String BAD_ROOM_FILE = null;
	private static final String BAD_LEGEND_FILE = null;
	private static final String CORRECT_LEGEND_FILE = null;
	private static final String CORRECT_ROOM_FILE = null;
	
	private static Board board;
	
	@BeforeClass					// Ran once only, prior to beginning the first test
	public static void setUp() {	// Static method, the board is the same every time
		board = new Board();
		board.initialize();	// Sets up the board
	}
	
	@Test
	public void testRowColCount() {
		assertEquals(NUM_ROWS, board.getNumRows());
		assertEquals(NUM_COLUMNS, board.getNumColumns());
	}
	
	@Test
	public void testDoorsInit() {
		// DoorDirection and isDoorWay testing
		// Up
		assertTrue(board.getCellAt(8, 4).getDoorDirection().equals(DoorDirection.UP));
		assertTrue(board.getCellAt(8, 4).isDoorway());
		assertTrue(board.getCellAt(6, 24).getDoorDirection().equals(DoorDirection.UP));
		assertTrue(board.getCellAt(6, 24).isDoorway());
		assertTrue(board.getCellAt(11, 22).getDoorDirection().equals(DoorDirection.UP));
		assertTrue(board.getCellAt(11, 22).isDoorway());
		assertTrue(board.getCellAt(11, 23).getDoorDirection().equals(DoorDirection.UP));
		assertTrue(board.getCellAt(11, 23).isDoorway());
		assertTrue(board.getCellAt(14, 2).getDoorDirection().equals(DoorDirection.UP));
		assertTrue(board.getCellAt(14, 2).isDoorway());
		assertTrue(board.getCellAt(14, 12).getDoorDirection().equals(DoorDirection.UP));
		assertTrue(board.getCellAt(14, 12).isDoorway());
		assertTrue(board.getCellAt(14, 13).getDoorDirection().equals(DoorDirection.UP));
		assertTrue(board.getCellAt(14, 13).isDoorway());
		// Down
		assertTrue(board.getCellAt(5, 0).getDoorDirection().equals(DoorDirection.DOWN));
		assertTrue(board.getCellAt(5, 0).isDoorway());
		assertTrue(board.getCellAt(5, 5).getDoorDirection().equals(DoorDirection.DOWN));
		assertTrue(board.getCellAt(5, 5).isDoorway());
		// Left
		assertTrue(board.getCellAt(0, 5).getDoorDirection().equals(DoorDirection.LEFT));
		assertTrue(board.getCellAt(0, 5).isDoorway());
		assertTrue(board.getCellAt(0, 19).getDoorDirection().equals(DoorDirection.LEFT));
		assertTrue(board.getCellAt(0, 19).isDoorway());
		assertTrue(board.getCellAt(1, 11).getDoorDirection().equals(DoorDirection.LEFT));
		assertTrue(board.getCellAt(1, 11).isDoorway());
		// Right
		assertTrue(board.getCellAt(0, 3).getDoorDirection().equals(DoorDirection.RIGHT));
		assertTrue(board.getCellAt(0, 3).isDoorway());
		assertTrue(board.getCellAt(17, 4).getDoorDirection().equals(DoorDirection.RIGHT));
		assertTrue(board.getCellAt(17, 4).isDoorway());
		assertTrue(board.getCellAt(17, 15).getDoorDirection().equals(DoorDirection.RIGHT));
		assertTrue(board.getCellAt(17, 15).isDoorway());
	}
	
	@Test
	public void testIsDoorwayNegative() {
		// Testing isDoorway()
		BoardCell notADoorWay = board.getCellAt(13, 24);
		assertFalse(notADoorWay.isDoorway());
		notADoorWay = board.getCellAt(12, 6);
		assertFalse(notADoorWay.isDoorway());
	}
	
	@Test
	public void testDoorsCount() {
		int numDoors = 0;
		for(int i = 0; i < board.getNumRows(); i++) {
			for(int j = 0; j < board.getNumColumns(); j++) {
				BoardCell thisCell = board.getCellAt(i, j);
				if(thisCell.isDoorway()) {
					numDoors++;
				}
			}
		}
		assertEquals(numDoors, 15);
	}
	
	@Test
	public void testRoomInitials() {
		BoardCell room = board.getCellAt(2, 2);
		assertEquals(room.getInitial(), 'K');
		room = board.getCellAt(1, 7);
		assertEquals(room.getInitial(), 'D');
		room = board.getCellAt(1, 15);
		assertEquals(room.getInitial(), 'R');
		room = board.getCellAt(2, 22);
		assertEquals(room.getInitial(), 'P');
		room = board.getCellAt(9, 2);
		assertEquals(room.getInitial(), 'L');
		room = board.getCellAt(13, 22);
		assertEquals(room.getInitial(), 'B');
		room = board.getCellAt(17, 2);
		assertEquals(room.getInitial(), 'S');
		room = board.getCellAt(17, 13);
		assertEquals(room.getInitial(), 'G');
		room = board.getCellAt(7, 22);
		assertEquals(room.getInitial(), 'M');
	}
	
	@Test (expected = BadConfigFormatException.class)
	public void testExceptionBadRoom() throws BadConfigFormatException, FileNotFoundException {
		Board testBoard = new Board(BAD_ROOM_FILE, CORRECT_LEGEND_FILE);
		testBoard.loadBoardConfig();
		testBoard.loadRoomConfig();
	}
	
	@Test (expected = BadConfigFormatException.class)
	public void testExceptionBadLegend() throws BadConfigFormatException, FileNotFoundException {
		Board testBoard = new Board(CORRECT_ROOM_FILE, BAD_LEGEND_FILE);
		testBoard.loadBoardConfig();
		testBoard.loadRoomConfig();
	}	
}