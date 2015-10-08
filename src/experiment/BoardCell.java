package experiment;

public class BoardCell {
	private int row, column;
	
	public BoardCell(int i, int j) {
		row = i;
		column = j;
	}
	
	@Override
	public String toString() {
		return "[" + row + ", " + column + "]";
	}
}
