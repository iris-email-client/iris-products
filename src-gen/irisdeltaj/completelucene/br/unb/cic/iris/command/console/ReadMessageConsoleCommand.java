package irisdeltaj.completelucene.br.unb.cic.iris.command.console;

import irisdeltaj.completelucene.br.unb.cic.iris.command.AbstractMailCommand;
import irisdeltaj.completelucene.br.unb.cic.iris.core.FolderManager;
import irisdeltaj.completelucene.br.unb.cic.iris.core.exception.EmailException;
import irisdeltaj.completelucene.br.unb.cic.iris.core.model.EmailMessage;

/***
 * added by dConsole
 */
public class ReadMessageConsoleCommand extends AbstractMailCommand {
	static final String COMMAND_NAME = "read";

	@Override
	public void explain() {
		System.out.println("(read <id_msg>) - read specified message)");
	}

	@Override
	public void handleExecute() throws EmailException {
		if (validParameters()) {
			Integer idx = Integer.parseInt(parameters.get(0));
			EmailMessage message = FolderManager.instance().getCurrentMessages().get(idx - 1);
			System.out.println("FROM: " + message.getFrom());
			System.out.println("DATE: " + message.getDate());
			System.out.println("SUBJECT: " + message.getSubject());
			System.out.println("CONTENT:\n" + message.getMessage());
		} else {
		}
	}

	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}
}