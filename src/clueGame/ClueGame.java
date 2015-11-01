package clueGame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class ClueGame extends JFrame {
	
	JPanel boardPanel;
	private int width;
	private int height;
	
	// Constructor that creates the game's GUI
	public ClueGame() {
		
		// Set up board to be drawn
		setupBoard();
		
		// Set up Menu
		setupMenu();
		
		// Set up window with the same width as the board, and more height than the board.
		// This is so the menu can fit at the top and the control panel will fit on the
		// bottom of the screen, and no extra space will be on the sides.
		setTitle("Clue!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int heightToAdd = 20; // extra height (in pixels) needed to display the menubar, controlpanel, etc...
							  // For now this is set to a low number because we only need to fit the menubar.
		width = ((Board)boardPanel).getNumColumns() * BoardCell.DIMENSIONS + 1;
		height = ((Board)boardPanel).getNumRows() * BoardCell.DIMENSIONS + 1;
		getContentPane().setPreferredSize(new Dimension(width, height + heightToAdd));
		//add(new ControlPanel(), BorderLayout.SOUTH);
		pack();
	}
	
	public void setupMenu() {
		JMenu menu = new JMenu("File");
		JMenuItem detectiveSheet = new JMenuItem("Detective Sheet");
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ExitListener());
		
		menu.add(detectiveSheet);
		menu.add(exit);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menu);
		add(menuBar, BorderLayout.NORTH);
	}
	
	private class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
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
