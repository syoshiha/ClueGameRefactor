package clueTest;

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
		// DoorDirection testing
		// Up
		assertTrue(board.getCellAt(8, 4).getDoorDirection().equals(DoorDirection.UP));
		assertTrue(board.getCellAt(6, 24).getDoorDirection().equals(DoorDirection.UP));
		assertTrue(board.getCellAt(11, 22).getDoorDirection().equals(DoorDirection.UP));
		assertTrue(board.getCellAt(11, 23).getDoorDirection().equals(DoorDirection.UP));
		assertTrue(board.getCellAt(14, 2).getDoorDirection().equals(DoorDirection.UP));
		assertTrue(board.getCellAt(14, 12).getDoorDirection().equals(DoorDirection.UP));
		assertTrue(board.getCellAt(14, 13).getDoorDirection().equals(DoorDirection.UP));
		// Down
		assertTrue(board.getCellAt(5, 0).getDoorDirection().equals(DoorDirection.DOWN));
		assertTrue(board.getCellAt(5, 5).getDoorDirection().equals(DoorDirection.DOWN));
		// Left
		assertTrue(board.getCellAt(0, 5).getDoorDirection().equals(DoorDirection.LEFT));
		assertTrue(board.getCellAt(0, 19).getDoorDirection().equals(DoorDirection.LEFT));
		assertTrue(board.getCellAt(1, 11).getDoorDirection().equals(DoorDirection.LEFT));
		// Right
		assertTrue(board.getCellAt(0, 3).getDoorDirection().equals(DoorDirection.RIGHT));
		assertTrue(board.getCellAt(17, 4).getDoorDirection().equals(DoorDirection.RIGHT));
		assertTrue(board.getCellAt(17, 15).getDoorDirection().equals(DoorDirection.RIGHT));

		// Testing isDoorway()
		BoardCell notADoorWay = board.getCellAt(13, 24);
		assertFalse(notADoorWay.isDoorway());
		
		// Door count
	}
}