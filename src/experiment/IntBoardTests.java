package experiment;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class IntBoardTests {
	private IntBoard board;
	@Before
	public void setupBoard(){
		board = new IntBoard();
	} 
	
	@Test
	public void testAdjacency0_0(){ //top left corner
		BoardCell cell = board.getCell(0,0);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		Assert.assertTrue(testList.contains(board.getCell(1, 0)));
		Assert.assertTrue(testList.contains(board.getCell(0, 1)));
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency1_0(){ //left edge
		BoardCell cell = board.getCell(1, 0);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		Assert.assertTrue(testList.contains(board.getCell(2,0)));
		Assert.assertTrue(testList.contains(board.getCell(1, 1)));
		Assert.assertTrue(testList.contains(board.getCell(0, 0)));
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency1_2changeme(){ //second from last middle of grid
		BoardCell cell = board.getCell(1, 2);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		Assert.assertTrue(testList.contains(board.getCell(0,2)));  //cell 2
		Assert.assertTrue(testList.contains(board.getCell(1, 1))); //cell 5
		Assert.assertTrue(testList.contains(board.getCell(2, 2))); //cell 10
		Assert.assertTrue(testList.contains(board.getCell(1, 3))); //cell 7
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void testTargets0_3(){
		BoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets(cell);
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(3, 0)));
		Assert.assertTrue(targets.contains(board.getCell(2, 1)));
		Assert.assertTrue(targets.contains(board.getCell(0, 1)));
		Assert.assertTrue(targets.contains(board.getCell(1, 2)));
		Assert.assertTrue(targets.contains(board.getCell(0, 3)));
		Assert.assertTrue(targets.contains(board.getCell(1, 0)));
	}

	@Test
	public void testTargets1_3(){
		BoardCell cell = board.getCell(1, 3);
		board.calcTargets(cell, 2);
		Set<BoardCell> targets = board.getTargets(cell);
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(0, 1)));
		Assert.assertTrue(targets.contains(board.getCell(1, 2)));
		Assert.assertTrue(targets.contains(board.getCell(2, 3)));
	}
	
	@Test
	public void testTargets1_4() {
		
	}
}
