package clueGame;

public class BoardCell {
	private int row;
	private int column;
	private char initial;
	
	public boolean isWalkway() {
		return false;
	}
	
	public boolean isRoom() {
		return false;
	}
	
	public boolean isDoorway() {
		return false;
	}

	public DoorDirection getDoorDirection() {
		return DoorDirection.NONE;
	}

	public char getInitial() {
		return initial;
	}
}