package com.asjy.book.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asjy.book.dao.BookDao;
import com.asjy.book.model.User;
import com.asjy.book.service.BookService;

@Component
public class BookServiceImpl implements BookService{
	@Autowired
	BookDao bd;
	
	
	/**
	 * µÇÂ¼²éÑ¯
	 */
	public User findUserByName(User user) {
		// TODO Auto-generated method stub
		User findUserById = bd.findUserById(user);
		if(findUserById != null) {
			if(findUserById.getPassword().equals(user.getPassword())) {
				return findUserById;
			}
		}
		return null;
	}
		/**
		 * ×¢²á²éÑ¯
		 */
	
	public User findUserByNo(User user) {
		User findUserById = bd.findUserById(user);
		return findUserById;
	}
	
		/**
		 * ×¢²áÌí¼ÓÓÃ»§
		 */
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		int addUser = bd.addUser(user);
		System.out.println(user.getLevel());
		if(addUser==1) {
			return true;
		}
		return false;
	}

}
