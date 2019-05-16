package com.asjy.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asjy.book.model.Book;
import com.asjy.book.model.BookPage;
import com.asjy.book.service.impl.AdminServiceImpl;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import net.sf.json.JSON;
import net.sf.json.JSONObject;


/**
 * 
 * @author BYM
 * ��ת����Ա��ҳ������ҳ��ѯ
 */

@Controller
@RequestMapping("/AdminController")
public class AdminController {
	
	@Autowired
	AdminServiceImpl adminServiceImpl;
	@Autowired
	Book book;
	@RequestMapping("/goIndex")
	public String goIndex() {
		System.out.println("����admincontroller�����ת��ҳ����");
		return "admin_index";
	}
	 
	
	/**
	 * @author BYM
	 * @param ������������ţ���ǰҳ
	 * @return ���ظ��ݷ�ҳ��������ѯ�õ��ķ�ҳ��Ķ���
	 */
	@ResponseBody
	@RequestMapping(value="/doSearch",produces= {"text/html;charset=utf-8"})
	public String doSearch(String no, String name ,int countPage){
		System.err.println("*********************���ҽ���AdminController*********************");
		book.setNo(no);
		book.setName(name);
		BookPage bookPage = adminServiceImpl.findBookPage(book, countPage);
		
		JSONObject fromObject = JSONObject.fromObject(bookPage);
		return fromObject.toString();
	}
	/**
	 * @author BYM
	 * @param ���պ�����Ҫɾ����ͼ��������
	 * @return ����һ��boolean���͵�ֵ�ж���ɾ���ɹ�
	 */
	
	@ResponseBody
	@RequestMapping(value="deleteBook",produces= {"text/html;charset=utf-8"})
	public String deleteBook(int[] manyNo) {
		System.out.println("����ɾ��controller��");
		boolean deleteMany = adminServiceImpl.deleteMany(manyNo);
		String doDelete = String.valueOf(deleteMany);
		return doDelete;
	}

}
