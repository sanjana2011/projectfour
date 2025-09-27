package in.co.rays.Proj4.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.Proj4.bean.TimeTableBean;
import in.co.rays.Proj4.model.TimeTableModel;

/**
 * TestTimeTable class is used to test the TimeTableModel functionalities such as
 * add, update, delete, findByPk, and search operations.
 *
 * It acts as a unit test for the TimeTable module in the ORS project,
 * allowing validation of timetable-related CRUD operations.
 * 
 * @author Sanjana Gangrade
 */
public class TestTimeTable {

    /**
     * Main method to invoke test methods for TimeTableModel.
     *
     * @param args Command-line arguments
     * @throws Exception if any exception occurs during the test execution
     */
	public static void main(String[] args) throws Exception {
		 testAdd();
		// testUpdate();
		// testDelete();
		// testFindByPk();
		//testSearch();
	}
	  /**
     * Tests the add operation of TimeTableModel by creating a new TimeTableBean
     * and inserting it into the database.
     *
     * @throws Exception if an error occurs during the add operation
     */
	public static void testAdd() throws Exception {

		TimeTableBean bean = new TimeTableBean();
		bean.setSemester("fourth");
		bean.setDescription("2nd");
		bean.setExamDate(new Date());
		bean.setExamTime("12:00AM - 01:00PM");
		bean.setCourseId(2);
		bean.setSubjectId(3);
		bean.setCreatedBy("student@gmail.com");
		bean.setModifiedBy("student@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		TimeTableModel model = new TimeTableModel();
		model.add(bean);
	}
	 /**
     * Tests the update operation of TimeTableModel by modifying an existing
     * TimeTableBean and updating it in the database.
     *
     * @throws Exception if an error occurs during the update operation
     */
	public static void testUpdate() throws Exception {

		TimeTableModel model = new TimeTableModel();

		TimeTableBean bean = model.findByPk(3);
		bean.setSemester("2nd");
		bean.setDescription("2nd");
		bean.setExamDate(new Date());
		bean.setExamTime("11:00AM - 12:00PM");
		bean.setCourseId(1);
		bean.setSubjectId(1);

		model.update(bean);
	}

    /**
     * Tests the delete operation of TimeTableModel by deleting a timetable record
     * using its ID.
     *
     * @throws Exception if an error occurs during the delete operation
     */
	public static void testDelete() throws Exception {
		TimeTableModel model = new TimeTableModel();
		TimeTableBean bean = new TimeTableBean();
		bean.setId(2);
		model.delete(bean);
	}

    /**
     * Tests the findByPk operation of TimeTableModel by retrieving a timetable record
     * using its primary key.
     *
     * @throws Exception if an error occurs during the fetch operation
     */
	public static void testFindByPk() throws Exception {

		TimeTableModel model = new TimeTableModel();

		TimeTableBean bean = model.findByPk(1);

		if (bean != null) {
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getSemester());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getExamDate());
			System.out.print("\t" + bean.getExamTime());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getSubjectId());
			System.out.print("\t" + bean.getSubjectName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		} else {
			System.out.println("id not found");
		}
	}

    /**
     * Tests the search functionality of TimeTableModel by retrieving a list of
     * timetable entries matching search criteria.
     *
     * @throws Exception if an error occurs during the search operation
     */
	public static void testSearch() throws Exception {

		TimeTableBean bean = new TimeTableBean();
		// bean.setSemester("1");

		TimeTableModel model = new TimeTableModel();

		List list = model.search(bean, 1, 10);

		Iterator it = list.iterator();

		while (it.hasNext()) {
			bean = (TimeTableBean) it.next();
			System.out.print(bean.getId());
			System.out.print("\t" + bean.getSemester());
			System.out.print("\t" + bean.getDescription());
			System.out.print("\t" + bean.getExamDate());
			System.out.print("\t" + bean.getExamTime());
			System.out.print("\t" + bean.getCourseId());
			System.out.print("\t" + bean.getCourseName());
			System.out.print("\t" + bean.getSubjectId());
			System.out.print("\t" + bean.getSubjectName());
			System.out.print("\t" + bean.getCreatedBy());
			System.out.print("\t" + bean.getModifiedBy());
			System.out.print("\t" + bean.getCreatedDatetime());
			System.out.println("\t" + bean.getModifiedDatetime());
		}
	}
}