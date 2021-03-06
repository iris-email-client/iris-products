package irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.command.console;

import
irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.command.AbstractMailCommand;
import irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.core.TagManager;
import irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.core.FolderManager;
import
irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.core.exception.EmailException;
import
irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.core.model.EmailMessage;
import irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.core.model.Tag;
import java.security.InvalidParameterException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
/*** added by dTagConsole
 */
public class TagConsoleCommand extends AbstractMailCommand {
	DateFormat formatter = new SimpleDateFormat("dd/MMM/yy 'at' HH:mm");
	@Override
	public void explain() {
		System.out.println("(tag list) - show existing tags");
		System.out.println("(tag list <tag>) - messages containing specified tag");
		System.out.println("(tag add <messageID> <tags>) - add tag(s) to a message. EX: tag add 10 tag1, tag2");
	}
	@Override
	public String getCommandName() {
		return "tag";
	}
	@Override
	protected void handleExecute() throws EmailException {
		switch(parameters.get(0)) {
			case "list" : list();
			break;
			case "add" : add();
			break;
			default : throw new EmailException(parameters.get(0) +
				" is an invalid command");
		}
	}
	private void list() throws EmailException {
		if(parameters.size() == 2) {
			String tag = parameters.get(1);
			List<EmailMessage> messages = TagManager.instance().listMessagesByTag(tag);
			for(EmailMessage msg : messages) {
				System.out.printf("%s - %s - %s \t- %s%n", msg.getId(),
					formatter.format(msg.getDate()), msg.getFrom(), msg.getSubject());
			}
		}
		else {
			listAll();
		}
	}
	private void add() throws EmailException {
		if(parameters.size() == 3) {
			Integer idx = Integer.parseInt(parameters.get(1));
			EmailMessage message = FolderManager.instance().getCurrentMessages().get(idx
				- 1);
			String tags = parameters.get(2);
			TagManager.instance().saveTags(message.getId(), tags);
		}
		else {
			throw new InvalidParameterException();
		}
	}
	private void listAll() throws EmailException {
		List<Tag> tags = TagManager.instance().findAll();
		System.out.println("-----------------------------------------------------");
		System.out.println("TAGS:");
		System.out.println("-----------------------------------------------------");
		for(Tag t : tags) {
			System.out.print(t.getName() + " ");
		}
		System.out.println("\n-----------------------------------------------------");
	}
}