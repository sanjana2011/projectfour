
package in.co.rays.Proj4.util;

import java.util.HashMap;
/**
 * Utility class to build HTML email messages for user-related actions.
 * Provides methods to generate email content for registration, password recovery,
 * and password change notifications.
 * <p>This class contains only static methods and should not be instantiated.</p>
 * 
 * @author Sanjana Gangrade
 * @version 1.0
 */
public class EmailBuilder {
	 /**
     * Private constructor to prevent instantiation of utility class.
     */
    private EmailBuilder() {
        // Prevent instantiation
    }

    /**
     * Builds an HTML message confirming successful user registration.
     *
     * @param map a map containing user details: {@code "login"} and {@code "password"}
     * @return HTML-formatted welcome message including login and password
     */
	public static String getUserRegistrationMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();
		msg.append("<HTML><BODY>");
		msg.append("<H1>Welcome to ORS, ").append(map.get("login")).append("!</H1>");
		msg.append("<P>Your registration is successful. You can now log in and manage your account.</P>");
		msg.append("<P><B>Login Id: ").append(map.get("login")).append("<BR>Password: ").append(map.get("password"))
				.append("</B></P>");
		msg.append("<P>Change your password after logging in for security reasons.</P>");
		msg.append("<P>For support, contact +91 98273 60504 or hrd@sunrays.co.in.</P>");
		msg.append("</BODY></HTML>");
		return msg.toString();
	}

    /**
     * Builds an HTML message for password recovery (forgot password).
     *
     * @param map a map containing user details: {@code "firstName"}, {@code "lastName"},
     *            {@code "login"}, and {@code "password"}
     * @return HTML-formatted password recovery message personalized with the user's name
     */
	public static String getForgetPasswordMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();
		msg.append("<HTML><BODY>");
		msg.append("<H1>Password Recovery</H1>");
		msg.append("<P>Hello, ").append(map.get("firstName")).append(" ").append(map.get("lastName")).append(".</P>");
		msg.append("<P>Your login details are:</P>");
		msg.append("<P><B>Login Id: ").append(map.get("login")).append("<BR>Password: ").append(map.get("password"))
				.append("</B></P>");
		msg.append("</BODY></HTML>");
		return msg.toString();
	}
	/**
     * Builds an HTML notification message for successful password change.
     *
     * @param map a map containing user details: {@code "firstName"}, {@code "lastName"},
     *            {@code "login"}, and {@code "password"} (new one)
     * @return HTML-formatted confirmation message including updated login details
     */
	public static String getChangePasswordMessage(HashMap<String, String> map) {
		StringBuilder msg = new StringBuilder();
		msg.append("<HTML><BODY>");
		msg.append("<H1>Password Changed Successfully</H1>");
		msg.append("<P>Dear ").append(map.get("firstName")).append(" ").append(map.get("lastName"))
				.append(", your password has been updated.</P>");
		msg.append("<P>Your updated login details are:</P>");
		msg.append("<P><B>Login Id: ").append(map.get("login")).append("<BR>New Password: ").append(map.get("password"))
				.append("</B></P>");
		msg.append("</BODY></HTML>");
		return msg.toString();
	}
}
