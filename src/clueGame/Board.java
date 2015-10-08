package clueGame;

import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class Board {
	private int numRows;
	private int numColumns;
	public static final int BOARD_SIZE = 5;
	BoardCell[] board;
	Map<Character, String> rooms;
	Map<BoardCell, LinkedList<BoardCell>> adjMatrix;
	Set<BoardCell> targets;
	String boardConfigFile;
	String roomConfigFile;
	
	
	
	public Board() { //default constructor
		super();
		this.boardConfigFile = "Layout.csv";
		this.roomConfigFile = "Legend.txt";
	}

	public Board(String boardConfigFile, String roomConfigFile) { //constructor with board and room files passed in for testing with other files
		super();
		this.boardConfigFile = boardConfigFile;
		this.roomConfigFile = roomConfigFile;
	}

	public void initialize(){
		
	}
	
	public void loadroomConfig(String filename){
		
	}
	
	public void loadBoardConfig(String filename){
		
	}
	
	public void calcAdjacencies(){
		
	}

	public void calcTargets(int row, int column, int pathLength){
		
	}
	
	public BoardCell getCellAt(int row, int column){
		return null;
	}

	public LinkedList<BoardCell> getAdjList(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<BoardCell> getTargets() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

