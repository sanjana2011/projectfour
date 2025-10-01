package in.co.rays.Proj4.util;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public final class JDBCDataSource {

	private static JDBCDataSource jds = null;

	private ComboPooledDataSource cpds = null;

	private static ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.Proj4.bundle.system");
	/**
	 * Singleton utility for managing JDBC connections using c3p0 connection pooling.
	 * <p>
	 * Loads configuration from ResourceBundle (driver, URL, credentials, pool sizes),
	 * initializes a {@link ComboPooledDataSource}, and provides connection lifecycle methods.
	 * </p>
	 * <p>
	 * Designed as a final class with private constructor and static {@code getInstance()}
	 * to enforce a single instance (Singleton pattern) across the application :contentReference[oaicite:2]{index=2}.
	 * Uses c3p0’s ComboPooledDataSource for efficient connection pooling :contentReference[oaicite:3]{index=3}.
	 * </p>
	 * 
	 * @author Sanjana Gangrade
	 * @version 1.0
	 * @since 1.0
	 */
	private JDBCDataSource() {
		try {
			cpds = new ComboPooledDataSource();
			cpds.setDriverClass(rb.getString("driver"));
			String jdbcurl = System.getenv("DATABASE_URL");
			if(jdbcurl == null ) {
				jdbcurl= rb.getString("url");
			}
			cpds.setJdbcUrl(jdbcurl);
			cpds.setUser(rb.getString("username"));
			cpds.setPassword(rb.getString("password"));
			cpds.setInitialPoolSize(Integer.parseInt(rb.getString("initialpoolsize")));
			cpds.setAcquireIncrement(Integer.parseInt(rb.getString("acquireincrement")));
			cpds.setMaxPoolSize(Integer.parseInt(rb.getString("maxpoolsize")));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 /**
     * Returns the singleton instance of JDBCDataSource, creating it if not already initialized.
     *
     * @return the single JDBCDataSource instance
     */
	public static JDBCDataSource getInstance() {
		if (jds == null) {
			jds = new JDBCDataSource();
		}
		return jds;
	}
	/**
     * Retrieves a pooled JDBC connection from the configured ComboPooledDataSource.
     *
     * @return a refreshed {@link Connection}, or {@code null} if unable to acquire one
     */
	public static Connection getConnection() {
		try {
			return getInstance().cpds.getConnection();
		} catch (SQLException e) {
			return null;
		}
	}

    /**
     * Closes JDBC resources in the proper order: {@code ResultSet}, {@code Statement}, then {@code Connection}.
     * Ignores null values and handles {@code SQLException} internally.
     *
     * @param conn the JDBC Connection to close
     * @param stmt the JDBC Statement to close
     * @param rs   the JDBC ResultSet to close
     */
	public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	  /**
     * Overloaded convenience method to close only Connection and Statement.
     *
     * @param conn the JDBC Connection to close
     * @param stmt the JDBC Statement to close
     */
	public static void closeConnection(Connection conn, Statement stmt) {
		closeConnection(conn, stmt, null);
	}

    /**
     * Overloaded convenience method to close only the Connection.
     *
     * @param conn the JDBC Connection to close
     */
	public static void closeConnection(Connection conn) {
		closeConnection(conn, null);
	}

}