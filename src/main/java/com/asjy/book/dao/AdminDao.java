package com.asjy.book.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.asjy.book.model.Book;

public interface AdminDao {
	List<Book> findBookPageDynamic(@Param("no") String no,@Param("name") String name,@Param("nowpage")int nowpage,@Param("pageCount") int pageCount);
	//��Ϊ�Ƕ�̬��sql��䣬����ֻ��ִ��ȫ��ѯ���ܣ���Ҫִ����������ҳ��ѯ��ȫ��ѯ��ҳ�����Դ�pageCount����д�ɹ̶�����ʵ�����еĳ�����PAGE_COUNT,��Ϊ����ȫ��ѯ��ҳ������������ѯ��ҳ��
	//����Ҫ�����ܹ��ɷֳɵ�ҳ�������ҳ�����ɷ�����������������ÿҳ��ʾ�������õ��������������������ǲ��ӷ�ҳ�õ��Ľ��,���
	
	int deleteBook(int[] manyNo);
}
