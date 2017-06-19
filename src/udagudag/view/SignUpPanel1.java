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

public class SignUpPanel1 extends JLabel {

	private JPanel centeredPanel;
	private JTextField emailField;
	private JTextField birthdayField;
	private JPasswordField passwordField;
	private JPasswordField repeatPasswordField;
	private Image continueImage;
	private Image continuePressedImage;
	private Image backImage;
	private Image backPressedImage;
	private JButton continueButton;
	private JButton backButton;

	public SignUpPanel1(int width, int height) {

		setPreferredSize(new Dimension(width, height));
		setOpaque(true);
		setIcon(new ImageIcon(Main.class.getResource("/images/background.png")));
		setLayout(new GridBagLayout());

		centeredPanel = new JPanel();
		centeredPanel.setPreferredSize(new Dimension(150, 335));
		centeredPanel.setOpaque(false);
		centeredPanel.setLayout(new GridBagLayout());

		emailField = new JTextField("Email");
		emailField.setFont(new Font("Gabriola", Font.BOLD, 22));
		emailField.setForeground(Color.white);
		emailField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		emailField.setOpaque(false);
		emailField.selectAll();
		emailField.setHorizontalAlignment(JTextField.CENTER);
		emailField.setPreferredSize(new Dimension(150, 35));

		birthdayField = new JTextField("Birthday");
		birthdayField.setFont(new Font("Gabriola", Font.BOLD, 22));
		birthdayField.setForeground(Color.white);
		birthdayField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		birthdayField.setOpaque(false);
		birthdayField.selectAll();
		birthdayField.setHorizontalAlignment(JTextField.CENTER);
		birthdayField.setPreferredSize(new Dimension(150, 35));

		passwordField = new JPasswordField("Password");
		passwordField.setFont(new Font("Gabriola", Font.BOLD, 22));
		passwordField.setForeground(Color.white);
		passwordField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		passwordField.setOpaque(false);
		passwordField.selectAll();
		passwordField.setHorizontalAlignment(JTextField.CENTER);
		passwordField.setPreferredSize(new Dimension(150, 35));

		repeatPasswordField = new JPasswordField("Password");
		repeatPasswordField.setFont(new Font("Gabriola", Font.BOLD, 22));
		repeatPasswordField.setForeground(Color.white);
		repeatPasswordField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		repeatPasswordField.setOpaque(false);
		repeatPasswordField.selectAll();
		repeatPasswordField.setHorizontalAlignment(JTextField.CENTER);
		repeatPasswordField.setPreferredSize(new Dimension(150, 35));

		try {
			continueImage = ImageIO.read(getClass().getResource("/images/continue.png"));
			continuePressedImage = ImageIO.read(getClass().getResource("/images/continuePressed.png"));
			backImage = ImageIO.read(getClass().getResource("/images/back.png"));
			backPressedImage = ImageIO.read(getClass().getResource("/images/backPressed.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		continueButton = new JButton();
		continueButton.setPreferredSize(new Dimension(150, 35));
		continueButton.setActionCommand("Continue");
		continueButton.putClientProperty("Parent", "SignUpPanel1");
		continueButton.setBorder(null); // Remove border-graphics.
		continueButton.setContentAreaFilled(false); // Remove default graphics from the button.
		continueButton.setFocusPainted(false); // Remove the focus-indicating dotted square when focused (optional).
		continueButton.setIcon(new ImageIcon(continueImage));
		continueButton.setPressedIcon(new ImageIcon(continuePressedImage));

		backButton = new JButton();
		backButton.setPreferredSize(new Dimension(150, 35));
		backButton.setActionCommand("Back");
		backButton.putClientProperty("Parent", "SignUpPanel1");
		backButton.setBorder(null); // Remove border-graphics.
		backButton.setContentAreaFilled(false); // Remove default graphics from the button.
		backButton.setFocusPainted(false); // Remove the focus-indicating dotted square when focused (optional).
		backButton.setIcon(new ImageIcon(backImage));
		backButton.setPressedIcon(new ImageIcon(backPressedImage));

		GridBagConstraints c1 = new GridBagConstraints();

		c1.gridy = 0;
		c1.insets = new Insets(0, 0, 15, 0);
		centeredPanel.add(emailField, c1);

		c1.gridy = 1;
		c1.insets = new Insets(0, 0, 15, 0);
		centeredPanel.add(birthdayField, c1);

		c1.gridy = 2;
		c1.insets = new Insets(0, 0, 15, 0);
		centeredPanel.add(passwordField, c1);

		c1.gridy = 3;
		c1.insets = new Insets(0, 0, 15, 0);
		centeredPanel.add(repeatPasswordField, c1);

		c1.gridy = 4;
		c1.insets = new Insets(0, 0, 15, 0);
		centeredPanel.add(continueButton, c1);

		c1.gridy = 5;
		c1.insets = new Insets(0, 0, 0, 0);
		centeredPanel.add(backButton, c1);

		add(centeredPanel, c1);
	}

	public JTextField getEmailField() {
		return emailField;
	}

	public JTextField getBirthdayField() {
		return birthdayField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public JPasswordField getRepeatPasswordField() {
		return repeatPasswordField;
	}

	public JButton getContinueButton() {
		return continueButton;
	}

	public JButton getBackButton() {
		return backButton;
	}
}