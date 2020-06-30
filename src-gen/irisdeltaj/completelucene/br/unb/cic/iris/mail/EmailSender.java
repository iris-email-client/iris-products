package irisdeltaj.completelucene.br.unb.cic.iris.mail;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.event.TransportEvent;
import javax.mail.event.TransportListener;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import irisdeltaj.completelucene.br.unb.cic.iris.i18n.MessageBundle;
import irisdeltaj.completelucene.br.unb.cic.iris.util.StringUtil;
import irisdeltaj.completelucene.br.unb.cic.iris.core.exception.EmailException;
import irisdeltaj.completelucene.br.unb.cic.iris.core.exception.EmailMessageValidationException;
import irisdeltaj.completelucene.br.unb.cic.iris.core.model.EmailMessage;

/***
 * added by dBaseMail
 */
public class EmailSender implements TransportListener {
	private EmailSession session;
	private EmailProvider provider;

	public EmailSender(EmailProvider provider, String encoding) {
		this.provider = provider;
		session = new EmailSession(provider, encoding);
	}

	public void send(EmailMessage email) throws EmailException {
		List<String> errorMessages = validateEmailMessage(email);
		if (errorMessages.isEmpty()) {
			try {
				final Message message = createMessage(email);
				message.saveChanges();
				Transport transport = createTransport();
				session.connect(transport, provider.getTransportHost(), provider.getTransportPort());
				System.out.println("Sending message ...");
				transport.sendMessage(message, message.getAllRecipients());
				transport.close();
			} catch (final UnsupportedEncodingException e) {
				throw new EmailException(MessageBundle.message("error.invalid.encoding", e.getMessage()));
			} catch (final MessagingException e) {
				throw new EmailException(MessageBundle.message("error.send.email", e.getMessage()));
			}
		} else {
			throw new EmailMessageValidationException(errorMessages);
		}
	}

	public static List<String> validateEmailMessage(EmailMessage message) {
		List<String> errorMessages = new ArrayList<String>();
		if (message == null) {
			errorMessages.add(MessageBundle.message("error.null.message"));
		} else if (StringUtil.isEmpty(message.getFrom())) {
			errorMessages.add(
					MessageBundle.message("error.required.field", MessageBundle.message("command.send.label.from")));
		}
		return errorMessages;
	}

	private Transport createTransport() throws MessagingException {
		System.out.println("Creating transport: " + provider.getTransportProtocol());
		Transport transport = session.getSession().getTransport(provider.getTransportProtocol());
		transport.addTransportListener(this);
		transport.addConnectionListener(session);
		return transport;
	}

	private Message createMessage(final EmailMessage email) throws MessagingException, UnsupportedEncodingException {
		final MimeMessage message = new MimeMessage(session.getSession());
		message.setSubject(email.getSubject(), session.getEncoding());
		message.setFrom(new InternetAddress(email.getFrom(), session.getEncoding()));
		message.setRecipient(RecipientType.TO, new InternetAddress(email.getTo()));
		message.setText(email.getMessage(), session.getEncoding());
		if (StringUtil.notEmpty(email.getCc())) {
			message.setRecipient(RecipientType.CC, new InternetAddress(email.getCc()));
		}
		if (StringUtil.notEmpty(email.getBcc())) {
			message.setRecipient(RecipientType.BCC, new InternetAddress(email.getBcc()));
		}
		message.setSentDate(new Date());
		return message;
	}

	@Override
	public void messageDelivered(TransportEvent e) {
		System.out.println("Message delivered ... ");
	}

	@Override
	public void messageNotDelivered(TransportEvent e) {
		System.out.println("Message not delivered ... ");
	}

	@Override
	public void messagePartiallyDelivered(TransportEvent e) {
		System.out.println("Message partially delivered ... ");
	}
}