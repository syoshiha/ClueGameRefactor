package clueGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Board {
	private int numRows;
	private int numColumns;
	public static final int BOARD_SIZE = 5;
	private BoardCell[][] board;
	private static Map<Character, String> rooms;
	private String boardConfigFile;
	private String roomConfigFile;
	
	private Map<BoardCell, LinkedList<BoardCell>> adjMatrix;
	private Set<BoardCell> targets;

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
			loadRoomConfig();
			loadBoardConfig();
		} catch (FileNotFoundException e) {
			System.out.println("File not found. " + e.getMessage());
		} catch (BadConfigFormatException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void loadRoomConfig() throws FileNotFoundException, BadConfigFormatException {
		FileReader reader = new FileReader(roomConfigFile);
		Scanner in = new Scanner(reader);
		Map<Character, String> tempRooms = new HashMap<Character, String>();
		while (in.hasNextLine()){
			String value = in.nextLine();
			value = value.replace(", ", " ");
			value = value.replace(",", "");
			int lastSpot = value.lastIndexOf(" "); // get rid of card
			value = value.substring(0, lastSpot);
			if(!value.contains(" ")) {
				in.close();
				throw new BadConfigFormatException("Bad legend file; lacks a room name for initial");
			}
			Scanner scan = new Scanner(value);
			char key = scan.next().charAt(0);
			String put = scan.nextLine();
			scan.close();
			put = put.trim();
			tempRooms.put(key, put);
		}
		rooms = tempRooms;
		in.close();
	}
	
	public void loadBoardConfig() throws FileNotFoundException, BadConfigFormatException{
		numRows = getBoardConfigRows();
		numColumns = getBoardConfigColumns();
		board = new BoardCell[numRows][numColumns];
		FileReader fin = new FileReader(boardConfigFile);
		Scanner scan = new Scanner(fin);
		int row = 0;
		int column = 0;
		while(scan.hasNext()) {
			column = 0;
			String nextLine = scan.next();	// This is a single line of comma-separated values
			nextLine = nextLine.replace(',', ' ');		// Commas replaced by spaces, to generate a readable list
			Scanner scanIn = new Scanner(nextLine);
			while(scanIn.hasNext()) {
				String nextEntry = scanIn.next();
				if(!rooms.containsKey(nextEntry.charAt(0))) {
					throw new BadConfigFormatException("Bad room type");
				}
				if(nextEntry.length() > 1) {	// if this is true, then the cell must be a door
					DoorDirection d = DoorDirection.convert(nextEntry.charAt(1));
					this.board[row][column] = new BoardCell(row, column, nextEntry.charAt(0), d);
				}
				else {
					this.board[row][column] = new BoardCell(row, column, nextEntry.charAt(0), DoorDirection.NONE);
				}
				column++;
			}
			scanIn.close();
			row++;
		}
		scan.close();
	}
	
	private int getBoardConfigRows() throws FileNotFoundException {
		FileReader fin = new FileReader(boardConfigFile);
		Scanner scan = new Scanner(fin);
		int count = 0;
		while(scan.hasNext()) {
			scan.next();
			count++;
		}
		scan.close();
		return count;
	}
	
	private int getBoardConfigColumns() throws FileNotFoundException, BadConfigFormatException {
		FileReader fin = new FileReader(boardConfigFile);
		Scanner scan = new Scanner(fin);
		int count = 0;
		int maxCount = 0;
		boolean firstGo = true;
		while(scan.hasNext()) {
			count = 0;
			String nextCol = scan.next();
			Scanner scanIn = new Scanner(nextCol);
			scanIn.useDelimiter(",");
			while(scanIn.hasNext()) {
				scanIn.next();
				count++;
			}
			scanIn.close();
			if(firstGo) {
				maxCount = count;
				firstGo = false;
			}
			else {
				if(count != maxCount) {
					throw new BadConfigFormatException("Number of rows or columns is not consistent");
				}
			}
		}
		scan.close();
		return count;
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
		return null;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}
	
	public Set<BoardCell> getTargets() {
		return null;
	}
	
}

