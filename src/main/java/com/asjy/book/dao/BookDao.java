package com.asjy.book.dao;

import com.asjy.book.model.User;

public interface BookDao {
		/**
		 * 
		 * µÇÂ¼/×¢²á²éÑ¯
		 */
		User findUserById(User user);
		
		/*
		 * ×¢²áÓÃ»§
		 */
		int addUser(User user);
}
