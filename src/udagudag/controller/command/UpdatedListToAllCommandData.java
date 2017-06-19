package udagudag.controller.command;

public class UpdatedListToAllCommandData {

	public String className = "UpdatedListToAllCommand";
	public String email;
	public boolean connected; // 'true' - has joined / 'false' - has left
	
	public UpdatedListToAllCommandData(String email, boolean connected) {
		this.email = email;
		this.connected = connected;
	}
}