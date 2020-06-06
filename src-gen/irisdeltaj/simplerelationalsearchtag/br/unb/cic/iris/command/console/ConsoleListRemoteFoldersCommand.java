package irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.command.console;

import java.util.List;
import
irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.command.AbstractMailCommand;
import irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.core.SystemFacade;
import
irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.core.exception.EmailException;
import
irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.core.model.IrisFolder;
/*** added by dConsole
 */
public class ConsoleListRemoteFoldersCommand extends AbstractMailCommand {
	static final String COMMAND_NAME = "lr";
	@Override
	public void explain() {
		System.out.printf("(%s) - %s %n", COMMAND_NAME, "List remote folders");
	}
	@Override
	public void handleExecute() throws EmailException {
		List<IrisFolder> irisFolders = SystemFacade.instance().listRemoteFolders();
		for(IrisFolder folder : irisFolders) {
			System.out.printf(" + %s%n", folder.getName());
		}
	}
	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}
}