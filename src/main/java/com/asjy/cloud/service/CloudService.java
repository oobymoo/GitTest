package com.asjy.cloud.service;

import com.asjy.cloud.model.ProjectPage;
import com.asjy.cloud.model.User;

public interface CloudService {
	
	/**
	 * ��¼��ѯ
	 * @param userName ��ǰ̨����������û���
	 * @return �����û����鵽�õ�һ������
	 */
	
	User findUserByName(String userName);
	/**
	 * 
	 * @param proNameǰ̨�������Ŀ��
	 * @param startNumber�ӵڼ������ݿ�ʼ��
	 * @param pagecountÿҳ��ʾ����������
	 * @return ���ظ��ݷ�ҳ������ѯ�õ��ķ�ҳ�������ݶ���
	 */
	ProjectPage findProjectByNameAndPage(String proName,int currentPage);
}
