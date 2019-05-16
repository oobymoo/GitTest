package com.asjy.book.service;

import com.asjy.book.model.User;

public interface BookService {
 
	User findUserByName(User user);
	
	User findUserByNo(User user);
	
	boolean addUser(User user);
}
