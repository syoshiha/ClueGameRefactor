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
		board.calcAdjacencies();
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
	public void testAdjacency3_3(){ //bottom right corner
		BoardCell cell = board.getCell(0,0);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		Assert.assertTrue(testList.contains(board.getCell(2, 3)));
		Assert.assertTrue(testList.contains(board.getCell(3, 2)));
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency1_0(){ //left edge
		BoardCell cell = board.getCell(1, 0);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		Assert.assertTrue(testList.contains(board.getCell(2, 0)));
		Assert.assertTrue(testList.contains(board.getCell(1, 1)));
		Assert.assertTrue(testList.contains(board.getCell(0, 0)));
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency1_2(){ //second from last middle of grid

		BoardCell cell = board.getCell(1, 2);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		Assert.assertTrue(testList.contains(board.getCell(0, 2)));  //cell 2
		Assert.assertTrue(testList.contains(board.getCell(1, 1))); //cell 5
		Assert.assertTrue(testList.contains(board.getCell(2, 2))); //cell 10
		Assert.assertTrue(testList.contains(board.getCell(1, 3))); //cell 7
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency2_1(){ //second column middle of grid
		BoardCell cell = board.getCell(2, 1);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		Assert.assertTrue(testList.contains(board.getCell(3, 1)));  //cell 13
		Assert.assertTrue(testList.contains(board.getCell(2, 2))); //cell 10
		Assert.assertTrue(testList.contains(board.getCell(1, 1))); //cell 5
		Assert.assertTrue(testList.contains(board.getCell(2, 0))); //cell 8
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency2_3(){ //right edge
		BoardCell cell = board.getCell(2, 3);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		Assert.assertTrue(testList.contains(board.getCell(3, 3))); //cell 15
		Assert.assertTrue(testList.contains(board.getCell(1, 3))); //cell 7
		Assert.assertTrue(testList.contains(board.getCell(2, 2))); //cell 10
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void testTargets0_3(){
		BoardCell cell = board.getCell(0, 0);
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(3, 0)));
		Assert.assertTrue(targets.contains(board.getCell(2, 1)));
		Assert.assertTrue(targets.contains(board.getCell(0, 1)));
		Assert.assertTrue(targets.contains(board.getCell(1, 2)));
		Assert.assertTrue(targets.contains(board.getCell(0, 3)));
		Assert.assertTrue(targets.contains(board.getCell(1, 0)));
	}

	@Test
	public void testTargets1_3(){ //cell 1 3 steps
		BoardCell cell = board.getCell(1, 3);
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(0, 0))); //cell 0
		Assert.assertTrue(targets.contains(board.getCell(0, 2))); //cell 2
		Assert.assertTrue(targets.contains(board.getCell(1, 1))); //cell 5
		Assert.assertTrue(targets.contains(board.getCell(1, 3))); //cell 7 
		Assert.assertTrue(targets.contains(board.getCell(2, 0))); //cell 8
		Assert.assertTrue(targets.contains(board.getCell(2, 2))); //cell 10
		Assert.assertTrue(targets.contains(board.getCell(3, 1))); //cell 13
	}
	
	@Test
	public void testTargets2_2() { // cell 2, 2 steps
		BoardCell cell = board.getCell(0, 2);
		board.calcTargets(cell, 2);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(4, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(0, 0)));
		Assert.assertTrue(targets.contains(board.getCell(1, 1)));
		Assert.assertTrue(targets.contains(board.getCell(2, 2)));
		Assert.assertTrue(targets.contains(board.getCell(1, 3)));
	}
	
	@Test
	public void testTargets9_5(){ //cell 9 5 steps
		BoardCell cell = board.getCell(2, 1);
		board.calcTargets(cell, 5);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(0, 0))); //cell 0
		Assert.assertTrue(targets.contains(board.getCell(0, 2))); //cell 2
		Assert.assertTrue(targets.contains(board.getCell(1, 1))); //cell 5
		Assert.assertTrue(targets.contains(board.getCell(1, 3))); //cell 7 
		Assert.assertTrue(targets.contains(board.getCell(2, 0))); //cell 8
		Assert.assertTrue(targets.contains(board.getCell(2, 2))); //cell 10
		Assert.assertTrue(targets.contains(board.getCell(3, 3))); //cell 15
	}
	
	@Test
	public void testTargets7_3(){ //cell 7 3 steps
		BoardCell cell = board.getCell(1, 3);
		board.calcTargets(cell, 3);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(3, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(0, 1))); //cell 1
		Assert.assertTrue(targets.contains(board.getCell(1, 0))); //cell 4
		Assert.assertTrue(targets.contains(board.getCell(1, 2))); //cell 6
		Assert.assertTrue(targets.contains(board.getCell(2, 1))); //cell 9 
		Assert.assertTrue(targets.contains(board.getCell(3, 2))); //cell 14
	}

	@Test
	public void testTargets15_4() { // cell 15, 4 steps
		BoardCell cell = board.getCell(3, 3);
		board.calcTargets(cell, 4);
		Set<BoardCell> targets = board.getTargets();
		Assert.assertEquals(6, targets.size());
		Assert.assertTrue(targets.contains(board.getCell(2, 0)));
		Assert.assertTrue(targets.contains(board.getCell(1, 1)));
		Assert.assertTrue(targets.contains(board.getCell(0, 2)));
		Assert.assertTrue(targets.contains(board.getCell(3, 1)));
		Assert.assertTrue(targets.contains(board.getCell(2, 2)));
		Assert.assertTrue(targets.contains(board.getCell(1, 3)));
	}	

}
