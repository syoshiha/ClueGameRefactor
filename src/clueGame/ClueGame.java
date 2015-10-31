package clueGame;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClueGame extends JFrame {
	
	JPanel boardPanel;
	private int width;
	private int height;
	
	// Constructor that creates the game's GUI
	public ClueGame() {
		
		// Set up board to be drawn
		setupBoard();
		
		// Set up window
		setTitle("Clue!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		width = ((Board)boardPanel).getNumColumns() * BoardCell.DIMENSIONS + 1;
		height = ((Board)boardPanel).getNumRows() * BoardCell.DIMENSIONS + 1;
		getContentPane().setPreferredSize(new Dimension(width, height));
		pack();
	}
	
	
	
	public void setupBoard() {
		boardPanel = new Board();
		((Board)boardPanel).initialize();
		add(boardPanel, BorderLayout.CENTER);
	}
	
	public static void main(String[] args) {
		ClueGame game = new ClueGame();
		game.setVisible(true);
	}
}
