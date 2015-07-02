package com.luo.demo.transcation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

public class ConnectionHolder {
	private Map<DataSource,Connection> map = new HashMap<>();
	
	public Connection getConn(DataSource dataSource) throws SQLException{
		Connection conn = map.get(dataSource);
		if(conn==null || conn.isClosed()){
			conn = dataSource.getConnection();
			map.put(dataSource, conn);
		}
		return conn;
	}
}
