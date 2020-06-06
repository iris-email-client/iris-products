package irisdeltaj.simplerelational.br.unb.cic.iris.test;

import java.util.Date;
import java.util.List;
import irisdeltaj.simplerelational.br.unb.cic.iris.core.exception.DBException;
import irisdeltaj.simplerelational.br.unb.cic.iris.core.model.EmailMessage;
import irisdeltaj.simplerelational.br.unb.cic.iris.core.model.IrisFolder;
import
irisdeltaj.simplerelational.br.unb.cic.iris.persistence.relational.EmailDAO;
import
irisdeltaj.simplerelational.br.unb.cic.iris.persistence.relational.FolderDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/*** added by dTestFolderDAO
 */
public class TestFolderDAO {
	private FolderDAO dao;
	@Before
	public void setUp() throws Exception {
		try {
			dao = FolderDAO.instance();
		}
		catch(Exception e) {
			throw new Exception("could not setUp the tests", e);
		}
	}
	@Test
	public void testFindByName() {
		try {
			IrisFolder findFolder = dao.findByName(IrisFolder.INBOX);
			Assert.assertNotNull(findFolder);
			System.out.println("folder=" + findFolder.getName());
		}
		catch(DBException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void saveFind() {
		String folderName = java.util.UUID.randomUUID().toString();
		try {
			IrisFolder folder = new IrisFolder(folderName, folderName);
			dao.saveOrUpdate(folder);
			IrisFolder findFolder = dao.findById(folderName);
			Assert.assertNotNull(findFolder);
		}
		catch(Exception e) {
			e.printStackTrace();
			Assert.fail("error while testing TestFolderDAO.saveFindDelete()");
		}
	}
	@Test
	public void testListMessages() {
		try {
			IrisFolder inbox = dao.findByName(IrisFolder.INBOX);
			EmailDAO.instance().saveMessage(new EmailMessage("from-1", "to-1",
					"subject-1", "message-1", new Date(System.currentTimeMillis()), inbox));
			EmailDAO.instance().saveMessage(new EmailMessage("from-1", "to-1",
					"subject-2", "message-2", new Date(), inbox));
			EmailDAO.instance().saveMessage(new EmailMessage("from-2", "to-1",
					"subject-3", "message-3", new Date(), inbox));
			EmailDAO.instance().saveMessage(new EmailMessage("from-2", "to-1",
					"subject-4", "message-4", new Date(), inbox));
			List<EmailMessage> list = EmailDAO.instance().listMessages(inbox.getId());
			Assert.assertNotNull(list);
			Assert.assertFalse(list.isEmpty());
			for(EmailMessage msg : list) {
				System.out.println("MSG: " + msg);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}