package irisdeltaj.simplerelationaladdressbook.br.unb.cic.iris.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Id;
import javax.persistence.Table;
/*** added by dAddressBookRelational
 */
@Entity
@Table(name = "TB_ADDRESS_BOOK")
public class AddressBookEntry {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	@Column(unique = true)
	private String nick;
	@Column
	private String address;
	public AddressBookEntry() {
	}
	public AddressBookEntry(String nick, String address) {
		this.nick = nick;
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}