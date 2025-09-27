
package in.co.rays.Proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import in.co.rays.Proj4.bean.CourseBean;
import in.co.rays.Proj4.exception.ApplicationException;
import in.co.rays.Proj4.exception.DatabaseException;
import in.co.rays.Proj4.exception.DuplicateRecordException;
import in.co.rays.Proj4.util.JDBCDataSource;
/**
 * CourseModel provides the data access logic for the Course entity.
 * It supports operations such as add, update, delete, find, and search.
 *
 * This class uses JDBC for database communication and handles
 * exceptions via custom exception classes such as ApplicationException,
 * DatabaseException, and DuplicateRecordException.
 *
 * Table used: st_course
 * 
 * @author Sanjana Gangrade
 */
public class CourseModel {
    /**
     * Returns the next primary key value from the st_course table.
     * 
     * @return next primary key as Integer
     * @throws DatabaseException if database access fails
     */
  
	public Integer nextPk() throws DatabaseException {
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_course");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk + 1;
	}
    /**
     * Adds a new Course to the database.
     * 
     * @param bean CourseBean containing course data
     * @return newly inserted course primary key
     * @throws ApplicationException if database error occurs
     * @throws DuplicateRecordException if course name already exists
     */
    public long add(CourseBean bean) throws ApplicationException, DuplicateRecordException {

	
		Connection conn = null;
		int pk = 0;

		CourseBean duplicateCourse = findByName(bean.getName());

		if (duplicateCourse != null) {
			throw new DuplicateRecordException("Course Name already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_course values(?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDuration());
			pstmt.setString(4, bean.getDescription());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Course");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return pk;
	}

    /**
     * Updates an existing Course in the database.
     * 
     * @param bean CourseBean containing updated course data
     * @throws ApplicationException if database error occurs
     * @throws DuplicateRecordException if course name already exists for another record
     */
  

	public void update(CourseBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;

		CourseBean duplicateCourse = findByName(bean.getName());
		if (duplicateCourse != null && duplicateCourse.getId() != bean.getId()) {
			throw new DuplicateRecordException("Course already exists");
		}
		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_course set name = ?, duration = ?, description = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDuration());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setLong(8, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Course ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}
    /**
     * Deletes a Course from the database by its ID.
     * 
     * @param bean CourseBean containing ID of the course to delete
     * @throws ApplicationException if deletion fails
     */

	public void delete(CourseBean bean) throws ApplicationException {
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_course where id = ?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Course");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
	}
    /**
     * Finds a Course by its primary key.
     * 
     * @param pk primary key (ID) of the course
     * @return CourseBean object if found, otherwise null
     * @throws ApplicationException if retrieval fails
     */
  

	public CourseBean findByPk(long pk) throws ApplicationException {
		StringBuffer sql = new StringBuffer("select * from st_course where id = ?");
		CourseBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDuration(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting Course by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}
    /**
     * Finds a Course by its name.
     * 
     * @param name Name of the course
     * @return CourseBean object if found, otherwise null
     * @throws ApplicationException if retrieval fails
     */
   

	public CourseBean findByName(String name) throws ApplicationException {
		StringBuffer sql = new StringBuffer("select * from st_course where name = ?");
		CourseBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDuration(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));

			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Course by Course Name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return bean;
	}
    /**
     * Returns a list of all courses.
     * 
     * @return List of CourseBean objects
     * @throws ApplicationException if listing fails
     */
   
	public List<CourseBean> list() throws ApplicationException {
		return search(null, 0, 0);
	}
    /**
     * Searches for courses based on the criteria in the CourseBean.
     * Supports optional pagination.
     * 
     * @param bean CourseBean with search criteria (can be null)
     * @param pageNo page number (starts from 1)
     * @param pageSize number of records per page
     * @return List of matching CourseBean objects
     * @throws ApplicationException if search fails
     */


	public List<CourseBean> search(CourseBean bean, int pageNo, int pageSize) throws ApplicationException {
		StringBuffer sql = new StringBuffer("select * from st_course where 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" and id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and name like '" + bean.getName() + "%'");
			}
			if (bean.getDuration() != null && bean.getDuration().length() > 0) {
				sql.append(" and duration like '" + bean.getDuration() + "%'");
			}
			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" and description like '" + bean.getDescription() + "%'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		ArrayList<CourseBean> list = new ArrayList<CourseBean>();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDuration(rs.getString(3));
				bean.setDescription(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
				list.add(bean);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in search Course");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

		
	}

