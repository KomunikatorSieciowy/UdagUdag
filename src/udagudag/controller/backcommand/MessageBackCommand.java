package udagudag.controller.backcommand;

import java.io.IOException;
import java.io.InputStream;

import javax.swing.JTextArea;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import udagudag.controller.Controller;

public class MessageBackCommand implements BackCommand {

	public String className = "MessageBackCommand";
	public String message;

	@Override
	public void execute() {

		if (!message.startsWith(Controller.getInstance().getUser().getEmail())) {
			try {
				String soundFilePath = "/sounds/newMessage.wav";
				InputStream inputStream = getClass().getResourceAsStream(soundFilePath);
				AudioStream audioStream = new AudioStream(inputStream);
				AudioPlayer.player.start(audioStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		JTextArea chatArea = Controller.getInstance().getView().getMainPanel().getChatArea();
		if (chatArea.getText().length() == 0) {
			chatArea.append(message);
		} else {
			chatArea.append(System.lineSeparator() + message);
		}
	}
}