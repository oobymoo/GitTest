package com.asjy.wtb.dao;

import java.util.List;

import com.asjy.wtb.model.Img;
import com.asjy.wtb.model.Permission;

public interface PermissionDao {

	List<Permission> majorPermissionList(int userId);
	
	int addImg(String picture);
	
	List<Img> findImg();
}
