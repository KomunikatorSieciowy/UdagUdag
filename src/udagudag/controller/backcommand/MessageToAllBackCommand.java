package udagudag.controller.backcommand;

public class MessageToAllBackCommand implements BackCommand {

	public String className = "MessageToAllBackCommand";
	public boolean success;

	@Override
	public void execute() {
		if (success) {
			System.out.println("Successfully delivered message to all clients.");
		} else {
			System.out.println("Did not manage to deliver message to all clients.");
		}
	}
}