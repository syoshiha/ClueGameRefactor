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

public class ControlPanel extends JPanel {
	
	public ControlPanel() {
		setLayout(new GridLayout(0, 3));
		addWhoseTurnPanel();
		addButtons();
		addRollPanel();
		addGuessPanel();
		addGuessResultPanel();
	}
	
	public void addWhoseTurnPanel() {
		JLabel whoseTurnLabel = new JLabel();
		JTextField whoseTurnText = new JTextField(10);
		JPanel whoseTurnPanel = new JPanel();
		
		whoseTurnLabel.setText("Whose turn?");
		whoseTurnText.setEditable(false);
		whoseTurnPanel.add(whoseTurnLabel);
		whoseTurnPanel.add(whoseTurnText);
		
		add(whoseTurnPanel);
	}
	
	public void addButtons() {
		JButton nextPlayerButton = new JButton("Next Player");
		JButton makeAccusationButton = new JButton("Make an Accusation");
		
		add(nextPlayerButton);
		add(makeAccusationButton);
	}
	
	public void addRollPanel() {
		JLabel rollLabel = new JLabel();
		JTextField rollText = new JTextField(10);
		JPanel rollPanel = new JPanel();
		
		rollLabel.setText("Die roll");
		rollText.setEditable(false);
		rollPanel.add(rollLabel);
		rollPanel.add(rollText);
		
		add(rollPanel);
	}
	
	public void addGuessPanel() {
		JLabel guessLabel = new JLabel();
		JTextField guessText = new JTextField(10);
		JPanel guessPanel = new JPanel();
		
		guessLabel.setText("Guess");
		guessText.setEditable(false);
		guessPanel.add(guessLabel);
		guessPanel.add(guessText);
		
		add(guessPanel);
	}
	
	public void addGuessResultPanel() {
		JLabel guessResultLabel = new JLabel();
		JTextField guessResultText = new JTextField(10);
		JPanel guessResultPanel = new JPanel();
		
		guessResultLabel.setText("Guess Result");
		guessResultText.setEditable(false);
		guessResultPanel.add(guessResultLabel);
		guessResultPanel.add(guessResultText);
		
		add(guessResultPanel);
	}
	
	public static void main(String[] args) {
		
		// Set up JFrame window
		JFrame frame = new JFrame();
		ControlPanel controlPanel = new ControlPanel();
		frame.setSize(700, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(controlPanel, BorderLayout.CENTER);
		
		frame.setVisible(true);
	}
}
