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
	
	
	
	public Board(String a, String b) {
		// TODO Auto-generated constructor stub
	}

	public Board() {
		// TODO Auto-generated constructor stub
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

	public static Map<Character, String> getRooms() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getNumRows() {
		// TODO Auto-generated method stub
		return 0;
	}

	public int getNumColumns() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void loadRoomConfig() {
		// TODO Auto-generated method stub
		
	}

	public void loadBoardConfig() {
		// TODO Auto-generated method stub
		
	}
	
	
}

