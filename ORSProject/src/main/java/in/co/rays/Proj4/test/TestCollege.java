package in.co.rays.Proj4.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.Proj4.bean.CollegeBean;
import in.co.rays.Proj4.model.CollegeModel;

/**
 * Test class for CollegeModel.
 * This class is used to test the CRUD and search functionalities
 * provided by the CollegeModel class.
 * 
 * It demonstrates:
 * <ul>
 *   <li>Adding a new college</li>
 *   <li>Updating existing college details</li>
 *   <li>Deleting a college by ID</li>
 *   <li>Finding a college by primary key (ID)</li>
 *   <li>Finding a college by name</li>
 *   <li>Searching colleges with pagination</li>
 * </ul>
 * 
 * To run a specific test, uncomment the corresponding method call in main().
 * 
 * @author Sanjana Gangrade
 */
public class TestCollege {
	 /**
     * Main method to call test methods.
     *
     * @param args command line arguments
     * @throws Exception if any error occurs during method execution
     */
	public static void main(String[] args) throws Exception {
		// testAdd();
		// testUpdate();
		// testDelete();
		// testFindByPk();
		// testFindByName();
		testSearch();
	}
	 /**
     * Tests the add() method of CollegeModel.
     * Adds a new college record to the database.
     *
     * @throws Exception if any database or model error occurs
     */
	public static void testAdd() throws Exception {

		CollegeBean bean = new CollegeBean();
		bean.setName("vedika");
		bean.setAddress("rewa");
		bean.setState("MP");
		bean.setCity("satna");
		bean.setPhoneNo("7234567892");
		bean.setCreatedBy("ananya@gmail.com");
		bean.setModifiedBy("ananya@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		CollegeModel model = new CollegeModel();
		model.add(bean);
	}

    /**
     * Tests the update() method of CollegeModel.
     * Updates an existing college's details using its primary key.
     *
     * @throws Exception if any database or model error occurs
     */
	public static void testUpdate() throws Exception {

		CollegeModel model = new CollegeModel();

		CollegeBean bean = model.findByPk(4);
		bean.setName("soumya");
		bean.setAddress("indore");
		bean.setState("MP");

		model.update(bean);
	}
	/**
     * Tests the delete() method of CollegeModel.
     * Deletes a college by ID.
     *
     * @throws Exception if any error occurs during deletion
     */
	public static void testDelete() throws Exception {
		CollegeModel model = new CollegeModel();
		CollegeBean bean = new CollegeBean ();
		bean.setId(2);
		model.delete(bean);
	}
	 /**
     * Tests the findByPk() method of CollegeModel.
     * Retrieves and displays a college's details using its ID.
     *
     * @throws Exception if any error occurs during retrieval
     */
	public static void testFindByPk() throws Exception {

		CollegeModel model = new CollegeModel();

		CollegeBean bean = model.findByPk(1);

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getAddress());
			System.out.print("\t" + bean.getState());
			System.out.print("\t" + bean.getCity());
			System.out.print("\t" + bean.getPhoneNo());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("id not found");
		}
	}
	  /**
     * Tests the findByName() method of CollegeModel.
     * Retrieves and displays college details by name.
     *
     * @throws Exception if any error occurs during retrieval
     */
	public static void testFindByName() throws Exception {

		CollegeModel model = new CollegeModel();

		CollegeBean bean = model.findByName("sanjana");

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getAddress());
			System.out.print("\t" + bean.getState());
			System.out.print("\t" + bean.getCity());
			System.out.print("\t" + bean.getPhoneNo());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("college name not found");
		}
	}
	 /**
     * Tests the search() method of CollegeModel.
     * Performs a paginated search for colleges and displays the result.
     *
     * @throws Exception if any error occurs during search
     */
	public static void testSearch() throws Exception {

		CollegeBean bean = new CollegeBean();
		// bean.setName("m");

		CollegeModel model = new CollegeModel();

		List list = model.search(bean, 1, 2);

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (CollegeBean) it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getAddress());
			System.out.print("\t" + bean.getState());
			System.out.print("\t" + bean.getCity());
			System.out.print("\t" + bean.getPhoneNo());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}
	}
}