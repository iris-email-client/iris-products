package irisdeltaj.completelucene.br.unb.cic.iris.persistence.lucene;

import java.text.ParseException;
import java.util.List;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import irisdeltaj.completelucene.br.unb.cic.iris.core.exception.EmailException;
import irisdeltaj.completelucene.br.unb.cic.iris.core.model.IrisFolder;
import irisdeltaj.completelucene.br.unb.cic.iris.persistence.IFolderDAO;

/***
 * added by dPersistenceLucene
 */
public class FolderDAO extends AbstractDAO<IrisFolder> implements IFolderDAO {
	private static FolderDAO instance;

	private FolderDAO() {
		this.type = "folder";
	}

	public static FolderDAO instance() throws EmailException {
		if (instance == null) {
			instance = new FolderDAO();
			ensureIsCreated(IrisFolder.INBOX);
			ensureIsCreated(IrisFolder.OUTBOX);
		}
		return instance;
	}

	private static void ensureIsCreated(String folderName) throws EmailException {
		List<IrisFolder> folders = instance.doFindByName(folderName);
		if (folders.isEmpty()) {
			IrisFolder inbox = new IrisFolder();
			inbox.setName(folderName);
			instance.saveOrUpdate(inbox);
			System.out.println(String.format("%s folder created.", folderName));
		}
	}

	public IrisFolder findByName(String folderName) throws EmailException {
		List<IrisFolder> result = doFindByName(folderName);
		if (result.isEmpty()) {
			throw new EmailException(String.format("Folder '%s' not found", folderName), null);
		}
		return result.iterator().next();
	}

	public List<IrisFolder> doFindByName(String folderName) throws EmailException {
		Query nameQuery = new TermQuery(new Term("name", folderName));
		return findByTerms(new Query[] { nameQuery });
	}

	protected IrisFolder fromDocument(Document d) throws ParseException {
		IrisFolder f = new IrisFolder();
		f.setId(d.get("id"));
		f.setName(d.get("name"));
		return f;
	}

	protected Document toDocument(IrisFolder f) throws Exception {
		Document doc = new Document();
		doc.add(new StringField("id", String.valueOf(f.getId()), Store.YES));
		doc.add(new StringField("name", f.getName(), Store.YES));
		return doc;
	}
}