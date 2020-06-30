package irisdeltaj.completelucene.br.unb.cic.iris.core.model;

/***
 * added by dAddressBook
 */
public class AddressBookEntry {
	private String id;
	private String nick;
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