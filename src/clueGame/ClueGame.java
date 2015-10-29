package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ClueGame {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(700, 150);
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
		JTextField whoseTurnText = new JTextField(10);
		JTextField rollText = new JTextField(10);
		JTextField guessText = new JTextField(10);
		JTextField guessResultText = new JTextField(10);
		whoseTurnText.setEditable(false);
		rollText.setEditable(false);
		guessText.setEditable(false);
		guessResultText.setEditable(false);
		
		// Buttons
		JButton nextPlayerButton = new JButton("Next Player");
		JButton makeAccusationButton = new JButton("Make an Accusation");
		
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
		
		controlPanel.add(whoseTurnPanel);
		controlPanel.add(nextPlayerButton);
		controlPanel.add(makeAccusationButton);
		controlPanel.add(rollPanel);
		controlPanel.add(guessPanel);
		controlPanel.add(guessResultPanel);
		
		frame.setVisible(true);
	}
}
