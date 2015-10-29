package clueGame;

import java.awt.GridLayout;

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
		
		JLabel whoseTurnLabel = new JLabel();
		JLabel rollLabel = new JLabel();
		JLabel guessLabel = new JLabel();
		JLabel guessResultLabel = new JLabel();
		whoseTurnLabel.setText("Whose turn?");
		rollLabel.setText("Die roll");
		guessLabel.setText("Guess");
		guessResultLabel.setText("Guess Result");
		
		JTextField whoseTurnText = new JTextField();
		JTextField rollText = new JTextField();
		JTextField guessText = new JTextField();
		JTextField guessResultText = new JTextField();
		
		
		
		
		
		
		frame.setVisible(true);
	}
}
