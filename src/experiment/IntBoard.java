package experiment;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class IntBoard {
	private Map<BoardCell, LinkedList<BoardCell>> adjacentMatrix;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;
	private BoardCell[][] grid;
	
	public static final int TOTAL_X = 4;
	public static final int TOTAL_Y = 4;
	
	public IntBoard() {
		super();
		adjacentMatrix = new HashMap<BoardCell, LinkedList<BoardCell>>();
	}
	
	public void calcAdjacencies(){
		for(int i = 0; i < TOTAL_X; i++) {
			for(int j = 0; j < TOTAL_Y; j++) {
				// stuff here
			}
		}
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
