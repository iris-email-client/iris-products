package irisdeltaj.simplerelationaladdressbooktag.br.unb.cic.iris.core.model;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
/*** added by dTagRelational
 */
@Entity
@Table(name = "TB_TAG")
public class Tag {
	@Id
	@GeneratedValue(generator = "uuid")
	@Column(name = "TAG_ID", nullable = false, updatable = false)
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;
	@Column(unique = true)
	private String name;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "tags")
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