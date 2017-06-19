package udagudag.controller.backcommand;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import udagudag.controller.Controller;

public class UpdatedListBackCommand implements BackCommand {

	public String className = "UpdatedListBackCommand";
	public List<String> emailsList;

	@Override
	public void execute() {
		int oldListSize = Controller.getInstance().getView().getMainPanel().getEmailsList().getModel().getSize();
		int newListSize = emailsList.size();

		boolean someoneJoined = (newListSize > oldListSize);
		if (someoneJoined) {
			String userEmail = Controller.getInstance().getUser().getEmail();
			String newEmail = emailsList.get(emailsList.size() - 1);
			if (!userEmail.equals(newEmail)) {
				try {
					String soundFilePath = "/sounds/someoneJoined.wav";
					InputStream inputStream = getClass().getResourceAsStream(soundFilePath);
					AudioStream audioStream = new AudioStream(inputStream);
					AudioPlayer.player.start(audioStream);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				String soundFilePath = "/sounds/someoneLeft.wav";
				InputStream inputStream = getClass().getResourceAsStream(soundFilePath);
				AudioStream audioStream = new AudioStream(inputStream);
				AudioPlayer.player.start(audioStream);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		String[] array = new String[emailsList.size()];
		array = emailsList.toArray(array);
		Controller.getInstance().getView().getMainPanel().getEmailsList().setListData(array);
	}
}