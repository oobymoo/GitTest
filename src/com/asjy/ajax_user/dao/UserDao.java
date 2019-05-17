package com.asjy.ajax_user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.asjy.ajax_user.model.User;
import com.asjy.ajax_user.util.JDBCUser;

public class UserDao {
	public static boolean exectue(String sql,Object...obj) {
		Connection conn=JDBCUser.getConn();
		PreparedStatement ps=null;;
		int result=0;
		try {
			ps = conn.prepareStatement(sql);
			for(int i=0;i<obj.length;i++) {
				
				ps.setObject(i+1, obj[i]);
			}

			result=ps.executeUpdate();
			if(result==1) {
				return true;
			}
		} 
			catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
		JDBCUser.Close(conn, ps, null);	
		}
		return result >0?true :false;
	}
	public static List<User> find(String sql,Object...obj){
		Connection conn=JDBCUser.getConn();
		List<User> list=new ArrayList<>();
		ResultSet rs=null;
		PreparedStatement ps=null;
		try {
			ps=conn.prepareStatement(sql);
			for(int i=0;i<obj.length;i++) {
				ps.setObject(i+1, obj[i]);
				}	
				rs=ps.executeQuery();
			while(rs.next()) {
				User user=new User();
				user.setUserid(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setUserpassword(rs.getString(3));
				list.add(user);
				}
			
			}
				 catch (SQLException e1) {
					System.out.println(e1.getMessage());
				}
		finally {
			JDBCUser.Close(conn, ps, rs);
		}
			return list;
	}
}
