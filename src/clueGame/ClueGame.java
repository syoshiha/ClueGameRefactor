package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClueGame {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new GridLayout(0, 3));
		frame.add(controlPanel, BorderLayout.CENTER);
		
		// Labels
		JLabel whoseTurnLabel = new JLabel();
		JLabel rollLabel = new JLabel();
		JLabel guessLabel = new JLabel();
		JLabel guessResultLabel = new JLabel();
		whoseTurnLabel.setText("Whose turn?");
		rollLabel.setText("Die roll");
		guessLabel.setText("Guess");
		guessResultLabel.setText("Guess Result");
		
		// Text Fields
		JTextField whoseTurnText = new JTextField();
		JTextField rollText = new JTextField();
		JTextField guessText = new JTextField();
		JTextField guessResultText = new JTextField();
		//whoseTurnText.setEditable(false);
		rollText.setEditable(false);
		guessText.setEditable(false);
		guessResultText.setEditable(false);
		
		// Buttons
		JButton nextPlayerButton = new JButton();
		JButton makeAccusationButton = new JButton();
		
		// Panels
		JPanel whoseTurnPanel = new JPanel();
		JPanel rollPanel = new JPanel();
		JPanel guessPanel = new JPanel();
		JPanel guessResultPanel = new JPanel();
		whoseTurnPanel.add(whoseTurnLabel);
		whoseTurnPanel.add(whoseTurnText);
		rollPanel.add(rollLabel);
		rollPanel.add(rollText);
		guessPanel.add(guessLabel);
		guessPanel.add(guessText);
		guessResultPanel.add(guessResultLabel);
		guessResultPanel.add(guessResultText);
		
		//
		//
		//
		//
		
		
		frame.setVisible(true);
	}
}
