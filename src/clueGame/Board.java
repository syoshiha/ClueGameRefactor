package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Board {
	private int numRows;
	private int numColumns;
	public static final int BOARD_SIZE = 5;
	BoardCell[][] board;
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
		loadRoomConfig(roomConfigFile);
		loadBoardConfig(boardConfigFile);		
	}
	
	public void loadRoomConfig(String filename) throws FileNotFoundException{
		FileReader reader = new FileReader(filename);
		Scanner in = new Scanner(reader);
		in.useDelimiter(",");
		while (in.hasNextLine()){
			char key = in.next().charAt(0); //gets first char as Key
			String value = in.nextLine(); //rest of the line is the value
			rooms.put(key, value);
			System.out.println("key: "+key +" value: " +value);
		}
		in.close();
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
		return null;
	}
	
	public LinkedList<BoardCell> getAdjList(int i, int j) {
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
	
	public Set<BoardCell> getTargets() {
		return null;
	}
	
	
}

