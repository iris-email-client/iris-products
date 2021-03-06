package irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.command.console;

import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.i18n.MessageBundle;
import irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.util.StringUtil;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.command.MailCommand;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.command.manager.AbstractCommandManager;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.core.exception.EmailException;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.core.exception.EmailMessageValidationException;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.core.exception.EmailUncheckedException;
import java.util.List;
import java.util.ArrayList;
/*** added by dConsole
 */
public class ConsoleCommandManager extends AbstractCommandManager {
	private static ConsoleCommandManager singleton = new ConsoleCommandManager();
	private ConsoleCommandManager() {
	}
	public static ConsoleCommandManager singleton() {
		return singleton;
	}
	public static void run(String cmd) {
		ConsoleCommandManager.singleton().runCommand(cmd);
	}
	public void runCommand(String cmd) {
		if(StringUtil.notEmpty(cmd)) {
			try {
				MailCommand command = createCommand(cmd.trim());
				command.execute();
			}
			catch(EmailUncheckedException eux) {
				System.err.printf("%s: %s\n", MessageBundle.message("error"),
					eux.getLocalizedMessage());
			}
			catch(EmailMessageValidationException emvx) {
				System.err.println(MessageBundle.message("error.validation"));
				for(String msg : emvx.getMessages()) {
					System.err.println(" - " + msg);
				}
			}
			catch(EmailException ex) {
				System.err.printf("%s: %s\n", MessageBundle.message("error"),
					ex.getMessage());
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			catch(Throwable t) {
				t.printStackTrace();
			}
		}
	}
	private MailCommand createCommand(String cmd) throws EmailException {
		String [] split = cmd.split(" ");
		MailCommand command = null;
		if(split.length > 1) {
			command = getCommand(split[0].trim());
			command.setParameters(getParameters(split));
		}
		else {
			command = getCommand(cmd);
		}
		return command;
	}
	private static List<String> getParameters(String [] split) {
		List<String> parameters = new ArrayList<String>();
		for(int i = 1;
			i < split.length;
			i ++) {
			parameters.add(split[i].trim());
		}
		return parameters;
	}
	@Override
	protected void handleAddCommand(MailCommand command) {
	}
}