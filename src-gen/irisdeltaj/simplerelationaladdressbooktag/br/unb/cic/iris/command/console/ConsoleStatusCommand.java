package
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.command.console;

import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.command.AbstractMailCommand;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.core.FolderManager;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.core.SystemFacade;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.core.model.Status;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.mail.EmailProvider;
/*** added by dConsole
 */
public class ConsoleStatusCommand extends AbstractMailCommand {
	static final String COMMAND_NAME = "status";
	@Override
	public void explain() {
		System.out.printf("(%s) - %s %n", COMMAND_NAME,
			message("command.status.explain"));
	}
	@Override
	public void handleExecute() {
		Status status = SystemFacade.instance().getStatus();
		System.out.println("System status: " + status);
		System.out.println("Current folder: " +
			FolderManager.instance().getCurrentFolderName());
		if(Status.CONNECTED == status) {
			EmailProvider provider = SystemFacade.instance().getProvider();
			System.out.println(" - Provider: " + provider.getName());
			System.out.println(" - Username: " + provider.getUsername());
		}
	}
	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}
}