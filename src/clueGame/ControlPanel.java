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
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;

public class ControlPanel extends JPanel {
	public JTextField rolltextField;
	public JButton nextPlayerButton;
	public JButton makeAccusationButton;
	public JTextField guessResultText;
	public JTextField guessText;
	public JTextField whoseTurnText;
	
	public ControlPanel() {
		setLayout(new GridLayout(0, 3));
		addWhoseTurnPanel();
		addButtons();
		addRollPanel();
		addGuessPanel();
		addGuessResultPanel();
	}
	
	public void addWhoseTurnPanel() {
		whoseTurnText = new JTextField(10);
		whoseTurnText.setHorizontalAlignment(SwingConstants.CENTER);
		JPanel whoseTurnPanel = new JPanel();
		whoseTurnPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Who's Turn?", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		whoseTurnPanel.setLayout(new BorderLayout(0, 0));
		whoseTurnText.setEditable(false);
		whoseTurnPanel.add(whoseTurnText, BorderLayout.NORTH);
		
		add(whoseTurnPanel);
	}
	
	public void addButtons() {
		nextPlayerButton = new JButton("Next Player");
		makeAccusationButton = new JButton("Make an Accusation");
		
		add(nextPlayerButton);
		add(makeAccusationButton);
	}
	
	public void addRollPanel() {
		JPanel rollPanel = new JPanel();
		rollPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Roll", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		add(rollPanel);
		GridBagLayout gbl_rollPanel = new GridBagLayout();
		gbl_rollPanel.columnWidths = new int[]{70, 20, 70, 0};
		gbl_rollPanel.rowHeights = new int[]{20, 0};
		gbl_rollPanel.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_rollPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		rollPanel.setLayout(gbl_rollPanel);
		
		rolltextField = new JTextField(1);
		rolltextField.setHorizontalAlignment(SwingConstants.CENTER);
		rolltextField.setPreferredSize(new Dimension(10, 20));
		rolltextField.setMaximumSize(new Dimension(40, 20));
		rolltextField.setFont(new Font("Tahoma", Font.BOLD, 14));
		rolltextField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		rollPanel.add(rolltextField, gbc_textField);
	}
	
	public void addGuessPanel() {
		guessText = new JTextField(10);
		guessText.setHorizontalAlignment(SwingConstants.CENTER);
		JPanel guessPanel = new JPanel();
		guessPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Guess", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		guessPanel.setLayout(new BorderLayout(0, 0));
		guessText.setEditable(false);
		guessPanel.add(guessText, BorderLayout.NORTH);
		
		add(guessPanel);
	}
	
	public void addGuessResultPanel() {
		guessResultText = new JTextField(10);
		guessResultText.setHorizontalAlignment(SwingConstants.CENTER);
		JPanel guessResultPanel = new JPanel();
		guessResultPanel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Guess Result", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		guessResultPanel.setLayout(new BorderLayout(0, 0));
		guessResultText.setEditable(false);
		guessResultPanel.add(guessResultText, BorderLayout.NORTH);
		
		add(guessResultPanel);
	}
	

}
