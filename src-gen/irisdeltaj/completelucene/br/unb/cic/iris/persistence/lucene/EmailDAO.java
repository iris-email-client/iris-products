package irisdeltaj.completelucene.br.unb.cic.iris.persistence.lucene;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopFieldDocs;
import irisdeltaj.completelucene.br.unb.cic.iris.core.exception.EmailException;
import irisdeltaj.completelucene.br.unb.cic.iris.core.model.EmailMessage;
import irisdeltaj.completelucene.br.unb.cic.iris.persistence.IEmailDAO;
import irisdeltaj.completelucene.br.unb.cic.iris.core.model.IrisFolder;
import java.util.ArrayList;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import irisdeltaj.completelucene.br.unb.cic.iris.core.exception.EmailException;
import irisdeltaj.completelucene.br.unb.cic.iris.core.model.Tag;
import org.apache.lucene.index.IndexableField;

/***
 * added by dPersistenceLucene* modified by dAdvancedSearch* modified by
 * dTagLucene
 */
public final class EmailDAO extends AbstractDAO<EmailMessage> implements IEmailDAO {
	private static EmailDAO instance = new EmailDAO();

	private EmailDAO() {
		this.type = "email";
	}

	public static EmailDAO instance() {
		return instance;
	}

	@Override
	public void saveMessage(EmailMessage m) throws EmailException {
		saveOrUpdate(m);
	}

	@Override
	public Date lastMessageReceived() throws EmailException {
		Date date = null;
		try {
			Query query = new TermQuery(new Term("type", "email"));
			Sort sort = new Sort(new SortField("date", Type.STRING, true));
			IndexSearcher searcher = IndexManager.getSearcher();
			TopFieldDocs docs = searcher.search(query, 1, sort);
			if (docs.totalHits > 0) {
				Document doc = searcher.doc(docs.scoreDocs[0].doc);
				date = DateTools.stringToDate(doc.get("date"));
			}
		} catch (IOException e) {
			throw new EmailException("An error occurred while retrieving last message received", e);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public List<EmailMessage> listMessages(String idFolder) throws EmailException {
		Query folderQuery = new TermQuery(new Term("folderId", idFolder));
		return findByTerms(new Query[] { folderQuery });
	}

	/***
	 * modified by dTagLucene
	 */
	protected Document toDocument(EmailMessage m) throws Exception {
		Document d = toDocument_original2(m);
		if (m.getTags() != null) {
			for (Tag t : m.getTags()) {
				d.add(new StringField("tag", t.getName(), Store.YES));
			}
		}
		return d;
	}

	/***
	 * modified by dTagLucene
	 */
	protected EmailMessage fromDocument(Document d) throws ParseException {
		EmailMessage m = fromDocument_original0(d);
		for (IndexableField f : d.getFields()) {
			if ("tag".equals(f.name())) {
				Tag t = new Tag();
				t.setName(f.stringValue());
				m.addTag(t);
			}
		}
		return m;
	}

	public static void main(String[] args) throws EmailException {
		EmailMessage message = new EmailMessage();
		message.setFrom("alexandrelucchesi@gmail.com");
		message.setTo("rbonifacio123@gmail.com");
		message.setCc("jeremiasmg@gmail.com");
		message.setBcc("somebcc@gmail.com");
		message.setSubject("Alexandre Lucchesi");
		message.setMessage("Testing Lucene. :-)");
		message.setDate(new Date());
		message.setFolder(new IrisFolder("19", "UnB"));
		EmailDAO emailDAO = new EmailDAO();
		emailDAO.saveMessage(message);
		System.out.println(emailDAO.listMessages("19"));
		System.out.println(emailDAO.findAll());
		System.out.println(emailDAO.findById(emailDAO.findAll().iterator().next().getId()));
		System.out.println(emailDAO.findById(emailDAO.findAll().iterator().next().getId()).getSubject());
	}

	/***
	 * added by dAdvancedSearch
	 */
	public List<EmailMessage> search(String queryStr) throws EmailException {
		List<EmailMessage> emails = new ArrayList<EmailMessage>();
		try {
			Query userQuery = new QueryParser("subject", new StandardAnalyzer()).parse(queryStr);
			Query typeQuery = new TermQuery(new Term("type", "email"));
			BooleanQuery query = new BooleanQuery();
			query.add(new BooleanClause(typeQuery, Occur.MUST));
			query.add(new BooleanClause(userQuery, Occur.MUST));
			IndexSearcher searcher = IndexManager.getSearcher();
			searcher.search(query, new TCollector(searcher, emails));
		} catch (Exception e) {
			e.printStackTrace();
			throw new EmailException("Error ocurred while retrieving messages from folder.", e);
		}
		return emails;
	}

	/***
	 * modified by dTagLucene
	 */
	protected EmailMessage fromDocument_original0(Document d) throws ParseException {
		EmailMessage m = new EmailMessage();
		m.setId(d.get("id"));
		m.setFrom(d.get("from"));
		m.setTo(d.get("to"));
		m.setCc(d.get("cc"));
		m.setBcc(d.get("bcc"));
		m.setSubject(d.get("subject"));
		m.setMessage(d.get("message"));
		m.setDate(DateTools.stringToDate(d.get("date")));
		IrisFolder f = new IrisFolder();
		f.setId(d.get("folderId"));
		m.setFolder(f);
		return m;
	}

	/***
	 * added by dTagLucene
	 */
	@Override
	public List<EmailMessage> listMessagesByTag(String tag) throws EmailException {
		Query nameQuery = new TermQuery(new Term("tag", tag));
		return findByTerms(new Query[] { nameQuery });
	}

	/***
	 * modified by dTagLucene
	 */
	protected Document toDocument_original2(EmailMessage m) throws Exception {
		Document doc = new Document();
		doc.add(new StringField("id", String.valueOf(m.getId()), Store.YES));
		doc.add(new TextField("from", m.getFrom(), Store.YES));
		doc.add(new StringField("to", m.getTo(), Store.YES));
		doc.add(new StringField("cc", m.getCc(), Store.YES));
		doc.add(new StringField("bcc", m.getBcc(), Store.YES));
		doc.add(new TextField("subject", m.getSubject(), Store.YES));
		doc.add(new TextField("message", m.getMessage(), Store.YES));
		doc.add(new StringField("date", DateTools.dateToString(m.getDate(), DateTools.Resolution.SECOND), Store.YES));
		doc.add(new StringField("folderId", m.getFolder().getId(), Store.YES));
		return doc;
	}
}