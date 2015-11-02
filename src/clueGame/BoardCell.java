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
			g.setColor(Color.BLACK);
		} else {
			g.setColor(new Color(80, 80, 80));
		}
		g.fillRect(column*DIMENSIONS, row*DIMENSIONS, DIMENSIONS, DIMENSIONS);
		if (!isRoom()) g.setColor(Color.GRAY);
		g.drawRect(column*DIMENSIONS, row*DIMENSIONS, DIMENSIONS, DIMENSIONS);
		
		// Draw the door
		g.setColor(new Color(0, 255, 0));
		int doorWidthFactor = 6;
		if (doorDirection == DoorDirection.UP) {
			g.fillRect(column*DIMENSIONS, row*DIMENSIONS, DIMENSIONS, DIMENSIONS/doorWidthFactor);
		} else if (doorDirection == DoorDirection.RIGHT) {
			g.fillRect(column*DIMENSIONS + DIMENSIONS - DIMENSIONS/doorWidthFactor, row*DIMENSIONS, DIMENSIONS/doorWidthFactor, DIMENSIONS);
		} else if (doorDirection == DoorDirection.DOWN) {
			g.fillRect(column*DIMENSIONS, row*DIMENSIONS + DIMENSIONS - DIMENSIONS/doorWidthFactor, DIMENSIONS, DIMENSIONS/5);
		} else if (doorDirection == DoorDirection.LEFT) {
			g.fillRect(column*DIMENSIONS, row*DIMENSIONS, DIMENSIONS/doorWidthFactor, DIMENSIONS);
		}
	}
	
	public void drawName(Graphics g) {
		if (hasName) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Verdana", Font.PLAIN, 12));
			g.drawString(Board.rooms.get(initial),
					column*BoardCell.DIMENSIONS + BoardCell.DIMENSIONS/2,
					row*BoardCell.DIMENSIONS + 3*BoardCell.DIMENSIONS/4);
		}
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