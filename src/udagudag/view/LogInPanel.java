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

public class LogInPanel extends JLabel {

	private JPanel centeredPanel;
	
	private JTextField emailField;
	private JPasswordField passwordField;
	
	private Image logInImage;
	private Image logInPressedImage;
	private Image signUpImage;
	private Image signUpPressedImage;
	
	private JButton logInButton;
	private JButton signUpButton;

	public LogInPanel(int width, int height) {
		
		setPreferredSize(new Dimension(width, height));
		setOpaque(true);
		setIcon(new ImageIcon(Main.class.getResource("/background.png")));
		setLayout(new GridBagLayout());
		
		centeredPanel = new JPanel();
		centeredPanel.setPreferredSize(new Dimension(150,185));
		centeredPanel.setOpaque(false);
		centeredPanel.setLayout(new GridBagLayout());

		emailField = new JTextField("Email");
		emailField.setFont(new Font("Gabriola",Font.BOLD,22));
		emailField.setForeground(Color.white);
		emailField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		emailField.setOpaque(false);
		emailField.selectAll();
		emailField.setHorizontalAlignment(JTextField.CENTER);
		emailField.setPreferredSize(new Dimension(150,35));

		passwordField = new JPasswordField("Password");
		passwordField.setFont(new Font("Gabriola",Font.BOLD,22));
		passwordField.setForeground(Color.white);
		passwordField.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		passwordField.setOpaque(false);
		passwordField.selectAll();
		passwordField.setHorizontalAlignment(JTextField.CENTER);
		passwordField.setPreferredSize(new Dimension(150,35));

		try {
			logInImage = ImageIO.read(getClass().getResource("/logIn.png"));
			logInPressedImage = ImageIO.read(getClass().getResource("/logInPressed.png"));
			signUpImage = ImageIO.read(getClass().getResource("/signUp.png"));
			signUpPressedImage = ImageIO.read(getClass().getResource("/signUpPressed.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}		

		logInButton = new JButton();
		logInButton.setPreferredSize(new Dimension(150,35));
		logInButton.setActionCommand("LogIn");
		logInButton.putClientProperty("Parent", "LogInPanel");	
		logInButton.setBorder(null); // Remove border-graphics.
		logInButton.setContentAreaFilled(false);	// Remove default graphics from the button.
		logInButton.setFocusPainted(false); // Remove the focus-indicating dotted square when focused (optional).
		logInButton.setIcon(new ImageIcon(logInImage));
		logInButton.setPressedIcon(new ImageIcon(logInPressedImage));

		signUpButton = new JButton();
		signUpButton.setPreferredSize(new Dimension(150,35));
		signUpButton.setActionCommand("SignUp");
		signUpButton.putClientProperty("Parent", "LogInPanel");	
		signUpButton.setBorder(null); // Remove border-graphics.
		signUpButton.setContentAreaFilled(false);	// Remove default graphics from the button
		signUpButton.setFocusPainted(false); // Remove the focus-indicating dotted square when focused (optional)
		signUpButton.setIcon(new ImageIcon(signUpImage));
		signUpButton.setPressedIcon(new ImageIcon(signUpPressedImage));
		
		GridBagConstraints c1 = new GridBagConstraints();
		
		c1.gridy = 0;
		c1.insets = new Insets(0,0,15,0);
		centeredPanel.add(emailField,c1);
		
		c1.gridy = 1;
		c1.insets = new Insets(0,0,15,0);
		centeredPanel.add(passwordField, c1);
		
		c1.gridy = 2;
		c1.insets = new Insets(0,0,15,0);
		centeredPanel.add(logInButton, c1);
		
		c1.gridy = 3;
		c1.insets = new Insets(0,0,0,0);
		centeredPanel.add(signUpButton, c1);	
		
		add(centeredPanel,c1);		
	}

	public JTextField getEmailField() {
		return emailField;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}
	
	public JButton getLogInButton() {
		return logInButton;
	}
	
	public JButton getSignUpButton() {
		return signUpButton;
	}
}