package com.luo.demo.transcation;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

public class TranscationManager {
	private DataSource dataSource;
	
	public TranscationManager(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	public void start() throws SQLException{
		Connection conn = SingleThreadConnecionHolder.getConn(dataSource);
		conn.setAutoCommit(false);
	}
	
	public void commit() throws SQLException{
		Connection conn = SingleThreadConnecionHolder.getConn(dataSource);
		conn.commit();
	}
	
	public void rollback() throws SQLException{
		Connection conn = SingleThreadConnecionHolder.getConn(dataSource);
		conn.rollback();
	}
	
	public void close() throws SQLException{
		Connection conn = SingleThreadConnecionHolder.getConn(dataSource);
		conn.close();
	}
}
