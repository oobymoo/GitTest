package com.asjy.wtb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.asjy.wtb.model.User;

public interface UserDao {
	List<User> findUserByName(@Param("username")String username,@Param("password")String password);
	
	
}
