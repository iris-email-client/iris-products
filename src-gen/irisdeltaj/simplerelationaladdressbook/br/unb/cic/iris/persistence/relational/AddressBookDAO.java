package
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.persistence.relational;

import java.util.List;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.core.exception.DBException;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.core.model.AddressBookEntry;
import
irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.persistence.IAddressBookDAO;
/*** added by dAddressBookRelational
 */
public final class AddressBookDAO extends AbstractDAO<AddressBookEntry>
implements IAddressBookDAO {
	private static final String FIND_BY_NICK_NAME = "FROM AddressBookEntry a " +
	"where a.nick = :pNick";
	private static AddressBookDAO instance;
	private AddressBookDAO() {
	}
	public static AddressBookDAO instance() {
		if(instance == null) {
			instance = new AddressBookDAO();
		}
		return instance;
	}
	@Override
	public void save(AddressBookEntry entry) throws DBException {
		super.saveOrUpdate(entry);
	}
	@Override
	public AddressBookEntry find(String nick) throws DBException {
		try {
			startSession(false);
			List<AddressBookEntry> entries =
			session.createQuery(FIND_BY_NICK_NAME).setParameter("pNick",
				nick).setCacheable(false).list();
			if(entries != null && entries.size() == 1) {
				return entries.get(0);
			}
			return null;
		}
		catch(Exception e) {
			throw new DBException("could not save the address book entry", e);
		}
		finally {
			closeSession();
		}
	}
	@Override
	public void delete(String nick) throws DBException {
		AddressBookEntry entry = find(nick);
		if(entry != null) {
			super.delete(entry);
		}
	}
}