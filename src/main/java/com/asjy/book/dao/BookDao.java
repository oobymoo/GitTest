package com.asjy.book.dao;

import com.asjy.book.model.User;

public interface BookDao {
		/**
		 * 
		 * ��¼/ע���ѯ
		 */
		User findUserById(User user);
		
		/*
		 * ע���û�
		 */
		int addUser(User user);
}
