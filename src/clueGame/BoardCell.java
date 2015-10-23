package clueGame;

public class BoardCell {

	private int row;
	private int column;
	private char initial;
	private DoorDirection doorDirection;
	
	public static final char WALKWAY_INITIAL = 'W';
	//public static final char CLOSET_INITIAL = 'X';
	
	public BoardCell() {
		super();
	}
	
	public BoardCell(int row, int column, char initial, DoorDirection doorDirection) {
		super();
		this.row = row;
		this.column = column;
		this.initial = initial;
		this.doorDirection = doorDirection;
	}
	
	public boolean isWalkway() {
		if(initial == WALKWAY_INITIAL) {
			return true;
		}
		return false;
	}
	
	public boolean isRoom() {
		if(initial == WALKWAY_INITIAL) {
			return false;
		}
		return true;
	}
	
	public boolean isDoorway() {
		if(doorDirection != DoorDirection.NONE) {
			return(true);
		}
		return false;
	}

	public DoorDirection getDoorDirection() {
		return doorDirection;
	}

	public char getInitial() {
		return initial;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return column;
	}
}