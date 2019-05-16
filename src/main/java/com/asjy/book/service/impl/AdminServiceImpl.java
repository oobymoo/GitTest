package com.asjy.book.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asjy.book.dao.AdminDao;
import com.asjy.book.model.Book;
import com.asjy.book.model.BookPage;
import com.asjy.book.service.AdminService;

@Component
public class AdminServiceImpl implements AdminService{
	/**
	 * ����������ȫ��ѯ��limit��ѯ
	 */
	@Autowired
	AdminDao adminDao;
	@Autowired
	BookPage bookPage;
	
	
	public BookPage findBookPage(Book book, int countPage) {
		// TODO Auto-generated method stub
		if(book==null) {
		book.setName("");
		book.setNo("");
		}
		List<Book> allList = adminDao.findBookPageDynamic(book.getNo(),book.getName(), 999,0);
		List<Book> pageList = adminDao.findBookPageDynamic(book.getNo(),book.getName(), (countPage-1)*BookPage.PAGE_COUNT,BookPage.PAGE_COUNT);
		System.out.println("Impl���ҵ���pageList�ĳ�����"+pageList.size());
		int totalPage;
		if(allList.size() % BookPage.PAGE_COUNT==0) {
			totalPage=allList.size()/BookPage.PAGE_COUNT;
		}
		else {
			totalPage=allList.size()/BookPage.PAGE_COUNT+1;
		}
		
		bookPage.setTotalPage(totalPage);
		bookPage.setCurrentPage(countPage);
		bookPage.setBookPageList(pageList);
		return bookPage;
	}

	
	public boolean deleteMany(int[] manyNo) {
		// TODO ִ������ɾ������
		int deleteBook = adminDao.deleteBook(manyNo);
		if(deleteBook !=manyNo.length) {
			//****************���ɾ�������������ڽӵ�������ĳ��ȣ������ûع�����ʲôע�⣿����������****************
			return false;
		}
			return true;
		//ԭ��д��,�����ûع�,���������û�а���Ԥ����ɾ�� return deleteBook==manyNo.length ? true : false;
	}
	
}
