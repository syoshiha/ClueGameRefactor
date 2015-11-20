package clueGame;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.Window.Type;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClueGame extends JFrame {
	
	static JPanel boardPanel;
	private int boardWidth;
	private int boardHeight;
	private static int myCardsPanelWidth=140;
	private static int controlPanelHeight=88;
	private static int meunBarHeight=21;
	static JOptionPane splashScreen;
	static JOptionPane errorScreen;
	DetectiveSheet sheet;
	static ControlPanel controlPanel;
	MyCardsPanel myCardsPanel;
	static boolean pickedLocation =false;
	private static ArrayDeque<Player> playerRotation = new ArrayDeque<Player>();
	
	// Constructor that creates the game's GUI
	public ClueGame() {
		
		// Set up board to be drawn
		setupBoard();
		
		// Set up Menu
		setupMenu();
		
		// Create a detective sheet
		sheet = new DetectiveSheet(((Board)boardPanel));
		
		// Set up window with the same width as the board, and more height than the board.
		// This is so the menu can fit at the top and the control panel will fit on the
		// bottom of the screen, and no extra space will be on the sides.
		setTitle("Clue!");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// add board to content plane
		boardWidth = ((Board)boardPanel).getNumColumns() * BoardCell.DIMENSIONS + 1;
		boardHeight = ((Board)boardPanel).getNumRows() * BoardCell.DIMENSIONS + 1;
		getContentPane().setPreferredSize(new Dimension(boardWidth + myCardsPanelWidth, boardHeight+ controlPanelHeight + meunBarHeight));
		
		//add control panel to content plane
		controlPanel = new ControlPanel();
		getContentPane().add(controlPanel, BorderLayout.SOUTH);
		
		// add player cards to content plane
		myCardsPanel = new MyCardsPanel();
		myCardsPanel.setPreferredSize(new Dimension(myCardsPanelWidth, boardHeight));
		myCardsPanel.setOpaque(false);
		getContentPane().add(myCardsPanel, BorderLayout.EAST);
		addPlayeCards();
		pack();
	}
	
	public void setupMenu() {
		JMenu menu = new JMenu("File");
		JMenuItem detectiveSheet = new JMenuItem("Detective Sheet");
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ExitListener());
		detectiveSheet.addActionListener(new SheetListener());
		
		menu.add(detectiveSheet);
		menu.add(exit);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(menu);
		getContentPane().add(menuBar, BorderLayout.NORTH);
	}
	
	private class ExitListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	
	private class SheetListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			sheet.setVisible(true);
		}
	}
	
	private void addPlayers(){
		playerRotation.add(((Board)boardPanel).getHumanPlayer());
		
		Set<ComputerPlayer> tmp = new HashSet<ComputerPlayer>();
		tmp = ((Board)boardPanel). getCompPlayers();
		for(ComputerPlayer c: tmp){
			playerRotation.add(c);
		}
	}
	
	public void setupBoard() {
		boardPanel = new Board();
		boardPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = (e.getX() / 30);
				int y = (e.getY() / 30);
				if (((Board) boardPanel).getHumanPlayer().getName() == playerRotation.peek().getName()) {
					//checks to see if location clicked is vaild
					if (((Board) boardPanel).validMove(y, x)) {
						((Board) boardPanel).getHumanPlayer().setRow(y);
						((Board) boardPanel).getHumanPlayer().setCol(x);
						((Board) boardPanel).repaint();
						pickedLocation = true;
					}
					// when player selects an invalid location an error is displayed
					else{
						errorScreen = new JOptionPane();
						JOptionPane.showMessageDialog(errorScreen,
							    "Select a Valid location",
							    "Invalid Location!",
							    JOptionPane.WARNING_MESSAGE);
					}
						
				}
				

			}


		});
		boardPanel.setPreferredSize(new Dimension(750, 660));
		((Board)boardPanel).initialize();
		addPlayers();
		getContentPane().add(boardPanel, BorderLayout.CENTER);
	}
	
	public void addPlayeCards(){
		Set<Card> cards = new HashSet<Card>();
		cards = ((Board) boardPanel).getHumanPlayer().getMyCards();
		String rooms="";
		String weapons="";
		String person="";
		
		for(Card c: cards){
			if(c.getCardType() == CardType.ROOM){
				myCardsPanel.rooms.setText(c.getCardName());
			}
			else if(c.getCardType() == CardType.PERSON){
				myCardsPanel.people.setText(c.getCardName());
			}
			else if(c.getCardType() == CardType.WEAPON){
			}	myCardsPanel.weapons.setText(c.getCardName());
			
		}

	}
	
	
	public static void splashScreen(){
		splashScreen = new JOptionPane();
		JOptionPane.showMessageDialog(splashScreen, "You are "+ ((Board)boardPanel).getHumanPlayer().getName()+", press NEXT PLAYER to begin play ","Welcome to Clue!", JOptionPane.INFORMATION_MESSAGE);
	}
	public static int roll(){
		Random die1 = new Random();
		Random die2 = new Random();
			
		return die1.nextInt(6) + die2.nextInt(6) + 2;
	}
	
	
	public static void main(String[] args) {
		ClueGame game = new ClueGame();
		game.setVisible(true);
		splashScreen();
		boolean gameFinished = false;
		
		
		while(!gameFinished){
			if (((Board) boardPanel).getHumanPlayer().getName() == playerRotation.peek().getName()) {
				int roll = roll();
				controlPanel.whoseTurnText.setText(playerRotation.peek().getName());
				controlPanel.rolltextField.setText(((Integer) roll).toString());
				((Board) boardPanel).highlight(playerRotation.peek().getRow(), playerRotation.peek().getCol(), roll);
				pickedLocation = false;
				while (!pickedLocation) {
				}
				((Board) boardPanel).unHighlight();
				
			}
			else {
				BoardCell moveLocation =new BoardCell();
				int roll = roll();
				controlPanel.whoseTurnText.setText(playerRotation.peek().getName());
				controlPanel.rolltextField.setText(((Integer) roll).toString());
				try {
				    Thread.sleep(1000);                 //1000 milliseconds is one second.
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
				Player c = new ComputerPlayer();
				c= playerRotation.peek();
				moveLocation = ((ComputerPlayer)c).pickLocation(((Board)boardPanel).returnTargets(c.getRow(), c.getCol(), roll));
				playerRotation.peek().setRow(moveLocation.getRow());
				playerRotation.peek().setCol(moveLocation.getCol());
				((Board) boardPanel).repaint();
				
			}
			playerRotation.offerLast(playerRotation.removeFirst());
		}
	}
}
