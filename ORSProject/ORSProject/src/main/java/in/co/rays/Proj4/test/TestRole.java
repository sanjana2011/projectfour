package in.co.rays.Proj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.Proj4.bean.RoleBean;
import in.co.rays.Proj4.exception.ApplicationException;
import in.co.rays.Proj4.exception.DuplicateRecordException;
import in.co.rays.Proj4.model.RoleModel;

/**
 * Test class for RoleModel.
 * 
 * This class tests various operations such as add, update, delete,
 * find by primary key, find by name, and search functionality on the
 * Role entity.
 * 
 * This is typically used to verify DAO methods during development.
 * 
 * Dependencies:
 * - RoleModel
 * - RoleBean
 * 
 * Author: Sanjana Gangrade 
 */
public class TestRole {

	public static RoleModel model = new RoleModel();

    /** RoleModel instance used for testing operations */
   
    /**
     * Main method to trigger individual test cases.
     * 
     * @param args command-line arguments (not used)
     * @throws ParseException if date parsing fails
     */
	public static void main(String[] args) throws ParseException {
		testAdd();
		testUpdate();
		// testDelete();
		// testfindByPk();
		// testFindByName();
		// testSearch();
	}
	/**
     * Tests the addition of a new Role record to the database.
     * Verifies if the record was added successfully.
     * 
     * @throws ParseException if timestamp creation fails
     */
	public static void testAdd() throws ParseException {
		try {
			RoleBean bean = new RoleBean();
			bean.setName("student");
			bean.setDescription("student");
			bean.setCreatedBy("admin");
			bean.setModifiedBy("admin");
			bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
			bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
			long pk = model.add(bean);
			RoleBean addedbean = model.findByPk(pk);
			if (addedbean == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException | DuplicateRecordException e) {
			e.printStackTrace();
		}
	}
	  /**
     * Tests the update operation for an existing Role record.
     * Verifies if changes are reflected correctly.
     */
	public static void testUpdate() {
		try {
			RoleBean bean = model.findByPk(1L);
			bean.setName("admin");
			bean.setDescription("admin");
			model.update(bean);

			RoleBean updatedbean = model.findByPk(1L);
			
			if (!"Admin".equals(updatedbean.getName())) {
				System.out.println("Test Update Success");
			}
		} catch (ApplicationException | DuplicateRecordException e) {
			e.printStackTrace();
		}
	}
	/**
     * Tests deletion of a Role record by primary key.
     * Verifies if the record was successfully deleted.
     */
	public static void testDelete() {
		try {
			RoleBean bean = new RoleBean();
			long pk = 1L;
			bean.setId(pk);
			model.delete(bean);
			RoleBean deletedbean = model.findByPk(pk);
			if (deletedbean != null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
	   /**
     * Tests retrieval of a Role record by its primary key.
     * Displays the details of the found record.
     */
	public static void testfindByPk() {
		try {
			RoleBean bean = model.findByPk(1L);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

    /**
     * Tests retrieval of a Role record by its name.
     * Displays the details of the found record.
     */
	public static void testFindByName() {
		try {
			RoleBean bean = model.findByName("admin");
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

    /**
     * Tests search functionality for Role records.
     * Displays all matching records based on search criteria.
     */
	public static void testSearch() {
		try {
			RoleBean bean = new RoleBean();
			List list = new ArrayList();
			bean.setName("student");
			list = model.search(bean, 0, 0);
			if (list.size() < 0) {
				System.out.println("Test Serach fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (RoleBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getDescription());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
}