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
	static Map<Character, String> rooms;
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

	public void initialize() {
		try {
			loadRoomConfig(roomConfigFile);
			loadBoardConfig(boardConfigFile);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void loadRoomConfig(String filename){
		
	}
	
	public void loadBoardConfig(String filename) throws FileNotFoundException{
		FileReader fin = new FileReader(filename);
		Scanner scan = new Scanner(fin);
		int row = 0;
		int column = 0;
		while(scan.hasNext()) {
			row++;
			column = 0;
			String nextLine = scan.next();	// This is a single line of comma-separated values
			nextLine.replace(',', ' ');		// Commas replaced by spaces, to generate a readable list
			Scanner scanIn = new Scanner(nextLine);
			while(scanIn.hasNext()) {
				column++;
				String nextEntry = scanIn.next();
				board[row][column] = new BoardCell(row, column, nextEntry.charAt(0));
			}
		}
		scan.close();
	}
	
	public void calcAdjacencies(){
		
	}

	public void calcTargets(int row, int column, int pathLength){
		
	}
	
	public BoardCell getCellAt(int row, int column){
		return board[row][column];
	}

	public static Map<Character, String> getRooms() {
		return rooms;
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

