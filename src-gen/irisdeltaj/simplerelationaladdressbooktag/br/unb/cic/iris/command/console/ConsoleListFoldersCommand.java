package
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.command.console;

import java.util.List;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.command.AbstractMailCommand;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.core.FolderManager;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.core.exception.EmailException;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.core.model.IrisFolder;
/*** added by dConsole
 */
public class ConsoleListFoldersCommand extends AbstractMailCommand {
	static final String COMMAND_NAME = "lf";
	@Override
	public void explain() {
		System.out.printf("(%s) - %s %n", COMMAND_NAME,
			message("command.list.folders.explain"));
	}
	@Override
	public void handleExecute() throws EmailException {
		List<IrisFolder> irisFolders = FolderManager.instance().listFolders();
		for(IrisFolder folder : irisFolders) {
			System.out.printf("%s - %s%n", folder.getId(), folder.getName());
		}
	}
	@Override
	public String getCommandName() {
		return COMMAND_NAME;
	}
}