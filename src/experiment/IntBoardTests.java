package experiment;

import static org.junit.Assert.*;

import java.util.LinkedList;

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
	public void testAdjacency0(){
		BoardCell cell = board.getCell(0,0);
		LinkedList<BoardCell> testList = board.getAdjList(cell);
		Assert.assertTrue(testList.contains(board.getCell(1, 0)));
		Assert.assertTrue(testList.contains(board.getCell(0, 1)));
		Assert.assertEquals(2, testList.size());
	}
	
	@Test
	public void testAdjacency1(){
		
	}
	
	@Test
	public void testAdjacency2(){
		
	}
	
	@Test
	public void testTargets0_3(){
	
	}
	
	@Test
	public void testTargets1_4() {
		
	}
}
