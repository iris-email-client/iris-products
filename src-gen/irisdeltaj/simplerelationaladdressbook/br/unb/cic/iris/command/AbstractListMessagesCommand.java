package irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.command;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.command.AbstractMailCommand;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.core.model.EmailMessage;
/*** added by dBaseCommand
 */
public abstract class AbstractListMessagesCommand extends AbstractMailCommand {
	DateFormat formatter = new SimpleDateFormat("dd/MMM/yy 'at' HH:mm");
	protected void print(List<EmailMessage> messages) {
		for(int i = 0;
			i < messages.size();
			i ++) {
			EmailMessage msg = messages.get(i);
			System.out.printf("%d - %s - %s \t- %s%n", i + 1,
				formatter.format(msg.getDate()), msg.getFrom(), msg.getSubject());
		}
	}
}