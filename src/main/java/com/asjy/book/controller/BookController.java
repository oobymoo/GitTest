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
	 * @return 跳转登录页
	 *
	 */
	@RequestMapping("/goLogin")
	public String goLogin() {
		
		return "login";
	}
		
	   /**
		 * 登录查询.如果成功,按照权限跳转不同的页面
		 * 
		 */
	@RequestMapping(value="/doLogin",method = RequestMethod.POST)
	public String doLogin(User user,Model model,HttpSession session) {
		User findUserByName = bsi.findUserByName(user);
		System.out.println(findUserByName);
		if(findUserByName != null) {
			model.addAttribute("findUserByName", findUserByName);
			if(findUserByName.getLevel() == 0) {
				System.out.println("*************************跳转AdminController********************************");
				return "forward:/AdminController/goIndex";
			}
			else {
				return "user_index";
			}
			
		}
		model.addAttribute("msg","用户名或密码错误");
		return "login";
	}
	
	/**
	 * 跳转注册页面
	 * 
	 */
	@RequestMapping("/goAdd")
	public String goAdd() {
		
		return "add_user";
	}
	
	/**
	 *执行注册用户方法 
	 */
	@RequestMapping(value="/doAdd",method=RequestMethod.POST)
	public String doAdd(User user,@RequestParam("password1") String password1,Model model) {
		String noreg = "[1]{1}[0]{2,3}[0-9]{4}";
		String reg = "[a-zA-Z]{2}[0-9a-zA-Z]{2,4}";
		
		if(noreg.matches(String.valueOf(user.getNo()))) {
			model.addAttribute("msg","用户no格式不正确");
			return "forward:goAdd";
		}
	
		if(!user.getId().toString().matches(reg)) {
			model.addAttribute("msg","用户id格式不正确");
			return "forward:goAdd";
			//同一个Controller中，方法间跳转，直接在 return后"forward:需要跳转方法注解映射的名"
		}
		 User userByNo = bsi.findUserByNo(user);
		if(userByNo != null){
			model.addAttribute("msg","id已被注册，请重新输入");
			return "add_user";
		}
		if(!user.getPassword().equals(password1)) {
			model.addAttribute("msg","两次密码输入不一致");
			return "add_user";
		}
		boolean addUser = bsi.addUser(user);
		if(!addUser) {
			model.addAttribute("msg","注册失败");
			return "add_user";
		}
		return "login";
	}
}
