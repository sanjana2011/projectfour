package in.co.rays.Proj4.util;
import java.util.Properties;
import java.util.ResourceBundle;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import in.co.rays.Proj4.exception.ApplicationException;
/**
 * Utility class for sending email messages via SMTP using JavaMail API.
 * <p>
 * Reads SMTP configuration from a ResourceBundle and configures {@link javax.mail.Session}
 * for authenticated, TLS-enabled sending.
 * </p>
 * Provides a single public method to send messages using {@link EmailMessage}.
 * Errors during sending are wrapped in {@link ApplicationException}.
 * </p>
 * <p>
 * Uses JavaMail API: {@link Session}, {@link MimeMessage}, {@link Transport}, and {@link InternetAddress}.
 * </p>
 *
 * @author Sanjana Gangrade
 * @version 1.0
 */
public class EmailUtility {

	static ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.Proj4.bundle.system");

	private static final String SMTP_HOST_NAME = rb.getString("smtp.server");
	private static final String SMTP_PORT = rb.getString("smtp.port");
	private static final String emailFromAddress = rb.getString("email.login");
	private static final String emailPassword = rb.getString("email.pwd");

	private static Properties props = new Properties();

	static {
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.port", SMTP_PORT);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");
	}
	 /**
     * Sends an email message using SMTP configuration loaded from system bundle.
     * <p>
     * Creates a {@link Session} authenticated with configured credentials,
     * builds a {@link MimeMessage}, sets sender, recipients, subject, and content (HTML or text),
     * then dispatches via {@link Transport.send(Message)}.
     * </p>
     *
     * @param emailMessageDTO the {@link EmailMessage} object encapsulating recipient(s), subject,
     *                        body, and message type (HTML or TEXT)
     * @throws ApplicationException if any error occurs during message composition or sending,
     *                              wrapping the underlying exception message
     */
	public static void sendMail(EmailMessage emailMessageDTO) throws ApplicationException {
		try {
			// Setup mail session
			Session session = Session.getDefaultInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(emailFromAddress, emailPassword);
				}
			});

			// Create and setup the email message
			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(emailFromAddress));
			msg.setRecipients(Message.RecipientType.TO, getInternetAddresses(emailMessageDTO.getTo()));
			msg.setSubject(emailMessageDTO.getSubject());

			// Set content type based on message type
			String contentType = emailMessageDTO.getMessageType() == EmailMessage.HTML_MSG ? "text/html" : "text/plain";
			msg.setContent(emailMessageDTO.getMessage(), contentType);

			// Send the email
			Transport.send(msg);

		} catch (Exception ex) {
			throw new ApplicationException("Email Error: " + ex.getMessage());
		}
	}
	  /**
     * Parses a comma-separated list of email addresses into InternetAddress array.
     *
     * @param emails a comma-separated string of email addresses
     * @return an array of {@link InternetAddress}, empty if input is null or empty
     * @throws Exception if parsing of any address fails
     */
	private static InternetAddress[] getInternetAddresses(String emails) throws Exception {
		if (emails == null || emails.isEmpty()) {
			return new InternetAddress[0];
		}
		String[] emailArray = emails.split(",");
		InternetAddress[] addresses = new InternetAddress[emailArray.length];
		for (int i = 0; i < emailArray.length; i++) {
			addresses[i] = new InternetAddress(emailArray[i].trim());
		}
		return addresses;
	}
}
