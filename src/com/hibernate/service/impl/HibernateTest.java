package com.hibernate.service.impl;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;

import com.hibernate.pojo.Book;

public class HibernateTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory sf=new Configuration().configure().buildSessionFactory();
		//获取SessionFactory对象
		Session s = sf.openSession();
		//通过SessionFactory对象获取session
		s.beginTransaction();
		//在session基础上开启一个事物
		Book book= new Book();
		book.setName("应召女郎");
		book.setAuthor("大屌哥");
		book.setPrice(19.8);
		book.setPublish("2019-5-18");
		book.setStore(50);
		s.save(book);
		//调用session的save方法把对象结果保存到数据库中
		s.getTransaction().commit();
		
		Book book1=(Book)s.get(Book.class,2);
		//获取到数据库中no=2的结果装进book1对象中
		System.err.println(book1.getNo());
		System.err.println(book1.getName());
		System.err.println(book1.getPublish());
		System.err.println(book1.getPrice());
		System.err.println(book1.getStore());
		//提交事物
		s.close();
		sf.close();

	}

}
