package com.edan.handler;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.edan.common.DBConn;
import com.edan.model.Comment;
import com.edan.model.IComment;
import com.hazy.common.HazyUtil;

public class CommentDAO {

	private static Logger logger = Logger.getLogger(CommentDAO.class);

	public static List<IComment> load(String userid) {
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		List<IComment> comments = new ArrayList<IComment>();
		try {
			conn = DBConn.getConnection();
			stmt = conn.createStatement();
			String sql = "select t.* " + "from hazy_comments t where t.userid ='" + userid + "'  order by id asc";
			logger.debug(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				IComment obj = new Comment();
				obj.setID(rs.getInt("ID"));
				obj.setUserID(rs.getInt("userid"));
				obj.setComment(rs.getString("comment2"));
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
			String sql = "delete Hazy_Comments t where t.ID ='" + ID + "'";
			rs = stmt.executeQuery(sql);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			HazyUtil.getDBConnectionHelper().close(conn, stmt, rs);
		}
	}

	public static void addUpdate(IComment line, String UserID) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			conn = DBConn.getConnection();
			stmt = conn.createStatement();

			Integer IDStr = line.getID();
			if (IDStr == 0) {
				Integer ID = 0;
				rs = stmt.executeQuery("select hazy_seq_comments.Nextval from dual");
				while (rs.next()) {
					ID = rs.getInt(1);
				}
				String sql = "insert into hazy_comments(ID,comment2,userid) values(hazy_seq_comments.Nextval,'"
						+ line.getComment() + "'," + UserID.toString() + ")";

				stmt.execute(sql);
			} else {
				String sql = "update hazy_comment set comment ='" + line.getComment() + "' where ID = " + IDStr;
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
