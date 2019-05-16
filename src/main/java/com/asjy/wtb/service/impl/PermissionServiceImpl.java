package com.asjy.wtb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asjy.wtb.dao.PermissionDao;
import com.asjy.wtb.dao.UserDao;
import com.asjy.wtb.model.Img;
import com.asjy.wtb.model.User;
import com.asjy.wtb.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {
@Autowired
	UserDao userDao;
@Autowired
	PermissionDao permissionDao;
	
	/**
	 * 用户授权前全查询用户方法
	 */
	public List<User> allUser() {
		// TODO 全查询用户方法
		List<User> allUserList = userDao.findUserByName(null, null);
		return allUserList;
	}

	public boolean addImg(String picture) {
		// TODO 图片添加方法
		int result = permissionDao.addImg(picture);
		return result > 0 ? true:false;
	}

	public List<Img> findImg() {
		// TODO 图片全查询
		List<Img> imgList = permissionDao.findImg();
		return imgList;
	}

}
