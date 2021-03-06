package
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.command.console;

import java.util.Scanner;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.command.AbstractMailCommand;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.core.SystemFacade;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.core.exception.EmailException;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.core.model.EmailMessage;
/*** added by dConsole
 */
public class ConsoleSendMessageCommand extends AbstractMailCommand {
	public static final String COMMAND_SEND = "send";
	@Override
	public void explain() {
		System.out.printf("(%s) - %s %n", COMMAND_SEND,
			message("command.send.explain"));
	}
	@Override
	public void handleExecute() throws EmailException {
		EmailMessage m = createMessage();
		SystemFacade.instance().send(m);
	}
	private EmailMessage createMessage() {
		Scanner sc = new Scanner(System.in);
		String from = SystemFacade.instance().getProvider().getUsername();
		System.out.printf("%s: %s%n", message("command.send.label.from"), from);
		String to = read(sc, "command.send.label.to");
		String cc = read(sc, "command.send.label.cc");
		String bcc = read(sc, "command.send.label.bcc");
		String subject = read(sc, "command.send.label.subject");
		String content = read(sc, "command.send.label.content");
		return new EmailMessage(from, to, cc, bcc, subject, content);
	}
	private static String read(Scanner sc, String question) {
		askQuestion(question);
		return sc.nextLine();
	}
	private static void askQuestion(String message) {
		System.out.printf("%s: ", message(message));
	}
	@Override
	public String getCommandName() {
		return COMMAND_SEND;
	}
}