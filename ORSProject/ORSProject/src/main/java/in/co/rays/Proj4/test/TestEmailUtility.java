package in.co.rays.Proj4.test;

import java.util.HashMap;

import in.co.rays.Proj4.exception.ApplicationException;
import in.co.rays.Proj4.util.EmailBuilder;
import in.co.rays.Proj4.util.EmailMessage;
import in.co.rays.Proj4.util.EmailUtility;
/**
 * Test class to demonstrate sending an email using the EmailUtility.
 * 
 * This class simulates a user registration scenario where:
 * <ul>
 *   <li>An email message is composed using EmailBuilder</li>
 *   <li>The message is sent using EmailUtility</li>
 * </ul>
 * 
 * The email contains login and password details formatted in HTML.
 * 
 * Dependencies:
 * <ul>
 *   <li>EmailBuilder - to construct email content</li>
 *   <li>EmailMessage - to encapsulate email properties</li>
 *   <li>EmailUtility - to send the actual email</li>
 * </ul>
 * 
 * Note: Update the email address and credentials with valid values for real execution.
 * 
 * @author Sanjana Gangrade
 */
public class TestEmailUtility {

    /**
     * Main method to execute the email sending test.
     * Prepares the email content and sends it to the specified address.
     * 
     * @param args command-line arguments (not used)
     */

	public static void main(String[] args) {
		
		try {
	
			
		HashMap<String , String>map = new HashMap <String , String> ();
		map.put("login", "gangradesanjana170@gmail.com");
		map.put("password", "pass123");
		
		String message = EmailBuilder.getUserRegistrationMessage(map);
		
		EmailMessage emailMessage = new EmailMessage ();
		emailMessage.setTo ("gangradesanjana170@gmail.com");
		emailMessage.setSubject("Registration is successful for ORS Project");
		emailMessage.setMessage(message);
		emailMessage.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(emailMessage);

		System.out.println("Email sent successfully.");
		
		
		}catch (ApplicationException ex) {
			ex.printStackTrace();
			System.err.println("Failed to send email: " + ex.getMessage());
		}
		
	}
}
