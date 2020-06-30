package irisdeltaj.completelucene.br.unb.cic.iris.core.model;

import java.util.Set;

/***
 * added by dTagLucene
 */
public class Tag {
	private String id;
	private String name;
	private Set<EmailMessage> messages;

	public Tag() {
		this(null);
	}

	public Tag(String name) {
		this(null, name);
	}

	public Tag(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<EmailMessage> getMessages() {
		return messages;
	}

	public void setMessages(Set<EmailMessage> messages) {
		this.messages = messages;
	}
}