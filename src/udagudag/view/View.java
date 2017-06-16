package udagudag.view;

import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

public class View extends JFrame {

	private final int width = 600;
	private final int height = 600;

	private LoadingPanel loadingPanel;
	private LogInPanel logInPanel;
	private SignUpPanel1 signUpPanel1;
	private SignUpPanel2 signUpPanel2;
	private MainPanel mainPanel;


	public View() {

		setTitle("UdagUdag");
		init();
		showLogInPanel();

		setResizable(false);
		pack();

		setLocationRelativeTo(null);
		setVisible(true);
	}

	private void init() {
		loadingPanel = new LoadingPanel(width, height);
		logInPanel = new LogInPanel(width, height);		
		signUpPanel1 = new SignUpPanel1(width,height);
		signUpPanel2 = new SignUpPanel2(width,height);
		mainPanel = new MainPanel(width, height);
	}

	public void showLoadingPanel() {
		remove(logInPanel);
		remove(signUpPanel1);
		remove(signUpPanel2);
		remove(mainPanel);
		add(loadingPanel);
		loadingPanel.revalidate();
		loadingPanel.repaint();
	}

	public void showLogInPanel() {
		remove(loadingPanel);
		remove(signUpPanel1);
		remove(signUpPanel2);
		remove(mainPanel);
		add(logInPanel);
		logInPanel.revalidate();
		logInPanel.repaint();
	}

	public void showSignUpPanel1() {
		remove(loadingPanel);
		remove(logInPanel);
		remove(signUpPanel2);
		remove(mainPanel);
		add(signUpPanel1);
		signUpPanel1.revalidate();
		signUpPanel1.repaint();
	}

	public void showSignUpPanel2() {
		remove(loadingPanel);
		remove(logInPanel);
		remove(signUpPanel1);
		remove(mainPanel);
		add(signUpPanel2);
		signUpPanel2.revalidate();
		signUpPanel2.repaint();
	}

	public void showMainPanel() {
		remove(loadingPanel);
		remove(logInPanel);
		remove(signUpPanel1);
		remove(signUpPanel2);
		add(mainPanel);
		mainPanel.revalidate();
		mainPanel.repaint();
	}

	public void addButtonsListener(ActionListener listener) {

		logInPanel.getLogInButton().addActionListener(listener);
		logInPanel.getSignUpButton().addActionListener(listener);

		signUpPanel1.getContinueButton().addActionListener(listener);
		signUpPanel1.getBackButton().addActionListener(listener);

		signUpPanel2.getSubmitButton().addActionListener(listener);
		signUpPanel2.getBackButton().addActionListener(listener);

		mainPanel.getSendButton().addActionListener(listener);
	}

	public void bindEnterActionToMessageArea(AbstractAction action) {
		mainPanel.getMessageArea().getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "doEnterAction");
		mainPanel.getMessageArea().getActionMap().put("doEnterAction", action);
	}

	public LogInPanel getLogInPanel() {
		return logInPanel;
	}

	public SignUpPanel1 getSignUpPanel1() {
		return signUpPanel1;
	}

	public SignUpPanel2 getSignUpPanel2() {
		return signUpPanel2;
	}

	public MainPanel getMainPanel() {
		return mainPanel;
	}

	public boolean askYesNoQuestion(String title, String question) {
		int choice = JOptionPane.showOptionDialog(null, question, title, JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE, null, null, null);
		if (choice == JOptionPane.YES_OPTION) {
			return true;
		} else {
			return false;
		}
	}

	public String askForIpAddress() {
		String ipAddress = JOptionPane.showInputDialog("Please enter the server IP.");
		if (ipAddress == null || ipAddress.isEmpty()) {
			ipAddress = "empty";
		}
		return ipAddress;
	}
}