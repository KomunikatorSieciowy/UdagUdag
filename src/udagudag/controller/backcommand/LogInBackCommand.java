package udagudag.controller.backcommand;

import udagudag.controller.Controller;

public class LogInBackCommand implements BackCommand {
	
	public String className = "LogInBackCommand";
	public boolean success;
	
	@Override
	public void execute() {
		if (success) {
			Controller.getInstance().getView().showMainPanel();
			Controller.getInstance().requestAddingUserToList();
		} else {
			Controller.getInstance().getView().showLogInPanel();
		}
	}
}