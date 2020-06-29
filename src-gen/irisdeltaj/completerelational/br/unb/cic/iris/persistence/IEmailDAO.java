package irisdeltaj.completerelational.br.unb.cic.iris.persistence;

import java.util.Date;
import java.util.List;
import irisdeltaj.completerelational.br.unb.cic.iris.core.exception.EmailException;
import irisdeltaj.completerelational.br.unb.cic.iris.core.model.EmailMessage;

/***
 * added by dBasePersistence* modified by dTagBase
 */
public interface IEmailDAO {
	public void saveMessage(EmailMessage message) throws EmailException;

	public Date lastMessageReceived() throws EmailException;

	public List<EmailMessage> listMessages(String idFolder) throws EmailException;

	/***
	 * added by dTagBase
	 */
	public List<EmailMessage> listMessagesByTag(String tag) throws EmailException;
	
	/***
	 * added by dCategoryBase
	 */
	public List<EmailMessage> listMessagesByCategory(String category) throws EmailException;
}