package com.asjy.ajax_user.service.Impl;

import java.util.List;

import com.asjy.ajax_user.dao.UserDao;
import com.asjy.ajax_user.model.User;
import com.asjy.ajax_user.service.UserService;

public class UserServiceImpl implements UserService{

	@Override
	public boolean addUser(User user) {
		String sql="insert into text_user value(?,?,?)";
		return UserDao.exectue(sql,user.getUserid(),user.getUsername(),user.getUserpassword());
	}

	@Override
	public List<User> findUserNameById(String id) {
		String sql="select*from text_user where user_name=?";
		return UserDao.find(sql,id);
	}

}
