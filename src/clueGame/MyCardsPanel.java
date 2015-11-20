package clueGame;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JComboBox;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JLabel;

public class MyCardsPanel extends JPanel {
	JLabel weapons;
	JLabel rooms;
	JLabel people;
	/**
	 * Create the panel.
	 */
	public MyCardsPanel() {
		setPreferredSize(new Dimension(140, 661));
		setMinimumSize(new Dimension(100, 10));
		setFont(new Font("Tahoma", Font.PLAIN, 12));
		setBorder(new TitledBorder(null, "My Cards", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		people = new JLabel();
		people.setPreferredSize(new Dimension(28, 90));
		people.setMinimumSize(new Dimension(28, 60));
		people.setBorder(new TitledBorder(null, "People", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.anchor = GridBagConstraints.NORTH;
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 0;
		add(people, gbc_comboBox);
		
		rooms = new JLabel();
		rooms.setPreferredSize(new Dimension(28, 90));
		rooms.setMinimumSize(new Dimension(28, 60));
		rooms.setBorder(new TitledBorder(null, "Rooms", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.anchor = GridBagConstraints.NORTH;
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 0;
		gbc_comboBox_1.gridy = 1;
		add(rooms, gbc_comboBox_1);
		
		weapons = new JLabel();
		weapons.setPreferredSize(new Dimension(28, 90));
		weapons.setMinimumSize(new Dimension(28, 60));
		weapons.setBorder(new TitledBorder(null, "Weapons", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.anchor = GridBagConstraints.NORTH;
		gbc_comboBox_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_2.gridx = 0;
		gbc_comboBox_2.gridy = 2;
		add(weapons, gbc_comboBox_2);

	}

}
