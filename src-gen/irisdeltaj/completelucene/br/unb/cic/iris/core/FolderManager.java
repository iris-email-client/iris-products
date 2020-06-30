package irisdeltaj.completelucene.br.unb.cic.iris.core;

import irisdeltaj.completelucene.br.unb.cic.iris.core.exception.EmailException;
import irisdeltaj.completelucene.br.unb.cic.iris.core.model.EmailMessage;
import irisdeltaj.completelucene.br.unb.cic.iris.core.model.IrisFolder;
import java.util.List;
import irisdeltaj.completelucene.br.unb.cic.iris.persistence.lucene.EmailDAO;
import irisdeltaj.completelucene.br.unb.cic.iris.persistence.lucene.FolderDAO;

/***
 * added by dBaseFacade* modified by dPersistenceLucene
 */
public final class FolderManager {
	private static final FolderManager instance = new FolderManager();
	private static final String ROOT_FOLDER = "ROOT";
	private IrisFolder currentFolder = new IrisFolder(ROOT_FOLDER);
	private List<EmailMessage> currentMessages;

	private FolderManager() {
	}

	public static FolderManager instance() {
		return instance;
	}

	public String getCurrentFolderName() {
		return currentFolder.getName();
	}

	public List<EmailMessage> getCurrentMessages() {
		return currentMessages;
	}

	public void changeToFolder(String folderName) throws EmailException {
		IrisFolder folder = FolderDAO.instance().findByName(folderName);
		if (folder != null) {
			currentFolder = folder;
			currentMessages = new java.util.ArrayList<EmailMessage>();
		}
	}

	public List<IrisFolder> listFolders() throws EmailException {
		return FolderDAO.instance().findAll();
	}

	public List<EmailMessage> listFolderMessages() throws EmailException {
		if (currentFolder == null || currentFolder.getId() == null) {
			return new java.util.ArrayList<EmailMessage>();
		}
		currentMessages = EmailDAO.instance().listMessages(currentFolder.getId());
		return currentMessages;
	}

	public EmailMessage getMessage(String id) throws EmailException {
		return EmailDAO.instance().findById(id);
	}
}