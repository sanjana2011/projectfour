package in.co.rays.Proj4.util;


import java.util.ResourceBundle;

/**
 * Utility class to read messages or property values from a ResourceBundle,
 * with support for parameter replacement within message templates.
 * <p>
 * Uses a base resource bundle identified by
 * {@code in.co.rays.Proj4.bundle.system}. Provides methods to read
 * simple values, single-parameter, and multiple-parameter substitutions.
 * </p>
 * <p>
 * If a key is not found in the bundle, the key itself is returned as fallback.
 * Parameter placeholders such as "{0}", "{1}", etc. are replaced in order.
 * </p>
 * 
 * @author Sanjana Gangrade
 * @version 1.0
 * @since 1.0
 */
public class PropertyReader {

	private static ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.Proj4.bundle.system");

    /**
     * Retrieves the value for the given key from the resource bundle.
     * If the key is missing, returns the key itself.
     *
     * @param key the message key to look up
     * @return the corresponding message string, or the key if not found
     */
	public static String getValue(String key) {

		String val = null;

		try {
			val = rb.getString(key); // {0} is required
		} catch (Exception e) {
			val = key;
		}
		return val;
	}
	  /**
     * Retrieves the value for the given key and replaces a single parameter placeholder "{0}".
     *
     * @param key   the message key to look up
     * @param param the single value to substitute into the message
     * @return the formatted message string, with "{0}" replaced by the provided param
     */
	public static String getValue(String key, String param) {
		String msg = getValue(key); // {0} is required
		msg = msg.replace("{0}", param);
		return msg;
	}

    /**
     * Retrieves the value for the given key and replaces multiple parameter placeholders "{0}", "{1}", etc.
     *
     * @param key    the message key to look up
     * @param params an array of substitution values for placeholders
     * @return the formatted message string with each placeholder replaced
     */
	public static String getValue(String key, String[] params) {
		String msg = getValue(key);
		for (int i = 0; i < params.length; i++) {
			msg = msg.replace("{" + i + "}", params[i]);
		}
		return msg;
	}
	 /**
     * Simple main method demonstrating usage of getValue methods:
     * - single key lookup
     * - single parameter substitution
     * - multiple parameter substitution
     *
     * @param args command-line arguments (unused)
     */
	public static void main(String[] args) {

		System.out.println("Single key example:");
		System.out.println(PropertyReader.getValue("error.require"));

		System.out.println("\nSingle parameter replacement example:");
		System.out.println(PropertyReader.getValue("error.require", "loginId"));

		System.out.println("\nMultiple parameter replacement example:");
		String[] params = { "Roll No", "Student Name" };
		System.out.println(PropertyReader.getValue("error.multipleFields", params));
	}
}