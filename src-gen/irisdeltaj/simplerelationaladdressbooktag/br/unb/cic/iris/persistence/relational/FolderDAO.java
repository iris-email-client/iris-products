package
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.persistence.relational;

import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.i18n.MessageBundle;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.core.exception.DBException;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.core.exception.EmailException;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.core.model.IrisFolder;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.persistence.IFolderDAO;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
/*** added by dPersistenceRelational
 */
public class FolderDAO extends AbstractDAO<IrisFolder> implements IFolderDAO {
	private static FolderDAO instance;
	private FolderDAO() {
	}
	public static FolderDAO instance() throws EmailException {
		if(instance == null) {
			instance = new FolderDAO();
			ensureIsCreated(IrisFolder.INBOX);
			ensureIsCreated(IrisFolder.OUTBOX);
		}
		return instance;
	}
	private static void ensureIsCreated(String folderName) throws EmailException {
		IrisFolder folder = instance.findByName(folderName);
		if(folder == null) {
			instance.saveOrUpdate(new IrisFolder(folderName));
			System.out.printf("%s folder created.\n", folderName);
		}
	}
	public IrisFolder findById(String id) throws DBException {
		IrisFolder obj = null;
		try {
			startSession(false);
			obj = populate(( IrisFolder ) session.load(IrisFolder.class, id));
		}
		catch(Exception e) {
			handleException(e);
		}
		finally {
			closeSession();
		}
		return obj;
	}
	public IrisFolder findByName(String folderName) throws DBException {
		try {
			startSession(false);
			Criteria criteria = session.createCriteria(IrisFolder.class);
			criteria.add(Restrictions.eq("name", folderName));
			return populate(( IrisFolder ) criteria.uniqueResult());
		}
		catch(Exception e) {
			handleException(new
				DBException(MessageBundle.message("error.folder.not.found", folderName),
					e));
		}
		finally {
			closeSession();
		}
		return null;
	}
	private IrisFolder populate(IrisFolder in) {
		if(in == null) {
			return null;
		}
		IrisFolder folder = new IrisFolder(in.getId(), in.getName());
		return folder;
	}
}