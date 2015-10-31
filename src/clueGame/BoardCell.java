package clueGame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class BoardCell {

	private int row;
	private int column;
	private char initial;
	private DoorDirection doorDirection;
	private boolean hasName;
	
	public static final int DIMENSIONS = 30;
	public static final char WALKWAY_INITIAL = 'W';
	//public static final char CLOSET_INITIAL = 'X';
	
	public BoardCell() {
		super();
	}
	
	public void draw(Graphics g) {
		
		// Draw appropriately colored square
		if (isWalkway()) {
			g.setColor(Color.YELLOW);
		} else {
			g.setColor(new Color(180, 180, 180));
		}
		g.fillRect(column*DIMENSIONS, row*DIMENSIONS, DIMENSIONS, DIMENSIONS);
		if (!isRoom()) g.setColor(Color.BLACK);
		g.drawRect(column*DIMENSIONS, row*DIMENSIONS, DIMENSIONS, DIMENSIONS);
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
	
	public void setName(boolean hasName) {
		this.hasName = hasName;
	}
	
	public boolean hasName() {
		return hasName;
	}
}