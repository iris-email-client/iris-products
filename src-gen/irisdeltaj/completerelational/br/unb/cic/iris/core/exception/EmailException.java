package irisdeltaj.completerelational.br.unb.cic.iris.core.exception;

/***
 * added by dBaseException
 */
public class EmailException extends Exception {
	private static final long serialVersionUID = -6229420404810056559L;

	public EmailException(String message) {
		super(message);
	}

	public EmailException(String message, Exception error) {
		super(message, error);
	}
}