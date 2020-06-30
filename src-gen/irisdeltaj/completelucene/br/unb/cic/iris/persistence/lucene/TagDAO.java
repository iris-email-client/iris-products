package irisdeltaj.completelucene.br.unb.cic.iris.persistence.lucene;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import irisdeltaj.completelucene.br.unb.cic.iris.core.exception.EmailException;
import irisdeltaj.completelucene.br.unb.cic.iris.core.model.EmailMessage;
import irisdeltaj.completelucene.br.unb.cic.iris.core.model.Tag;
import irisdeltaj.completelucene.br.unb.cic.iris.persistence.ITagDAO;

/***
 * added by dTagLucene
 */
public class TagDAO extends AbstractDAO<Tag> implements ITagDAO {
	private static final TagDAO instance = new TagDAO();

	private TagDAO() {
		this.type = "tag";
	}

	public static TagDAO instance() {
		return instance;
	}

	public Tag findByName(String tagName) throws EmailException {
		List<Tag> result = new ArrayList<Tag>();
		Query nameQuery = new TermQuery(new Term("name", tagName));
		result = findByTerms(new Query[] { nameQuery });
		if (result.isEmpty()) {
			throw new EmailException("Tag name not found", null);
		}
		return result.iterator().next();
	}

	public void save(Tag tagToSave, String messageId) throws EmailException {
		EmailMessage email = EmailDAO.instance().findById(messageId);
		email.addTag(tagToSave);
		EmailDAO.instance().saveOrUpdate(email);
	}

	protected Tag fromDocument(Document d) throws ParseException {
		Tag t = new Tag();
		t.setId(d.get("id"));
		t.setName(d.get("name"));
		return t;
	}

	protected Document toDocument(Tag t) throws Exception {
		Document doc = new Document();
		doc.add(new StringField("id", String.valueOf(t.getId()), Store.YES));
		doc.add(new StringField("name", t.getName(), Store.YES));
		return doc;
	}
}