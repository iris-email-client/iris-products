package
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.command.console;

import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.command.AbstractMailCommand;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.core.FolderManager;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.core.exception.EmailException;
/*** added by dConsole
 */
public class ChangeFolderConsoleCommand extends AbstractMailCommand {
	static final String COMMAND_NAME = "cd";
	@Override
	public void explain() {
		System.out.println("(cd <id_folder>) - change current folder)");
	}
	@Override
	public void handleExecute() throws EmailException {
		if(validParameters()) {
			FolderManager.instance().changeToFolder(parameters.get(0));
			String folder = FolderManager.instance().getCurrentFolderName();
			System.out.println(folder);
		}
		else {
		}
	}
	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}
}