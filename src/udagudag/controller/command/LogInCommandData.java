package udagudag.controller.command;

public class LogInCommandData {

	public String className = "LogInCommand";
	public String email;
	public String password;
	
	public LogInCommandData(String email, String password) {
		this.email = email;
		this.password = password;
	}
}