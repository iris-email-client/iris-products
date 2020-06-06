package irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.command;

import java.util.List;
import
irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.core.exception.EmailException;
/*** added by dBaseCommand
 */
public interface ICommandManager {
	public void addCommand(MailCommand command) throws EmailException;
	public MailCommand getCommand(String commandName) throws EmailException;
	public List<MailCommand> listAll();
	public void addCommandListener(CommandListener listener);
	public void reload() throws Exception;
}