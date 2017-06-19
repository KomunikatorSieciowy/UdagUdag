package udagudag.controller.command;

public class SignUpCommandData {

	public String className = "SignUpCommand";
	public String email;
	public String birthday;
	public String password;
	public String firstName;
	public String lastName;
	public String place;
	public String state;
	
	public SignUpCommandData(String email, String birthday, String password,
			String firstName, String lastName, String place, String state) {
		this.email = email;
		this.birthday = birthday;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.place = place;
		this.state = state;
	}
}