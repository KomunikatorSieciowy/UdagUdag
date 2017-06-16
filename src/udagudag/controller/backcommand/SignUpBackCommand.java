package udagudag.controller.backcommand;

import udagudag.controller.Controller;

public class SignUpBackCommand implements BackCommand {

	public String className = "SignUpBackCommand";
	public boolean success;
	
	@Override
	public void execute() {
		if (success) {
			Controller.getInstance().getView().showMainPanel();
		} else {
			Controller.getInstance().getView().showSignUpPanel1();
		}
	}
}