package irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.core;

import java.util.Date;
import java.util.List;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.core.model.Status;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.i18n.MessageBundle;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.core.exception.EmailException;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.core.exception.EmailUncheckedException;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.core.model.EmailMessage;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.core.model.IrisFolder;
import irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.mail.EmailClient;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.mail.EmailProvider;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.mail.IEmailClient;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.mail.provider.DefaultProvider;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.mail.provider.ProviderManager;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.persistence.IEmailDAO;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.persistence.relational.EmailDAO;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.persistence.relational.FolderDAO;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.persistence.relational.AddressBookDAO;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.core.model.AddressBookEntry;
/*** added by dBaseFacade* modified by dPersistenceRelational* modified by
dAddressBookRelational* modified by dAddressBookFacade
 */
public final class SystemFacade {
	private static final SystemFacade instance = new SystemFacade();
	private IEmailClient client;
	private EmailProvider provider;
	private Status status = Status.NOT_CONNECTED;
	private SystemFacade() {
		Configuration config = new Configuration();
		provider = new DefaultProvider(config.getProperties());
		ProviderManager.instance().addProvider(provider);
		connect(provider);
	}
	public static SystemFacade instance() {
		return instance;
	}
	public void connect(EmailProvider provider) {
		setStatus(Status.NOT_CONNECTED);
		this.provider = provider;
		client = new EmailClient(provider);
		setStatus(Status.CONNECTED);
	}
	public void send(EmailMessage message) throws EmailException {
		verifyConnection();
		client.send(message);
		message.setDate(new Date());
		saveMessage(message, IrisFolder.OUTBOX);
	}
	private void saveMessage(EmailMessage message) throws EmailException {
		saveMessage(message, IrisFolder.OUTBOX);
	}
	private void saveMessage(EmailMessage message, String folderName) throws
	EmailException {
		IEmailDAO dao = EmailDAO.instance();
		IrisFolder folder = FolderDAO.instance().findByName(folderName);
		message.setFolder(folder);
		dao.saveMessage(message);
	}
	public List<IrisFolder> listRemoteFolders() throws EmailException {
		verifyConnection();
		return client.listFolders();
	}
	public void downloadMessages(String folder) throws EmailException {
		verifyConnection();
		SearchTerm searchTerm = null;
		IEmailDAO dao = EmailDAO.instance();
		Date lastMessageReceived = dao.lastMessageReceived();
		System.out.println("**************************** lastMessageReceived=" +
			lastMessageReceived);
		if(lastMessageReceived != null) {
			searchTerm = new ReceivedDateTerm(ComparisonTerm.GT, lastMessageReceived);
		}
		List<EmailMessage> messages = client.getMessages(folder, searchTerm);
		for(EmailMessage message : messages) {
			saveMessage(message, folder);
		}
	}
	private void verifyConnection() {
		if(! isConnected()) {
			throw new
			EmailUncheckedException(MessageBundle.message("error.not.connected"));
		}
	}
	public boolean isConnected() {
		return Status.CONNECTED == getStatus();
	}
	private void setStatus(Status status) {
		this.status = status;
	}
	public Status getStatus() {
		return status;
	}
	public EmailProvider getProvider() {
		return provider;
	}
	/*** added by dAddressBookFacade
	 */
	public void addAddressBookEntry(String name, String email) throws
	EmailException {
		AddressBookDAO dao = AddressBookDAO.instance();
		dao.save(new AddressBookEntry(name, email));
	}
	/*** added by dAddressBookFacade
	 */
	public void deleteAddressBookEntry(String name) throws EmailException {
		AddressBookDAO dao = AddressBookDAO.instance();
		dao.delete(name);
	}
	/*** added by dAddressBookFacade
	 */
	public List<AddressBookEntry> listAddressBook() throws EmailException {
		return AddressBookDAO.instance().findAll();
	}
}