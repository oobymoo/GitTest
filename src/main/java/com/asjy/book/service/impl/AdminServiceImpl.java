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
	 * 符合条件的全查询和limit查询
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
		System.out.println("Impl查找到的pageList的长度是"+pageList.size());
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
		// TODO 执行批量删除方法
		int deleteBook = adminDao.deleteBook(manyNo);
		if(deleteBook !=manyNo.length) {
			//****************如果删除的条数不等于接到的数组的长度，则设置回滚，用什么注解？？？？？？****************
			return false;
		}
			return true;
		//原先写法,不设置回滚,会造成数组没有按照预期真删除 return deleteBook==manyNo.length ? true : false;
	}
	
}
