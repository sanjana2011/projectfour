package in.co.rays.Proj4.util;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Utility class to handle common data conversion operations such as
 * String to int/long/date/timestamp and vice versa.
 * Provides date/time formatting and null safety methods.
 * 
 * @author Sanjana Gangrade
 */
public class DataUtility {

	public static final String APP_DATE_FORMAT = "dd-MM-yyyy";

	public static final String APP_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";

	private static final SimpleDateFormat formatter = new SimpleDateFormat(APP_DATE_FORMAT);

	private static final SimpleDateFormat timeFormatter = new SimpleDateFormat(APP_TIME_FORMAT);

    /**
     * Returns trimmed version of input string if not null.
     *
     * @param val input string
     * @return trimmed string or null
     */
	public static String getString(String val) {
		if (DataValidator.isNotNull(val)) {
			return val.trim();
		} else {
			return val;
		}
	}
	/**
     * Converts an object to its string representation.
     *
     * @param val the object to convert
     * @return string representation or empty string if object is null
     */
	public static String getStringData(Object val) {
		if (val != null) {
			return val.toString();
		} else {
			return "";
		}
	}

    /**
     * Converts a string to an integer.
     *
     * @param val the string to convert
     * @return integer value or 0 if conversion fails
     */
	public static int getInt(String val) {
		if (DataValidator.isInteger(val)) {
			return Integer.parseInt(val);
		} else {
			return 0;
		}
	}
	 /**
     * Converts a string to a long.
     *
     * @param val the string to convert
     * @return long value or 0 if conversion fails
     */
	public static long getLong(String val) {
		if (DataValidator.isLong(val)) {
			return Long.parseLong(val);
		} else {
			return 0;
		}
	}
	  /**
     * Parses a string into a Date object using the application date format.
     *
     * @param val the string to parse
     * @return Date object or null if parsing fails
     */
	public static Date getDate(String val) {
		Date date = null;
		try {
			date = formatter.parse(val);
		} catch (Exception e) {

		}
		return date;
	}
	  /**
     * Formats a Date object into a string using the application date format.
     *
     * @param date the Date object to format
     * @return formatted date string or empty string if formatting fails
     */
	public static String getDateString(Date date) {
		try {
			return formatter.format(date);
		} catch (Exception e) {
		}
		return "";
	}
	  /**
     * Placeholder method to manipulate a Date object by adding days.
     *
     * @param date the original Date object
     * @param day  number of days to add
     * @return modified Date object
     */
	public static Date getDate(Date date, int day) {
		return null;
	}
	   /**
     * Parses a string into a Timestamp object using the application timestamp format.
     *
     * @param val the string to parse
     * @return Timestamp object or null if parsing fails
     */
	public static Timestamp getTimestamp(String val) {

		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp((timeFormatter.parse(val)).getTime());
		} catch (Exception e) {
			return null;
		}
		return timeStamp;
	}
	  /**
     * Converts a long value into a Timestamp object.
     *
     * @param l the long value representing milliseconds since epoch
     * @return Timestamp object or null if conversion fails
     */
	public static Timestamp getTimestamp(long l) {

		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp(l);
		} catch (Exception e) {
			return null;
		}
		return timeStamp;
	}
	 /**
     * Retrieves the current system timestamp.
     *
     * @return current Timestamp object
     */
	public static Timestamp getCurrentTimestamp() {
		Timestamp timeStamp = null;
		try {
			timeStamp = new Timestamp(new Date().getTime());
		} catch (Exception e) {
		}
		return timeStamp;

	}
	/**
     * Extracts the time in milliseconds from a Timestamp object.
     *
     * @param tm the Timestamp object
     * @return time in milliseconds or 0 if extraction fails
     */
	public static long getTimestamp(Timestamp tm) {
		try {
			return tm.getTime();
		} catch (Exception e) {
			return 0;
		}
	}

	public static void main(String[] args) {
		// Test getString
		System.out.println("getString Test:");
		System.out.println("Original: '  Hello World  ' -> Trimmed: '" + getString("  Hello World  ") + "'");
		System.out.println("Null input: " + getString(null));

		// Test getStringData
		System.out.println("\ngetStringData Test:");
		System.out.println("Object to String: " + getStringData(1234));
		System.out.println("Null Object: '" + getStringData(null) + "'");

		// Test getInt
		System.out.println("\ngetInt Test:");
		System.out.println("Valid Integer String: '124' -> " + getInt("124"));
		System.out.println("Invalid Integer String: 'abc' -> " + getInt("abc"));
		System.out.println("Null String: -> " + getInt(null));

		// Test getLong
		System.out.println("\ngetLong Test:");
		System.out.println("Valid Long String: '123456789' -> " + getLong("123456789"));
		System.out.println("Invalid Long String: 'abc' -> " + getLong("abc"));

		// Test getDate
		System.out.println("\ngetDate Test:");
		String dateStr = "10/15/2024";
		Date date = getDate(dateStr);
		System.out.println("String to Date: '" + dateStr + "' -> " + date);

		// Test getDateString
		System.out.println("\ngetDateString Test:");
		System.out.println("Date to String: '" + getDateString(new Date()) + "'");

		// Test getTimestamp (String)
		System.out.println("\ngetTimestamp(String) Test:");
		String timestampStr = "10/15/2024 10:30:45";
		Timestamp timestamp = getTimestamp(timestampStr);
		System.out.println("String to Timestamp: '" + timestampStr + "' -> " + timestamp);

		// Test getTimestamp (long)
		System.out.println("\ngetTimestamp(long) Test:");
		long currentTimeMillis = System.currentTimeMillis();
		Timestamp ts = getTimestamp(currentTimeMillis);
		System.out.println("Current Time Millis to Timestamp: '" + currentTimeMillis + "' -> " + ts);

		// Test getCurrentTimestamp
		System.out.println("\ngetCurrentTimestamp Test:");
		Timestamp currentTimestamp = getCurrentTimestamp();
		System.out.println("Current Timestamp: " + currentTimestamp);

		// Test getTimestamp(Timestamp)
		System.out.println("\ngetTimestamp(Timestamp) Test:");
		System.out.println("Timestamp to long: " + getTimestamp(currentTimestamp));
	}
}