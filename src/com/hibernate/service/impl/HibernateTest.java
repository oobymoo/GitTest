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
		//��ȡSessionFactory����
		Session s = sf.openSession();
		//ͨ��SessionFactory�����ȡsession
		s.beginTransaction();
		//��session�����Ͽ���һ������
		Book book= new Book();
		book.setName("Ӧ��Ů��");
		book.setAuthor("��Ÿ�");
		book.setPrice(19.8);
		book.setPublish("2019-5-18");
		book.setStore(50);
		s.save(book);
		//����session��save�����Ѷ��������浽���ݿ���
		s.getTransaction().commit();
		
		Book book1=(Book)s.get(Book.class,2);
		//��ȡ�����ݿ���no=2�Ľ��װ��book1������
		System.err.println(book1.getNo());
		System.err.println(book1.getName());
		System.err.println(book1.getPublish());
		System.err.println(book1.getPrice());
		System.err.println(book1.getStore());
		//�ύ����
		s.close();
		sf.close();

	}

}
