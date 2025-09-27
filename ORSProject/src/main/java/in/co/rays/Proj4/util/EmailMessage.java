
package in.co.rays.Proj4.util;

public class EmailMessage {

	private String to;
	private String subject;
	private String message;
	private int messageType = TEXT_MSG;

	public static final int HTML_MSG = 1;
	public static final int TEXT_MSG = 2;
	/**
	 * Represents an email message with recipient, subject, body, and type information.
	 * Supports both plain-text and HTML formats as defined by {@link #TEXT_MSG} and {@link #HTML_MSG}.
	 * <p>
	 * Instances of this class encapsulate all data needed for sending an email.
	 * </p>
	 * 
	 * @author Sanjana Gangrade
	 * @version 1.0
	 */
	public EmailMessage() {
	}

	public EmailMessage(String to, String subject, String message) {
		this.to = to;
		this.subject = subject;
		this.message = message;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getTo() {
		return to;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public int getMessageType() {
		return messageType;
	}
}
