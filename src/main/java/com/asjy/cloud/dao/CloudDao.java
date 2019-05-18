package com.asjy.cloud.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.asjy.cloud.model.Project;
import com.asjy.cloud.model.User;

public interface CloudDao {
	
	/**
	 * ��¼��ѯ
	 * @param userName ��ǰ̨��������û���
	 * @return �����û�������һ������
	 */
	List<User> findUserByName(@Param("userName")String userName);
	/**
	 * 
	 * @param proName��Ŀ��
	 * @param startNumber��ʼ��ѯ���ݵ�λ��
	 * @param pagecountÿҳ��ʾ������
	 * @return ���ظ���������ѯ���ķ�ҳ���ݼ���
	 */
	List<Project> findProjectByNameAndPage(@Param("proName")String proName,@Param("startNumber")int startNumber,@Param("pagecount")int pagecount);
}
