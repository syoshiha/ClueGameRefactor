package clueGame;

public class BoardCell {
	private int row;
	private int column;
	private char initial;
	private DoorDirection doorDirection;
	
	public BoardCell(int row, int column, char initial, DoorDirection doorDirection) {
		this.row = row;
		this.column = column;
		this.initial = initial;
		this.doorDirection = doorDirection;
	}
	
	public boolean isWalkway() {
		return false;
	}
	
	public boolean isRoom() {
		return false;
	}
	
	public boolean isDoorway() {
		if(doorDirection != DoorDirection.NONE) {
			return(true);
		}
		return false;
	}

	public DoorDirection getDoorDirection() {
		return DoorDirection.NONE;
	}

	public char getInitial() {
		return initial;
	}
}