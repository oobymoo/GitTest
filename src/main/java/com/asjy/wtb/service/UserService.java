package com.asjy.wtb.service;

import java.util.List;

import com.asjy.wtb.model.Permission;
import com.asjy.wtb.model.User;

public interface UserService {
	User findUserByName(String username,String password);
	
	List<Permission> majorPermissionList(int userId);
}
