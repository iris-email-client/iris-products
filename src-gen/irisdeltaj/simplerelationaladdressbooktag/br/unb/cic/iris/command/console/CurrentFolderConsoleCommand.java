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
public class CurrentFolderConsoleCommand extends AbstractMailCommand {
	static final String COMMAND_NAME = "pwd";
	@Override
	public void explain() {
		System.out.println("(pwd) - show current folder)");
	}
	@Override
	public void handleExecute() throws EmailException {
		System.out.println(FolderManager.instance().getCurrentFolderName());
	}
	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}
}