package in.co.rays.Proj4.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import in.co.rays.Proj4.bean.DropdownListBean;
import in.co.rays.Proj4.bean.RoleBean;
import in.co.rays.Proj4.model.RoleModel;

/**
 * Utility class to generate HTML `<select>` (dropdown) elements from
 * collections.
 * <p>
 * Supports rendering options from:
 * <ul>
 * <li>a {@link HashMap} mapping values to display text</li>
 * <li>a {@link List} of {@link DropdownListBean} instances</li>
 * </ul>
 * Automatically includes a default "Select" option and marks a given value as
 * selected.
 * </p>
 * <p>
 * This class provides only static methods and should not be instantiated.
 * </p>
 * 
 * @author Sanjana Gangrade
 * @version 1.0
 * @since 1.0
 */
public class HTMLUtility {
	/**
	 * Generates an HTML dropdown list from a map of key/value pairs.
	 *
	 * @param name        the HTML name attribute for the `<select>` tag
	 * @param selectedVal the key value to be marked as selected (can be null or
	 *                    empty)
	 * @param map         a {@code HashMap<String,String>} where the key is option
	 *                    value and the value is display text
	 * @return a {@code String} containing the complete HTML `<select>` element
	 */
	public static String getList(String name, String selectedVal, HashMap<String, String> map) {

		StringBuffer sb = new StringBuffer(
				"<select style=\"width: 170px;text-align-last: center;\"; class='form-control' name='" + name + "'>");

		sb.append("\n<option selected value=''>-------------Select-------------</option>");

		Set<String> keys = map.keySet();
		String val = null;

		for (String key : keys) {
			val = map.get(key);
			if (key.trim().equals(selectedVal)) {
				sb.append("\n<option selected value='" + key + "'>" + val + "</option>");
			} else {
				sb.append("\n<option value='" + key + "'>" + val + "</option>");
			}
		}
		sb.append("\n</select>");
		return sb.toString();
	}

	/**
	 * Generates an HTML dropdown list from a list of {@link DropdownListBean}.
	 *
	 * @param name        the HTML name attribute for the `<select>` tag
	 * @param selectedVal the key value to be marked as selected (can be null or
	 *                    empty)
	 * @param list        a {@code List} of {@link DropdownListBean}, each with
	 *                    {@code getKey()} and {@code getValue()}
	 * @return a {@code String} containing the complete HTML `<select>` element
	 */
	public static String getList(String name, String selectedVal, List list) {
		if (list == null) {
			list = new ArrayList<DropdownListBean>();
		}

		List<DropdownListBean> dd = (List<DropdownListBean>) list;

		StringBuffer sb = new StringBuffer("<select style=\"width: 170px;text-align-last: center;\"; "
				+ "class='form-control' name='" + name + "'>");

		sb.append("\n<option selected value=''>-------------Select-------------</option>");

		String key = null;
		String val = null;

		for (DropdownListBean obj : dd) {
			
			key = obj.getKey();
			val = obj.getValue();

			if (key != null && key.trim().equals(selectedVal)) {
				sb.append("\n<option selected value='" + key + "'>" + val + "</option>");
			} else {
				sb.append("\n<option value='" + key + "'>" + val + "</option>");
			}
		}
		sb.append("\n</select>");
		return sb.toString();
	}

	/**
	 * A test method demonstrating generation of HTML from a simple map. Prints the
	 * HTML dropdown to standard output.
	 */
	public static void testGetListByMap() {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("male", "male");
		map.put("female", "female");

		String selectedValue = "null";
		String htmlSelectFromMap = HTMLUtility.getList("gender", selectedValue, map);

		System.out.println(htmlSelectFromMap);
	}

	/**
	 * A test method demonstrating generation of HTML from a list of RoleBean
	 * objects. Prints the HTML dropdown to standard output.
	 *
	 * @throws Exception if retrieval of RoleBean list fails (e.g. model error)
	 */
	public static void testGetListByList() throws Exception {

		RoleModel model = new RoleModel();

		List<RoleBean> list = model.list();

		String selectedValue = null;

		String htmlSelectFromList = HTMLUtility.getList("fname", selectedValue, list);

		System.out.println(htmlSelectFromList);
	}

	public static void main(String[] args) throws Exception {

		// testGetListByMap();

		testGetListByList();

	}
}