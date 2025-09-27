package in.co.rays.Proj4.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.Proj4.bean.CourseBean;
import in.co.rays.Proj4.model.CourseModel;

/**
 * Test class for CourseModel.
 * This class is used to test CRUD and search operations on the Course entity.
 * 
 * It demonstrates:
 * <ul>
 *   <li>Adding a new course</li>
 *   <li>Updating existing course details</li>
 *   <li>Deleting a course by ID</li>
 *   <li>Finding a course by primary key (ID)</li>
 *   <li>Finding a course by name</li>
 *   <li>Searching courses with pagination</li>
 * </ul>
 * 
 * Uncomment any method in the main() to test its functionality.
 * 
 * @author Sanjana Gangrade 
 */

public class TestCourse {
	 /**
     * Main method to execute test methods.
     * 
     * @param args command line arguments
     * @throws Exception if any database or model-related error occurs
     */
	public static void main(String[] args) throws Exception {
		//testAdd();
		//testUpdate();
		// testDelete();
		 //testFindByPk();
		//testFindByName();
		testSearch();
	}
	 /**
     * Tests the add() method of CourseModel.
     * Adds a new course record into the database.
     * 
     * @throws Exception if there is an error during insertion
     */
	public static void testAdd() throws Exception {

		CourseBean bean = new CourseBean();
		bean.setName("vedik");
		bean.setDuration("1 year");
		bean.setDescription("test");
		bean.setCreatedBy("vedik@gmail.com");
		bean.setModifiedBy("vedik@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		CourseModel model = new CourseModel();

		model.add(bean);

	}
	/**
     * Tests the update() method of CourseModel.
     * Updates the details of an existing course using its primary key.
     * 
     * @throws Exception if there is an error during the update operation
     */
	public static void testUpdate() throws Exception {

		CourseModel model = new CourseModel();

		CourseBean bean = model.findByPk(1);
		bean.setName("B.ed");
		bean.setDuration("2year");
		bean.setDescription("B.ed");

		model.update(bean);
	}
	 /**
     * Tests the delete() method of CourseModel.
     * Deletes a course record based on the course ID.
     * 
     * @throws Exception if there is an error during deletion
     */
	public static void testDelete() throws Exception {
		CourseModel model = new CourseModel();
		CourseBean bean = new CourseBean();
		bean.setId(2);
		model.delete(bean);
	}
	 /**
     * Tests the findByPk() method of CourseModel.
     * Fetches and displays a course by its primary key (ID).
     * 
     * @throws Exception if there is an error during fetching
     */
	public static void testFindByPk() throws Exception {

		CourseModel model = new CourseModel();

		CourseBean bean = model.findByPk(2);

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDuration());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("id not found");
		}
	}
	/**
     * Tests the findByName() method of CourseModel.
     * Searches and displays a course by its name.
     * 
     * @throws Exception if there is an error during fetching
     */
	public static void testFindByName() throws Exception {

		CourseModel model = new CourseModel();

		CourseBean bean = model.findByName("sana");

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDuration());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("name not found");
		}
	}

    /**
     * Tests the search() method of CourseModel.
     * Searches for courses using pagination and optional filters.
     * 
     * @throws Exception if there is an error during the search
     */
	public static void testSearch() throws Exception {

		CourseBean bean = new CourseBean();
		bean.setName("sana");

		CourseModel model = new CourseModel();

		List list = model.search(bean, 1, 4);

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (CourseBean) it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getDuration());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}
	}
}