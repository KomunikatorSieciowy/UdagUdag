package udagudag.controller.backcommand;

import java.util.List;

public class UpdatedListToAllBackCommand implements BackCommand {

	public String className = "UpdatedListToAllBackCommand";
	public boolean success;
	public List<String> emailsList;
	
	@Override
	public void execute() {
		if (success) {
			System.out.println("Successfully delivered updated list to all clients.");
		} else {
			System.out.println("Did not manage to deliver updated list to all clients.");
		}
	}
}