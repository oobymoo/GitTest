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
 * 跳转管理员首页，并分页查询
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
		System.out.println("进入admincontroller层的跳转主页方法");
		return "admin_index";
	}
	 
	
	/**
	 * @author BYM
	 * @param 接受书名，书号，当前页
	 * @return 返回根据分页和条件查询得到的分页类的对象
	 */
	@ResponseBody
	@RequestMapping(value="/doSearch",produces= {"text/html;charset=utf-8"})
	public String doSearch(String no, String name ,int countPage){
		System.err.println("*********************查找进入AdminController*********************");
		book.setNo(no);
		book.setName(name);
		BookPage bookPage = adminServiceImpl.findBookPage(book, countPage);
		
		JSONObject fromObject = JSONObject.fromObject(bookPage);
		return fromObject.toString();
	}
	/**
	 * @author BYM
	 * @param 接收含有需要删除的图书编号数组
	 * @return 返回一个boolean类型的值判断是删除成功
	 */
	
	@ResponseBody
	@RequestMapping(value="deleteBook",produces= {"text/html;charset=utf-8"})
	public String deleteBook(int[] manyNo) {
		System.out.println("进入删除controller层");
		boolean deleteMany = adminServiceImpl.deleteMany(manyNo);
		String doDelete = String.valueOf(deleteMany);
		return doDelete;
	}

}
