package irisdeltaj.simplerelationalsearchaddressbook.br.unb.cic.iris.mail;

import java.util.List;
import javax.mail.search.SearchTerm;
import
irisdeltaj.simplerelationalsearchaddressbook.br.unb.cic.iris.core.exception.EmailException;
import
irisdeltaj.simplerelationalsearchaddressbook.br.unb.cic.iris.core.model.EmailMessage;
import
irisdeltaj.simplerelationalsearchaddressbook.br.unb.cic.iris.core.model.IrisFolder;
import
irisdeltaj.simplerelationalsearchaddressbook.br.unb.cic.iris.util.EmailValidator;
import
irisdeltaj.simplerelationalsearchaddressbook.br.unb.cic.iris.persistence.relational.AddressBookDAO;
/*** added by dBaseMail* modified by dAddressBook* modified by
dAddressBookRelational
 */
public class EmailClient implements IEmailClient {
	public static final String CHARACTER_ENCODING = "UTF-8";
	private final EmailSender sender;
	private final EmailReceiver receiver;
	public EmailClient(EmailProvider provider) {
		this(provider, CHARACTER_ENCODING);
	}
	public EmailClient(EmailProvider provider, String encoding) {
		sender = new EmailSender(provider, encoding);
		receiver = new EmailReceiver(provider, encoding);
	}
	/*** modified by dAddressBook
	 */
	public void send(EmailMessage email) throws EmailException {
		email.setTo(findAddress(email.getTo()));
		email.setCc(findAddress(email.getCc()));
		email.setBcc(findAddress(email.getBcc()));
		send_original0(email);
	}
	@Override
	public List<IrisFolder> listFolders() throws EmailException {
		System.out.println("listing folders ...");
		return receiver.listFolders();
	}
	@Override
	public List<EmailMessage> getMessages(String folder) throws EmailException {
		return getMessages(folder, null);
	}
	@Override
	public List<EmailMessage> getMessages(String folder, SearchTerm searchTerm)
	throws EmailException {
		return receiver.getMessages(folder, searchTerm);
	}
	@Override
	public List<EmailMessage> getMessages(String folder, int seqnum) throws
	EmailException {
		return receiver.getMessages(folder, seqnum);
	}
	@Override
	public List<EmailMessage> getMessages(String folder, int begin, int end)
	throws EmailException {
		return receiver.getMessages(folder, begin, end);
	}
	@Override
	public List<String> validateEmailMessage(EmailMessage message) {
		return EmailSender.validateEmailMessage(message);
	}
	/*** added by dAddressBook
	 */
	private String findAddress(String email) throws EmailException {
		if(email != null && ! email.equals("") && ! EmailValidator.validate(email)) {
			return AddressBookDAO.instance().find(email).getAddress();
		}
		return email;
	}
	/*** modified by dAddressBook
	 */
	public void send_original0(EmailMessage email) throws EmailException {
		System.out.println("send message: " + email);
		sender.send(email);
	}
}