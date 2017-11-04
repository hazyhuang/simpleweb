package com.edan.handler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.edan.common.DBConn;
import com.edan.model.HazyUser;
import com.edan.model.IHazyUser;
import com.hazy.common.HazyUtil;

public class UserDAO {

	private static Logger logger = Logger.getLogger(UserDAO.class);

	public static List<IHazyUser> loadUsers() {
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		List<IHazyUser> comments = new ArrayList<IHazyUser>();
		try {
			conn = DBConn.getConnection();
			stmt = conn.createStatement();
			String sql = "select t.* " + "from hazy_user t order by id asc";
			logger.debug(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				IHazyUser obj = new HazyUser();
				obj.setID(rs.getInt("ID"));
				obj.setUsername(rs.getString("username"));
				obj.setPassword(rs.getString("password"));
				comments.add(obj);
				logger.debug(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			HazyUtil.getDBConnectionHelper().close(conn, stmt, rs);
		}

		return comments;
	}

	public static void delete(String ID) {
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			stmt = conn.createStatement();
			String sql = "delete Hazy_user t where t.ID ='" + ID + "'";
			rs = stmt.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			HazyUtil.getDBConnectionHelper().close(conn, stmt, rs);
		}
	}

	public static void addUpdate(IHazyUser line, String UserID) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			stmt = conn.createStatement();

			Integer IDStr = line.getID();
			if (IDStr == 0) {

				String sql = "insert into hazy_user (ID,username,password) values(" + "Hazy_user.Nextval,"
						+ line.getUsername() + "'," + line.getPassword() + ")";

				stmt.execute(sql);
			} else {
				String sql = "update hazy_user set password ='" + line.getPassword() + "' where ID = " + IDStr;
				stmt.execute(sql);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			HazyUtil.getDBConnectionHelper().close(conn, stmt, rs);
		}
	}

}
