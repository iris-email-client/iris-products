package irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.persistence;

import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.core.exception.EmailException;
import
irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.core.model.Tag;
/*** added by dTagBase
 */
public interface ITagDAO {
	public Tag findById(String id) throws EmailException;
	public Tag findByName(String name) throws EmailException;
	public java.util.List<Tag> findAll() throws EmailException;
	public void saveOrUpdate(Tag tag) throws EmailException;
	public void save(Tag tag, String messageId) throws EmailException;
	public void delete(Tag t) throws EmailException;
}