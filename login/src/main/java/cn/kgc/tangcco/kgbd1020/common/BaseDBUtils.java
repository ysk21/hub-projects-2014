package cn.kgc.tangcco.kgbd1020.common;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public abstract class BaseDBUtils {
	private static ThreadLocal<Connection> t = new ThreadLocal<Connection>();
	private static DataSource ds = new ComboPooledDataSource();

	/**
	 * 获取数据源
	 */
	public static DataSource getDataSource() {
		return ds;
	}

	/**
	 * 获取Connection
	 */
	public static Connection getConnection() throws SQLException {
		Connection conn = t.get();
		if (conn != null) {
			return conn;
		}
		conn = ds.getConnection();
		t.set(conn);
		return conn;
	}

	/**
	 * 开启事务
	 */
	public static void startTransaction() throws SQLException {
		getConnection().setAutoCommit(false);
	}

	/**
	 * 获取PreparedStatement
	 */
	public static PreparedStatement getPreparedStatement(Connection conn, String sql) throws SQLException {
		return conn.prepareStatement(sql);
	}

	/**
	 * 获取PreparedStatement
	 */
	public static PreparedStatement getPreparedStatement(Connection conn, String sql, PageRang pr) throws SQLException {
		sql += " limit " + pr.getPageIndex() + " , " + pr.getPageSize();
		System.out.println(sql);
		return conn.prepareStatement(sql);
	}
	public static ResultSet executeQuery(PreparedStatement pst, Object... params) throws SQLException {
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				pst.setObject(i + 1, params[i]);
			}
		}
		return pst.executeQuery();
	}
	public static ResultSet executeQuery(PreparedStatement pst, PageRang pr, Object... params) throws SQLException {
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				pst.setObject(i + 1, params[i]);
			}
		}
		pst.setMaxRows(pr.getPageIndex() + pr.getPageSize());
		ResultSet rs = pst.executeQuery();
		rs.relative(pr.getPageIndex());
		return rs;
	}

	public static int executeUpdate(PreparedStatement pst, Object... params) throws SQLException {
		if (params != null && params.length > 0) {
			for (int i = 0; i < params.length; i++) {
				pst.setObject(i + 1, params[i]);
			}
		}
		return pst.executeUpdate();
	}

	public static void closeAll(Connection conn, PreparedStatement pst, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeAll(PreparedStatement pst, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pst != null) {
				pst.close();
			}
			Connection conn = getConnection();
			if (conn != null) {
				conn.close();
			}
			t.remove();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void commitAndClose(PreparedStatement pst) {
		try {
			Connection conn = getConnection();
			conn.commit();
			if (pst != null) {
				pst.close();
			}
			if (conn != null) {
				conn.close();
			}
			t.remove();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void roolbackAndClose(PreparedStatement pst) {
		try {
			Connection conn = getConnection();
			conn.rollback();
			if (pst != null) {
				pst.close();
			}
			if (conn != null) {
				conn.close();
			}
			t.remove();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
