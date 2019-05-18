package com.asjy.cloud.service;

import com.asjy.cloud.model.ProjectPage;
import com.asjy.cloud.model.User;

public interface CloudService {
	
	/**
	 * 登录查询
	 * @param userName 从前台接受输入的用户名
	 * @return 根据用户名查到得到一个对象
	 */
	
	User findUserByName(String userName);
	/**
	 * 
	 * @param proName前台输入的项目名
	 * @param startNumber从第几条数据开始查
	 * @param pagecount每页显示多少条数据
	 * @return 返回根据分页条件查询得到的分页集合数据对象
	 */
	ProjectPage findProjectByNameAndPage(String proName,int currentPage);
}
