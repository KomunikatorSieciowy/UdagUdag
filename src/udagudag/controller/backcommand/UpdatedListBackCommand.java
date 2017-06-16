package udagudag.controller.backcommand;

import java.util.List;

import udagudag.controller.Controller;

public class UpdatedListBackCommand implements BackCommand {

	public String className = "UpdatedListBackCommand";
	public List<String> emailsList;
	
	@Override
	public void execute() {
		String[] array = new String[emailsList.size()];
		for (int i = 0; i < emailsList.size(); i++) {
			array[i] = emailsList.get(i);
		}
		Controller.getInstance().getView().getMainPanel().getEmailsList().setListData(array);
	}
}
