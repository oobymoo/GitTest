package com.asjy.wtb.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.asjy.wtb.model.Permission;
import com.asjy.wtb.model.User;
import com.asjy.wtb.service.impl.UserServiceImpl;
import com.asjy.wtb.util.ValidateCode;

@Controller
@RequestMapping("/UserController")
@SessionAttributes(value={"findUserByName","majorPermissionList"})
public class UserController {
	@Autowired
	UserServiceImpl userServiceImpl;
	
	
	/**
	 * 
	 * @return 跳转登录页
	 */
	@RequestMapping("/goLogin")
	public String goLogin() {
		return "login";
	}
	
	
	/**
	 * 
	 * @param dayslogin 执行七天免登陆参数
	 * @param username 账号
	 * @param password 密码
	 * @param code	         验证码
	 * @param session  验证成功后的用户信息装进session域
	 * @param response 向客户端发送cookie
	 * @param model	        将登录用户信息发送给鉴权方法
	 * @return		        转发鉴权方法
	 */
	
	@RequestMapping(value="/doLogin",method=RequestMethod.POST)
	public String doLogin(String dayslogin,String username,String password,String code,HttpSession session,HttpServletResponse response,Model model) {
		if(username =="") {
			model.addAttribute("msg","账号不能为空");
			return "login";	
		} 
		if(password =="") {
			model.addAttribute("msg","密码不能为空");
			return "login";
		}
		if(code =="") {
			model.addAttribute("msg","验证码不能为空");
			return "login";
		}
		String randomString=(String)session.getAttribute(ValidateCode.RANDOMCODEKEY);
		if(!code.equalsIgnoreCase(randomString)) {
			model.addAttribute("msg","验证码错误");
			return "login";
		}
		
		User findUserByName = userServiceImpl.findUserByName(username, password);
			
		if(findUserByName == null) {
			model.addAttribute("msg","账号或密码错误");
			return "login";
		}
		Cookie wtbNameCookie = new Cookie("wtbNameCookie", username);
		Cookie wtbPasswordCookie = new Cookie("wtbPasswordCookie",password);
		if(dayslogin != null) {
			wtbNameCookie.setMaxAge(60*60*24*7);
			wtbPasswordCookie.setMaxAge(60*60*24*7);
		}
		else {
			wtbNameCookie.setMaxAge(0);
			wtbPasswordCookie.setMaxAge(0);
		}
		wtbNameCookie.setPath("/");
		wtbPasswordCookie.setPath("/");
		response.addCookie(wtbNameCookie);
		response.addCookie(wtbPasswordCookie);
		
		model.addAttribute("findUserByName",findUserByName);
		return "forward:goIndex";
	}
	/**
	 * 登录成功后,根据用户id查询用户权限方法
	 * @param user 登录用户对象
	 * @param model 向画面发送主分权限集合
	 * @return 登录主页
	 */
	@RequestMapping("/goIndex")
	public String goIndex(Model model,HttpServletRequest request,HttpSession session) {
		
		User findUserByName = (User)request.getAttribute("findUserByName");

		List<Permission> majorPermissionList = userServiceImpl.majorPermissionList(findUserByName.getId());
		//String masg="用户的跳转路径是：";
		for(Permission majorpermission : majorPermissionList) {
			System.out.println("用户的主权限有："+majorpermission.getPermissionName());
			for (Permission minorpermission : majorpermission.getMinorPermissinoList()) {
				System.out.println("此用户的分权限是："+minorpermission.getPermissionName());
				System.out.println("用户的跳转路径是："+minorpermission.getPermissionUrl());
				//System.out.println(masg.concat(minorpermission.getPermissionUrl()));
			}
		}
		session.setMaxInactiveInterval(60*60);
		System.out.println("查询返回主分权限集合");
		model.addAttribute("majorPermissionList",majorPermissionList);
		return "index";
	}
	
	@RequestMapping("/newUser")
	public String newUser(HttpServletResponse response) {
		
		Cookie wtbNameCookie = new Cookie("wtbNameCookie","");
		Cookie wtbPasswordCookie = new Cookie("wtbPasswordCookie","");
		wtbNameCookie.setMaxAge(0);
		wtbPasswordCookie.setMaxAge(0);
		wtbNameCookie.setPath("/");
		wtbPasswordCookie.setPath("/");
		response.addCookie(wtbPasswordCookie);
		response.addCookie(wtbNameCookie);
		return "login";
	}
	
	/**
	 * 七天免登陆方法
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/easyLogin")
	public String easyLogin(HttpServletRequest request,HttpServletResponse response,Model model) {
		System.out.println("进入easylogin");
		String name="";
		String password="";
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if("wtbNameCookie".equals(cookie.getName())) {
				name=cookie.getValue();
			}
			if("wtbPasswordCookie".equals(cookie.getName())) {
				password=cookie.getValue();
			}
		}
		User findUserByName = userServiceImpl.findUserByName(name, password);
		if(findUserByName!=null) {
			System.out.println("重新查询找到用户");
			model.addAttribute("findUserByName",findUserByName);
			return "forward:goIndex";
		}
		System.out.println("没有找到用户，销毁cookie");
		Cookie wtbNameCookie=new Cookie("wtbNameCookie",name);
		Cookie wtbPasswordCookie=new Cookie("wtbPasswordCookie",password);
		
		wtbNameCookie.setPath("/");
		wtbPasswordCookie.setPath("/");
		wtbNameCookie.setMaxAge(0);
		wtbPasswordCookie.setMaxAge(0);
		response.addCookie(wtbNameCookie);
		response.addCookie(wtbPasswordCookie);
		return "login";
	}
}
