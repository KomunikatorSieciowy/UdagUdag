package udagudag.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JTextArea;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import udagudag.controller.backcommand.BackCommand;
import udagudag.controller.backcommand.LogInBackCommand;
import udagudag.controller.backcommand.MessageBackCommand;
import udagudag.controller.backcommand.MessageToAllBackCommand;
import udagudag.controller.backcommand.SignUpBackCommand;
import udagudag.controller.backcommand.UpdatedListBackCommand;
import udagudag.controller.backcommand.UpdatedListToAllBackCommand;
import udagudag.controller.command.LogInCommandData;
import udagudag.controller.command.MessageToAllCommandData;
import udagudag.controller.command.SignUpCommandData;
import udagudag.controller.command.UpdatedListToAllCommandData;
import udagudag.model.User;
import udagudag.view.View;

public class Controller {
	
	private static Controller instance = new Controller();
	public static Controller getInstance() {
		return instance;
	}
	
	private View view;
	private User user;
	private String ipAddress;
	private InputValidator inputValidator;
	private TcpClient tcpClient;

	private Controller() {
		view = new View();
		user = new User();
		view.addButtonsListener(new ButtonsListener());
		view.bindEnterActionToMessageArea(new EnterAction());
		view.addWindowListener(new WindowListenerForClosing());
		ipAddress = view.askForIpAddress();
		inputValidator = new InputValidator();
		tcpClient = new TcpClient(this);
		tcpClient.start();
	}

	private class EnterAction extends AbstractAction {
		@Override
		public void actionPerformed(ActionEvent e) {
			view.getMainPanel().getSendButton().doClick();
		}
	}

	private class ButtonsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			String parent = (String) ((JButton) e.getSource()).getClientProperty("Parent");
			String actionCommand = e.getActionCommand();

			switch (parent) {
			case "LogInPanel":
				if (actionCommand.equals("LogIn")) {
					String email = view.getLogInPanel().getEmailField().getText();
					String password = view.getLogInPanel().getPasswordField().getText();

					user.setEmail(email);
					user.setPassword(password);
										
					LogInCommandData logInCommandData = new LogInCommandData();
					logInCommandData.email = user.getEmail();
					logInCommandData.password = user.getPassword();
					String json = new GsonBuilder().create().toJson(logInCommandData, LogInCommandData.class);					
					tcpClient.sendCommandToServer(json);

					view.showLoadingPanel();
				} else if (actionCommand.equals("SignUp")) {
					view.showSignUpPanel1();
				}
				break;

			case "SignUpPanel1":
				if (actionCommand.equals("Continue")) {

					boolean isSomethingWrong = false;

					String email = view.getSignUpPanel1().getEmailField().getText();
					if (!inputValidator.isEmailValid(email)) {
						view.getSignUpPanel1().getEmailField().setForeground(Color.ORANGE);
						isSomethingWrong = true;
					} else {
						view.getSignUpPanel1().getEmailField().setForeground(Color.white);
					}

					String birthday = view.getSignUpPanel1().getBirthdayField().getText();
					if (!inputValidator.isDateValid(birthday, "dd-MM-yyyy")) {
						view.getSignUpPanel1().getBirthdayField().setForeground(Color.ORANGE);
						isSomethingWrong = true;
					} else {
						view.getSignUpPanel1().getBirthdayField().setForeground(Color.white);
					}

					String password = view.getSignUpPanel1().getPasswordField().getText();
					String repeatPassword = view.getSignUpPanel1().getRepeatPasswordField().getText();
					if (!inputValidator.isPasswordValid(password) || !password.equals(repeatPassword)) {
						view.getSignUpPanel1().getRepeatPasswordField().setForeground(Color.ORANGE);
						isSomethingWrong = true;
					} else {
						view.getSignUpPanel1().getRepeatPasswordField().setForeground(Color.white);
					}

					if (isSomethingWrong) {
						return;
					}

					user.setEmail(email);
					user.setBirthday(birthday);
					user.setPassword(password);
					
					view.showSignUpPanel2();

				} else if (actionCommand.equals("Back")) {
					view.showLogInPanel();
				}
				break;

			case "SignUpPanel2":
				if (actionCommand.equals("Submit")) {

					boolean isSomethingWrong = false;

					String firstName = view.getSignUpPanel2().getFirstNameField().getText();
					if (!inputValidator.isNameValid(firstName)) {
						view.getSignUpPanel2().getFirstNameField().setForeground(Color.ORANGE);
						isSomethingWrong = true;
					} else {
						view.getSignUpPanel2().getFirstNameField().setForeground(Color.white);
					}

					String lastName = view.getSignUpPanel2().getLastNameField().getText();
					if (!inputValidator.isNameValid(lastName)) {
						view.getSignUpPanel2().getLastNameField().setForeground(Color.ORANGE);
						isSomethingWrong = true;
					} else {
						view.getSignUpPanel2().getLastNameField().setForeground(Color.white);
					}

					String place = view.getSignUpPanel2().getPlaceField().getText();
					if (!inputValidator.isNameValid(place)) {
						view.getSignUpPanel2().getPlaceField().setForeground(Color.ORANGE);
						isSomethingWrong = true;
					} else {
						view.getSignUpPanel2().getPlaceField().setForeground(Color.white);
					}

					String state = view.getSignUpPanel2().getStateField().getText();
					if (!inputValidator.isNameValid(state)) {
						view.getSignUpPanel2().getStateField().setForeground(Color.ORANGE);
						isSomethingWrong = true;
					} else {
						view.getSignUpPanel2().getStateField().setForeground(Color.white);
					}

					if (isSomethingWrong) {
						return;
					}

					user.setFirstName(firstName);
					user.setLastName(lastName);
					user.setPlace(place);
					user.setState(state);

					SignUpCommandData signUpCommandData = new SignUpCommandData();
					signUpCommandData.email = user.getEmail();
					signUpCommandData.birthday = user.getBirthday();
					signUpCommandData.password = user.getPassword();
					signUpCommandData.firstName = user.getFirstName();
					signUpCommandData.lastName = user.getLastName();
					signUpCommandData.place = user.getPlace();
					signUpCommandData.state = user.getState();
					String json = new GsonBuilder().create().toJson(signUpCommandData, SignUpCommandData.class);
					tcpClient.sendCommandToServer(json);

					view.showLoadingPanel();
				} else if (actionCommand.equals("Back")) {
					view.showSignUpPanel1();
				}
				break;

			case "MainPanel":
				if (actionCommand.equals("Send")) {
					JTextArea messageArea = view.getMainPanel().getMessageArea();
					String message = user.getEmail() + ": " + messageArea.getText();
					
					MessageToAllCommandData messageToAllCommandData = new MessageToAllCommandData();
					messageToAllCommandData.message = message;
					String json = new GsonBuilder().create().toJson(messageToAllCommandData, MessageToAllCommandData.class);
					tcpClient.sendCommandToServer(json);

					messageArea.setText("");
				}
				break;
			}
		}
	}

	private class WindowListenerForClosing extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent we) {
			tcpClient.setRunning(false);
			tcpClient.closeConnections();
			System.exit(0);
		}
	}

	public void executeBackCommand(String json) {
		Gson gson = new GsonBuilder().create();
		BackCommand backCommand = null;

		if (json.contains("LogInBackCommand")) {
			backCommand = gson.fromJson(json, LogInBackCommand.class);
		} else if (json.contains("MessageBackCommand")) {
			backCommand = gson.fromJson(json, MessageBackCommand.class);
		} else if (json.contains("MessageToAllBackCommand")) {
			backCommand = gson.fromJson(json, MessageToAllBackCommand.class);
		} else if (json.contains("SignUpBackCommand")) {
			backCommand = gson.fromJson(json, SignUpBackCommand.class);
		} else if (json.contains("UpdatedListBackCommand")) {
			backCommand = gson.fromJson(json, UpdatedListBackCommand.class);
		} else if (json.contains("UpdatedListToAllBackCommand")) {
			backCommand = gson.fromJson(json, UpdatedListToAllBackCommand.class);
		}

		backCommand.execute();
	}

	public boolean askForReconnection() {
		return view.askYesNoQuestion("Connection with server has failed.", "Connect again?");
	}

	public void tellConnectionIsReady() {
		JTextArea chatArea = view.getMainPanel().getChatArea();
		JButton sendButton = view.getMainPanel().getSendButton();

		if (chatArea.getText().length() == 0) {
			chatArea.append("Connected!");
		} else {
			chatArea.append(System.lineSeparator() + "Connected!");
		}

		sendButton.setEnabled(true);
	}

	public void tellConnectionIsNotReady() {
		JTextArea chatArea = view.getMainPanel().getChatArea();
		JButton sendButton = view.getMainPanel().getSendButton();

		if (!chatArea.getText().endsWith("Waiting for server...")) {
			if (chatArea.getText().length() == 0) {
				chatArea.append("Waiting for server...");
			} else {
				chatArea.append(System.lineSeparator() + "Waiting for server");
			}
		}
		sendButton.setEnabled(false);
	}
	
	public void requestAddingUserToList() {
		UpdatedListToAllCommandData updateListCommandData = new UpdatedListToAllCommandData();
		updateListCommandData.connected = true;
		updateListCommandData.email = user.getEmail();
		String json = new GsonBuilder().create().toJson(updateListCommandData, UpdatedListToAllCommandData.class);
		tcpClient.sendCommandToServer(json);
	}
	
	public void requestRemovingUserFromList() {
		UpdatedListToAllCommandData updateListCommandData = new UpdatedListToAllCommandData();
		updateListCommandData.connected = false;
		updateListCommandData.email = user.getEmail();
		String json = new GsonBuilder().create().toJson(updateListCommandData, UpdatedListToAllCommandData.class);
		tcpClient.sendCommandToServer(json);		
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public void setTcpClient(TcpClient tcpClient) {
		this.tcpClient = tcpClient;
	}
	
	public View getView() {
		return this.view;
	}
}