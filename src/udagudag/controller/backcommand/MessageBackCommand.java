package udagudag.controller.backcommand;

import javax.swing.JTextArea;

import udagudag.controller.Controller;

public class MessageBackCommand implements BackCommand {
	
	public String className = "MessageBackCommand";
	public String message;

	@Override
	public void execute() {
		JTextArea chatArea = Controller.getInstance().getView().getMainPanel().getChatArea();
		if (chatArea.getText().length() == 0) {
			chatArea.append(message);
		} else {
			chatArea.append(System.lineSeparator() + message);
		}
	}
}