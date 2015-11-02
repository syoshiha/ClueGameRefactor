package clueGame;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class DetectiveSheet extends JDialog {
	
	Board board;
	
	public DetectiveSheet(Board board) {
		
		this.board = board;
		
		setLayout(new GridLayout(3, 2));
		setTitle("Detective Sheet");
		setSize(500, 750);
		createPeoplePanels();
		createRoomsPanels();
		createWeaponsPanels();
	}
	
	public void createPeoplePanels() {
		
		JPanel peopleCheckboxesPanel = new JPanel();
		JPanel personGuessPanel = new JPanel();
		JComboBox peopleCombo = new JComboBox();
		peopleCombo.addItem("Unsure");
		peopleCheckboxesPanel.add(new JCheckBox(board.getHumanPlayer().getName()));
		for (Player player : board.getCompPlayers()) {
			peopleCheckboxesPanel.add(new JCheckBox(player.getName()));
			peopleCombo.addItem(player.getName());
		}
		peopleCheckboxesPanel.setBorder(new TitledBorder(new EtchedBorder(), "People"));
		personGuessPanel.setBorder(new TitledBorder(new EtchedBorder(), "Person Guess"));
		personGuessPanel.add(peopleCombo);
		add(peopleCheckboxesPanel);
		add(personGuessPanel);
	}
	
	public void createRoomsPanels() {
		JPanel roomsCheckboxPanel = new JPanel();
		JPanel roomGuessPanel = new JPanel();
		JComboBox roomsCombo = new JComboBox();
		roomsCombo.addItem("Unsure");
		for (Character key : board.getRooms().keySet()) {
			roomsCheckboxPanel.add(new JCheckBox(board.getRooms().get(key)));
			roomsCombo.addItem(board.getRooms().get(key));
		}
		roomsCheckboxPanel.setBorder(new TitledBorder(new EtchedBorder(), "Rooms"));
		roomGuessPanel.setBorder(new TitledBorder(new EtchedBorder(), "Room Guess"));
		roomGuessPanel.add(roomsCombo);
		add(roomsCheckboxPanel);
		add(roomGuessPanel);
	}
	
	public void createWeaponsPanels() {
		JPanel weaponsCheckboxPanel = new JPanel();
		JPanel weaponGuessPanel = new JPanel();
		JComboBox weaponsCombo = new JComboBox();
		weaponsCombo.addItem("Unsure");
		for (Card card : board.getDeck()) {
			if (card.getCardType() == CardType.WEAPON) {
				weaponsCheckboxPanel.add(new JCheckBox(card.getCardName()));
				weaponsCombo.addItem(card.getCardName());
			}
		}
		weaponsCheckboxPanel.setBorder(new TitledBorder(new EtchedBorder(), "Weapons"));
		weaponGuessPanel.setBorder(new TitledBorder(new EtchedBorder(), "Weapon Guess"));
		weaponGuessPanel.add(weaponsCombo);
		add(weaponsCheckboxPanel);
		add(weaponGuessPanel);
	}
	/*
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setSize(500, 750);
		Board board = new Board();
		board.initialize();
		f.add(new DetectiveSheet(board), BorderLayout.CENTER);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}*/
}
