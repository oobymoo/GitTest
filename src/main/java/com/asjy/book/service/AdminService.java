package com.asjy.book.service;

import com.asjy.book.model.Book;
import com.asjy.book.model.BookPage;

public interface AdminService {

	BookPage findBookPage(Book book,int countPage);
	
	boolean deleteMany(int[] manyNo);
	
}
