package
irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.persistence.relational;

import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import
irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.core.exception.DBException;
import
irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.core.model.EmailMessage;
import
irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.persistence.IEmailDAO;
import
irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.core.model.IrisFolder;
import java.util.ArrayList;
import
irisdeltaj.simplerelationalsearchtag.br.unb.cic.iris.core.exception.EmailException;
/*** added by dPersistenceRelational* modified by dSimpleSearch* modified by
dTagRelational
 */
public final class EmailDAO extends AbstractDAO<EmailMessage> implements
IEmailDAO {
	private static final String FIND_MAX_DATE =
	"select max(e.date) FROM EmailMessage e";
	private static EmailDAO instance = new EmailDAO();
	private EmailDAO() {
	}
	public static EmailDAO instance() {
		return instance;
	}
	@Override
	public void saveMessage(EmailMessage message) throws DBException {
		super.saveOrUpdate(message);
	}
	@Override
	public Date lastMessageReceived() throws DBException {
		Date date = null;
		try {
			startSession(false);
			date = ( Date ) session.createQuery(FIND_MAX_DATE).uniqueResult();
		}
		finally {
			closeSession();
		}
		return date;
	}
	public EmailMessage findById(String id) throws DBException {
		EmailMessage obj = null;
		try {
			startSession(false);
			obj = populate(( EmailMessage ) session.load(EmailMessage.class, id));
		}
		catch(Exception e) {
			handleException(e);
		}
		finally {
			closeSession();
		}
		return obj;
	}
	public List<EmailMessage> listMessages(String idFolder) throws DBException {
		List<EmailMessage> messages = new java.util.ArrayList<EmailMessage>();
		try {
			startSession(false);
			Criteria criteria =
			session.createCriteria(EmailMessage.class).createCriteria("folder").add(Restrictions.idEq(idFolder));
			messages = populate(( List<EmailMessage> ) criteria.list());
		}
		catch(Exception e) {
			handleException(new DBException("Error listing messages from folder: " +
					idFolder, e));
		}
		finally {
			closeSession();
		}
		return messages;
	}
	private List<EmailMessage> populate(List<EmailMessage> in) {
		List<EmailMessage> list = new java.util.ArrayList<EmailMessage>();
		for(EmailMessage msg : in) {
			list.add(populate(msg));
		}
		return list;
	}
	private EmailMessage populate(EmailMessage in) {
		if(in == null) {
			return null;
		}
		EmailMessage msg = new EmailMessage(in.getFrom(), in.getTo(), in.getCc(),
			in.getBcc(), in.getSubject(), in.getMessage());
		msg.setId(in.getId());
		msg.setFolder(new IrisFolder(in.getFolder().getId(),
				in.getFolder().getName()));
		msg.setDate(in.getDate());
		return msg;
	}
	/*** added by dSimpleSearch
	 */
	public List<EmailMessage> search(String queryStr) throws EmailException {
		String FIND =
		"select e FROM EmailMessage e where e.from like :text or e.to like :text or e.cc like :text or e. cc like :text or e.subject like :text or e.message like :text";
		List<EmailMessage> result = new ArrayList<EmailMessage>();
		try {
			startSession(false);
			result = session.createQuery(FIND).setParameter("text", "%" + queryStr +
				"%").list();
		}
		catch(Exception e) {
			e.printStackTrace();
			throw new
			EmailException("Error ocurred while retrieving messages from folder.", e);
		}
		finally {
			closeSession();
		}
		return result;
	}
	/*** added by dTagRelational
	 */
	@Override
	public List<EmailMessage> listMessagesByTag(String tag) throws DBException {
		List<EmailMessage> messages = new java.util.ArrayList<EmailMessage>();
		try {
			startSession(false);
			Criteria criteria =
			session.createCriteria(EmailMessage.class).createCriteria("tags").add(Restrictions.eq("name",
					tag));
			messages = populate(( List<EmailMessage> ) criteria.list());
			System.out.println("messages: " + messages);
		}
		catch(Exception e) {
			handleException(new DBException("Error listing messages with tag: " + tag,
					e));
		}
		finally {
			closeSession();
		}
		return messages;
	}
}