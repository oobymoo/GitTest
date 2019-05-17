package com.asjy.ajax_user.service;

import java.util.List;

import com.asjy.ajax_user.model.User;

public interface UserService {
	boolean addUser(User user);
	
	List<User> findUserNameById(String id);
}
