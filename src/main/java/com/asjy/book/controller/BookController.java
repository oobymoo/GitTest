package com.asjy.book.controller;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.asjy.book.model.User;
import com.asjy.book.service.impl.BookServiceImpl;

/**
 * 
 *@author BYM
 *
 */
@SessionAttributes(value= {"findUserByName"})
@Controller
@RequestMapping("/BookController")
public class BookController {
	@Autowired
	BookServiceImpl bsi;
	
   /**
	 * @author BYM
	 * @return ��ת��¼ҳ
	 *
	 */
	@RequestMapping("/goLogin")
	public String goLogin() {
		
		return "login";
	}
		
	   /**
		 * ��¼��ѯ.����ɹ�,����Ȩ����ת��ͬ��ҳ��
		 * 
		 */
	@RequestMapping(value="/doLogin",method = RequestMethod.POST)
	public String doLogin(User user,Model model,HttpSession session) {
		User findUserByName = bsi.findUserByName(user);
		System.out.println(findUserByName);
		if(findUserByName != null) {
			model.addAttribute("findUserByName", findUserByName);
			if(findUserByName.getLevel() == 0) {
				System.out.println("*************************��תAdminController********************************");
				return "forward:/AdminController/goIndex";
			}
			else {
				return "user_index";
			}
			
		}
		model.addAttribute("msg","�û������������");
		return "login";
	}
	
	/**
	 * ��תע��ҳ��
	 * 
	 */
	@RequestMapping("/goAdd")
	public String goAdd() {
		
		return "add_user";
	}
	
	/**
	 *ִ��ע���û����� 
	 */
	@RequestMapping(value="/doAdd",method=RequestMethod.POST)
	public String doAdd(User user,@RequestParam("password1") String password1,Model model) {
		String noreg = "[1]{1}[0]{2,3}[0-9]{4}";
		String reg = "[a-zA-Z]{2}[0-9a-zA-Z]{2,4}";
		
		if(noreg.matches(String.valueOf(user.getNo()))) {
			model.addAttribute("msg","�û�no��ʽ����ȷ");
			return "forward:goAdd";
		}
	
		if(!user.getId().toString().matches(reg)) {
			model.addAttribute("msg","�û�id��ʽ����ȷ");
			return "forward:goAdd";
			//ͬһ��Controller�У���������ת��ֱ���� return��"forward:��Ҫ��ת����ע��ӳ�����"
		}
		 User userByNo = bsi.findUserByNo(user);
		if(userByNo != null){
			model.addAttribute("msg","id�ѱ�ע�ᣬ����������");
			return "add_user";
		}
		if(!user.getPassword().equals(password1)) {
			model.addAttribute("msg","�����������벻һ��");
			return "add_user";
		}
		boolean addUser = bsi.addUser(user);
		if(!addUser) {
			model.addAttribute("msg","ע��ʧ��");
			return "add_user";
		}
		return "login";
	}
}
