
package in.co.rays.Proj4.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.Proj4.bean.CartBean;
import in.co.rays.Proj4.bean.UserBean;
import in.co.rays.Proj4.exception.ApplicationException;
import in.co.rays.Proj4.exception.DatabaseException;
import in.co.rays.Proj4.exception.DuplicateRecordException;
import in.co.rays.Proj4.util.JDBCDataSource;

//import org.apache.log4j.Logger;

/**
 * JDBC Implementation of CartModel.
 * 
 * @author Sanjana Gangrade
 *
 */

public class CartModel {
	
	private static Logger log = Logger.getLogger(CartModel.class);

	/**
	 * Find next PK of Cart
	 *
	 * @throws DatabaseException
	 */

	public int nextPK() throws DatabaseException {

		log.debug("Model nextPK Started");

		String sql = "select max(id) from st_cart";
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {

			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK Started");
		return pk + 1;

	}

	/**
	 * Cart Add
	 *
	 * @param bean
	 * @throws DatabaseException
	 *
	 */

	/**
	 * @param bean
	 * @return
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 */
	public long add(CartBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");

		String sql = "insert into st_cart values(?,?,?,?,?,?,?,?,?)";

		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getCustomerName());
			pstmt.setString(3, bean.getProduct());
			pstmt.setDate(4, new java.sql.Date(bean.getTransactionDate().getTime()));
			pstmt.setInt(5, bean.getQuantity());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			int i = pstmt.executeUpdate();
			System.out.println(i);
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			log.error("Database Exception ...", e);
			try {
				e.printStackTrace();
				conn.rollback();

			} catch (Exception e2) {
				e2.printStackTrace();
				// application exception
				throw new ApplicationException("Exception : add rollback exceptionn" + e2.getMessage());
			}
		}

		finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model Add End");
		return pk;

	}

	/**
	 * Delete a Cart
	 *
	 * @param bean
	 * @throws DatabaseException
	 */
	public void delete(CartBean bean) throws ApplicationException {
		log.debug("Model delete start");
		String sql = "delete from st_cart where id=?";
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			log.error("DataBase Exception", e);
			try {
				conn.rollback();
			} catch (Exception e2) {
				throw new ApplicationException("Exception: Delete rollback Exception" + e2.getMessage());
			}
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model Delete End");
	}

	/**
	 * Find Cart by PK
	 *
	 * @param pk : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public CartBean findByPK(long id) throws ApplicationException {
		log.debug("Model findBy PK start");
		String sql = "select * from st_cart where id=?";
		CartBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CartBean();
				bean.setId(rs.getLong(1));
				bean.setCustomerName(rs.getString(2));
				bean.setProduct(rs.getString(3));
				bean.setTransactionDate(rs.getDate(4));
				bean.setQuantity(rs.getInt(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("DataBase Exception ", e);
			throw new ApplicationException("Exception : Exception in getting Cart by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Method Find By PK end");
		return bean;
	}

	/**
	 * Update a cart
	 *
	 * @param bean
	 * @throws DatabaseException
	 */

	public void update(CartBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model Update Start");
		String sql = "UPDATE st_cart SET customerName=?, product=?, transactionDate=?, quantity=?, created_by=?, modified_by=?, created_datetime=?, modified_datetime=? WHERE id=?";

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, bean.getCustomerName());
			pstmt.setString(2, bean.getProduct());
			pstmt.setDate(3, new java.sql.Date(bean.getTransactionDate().getTime()));
			pstmt.setInt(4, bean.getQuantity());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setLong(9, bean.getId());
			int i = pstmt.executeUpdate();
			System.out.println("update cart>> " + i);
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("DataBase Exception", e);
			try {
				conn.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
				throw new ApplicationException("Exception : Update Rollback Exception " + e2.getMessage());
			}
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model Update End ");
	}

	/**
	 * Search Cart
	 *
	 * @param bean : Search Parameters
	 * @throws DatabaseException
	 */

	/**
	 * Search Cart with pagination
	 *
	 * @return list : List of Carts
	 * @param bean     : Search Parameters
	 * @param pageNo   : Current Page No.
	 * @param pageSize : Size of Page
	 *
	 * @throws DatabaseException
	 */

	public List <CartBean>search(CartBean bean, int pageNo, int pageSize) throws ApplicationException {
		Connection conn = null;
		ArrayList<CartBean> list = new ArrayList<CartBean>();

		StringBuffer sql = new StringBuffer("select * from st_cart where 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}

			if (bean.getCustomerName() != null && bean.getCustomerName().length() > 0) {
				sql.append(" AND customerName like '" + bean.getCustomerName() + "%'");
			}
			if (bean.getProduct() != null && bean.getProduct().length() > 0) {
				
				sql.append(" AND product like '" + bean.getProduct() + "%'");
			}

			if (bean.getQuantity() > 0) {
			    sql.append(" AND quantity = " + bean.getQuantity());
			}


		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}
		
		System.out.println("erroooooooorrrrrr" + sql.toString());
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				CartBean bean1 = new CartBean();
				bean1.setId(rs.getLong(1));
				bean1.setCustomerName(rs.getString(2));
				bean1.setProduct(rs.getString(3));
				bean1.setTransactionDate(rs.getDate(4));
				bean1.setQuantity(rs.getInt(5));
				bean1.setCreatedBy(rs.getString(6));
				bean1.setModifiedBy(rs.getString(7));
				bean1.setCreatedDatetime(rs.getTimestamp(8));
				bean1.setModifiedDatetime(rs.getTimestamp(9));
				list.add(bean1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in search user");
		} finally {

			JDBCDataSource.closeConnection(conn);
		}
		return list;
	}

	public List list() throws Exception {

		ArrayList list = new ArrayList();

		StringBuffer sql = new StringBuffer("select * from st_cart");

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			CartBean bean = new CartBean();
			bean.setId(rs.getLong(1));
			bean.setCustomerName(rs.getString(2));
			bean.setProduct(rs.getString(3));
			bean.setTransactionDate(rs.getDate(4));
			bean.setQuantity(rs.getInt(5));
			bean.setCreatedBy(rs.getString(6));
			bean.setModifiedBy(rs.getString(7));
			bean.setCreatedDatetime(rs.getTimestamp(8));
			bean.setModifiedDatetime(rs.getTimestamp(9));
			list.add(bean);

		}

		rs.close();

		return list;
	}
}