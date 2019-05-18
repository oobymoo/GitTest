package com.asjy.cloud.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asjy.cloud.dao.CloudDao;
import com.asjy.cloud.model.Project;
import com.asjy.cloud.model.ProjectPage;
import com.asjy.cloud.model.User;
import com.asjy.cloud.service.CloudService;


@Service
public class CloudServiceImpl implements CloudService{
	@Autowired
	CloudDao clouddao;
	@Autowired
	ProjectPage projectpage;
	
	
	
	/**
	 * 
	 * �̳�ʵ�ֵ�¼��ѯ����
	 */
	@Override
	public User findUserByName(String userName) {
		// TODO ִ�е�¼���ҷ���
		List<User> findUserList = clouddao.findUserByName(userName);
		if(findUserList.size()>0) {
			User findUserByName = findUserList.get(0);
			System.out.println("impl���ѯ��õ��Ľ����"+findUserByName.getUserName()+"#####################"+findUserByName.getPassword());
			return findUserByName;
		}
		return null;
		
	}

	/**
	 * ��������ҳ��ѯ����
	 */

	@Override
	public ProjectPage findProjectByNameAndPage(String proName, int currentPage) {
		// TODO ��Ŀ��������ҳ��ѯ
		List<Project> pageList = clouddao.findProjectByNameAndPage(proName,(currentPage-1)*ProjectPage.PAGE_COUNT,ProjectPage.PAGE_COUNT);
		List<Project> allList = clouddao.findProjectByNameAndPage(proName, 888,0);
		int totalPage;
		if(allList.size()%ProjectPage.PAGE_COUNT== 0) {
			totalPage=allList.size()/ProjectPage.PAGE_COUNT;
		}
		else {
			totalPage=allList.size()/ProjectPage.PAGE_COUNT+1;
		}
		List<User> userList = clouddao.findUserByName(null);
		projectpage.setCurrentPage(currentPage);
		projectpage.setTotalPage(totalPage);
		projectpage.setPageList(pageList);
		projectpage.setUserList(userList);
		return projectpage;
	}

}
