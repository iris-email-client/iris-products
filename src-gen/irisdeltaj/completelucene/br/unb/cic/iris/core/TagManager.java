package irisdeltaj.completelucene.br.unb.cic.iris.core;

import irisdeltaj.completelucene.br.unb.cic.iris.core.exception.EmailException;
import irisdeltaj.completelucene.br.unb.cic.iris.core.model.EmailMessage;
import irisdeltaj.completelucene.br.unb.cic.iris.core.model.Tag;
import irisdeltaj.completelucene.br.unb.cic.iris.persistence.ITagDAO;
import irisdeltaj.completelucene.br.unb.cic.iris.persistence.lucene.TagDAO;
import irisdeltaj.completelucene.br.unb.cic.iris.persistence.lucene.EmailDAO;

/***
 * added by dTagBase* modified by dTagLucene
 */
public final class TagManager {
	private static final TagManager instance = new TagManager();

	private TagManager() {
	}

	public static TagManager instance() {
		return instance;
	}

	/***
	 * modified by dTagLucene
	 */
	public ITagDAO getTagDAO() {
		return TagDAO.instance();
	}

	public Tag findById(String id) throws EmailException {
		return getTagDAO().findById(id);
	}

	public Tag findByName(String name) throws EmailException {
		return getTagDAO().findByName(name);
	}

	public java.util.List<Tag> findAll() throws EmailException {
		return getTagDAO().findAll();
	}

	public void saveOrUpdate(Tag tag) throws EmailException {
		getTagDAO().saveOrUpdate(tag);
	}

	public void delete(Tag tag) throws EmailException {
		getTagDAO().delete(tag);
	}

	public void saveTags(String messageId, String tagsStr) throws EmailException {
		String[] tags = tagsStr.trim().split(",");
		for (String str : tags) {
			Tag tag = new Tag(str.trim());
			getTagDAO().save(tag, messageId);
		}
	}

	public java.util.List<EmailMessage> listMessagesByTag(String tag) throws EmailException {
		return EmailDAO.instance().listMessagesByTag(tag);
	}
}