package irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.core;

import java.util.Date;
import java.util.List;
import javax.mail.search.ComparisonTerm;
import javax.mail.search.ReceivedDateTerm;
import javax.mail.search.SearchTerm;
import irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.core.model.Status;
import irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.i18n.MessageBundle;
import
irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.core.exception.EmailException;
import
irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.core.exception.EmailUncheckedException;
import
irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.core.model.EmailMessage;
import
irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.core.model.IrisFolder;
import irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.mail.EmailClient;
import irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.mail.EmailProvider;
import irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.mail.IEmailClient;
import
irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.mail.provider.DefaultProvider;
import
irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.mail.provider.ProviderManager;
import
irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.persistence.IEmailDAO;
import
irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.persistence.relational.EmailDAO;
import
irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.persistence.relational.FolderDAO;
/*** added by dBaseFacade* modified by dPersistenceRelational* modified by
dSearchFacade
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
	/*** added by dSearchFacade
	 */
	public List<EmailMessage> search(String query) throws EmailException {
		EmailDAO dao = EmailDAO.instance();
		return dao.search(query);
	}
}