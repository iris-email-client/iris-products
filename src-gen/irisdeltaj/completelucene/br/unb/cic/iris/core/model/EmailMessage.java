package irisdeltaj.completelucene.br.unb.cic.iris.core.model;

import java.util.Date;
import java.util.Set;
import java.util.HashSet;

/***
 * added by dBaseModel* modified by dTagLucene
 */
public class EmailMessage extends FolderContent {
	private String from;
	private String to;
	private String cc;
	private String bcc;
	private String subject;
	private String message;
	private Date date;
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

	/***
	 * added by dTagLucene
	 */
	private Set<Tag> tags;

	/***
	 * added by dTagLucene
	 */
	public Set<Tag> getTags() {
		return tags;
	}

	/***
	 * added by dTagLucene
	 */
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	/***
	 * added by dTagLucene
	 */
	public void addTag(Tag tag) {
		if (tags == null) {
			tags = new HashSet<Tag>();
		}
		this.tags.add(tag);
	}
}