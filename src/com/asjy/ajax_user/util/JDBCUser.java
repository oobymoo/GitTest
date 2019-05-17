package com.asjy.ajax_user.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface JDBCUser {
	public static final String DRIVE="com.mysql.jdbc.Driver";
	public static final String URL="jdbc:mysql://127.0.0.1:3306/asjy";
	public static final String USERNAME="root";//常量字母全部大写！
	public static final String PASSWORD="root";	
	
	public static Connection getConn() {
		Connection conn=null;
		try {
			Class.forName(DRIVE);
			conn=DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			
			System.out.println("数据登录异常"+e.getMessage());
		}
		catch (ClassNotFoundException e) {
			System.out.println("数据库链接异常"+e.getMessage());;
		}
		return conn;//数据库路径，密码，用户名都正常，就返回对象conn
	}
	public static  void Close(Connection conn,PreparedStatement ps,ResultSet rs) {
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(ps!=null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
