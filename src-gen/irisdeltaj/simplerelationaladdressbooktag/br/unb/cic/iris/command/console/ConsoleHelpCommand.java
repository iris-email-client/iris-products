package
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.command.console;

import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.command.AbstractMailCommand;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.command.MailCommand;
/*** added by dConsole
 */
public class ConsoleHelpCommand extends AbstractMailCommand {
	static final String COMMAND_HELP = "help";
	@Override
	public void explain() {
		System.out.printf("(%s) - %s %n", COMMAND_HELP,
			message("command.help.explain"));
	}
	@Override
	public void handleExecute() {
		for(MailCommand c : ConsoleCommandManager.singleton().listAll()) {
			c.explain();
		}
	}
	@Override
	public String getCommandName() {
		return COMMAND_HELP;
	}
}