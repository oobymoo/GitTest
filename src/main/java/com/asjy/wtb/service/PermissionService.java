package com.asjy.wtb.service;

import java.util.List;

import com.asjy.wtb.model.Img;
import com.asjy.wtb.model.User;

public interface PermissionService {

	List<User> allUser();
	
	boolean addImg(String picture);
	
	List<Img> findImg();
	
}
