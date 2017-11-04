package com.edan.common;

import java.sql.Connection;
import java.sql.SQLException;


import com.hazy.pool.DataBasePoolFactory;

public class DBConn {


	public static Connection getConnection() throws SQLException {
		String fullpath= DBConn.class.getResource("/").getPath()+"dbcp-config.properties";
		
		Connection con2=DataBasePoolFactory.getInstance(fullpath).getConnection();
		return con2;

	}
}
