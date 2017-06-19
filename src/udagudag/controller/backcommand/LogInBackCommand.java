package udagudag.controller.backcommand;

import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import udagudag.controller.Controller;

public class LogInBackCommand implements BackCommand {

	public String className = "LogInBackCommand";
	public boolean success;

	@Override
	public void execute() {
		if (success) {
			try {
				String soundFilePath = "/sounds/success.wav";
				InputStream inputStream = getClass().getResourceAsStream(soundFilePath);
				AudioStream audioStream = new AudioStream(inputStream);
				AudioPlayer.player.start(audioStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Controller.getInstance().getView().showMainPanel();
			Controller.getInstance().requestAddingUserToList();
		} else {
			try {
				String soundFilePath = "/sounds/fail.wav";
				InputStream inputStream = getClass().getResourceAsStream(soundFilePath);
				AudioStream audioStream = new AudioStream(inputStream);
				AudioPlayer.player.start(audioStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Controller.getInstance().getView().showLogInPanel();
		}
	}
}