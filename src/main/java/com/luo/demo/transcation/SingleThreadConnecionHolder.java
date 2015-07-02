package com.luo.demo.transcation;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * 同一个线程的 不同的service里，是同一个connection
 * 不同的线程 是不同的connection
 * @author hui.luo
 *
 */
public class SingleThreadConnecionHolder {
	private static ThreadLocal<ConnectionHolder> holder = new ThreadLocal<ConnectionHolder>(){

		@Override
		protected ConnectionHolder initialValue() {
			ConnectionHolder ch = new ConnectionHolder();
			return ch;
		}
		
	};
	
	public static Connection getConn(DataSource dataSource) throws SQLException{
		return holder.get().getConn(dataSource);
	}
}
