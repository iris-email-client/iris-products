package irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.test;

import irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.core.model.Tag;
import
irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.persistence.ITagDAO;
import
irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.persistence.relational.TagDAO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
/*** added by dTestRelationalTagDAO
 */
public class TestTagDAO {
	private static final String TAG_TEST = "tag-test";
	private ITagDAO dao;
	@Before
	public void setUp() throws Exception {
		try {
			dao = TagDAO.instance();
			Tag tag = dao.findByName(TAG_TEST);
			if(tag != null) {
				dao.delete(tag);
			}
		}
		catch(Exception e) {
			throw new Exception("could not setUp the tests", e);
		}
	}
	@Test
	public void save() {
		try {
			dao.saveOrUpdate(new Tag(TAG_TEST));
			Tag tag = dao.findByName(TAG_TEST);
			Assert.assertNotNull(tag);
		}
		catch(Exception e) {
			e.printStackTrace();
			Assert.fail("error while testing TagDAO.saveOrUpdate()");
		}
	}
}