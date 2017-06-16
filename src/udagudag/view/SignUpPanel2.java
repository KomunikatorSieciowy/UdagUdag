package udagudag.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import udagudag.Main;

public class SignUpPanel2 extends JLabel {

	private JPanel centeredPanel;
	
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField placeField;
	private JTextField stateField;
	
	private Image submitImage;
	private Image submitPressedImage;
	private Image backImage;
	private Image backPressedImage;
	
	private JButton submitButton;
	private JButton backButton;
	
	public SignUpPanel2(int width, int height) {
		
		setPreferredSize(new Dimension(width, height));
		setOpaque(true);
		setIcon(new ImageIcon(Main.class.getResource("/background.png")));
		setLayout(new GridBagLayout());
		
		centeredPanel = new JPanel();
		centeredPanel.setPreferredSize(new Dimension(150,335));
		centeredPanel.setOpaque(false);
		centeredPanel.setLayout(new GridBagLayout());

		firstNameField = new JTextField("First name");
		firstNameField.setFont(new Font("Gabriola",Font.BOLD,22));
		firstNameField.setForeground(Color.white);
		firstNameField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		firstNameField.setOpaque(false);
		firstNameField.selectAll();
		firstNameField.setHorizontalAlignment(JTextField.CENTER);
		firstNameField.setPreferredSize(new Dimension(150,35));

		lastNameField = new JTextField("Last name");
		lastNameField.setFont(new Font("Gabriola",Font.BOLD,22));
		lastNameField.setForeground(Color.white);
		lastNameField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		lastNameField.setOpaque(false);
		lastNameField.selectAll();
		lastNameField.setHorizontalAlignment(JTextField.CENTER);
		lastNameField.setPreferredSize(new Dimension(150,35));

		placeField = new JTextField("Place");
		placeField.setFont(new Font("Gabriola",Font.BOLD,22));
		placeField.setForeground(Color.white);
		placeField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		placeField.setOpaque(false);
		placeField.selectAll();
		placeField.setHorizontalAlignment(JTextField.CENTER);
		placeField.setPreferredSize(new Dimension(150,35));

		stateField = new JTextField("State");
		stateField.setFont(new Font("Gabriola",Font.BOLD,22));
		stateField.setForeground(Color.white);
		stateField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		stateField.setOpaque(false);
		stateField.selectAll();
		stateField.setHorizontalAlignment(JTextField.CENTER);
		stateField.setPreferredSize(new Dimension(150,35));

		try {
			submitImage = ImageIO.read(getClass().getResource("/submit.png"));
			submitPressedImage = ImageIO.read(getClass().getResource("/submitPressed.png"));
			backImage = ImageIO.read(getClass().getResource("/back.png"));
			backPressedImage = ImageIO.read(getClass().getResource("/backPressed.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}		

		submitButton = new JButton();
		submitButton.setPreferredSize(new Dimension(150,35));
		submitButton.setActionCommand("Submit");
		submitButton.putClientProperty("Parent", "SignUpPanel2");	
		submitButton.setBorder(null); // Remove border-graphics.
		submitButton.setContentAreaFilled(false); // Remove default graphics from the button.
		submitButton.setFocusPainted(false); // Remove the focus-indicating dotted square when focused (optional).
		submitButton.setIcon(new ImageIcon(submitImage));
		submitButton.setPressedIcon(new ImageIcon(submitPressedImage));

		backButton = new JButton();
		backButton.setPreferredSize(new Dimension(150,35));
		backButton.setActionCommand("Back");
		backButton.putClientProperty("Parent", "SignUpPanel2");	
		backButton.setBorder(null); // Remove border-graphics.
		backButton.setContentAreaFilled(false);	// Remove default graphics from the button.
		backButton.setFocusPainted(false); // Remove the focus-indicating dotted square when focused (optional).
		backButton.setIcon(new ImageIcon(backImage));
		backButton.setPressedIcon(new ImageIcon(backPressedImage));
		
		GridBagConstraints c1 = new GridBagConstraints();
		
		c1.gridy = 0;
		c1.insets = new Insets(0,0,15,0);
		centeredPanel.add(firstNameField,c1);
		
		c1.gridy = 1;
		c1.insets = new Insets(0,0,15,0);
		centeredPanel.add(lastNameField, c1);
		
		c1.gridy = 2;
		c1.insets = new Insets(0,0,15,0);
		centeredPanel.add(placeField, c1);
		
		c1.gridy = 3;
		c1.insets = new Insets(0,0,15,0);
		centeredPanel.add(stateField, c1);
		
		c1.gridy = 4;
		c1.insets = new Insets(0,0,15,0);
		centeredPanel.add(submitButton, c1);
		
		c1.gridy = 5;
		c1.insets = new Insets(0,0,0,0);
		centeredPanel.add(backButton, c1);	
		
		add(centeredPanel,c1);
	}

	public JTextField getFirstNameField() {
		return firstNameField;
	}

	public JTextField getLastNameField() {
		return lastNameField;
	}

	public JTextField getPlaceField() {
		return placeField;
	}

	public JTextField getStateField() {
		return stateField;
	}

	public JButton getSubmitButton() {
		return submitButton;
	}
	
	public JButton getBackButton() {
		return backButton;
	}
}