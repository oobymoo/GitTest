package com.asjy.cloud.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.asjy.cloud.model.Project;
import com.asjy.cloud.model.User;

public interface CloudDao {
	
	/**
	 * 登录查询
	 * @param userName 从前台如输入的用户名
	 * @return 根据用户名返回一个对象
	 */
	List<User> findUserByName(@Param("userName")String userName);
	/**
	 * 
	 * @param proName项目名
	 * @param startNumber开始查询数据的位置
	 * @param pagecount每页显示多少条
	 * @return 返回根据条件查询到的分页数据集合
	 */
	List<Project> findProjectByNameAndPage(@Param("proName")String proName,@Param("startNumber")int startNumber,@Param("pagecount")int pagecount);
}
