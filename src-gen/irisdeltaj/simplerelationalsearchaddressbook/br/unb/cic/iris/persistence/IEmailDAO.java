package
irisdeltaj.simplerelationalsearchaddressbook.br.unb.cic.iris.persistence;

import java.util.Date;
import java.util.List;
import
irisdeltaj.simplerelationalsearchaddressbook.br.unb.cic.iris.core.exception.EmailException;
import
irisdeltaj.simplerelationalsearchaddressbook.br.unb.cic.iris.core.model.EmailMessage;
/*** added by dBasePersistence
 */
public interface IEmailDAO {
	public void saveMessage(EmailMessage message) throws EmailException;
	public Date lastMessageReceived() throws EmailException;
	public List<EmailMessage> listMessages(String idFolder) throws EmailException;
}