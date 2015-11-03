package clueGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JPanel;

public class Board extends JPanel {
	private int numRows;
	private int numColumns;
	public static final int BOARD_SIZE = 5;
	private BoardCell[][] board;
	public static Map<Character, String> rooms;
	static private Set<Card> cardDeck; // This is static because there will only
									   // one card deck associated with each game.

	private HumanPlayer humanPlayer;
	private Set<ComputerPlayer> compPlayers;
	private String boardConfigFile;
	private String roomConfigFile;
	private String peopleConfigFile;
	private String weaponConfigFile;
	
	private Map<BoardCell, LinkedList<BoardCell>> adjMatrix;
	private Set<BoardCell> visited;
	private Set<BoardCell> targets;

	private Solution theAnswer;
	private Set<Card> answerCards; // Set of cards in the solution. This
								   // variable is mostly useful for testing.
	
	@Override
	public void paintComponent(Graphics g) {
		// Check that board has been initialized before drawing it
		
		
		// Draw cells
		
		
		// Draw Players
		
		
		// Draw Labels
		
		
	}
	
	// Default constructor
	public Board() { 
		super();
		this.boardConfigFile = "Layout.csv";
		this.roomConfigFile = "Legend.txt";
		this.peopleConfigFile = "Players.txt";
		this.weaponConfigFile = "Weapons.txt";
	}
	
	// Constructor used for first set of unit tests. Parameterized for board and room
	// files. Other files use default values
	public Board(String boardConfigFile, String roomConfigFile) {
		super();
		this.boardConfigFile = boardConfigFile;
		this.roomConfigFile = roomConfigFile;
		this.peopleConfigFile = "Players.txt";
		this.weaponConfigFile = "Weapons.txt";
	}
	
	// Constructor parameterized for all four files
	public Board(String boardConfigFile, String roomConfigFile, String peopleConfigFile, String weaponConfigFile) {
		super();
		this.boardConfigFile = boardConfigFile;
		this.roomConfigFile = roomConfigFile;
		this.peopleConfigFile = peopleConfigFile;
		this.weaponConfigFile = weaponConfigFile;
	}

	public void initialize() {
		
		cardDeck = new HashSet<Card>();
		
		try {
			loadRoomConfig();
			loadBoardConfig();
			loadPeopleConfig();
			loadWeaponConfig();
			
		} catch (FileNotFoundException e) {
			System.out.println("File not found. " + e.getMessage());
		} catch (BadConfigFormatException e) {
			System.out.println(e.getMessage());
		}
		
		theAnswer = new Solution();
		answerCards = new HashSet<Card>();
		dealCards();
		setNamePositions();
	}
	
	
	// This function determines where to write the name for
	// each room. It finds the row in each room that has the
	// most space, and decides to write the name there.
	public void setNamePositions() {
		
		int[][] maxLengths = new int[numRows][];
		for (int i=0; i<numRows; i++) {
			maxLengths[i] = new int[numColumns];
		}
		
		for (int i=0; i<numRows; i++) {
			for (int j=0; j<numColumns; j++) {
				int offset = 0;
				for (offset = 0; j+offset < numColumns; offset++) {
					if (board[i][j+offset].getInitial() != board[i][j].getInitial()) {
						break;
					}
				}
				maxLengths[i][j] = offset;
			}
		}
		
		for (Character initial : rooms.keySet()) {
			int currentRow = -1;
			int currentColumn = -1;
			for (int i=0; i<numRows; i++) {
				for (int j=0; j<numColumns; j++) {
					if (board[i][j].getInitial() == initial &&
						(currentRow == -1 ||
						maxLengths[i][j] > maxLengths[currentRow][currentColumn])) {
						currentRow = i;
						currentColumn = j;
					}
				}
			}
			if (board[currentRow][currentColumn].isRoom()) {
				board[currentRow][currentColumn].setName(true);
			}
		}
		
		
	}
	
	public Card handleSuggestion(Solution suggestion, String accusingPlayer) {
		// Find the accusing player object
		Player accuser = null;
		boolean accuserIsHuman = false;
		if (humanPlayer.getName() == accusingPlayer) {
			accuser = humanPlayer;
			accuserIsHuman = true;
		}
		for (Player player : compPlayers) {
			if (player.getName() == accusingPlayer) {
				accuser = player;
			}
		}
		if (accuser == null) return null;
		
		// Query players that come after the accuser in the cycle
		Card disprovingCard;
		boolean startedCycle = false;
		if (accuserIsHuman) startedCycle = true;
		for (ComputerPlayer player : compPlayers) {
			if (startedCycle) {
				disprovingCard = player.disproveSuggestion(suggestion);
				if (disprovingCard != null) {
					return disprovingCard;
				}
			}
			if (player.getName() == accuser.getName()) {
				startedCycle = true;
			}
		}
		
		// Query players that come before the accuser in the cycle
		if (accuserIsHuman) return null;
		disprovingCard = humanPlayer.disproveSuggestion(suggestion);
		if (disprovingCard != null) {
			return disprovingCard;
		}
		for (ComputerPlayer player : compPlayers) {
			if (player.getName() == accuser.getName()) {
				break;
			}
			disprovingCard = player.disproveSuggestion(suggestion);
			if (disprovingCard != null) {
				return disprovingCard;
			}
		}
		
		// Return null if none of the players could disprove
		return null;
	}
	
	// Returns true only if the accusation was correct
	public boolean checkAccusation(Solution accusation) {
		if (accusation == null) return false;
		return this.theAnswer.equals(accusation);
	}
	
	public void loadPeopleConfig() throws FileNotFoundException, BadConfigFormatException {
		FileReader reader = new FileReader(peopleConfigFile);
		Scanner scanner = new Scanner(reader);
		humanPlayer = new HumanPlayer();
		compPlayers = new TreeSet<ComputerPlayer>();
		ComputerPlayer tempPlayer;
		
		String temp = "";
		
		try {
			humanPlayer.setName(scanner.nextLine());
			humanPlayer.setColor(scanner.nextLine());
			humanPlayer.setRow(Integer.parseInt(scanner.next()));
			humanPlayer.setCol(Integer.parseInt(scanner.next()));
			scanner.nextLine(); // Flush rest of line
		} catch (Exception e) {
			throw new BadConfigFormatException("Bad people file format");
		}
		
		while (scanner.hasNextLine()) {
			try {
				tempPlayer = new ComputerPlayer();
				tempPlayer.setName(scanner.nextLine());
				tempPlayer.setColor(scanner.nextLine());
				tempPlayer.setRow(Integer.parseInt(scanner.next()));
				tempPlayer.setCol(Integer.parseInt(scanner.next()));
				if (scanner.hasNextLine()) scanner.nextLine(); // Flush rest of line
				compPlayers.add(tempPlayer);
			} catch (Exception e) {
				throw new BadConfigFormatException("Bad people file format"); 
			}
		}
		
		// Add the people to the card deck
		Card tempCard = new Card();
		tempCard.setType(CardType.PERSON);
		tempCard.setName(humanPlayer.getName());
		cardDeck.add(tempCard);
		
		for (ComputerPlayer player : compPlayers) {
			Card tempCompCard = new Card();
			tempCompCard.setType(CardType.PERSON);
			tempCompCard.setName(player.getName());
			cardDeck.add(tempCompCard);
		}
	}
	
	public void loadWeaponConfig() throws FileNotFoundException, BadConfigFormatException {
		FileReader reader = new FileReader(weaponConfigFile);
		Scanner scanner = new Scanner(reader);
		
		while (scanner.hasNextLine()) {
			Card tempCard = new Card();
			tempCard.setType(CardType.WEAPON);
			tempCard.setName(scanner.nextLine());
			cardDeck.add(tempCard);
		}
	}
	
	public void loadRoomConfig() throws FileNotFoundException, BadConfigFormatException {
		FileReader reader = new FileReader(roomConfigFile);
		Scanner in = new Scanner(reader);
		Map<Character, String> tempRooms = new HashMap<Character, String>();
		
		boolean isCard = true;
		
		while (in.hasNextLine()){
			String value = in.nextLine();
			
			if (value.charAt(value.length() - 1) == 'd') {
				isCard = true;
			} else {
				isCard = false;
			}
			
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
			
			if (isCard) {
				Card tempCard = new Card();
				tempCard.setType(CardType.ROOM);
				tempCard.setName(put);
				cardDeck.add(tempCard);
			}
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
					scanIn.close();
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
		adjMatrix = new HashMap<BoardCell, LinkedList<BoardCell>>();
		calcAdjacencies();
	}
	
	public void dealCards() {
		ArrayList<Card> shuffledCards = new ArrayList<Card>();
		shuffledCards.addAll(cardDeck);
		Collections.shuffle(shuffledCards);
		
		// Pick a person for the solution
		for (int i=0; i<shuffledCards.size(); i++) {
			if (shuffledCards.get(i).getCardType() == CardType.PERSON) {
				theAnswer.person = shuffledCards.get(i).getCardName();
				answerCards.add(shuffledCards.get(i));
				shuffledCards.remove(i);
				break;
			}
		}
		
		// Pick a weapon for the solution
		for (int i=0; i<shuffledCards.size(); i++) {
			if (shuffledCards.get(i).getCardType() == CardType.WEAPON) {
				theAnswer.weapon = shuffledCards.get(i).getCardName();
				answerCards.add(shuffledCards.get(i));
				shuffledCards.remove(i);
				break;
			}
		}
		
		// Pick a room for the solution
		for (int i=0; i<shuffledCards.size(); i++) {
			if (shuffledCards.get(i).getCardType() == CardType.ROOM) {
				theAnswer.room = shuffledCards.get(i).getCardName();
				answerCards.add(shuffledCards.get(i));
				shuffledCards.remove(i);
				break;
			}
		}
		
		while (!shuffledCards.isEmpty()) {
			try {
				
				// Give human a card
				humanPlayer.giveCard(shuffledCards.remove(0));
				
				// Give each computer player a card
				for (Player player : compPlayers) {
					player.giveCard(shuffledCards.remove(0));
				}
				
			} catch (Exception e) {
				
				// Break when there are no more cards to deal
				break;
			}
		}
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
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numColumns; j++) {
				LinkedList<BoardCell> neighbors = new LinkedList<BoardCell>();
				if (board[i][j].isRoom() && !board[i][j].isDoorway()){
					adjMatrix.put(board[i][j], neighbors);
					continue;
				}
				else if (board[i][j].isDoorway()) {
					DoorDirection initial = board[i][j].getDoorDirection();
					switch(initial) {							// the enumerator class
						case UP: if(i > 0) {
							neighbors.add(board[i - 1][j]);
							adjMatrix.put(board[i][j], neighbors);
						}
							break;
						case DOWN: if(i < numRows - 1) {
							neighbors.add(board[i + 1][j]);
							adjMatrix.put(board[i][j], neighbors);
						}
							break;
						case LEFT: if(j > 0) {
							neighbors.add(board[i][j - 1]);
							adjMatrix.put(board[i][j], neighbors);
						}
							break;
						case RIGHT: if(j < numColumns - 1) {
							neighbors.add(board[i][j + 1]);
							adjMatrix.put(board[i][j], neighbors);
						}
							break;
						default: System.out.println("Unknown Door Direction");;
					}
				}
				else {
					if(i > 0) {
						if (!board[i - 1][j].isRoom() || (board[i - 1][j].isDoorway() && board[i - 1][j].getDoorDirection() == DoorDirection.DOWN))
							neighbors.add(board[i - 1][j]);
					}
					if(i < numRows - 1) {
						if (!board[i + 1][j].isRoom() || (board[i + 1][j].isDoorway() && board[i + 1][j].getDoorDirection() == DoorDirection.UP))
							neighbors.add(board[i + 1][j]);
					}
					if(j > 0) {
						if(!board[i][j - 1].isRoom() || (board[i][j - 1].isDoorway() && board[i][j - 1].getDoorDirection() == DoorDirection.RIGHT))
							neighbors.add(board[i][j - 1]);
					}
					if(j < numColumns - 1) {
						if (!board[i][j + 1].isRoom() || (board[i][j + 1].isDoorway() && board[i][j + 1].getDoorDirection() == DoorDirection.LEFT))
							neighbors.add(board[i][j + 1]);
					}
					adjMatrix.put(board[i][j], neighbors);
				}
			}
		}
	}

	public void calcTargets(int row, int column, int pathLength){
		visited = new HashSet<BoardCell>(); //should we set these up here? might be ineff.
		targets = new HashSet<BoardCell>();
		visited.clear(); //clear the visited set
		targets.clear(); //clear the targets set
		visited.add(board[row][column]);
		targets = findAllTargets(board[row][column], pathLength);
	}
	
	private Set<BoardCell> findAllTargets(BoardCell currentCell, int remainingSteps) {
		visited.add(currentCell);
		LinkedList<BoardCell> adj = new LinkedList<BoardCell>(adjMatrix.get(currentCell));	//new linked list of cells that have not been visited
		for (BoardCell i:visited){
			adj.remove(i);
		}
		for (BoardCell i:adj){
			if(remainingSteps == 1){
				targets.add(i);
			}
			else if (i.isDoorway()){
				targets.add(i);
			}
			else {
				targets.addAll(findAllTargets(i, remainingSteps-1));
			}
			visited.remove(i);
		}
		return targets;
	}

	public BoardCell getCellAt(int row, int column){
		return board[row][column];
	}

	public static Map<Character, String> getRooms() {
		return rooms;
	}
	
	public LinkedList<BoardCell> getAdjList(int i, int j) {
		return adjMatrix.get(board[i][j]);
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}
	
	public Set<BoardCell> getTargets() {
		return targets;
	}
	
	public HumanPlayer getHumanPlayer() {
		return humanPlayer;
	}
	
	public Set<ComputerPlayer> getCompPlayers() {
		return compPlayers;
	}
	
	static public Set<Card> getDeck() {
		return cardDeck;
	}
	
	public Set<Card> getAnswerCards() {
		return answerCards;
	}
	
	public Solution getAnswer() {
		return theAnswer;
	}
	
	
	// These functions allow us to deal custom cards to the players
	// for our JUnit tests.
	public void setHumanPlayer(HumanPlayer hp) {
		humanPlayer = hp;
	}
	
	public void setCompPlayer(ArrayList<ComputerPlayer> cp) {
		compPlayers.clear();
		compPlayers.addAll(cp);
	}
}

