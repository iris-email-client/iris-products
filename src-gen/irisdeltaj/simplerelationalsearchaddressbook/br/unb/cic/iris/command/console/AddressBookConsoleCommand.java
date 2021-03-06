package
irisdeltaj.simplerelationalsearchaddressbook.br.unb.cic.iris.command.console;

import java.security.InvalidParameterException;
import java.util.List;
import
irisdeltaj.simplerelationalsearchaddressbook.br.unb.cic.iris.command.AbstractMailCommand;
import
irisdeltaj.simplerelationalsearchaddressbook.br.unb.cic.iris.core.SystemFacade;
import
irisdeltaj.simplerelationalsearchaddressbook.br.unb.cic.iris.core.exception.EmailException;
import
irisdeltaj.simplerelationalsearchaddressbook.br.unb.cic.iris.core.exception.EmailUncheckedException;
import
irisdeltaj.simplerelationalsearchaddressbook.br.unb.cic.iris.core.model.AddressBookEntry;
import
irisdeltaj.simplerelationalsearchaddressbook.br.unb.cic.iris.util.EmailValidator;
/*** added by dAddressBookConsole
 */
public class AddressBookConsoleCommand extends AbstractMailCommand {
	@Override
	public void explain() {
		System.out.println("(ab list) - show the address book entries");
		System.out.println("(ab add <name> <email>) - add an address book entry (name=email)");
		System.out.println("(ab delete <name> - delete an address book entry");
	}
	@Override
	public String getCommandName() {
		return "ab";
	}
	@Override
	protected void handleExecute() throws EmailException {
		switch(parameters.get(0)) {
			case "list" : listAll();
			break;
			case "add" : add();
			break;
			case "delete" : delete();
			break;
			default : throw new EmailException(parameters.get(0) +
				" is an invalid command");
		}
	}
	private void listAll() throws EmailException {
		List<AddressBookEntry> entries = SystemFacade.instance().listAddressBook();
		System.out.println("-----------------------------------------------------");
		System.out.println("Address Book:");
		System.out.println("-----------------------------------------------------");
		for(AddressBookEntry e : entries) {
			System.out.println(" " + e.getNick() + " -> " + e.getAddress());
		}
		System.out.println("-----------------------------------------------------");
	}
	private void delete() throws EmailException {
		if(parameters.size() == 2) {
			String name = parameters.get(1);
			SystemFacade.instance().deleteAddressBookEntry(name);
		}
		else {
			throw new InvalidParameterException();
		}
	}
	private void add() throws EmailException {
		if(parameters.size() == 3) {
			String name = parameters.get(1);
			String email = parameters.get(2);
			if(! EmailValidator.validate(email)) {
				throw new EmailUncheckedException("invalid email");
			}
			SystemFacade.instance().addAddressBookEntry(name, email);
		}
		else {
			throw new InvalidParameterException();
		}
	}
}