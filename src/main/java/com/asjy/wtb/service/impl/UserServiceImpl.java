package com.asjy.wtb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asjy.wtb.dao.PermissionDao;
import com.asjy.wtb.dao.UserDao;
import com.asjy.wtb.model.Permission;
import com.asjy.wtb.model.User;
import com.asjy.wtb.service.UserService;

@Component
public class UserServiceImpl implements UserService{
	@Autowired
	UserDao userDao;
	@Autowired
	PermissionDao permissionDao;
	/**
	 * 登录查询
	 */
	public User findUserByName(String username, String password) {
		// TODO 登录查询
		 List<User> findUserList = userDao.findUserByName(username, password);
		 if(findUserList.size()!=0) {
			 return findUserList.get(0);
		 }
		 		return null;
		
	}
	/**
	 * 登录用户鉴权查询
	 */
	public List<Permission> majorPermissionList(int userId) {
		// TODO 登录用户鉴权查询
		System.out.println("进入权限查询impl");
		List<Permission> majorPermissionList = permissionDao.majorPermissionList(userId);
		System.out.println("查询权限得到返回permissionList");
		return majorPermissionList;
	}

}
