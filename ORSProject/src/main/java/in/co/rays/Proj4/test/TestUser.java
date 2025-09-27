/**
 * TestUser class is used to test the functionalities of the UserModel.
 * It tests operations such as add, update, delete, findByPk, findByLogin,
 * authenticate, and search.
 *
 * @author Sanjana Gangrade 
 * @version 1.0
 */
package in.co.rays.Proj4.test;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.Proj4.bean.UserBean;
import in.co.rays.Proj4.exception.ApplicationException;
import in.co.rays.Proj4.exception.DuplicateRecordException;
import in.co.rays.Proj4.model.UserModel;


public class TestUser {

    /**
     * Main method to call test methods.
     * 
     * @param args command line arguments
     * @throws Exception if an error occurs during test execution
     */
	public static void main(String[] args) throws Exception {
		testAdd();
		 //testUpdate();
		// testDelete();
		// testFindByPk();
		// testFindByLogin();
		// testAuthenticate();
		//testSearch();
	}
	/**
     * Tests the add() method of UserModel.
     * 
     * @throws DuplicateRecordException if a duplicate user is found
     * @throws ApplicationException if a database error occurs
     */
	public static void testAdd() throws DuplicateRecordException, ApplicationException  {

		UserBean bean = new UserBean();
		bean.setFirstName("manali");
		bean.setLastName("gangrade");
		bean.setLogin("manali@gmail.com");
		bean.setPassword("12344");
		bean.setDob(new Date());
		bean.setMobileNo("9998890811");
		bean.setRoleId(4);
		bean.setGender("female");
		bean.setCreatedBy("manali@gmail.com");
		bean.setModifiedBy("manali@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		UserModel model = new UserModel();
		model.add(bean);
	}
	 /**
     * Tests the update() method of UserModel.
     * 
     * @throws Exception if a database error occurs
     */
	public static void testUpdate() throws Exception {

		UserModel model = new UserModel();

		UserBean bean = model.findByPk(3);
		bean.setFirstName("Aman");
		bean.setLastName("Gupta");
		bean.setLogin("sahil@gmail.com");
		bean.setPassword("1234");

		model.update(bean);
	}
	  /**
     * Tests the delete() method of UserModel.
     * 
     * @throws Exception if a database error occurs
     */
	public static void testDelete() throws Exception {
		UserModel model = new UserModel();
		UserBean bean  = new UserBean();
		model.delete(bean);
	}
	 /**
     * Tests the findByPk() method of UserModel.
     * 
     * @throws Exception if a database error occurs
     */
	public static void testFindByPk() throws Exception {

		UserModel model = new UserModel();

		UserBean bean = model.findByPk(1);

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getPassword());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getRoleId());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("id not found");
		}
	}
	   /**
     * Tests the findByLogin() method of UserModel.
     * 
     * @throws Exception if a database error occurs
     */
	public static void testFindByLogin() throws Exception {

		UserModel model = new UserModel();

		UserBean bean = model.findByLogin("sawan@gmail.com");

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getPassword());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getRoleId());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("login not found");
		}
	}
	  /**
     * Tests the authenticate() method of UserModel.
     * 
     * @throws Exception if a database error occurs
     */
	public static void testAuthenticate() throws Exception {

		UserModel model = new UserModel();

		UserBean bean = model.authenticate("aman@gmail.com", "1234");

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getPassword());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getRoleId());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("login & password invalid");
		}
	}

    /**
     * Tests the search() method of UserModel.
     * 
     * @throws Exception if a database error occurs
     */
	public static void testSearch() throws Exception {

		UserBean bean = new UserBean();
		// bean.setFirstName("k");

		UserModel model = new UserModel();

		List list = model.search(bean, 1, 0);

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (UserBean) it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getFirstName());
			System.out.print("\t" + bean.getLastName());
			System.out.print("\t" + bean.getLogin());
			System.out.print("\t" + bean.getPassword());
			System.out.print("\t" + bean.getDob());
			System.out.print("\t" + bean.getMobileNo());
			System.out.print("\t" + bean.getRoleId());
			System.out.print("\t" + bean.getGender());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}
	}
}