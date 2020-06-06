package
irisdeltaj.simplerelationalsearchaddressbook.br.unb.cic.iris.persistence;

import
irisdeltaj.simplerelationalsearchaddressbook.br.unb.cic.iris.core.exception.EmailException;
import
irisdeltaj.simplerelationalsearchaddressbook.br.unb.cic.iris.core.model.AddressBookEntry;
/*** added by dAddressBook
 */
public interface IAddressBookDAO {
	public void save(AddressBookEntry entry) throws EmailException;
	public AddressBookEntry find(String nick) throws EmailException;
	public void delete(String nick) throws EmailException;
}