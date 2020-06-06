package irisdeltaj.simplerelational.br.unb.cic.iris.command.console;

import irisdeltaj.simplerelational.br.unb.cic.iris.command.AbstractMailCommand;
import irisdeltaj.simplerelational.br.unb.cic.iris.core.SystemFacade;
import
irisdeltaj.simplerelational.br.unb.cic.iris.core.exception.EmailException;
import irisdeltaj.simplerelational.br.unb.cic.iris.core.model.IrisFolder;
/*** added by dConsole
 */
public class DownloadMessagesConsoleCommand extends AbstractMailCommand {
	static final String COMMAND_NAME = "download";
	@Override
	public void explain() {
		System.out.printf("(%s) - %s %n", COMMAND_NAME,
			message("command.download.explain"));
	}
	@Override
	public void handleExecute() throws EmailException {
		String folder = IrisFolder.INBOX;
		if(validParameters()) {
			folder = parameters.get(0);
		}
		System.out.println("Downloading messages from/to folder: " + folder);
		SystemFacade.instance().downloadMessages(folder);
	}
	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}
}