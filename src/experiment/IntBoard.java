package experiment;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	Map<BoardCell, LinkedList<BoardCell>> adjacentMatrix;

	public IntBoard() {
		super();
		adjacentMatrix = new HashMap<BoardCell, LinkedList<BoardCell>>();
	}
	
	public void calcAdjacencies(){
 
	}
	
	public void calcTargets(BoardCell startCell, int pathLength){
		
	}
	
	public Set<BoardCell> getTargets(BoardCell cell){
		return null;
	}
	
	public LinkedList<BoardCell> getAdjList(BoardCell cell){
		return null;
	}

	public BoardCell getCell(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
