package in.co.rays.Proj4.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.Proj4.bean.SubjectBean;
import in.co.rays.Proj4.model.SubjectModel;

/**
 * TestSubject class is used to test the SubjectModel class functionality such as
 * add, update, delete, findByPk, findByName, and search operations.
 *
 * This class acts as a unit test for verifying that SubjectModel methods work correctly.
 * It uses SubjectBean to pass data and interact with the model layer.
 * 
 * @author Sanjana Gangrade
 */
public class TestSubject {
	  /**
     * Main method to run the test methods.
     *
     * @param args Command-line arguments
     * @throws Exception if any error occurs during testing
     */
	public static void main(String[] args) throws Exception {
		 testAdd();
		// testUpdate();
		// testDelete();
		// testFindByPk();
		// testFindByName();
		//testSearch();
	}
	 /**
     * Tests the add functionality of SubjectModel by creating a new SubjectBean
     * and adding it to the database.
     *
     * @throws Exception if any error occurs during the add operation
     */
	public static void testAdd() throws Exception {

		SubjectBean bean = new SubjectBean();

		bean.setName("gk");
		bean.setCourseId(3);
		bean.setDescription("gk");
		bean.setCreatedBy("student@gmail.com");
		bean.setModifiedBy("student@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		SubjectModel model = new SubjectModel();
		model.add(bean);
	}
    /**
     * Tests the update functionality of SubjectModel by modifying an existing
     * SubjectBean and updating it in the database.
     *
     * @throws Exception if any error occurs during the update operation
     */

	public static void testUpdate() throws Exception {

		SubjectModel model = new SubjectModel();

		SubjectBean bean = model.findByPk(4);

		bean.setName("maths");
		bean.setCourseId(3);
		bean.setDescription("computer");

		model.update(bean);
	}

    /**
     * Tests the delete functionality of SubjectModel by removing a subject record
     * using its ID.
     *
     * @throws Exception if any error occurs during the delete operation
     */
	public static void testDelete() throws Exception {

		SubjectModel model = new SubjectModel();
		SubjectBean bean = new SubjectBean();
		bean.setId(2);
		model.delete(bean);
	}
	  /**
     * Tests finding a subject by its primary key (ID) using the SubjectModel.
     *
     * @throws Exception if any error occurs during the find operation
     */
	public static void testFindByPk() throws Exception {

		SubjectModel model = new SubjectModel();

		SubjectBean bean = model.findByPk(2);

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
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
     * Tests finding a subject by its name using the SubjectModel.
     *
     * @throws Exception if any error occurs during the find operation
     */
	public static void testFindByName() throws Exception {

		SubjectModel model = new SubjectModel();

		SubjectBean bean = model.findByName("maths");

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("subject name not found");
		}
	}
	/**
     * Tests searching for subjects based on criteria in SubjectBean.
     * This uses pagination.
     *
     * @throws Exception if any error occurs during the search operation
     */
	public static void testSearch() throws Exception {

		SubjectBean bean = new SubjectBean();
		bean.setName("m");

		SubjectModel model = new SubjectModel();

		List list = model.search(bean, 1, 10);

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (SubjectBean) it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getName());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}
	}
}