package com.kh.villagehall.common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class JDBCTemplate {
	
	private static Connection conn = null;
	
	/** DB 연결 정보를 담고있는 Connection 객체 생성 및 반환 메서드
	 * @return conn
	 */
	public static Connection getConnection() {
		
		try {
			
			Context initContext = new InitialContext();
			
			Context envContext = (Context)initContext.lookup("java:comp/env");
			
			DataSource ds = (DataSource)envContext.lookup("jdbc/oracle");
			
			conn = ds.getConnection();
			
			conn.setAutoCommit(false);
			
		}catch(Exception e) {
			System.out.println("[Connection 생성 중 예외 발생]");
			e.printStackTrace();
		}
		
		return conn;
	}
	
	
	/** Connection 객체 자원 반환 메소드
	 * @param connn
	 */
	public static void close(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** Statement(부모), PreparedStatement(자식) 객체 자원 반환 메서드
	 * (다형성, 동적바인딩)
	 * @param stmt
	 */
	public static void close(Statement stmt) {
		try {
			if(stmt != null && !stmt.isClosed()) stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	
	/** ResultSet 객체 자원 반환 메서드
	 * @param rs
	 */
	public static void close(ResultSet rs) {
		try {
			
			if(rs != null && !rs.isClosed()) rs.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/** 트랜잭션 Commit 메서드
	 * @param conn
	 */
	public static void commit(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) conn.commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 트랜잭션 Rollback 메서드
	 * @param conn
	 */
	public static void rollback(Connection conn) {
		try {
			if(conn != null && !conn.isClosed()) conn.rollback();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
