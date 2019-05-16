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
	 * ��¼��ѯ
	 */
	public User findUserByName(String username, String password) {
		// TODO ��¼��ѯ
		 List<User> findUserList = userDao.findUserByName(username, password);
		 if(findUserList.size()!=0) {
			 return findUserList.get(0);
		 }
		 		return null;
		
	}
	/**
	 * ��¼�û���Ȩ��ѯ
	 */
	public List<Permission> majorPermissionList(int userId) {
		// TODO ��¼�û���Ȩ��ѯ
		System.out.println("����Ȩ�޲�ѯimpl");
		List<Permission> majorPermissionList = permissionDao.majorPermissionList(userId);
		System.out.println("��ѯȨ�޵õ�����permissionList");
		return majorPermissionList;
	}

}
