package irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.test;

import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.core.model.AddressBookEntry;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.core.model.EmailMessage;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.core.model.FolderContent;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.core.model.IrisFolder;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.persistence.relational.AddressBookDAO;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/*** added by dTestAddressBookDAO
 */
public class TestAddressBookDAO {
	private static final String USER_TEST = "user-test";
	private AddressBookDAO dao;
	@Before
	public void setUp() throws Exception {
		try {
			dao = AddressBookDAO.instance();
			AddressBookEntry entry = dao.find(USER_TEST);
			if(entry != null) {
				dao.delete(entry.getNick());
			}
		}
		catch(Exception e) {
			throw new Exception("could not setUp the tests", e);
		}
	}
	@Test
	public void save() {
		try {
			AddressBookEntry entry = dao.find(USER_TEST);
			Assert.assertNull(entry);
			dao.save(new AddressBookEntry(USER_TEST, "br.unb.cic.iris@gmail.com"));
			entry = dao.find(USER_TEST);
			Assert.assertNotNull(entry);
		}
		catch(Exception e) {
			e.printStackTrace();
			Assert.fail("error while testing AddressBookDao.save()");
		}
	}
}