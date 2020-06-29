package irisdeltaj.completerelational.br.unb.cic.iris.core.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/***
 * added by dPersistenceRelational* modified by dTagRelational
 */
@Entity
@Table(name = "TB_MESSAGE")
public class EmailMessage extends FolderContent {
	@Column(name = "MSG_FROM", length = 1024)
	private String from;
	@Column(name = "RECIPIENT", length = 1024)
	private String to;
	@Column(name = "CC_RECIPIENT", length = 1024)
	private String cc;
	@Column(name = "BCC_RECIPIENT", length = 1024)
	private String bcc;
	@Column(name = "SUBJECT", length = 1024)
	private String subject;
	@Column(name = "MESSAGE_CONTENT", columnDefinition = "TEXT")
	private String message;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATE")
	private Date date;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "FOLDER_ID", nullable = false)
	private IrisFolder folder;

	public EmailMessage() {
	}

	public EmailMessage(String to, String subject, String message) {
		this(null, to, subject, message);
	}

	public EmailMessage(String from, String to, String subject, String message, Date date, IrisFolder folder) {
		this(from, to, null, null, subject, message, date, folder);
	}

	public EmailMessage(String from, String to, String subject, String message) {
		this(from, to, null, null, subject, message);
	}

	public EmailMessage(String from, String to, String cc, String bcc, String subject, String message) {
		this(from, to, cc, bcc, subject, message, null, null);
	}

	public EmailMessage(String from, String to, String cc, String bcc, String subject, String message, Date date,
			IrisFolder folder) {
		this.from = from;
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.subject = subject;
		this.message = message;
		this.date = date;
		this.folder = folder;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public String getCc() {
		return cc;
	}

	public String getBcc() {
		return bcc;
	}

	public String getSubject() {
		return subject;
	}

	public String getMessage() {
		return message;
	}

	public IrisFolder getFolder() {
		return folder;
	}

	public void setFolder(IrisFolder folder) {
		this.folder = folder;
	}

	@Override
	public String toString() {
		return String.format("%s - %s - %s - %s", getId(), getFrom(), getSubject(), getDate());
	}

	/***
	 * added by dTagRelational
	 */
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "message_tags", joinColumns = {
			@JoinColumn(name = "ID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "TAG_ID", nullable = false, updatable = false) })
	private Set<Tag> tags;

	/***
	 * added by dTagRelational
	 */
	public Set<Tag> getTags() {
		return tags;
	}

	/***
	 * added by dTagRelational
	 */
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	
	/***
	 * added by dCategoryRelational
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CATEGORY_ID", nullable = false)
	private Category category;

	/***
	 * added by dCategoryRelational
	 */
	public Category getCategory() {
		return category;
	}

	/***
	 * added by dCategoryRelational
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
}