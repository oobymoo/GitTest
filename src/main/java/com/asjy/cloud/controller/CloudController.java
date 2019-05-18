package com.asjy.cloud.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.asjy.cloud.model.ProjectPage;
import com.asjy.cloud.model.User;
import com.asjy.cloud.service.impl.CloudServiceImpl;
import com.asjy.cloud.util.ValidateCode;

import net.sf.json.JSONObject;

@SessionAttributes(value= {"findUserByName"})
@Controller
@RequestMapping("/CloudController")
public class CloudController {
	@Autowired
	CloudServiceImpl cloudserviceImpl;
	
	@RequestMapping("/login")
	public String login() {
		
		return "login";
	}
	
	@RequestMapping(value="doLogin",method=RequestMethod.POST)
	public String doLogin(String userName,String password,String code,String easy,String remberme,HttpSession session,HttpServletResponse response,HttpServletRequest request,Model model){
		//doLogin后面的括号内形参的名必须和前台传来的键名一致
		String randomString=(String)session.getAttribute(ValidateCode.RANDOMCODEKEY);
		if(!code.equalsIgnoreCase(randomString)) {
			model.addAttribute("msg","验证码错误");
			return "login";
		}
		User findUserByName = cloudserviceImpl.findUserByName(userName);
		
		if(findUserByName != null) {
			System.out.println("进入创建cookie");
			if(findUserByName.getPassword().equals(password)){
				
				System.out.println(findUserByName.getUserName()+"-------"+findUserByName.getPassword());
				
				//将值装进model对象，没有加session注解的时候，默认是request域。必须装进model对象才能向后台传值
				Cookie cloudNameCookie = new Cookie("cloudNameCookie", userName);
				Cookie cloudPasswordCookie = new Cookie("cloudPasswordCookie", password);
				
				if("kkk".equals(remberme)) {
					System.out.println("记住我");
					cloudNameCookie.setMaxAge(60*60);
					cloudPasswordCookie.setMaxAge(60*60);
				}
				else if("oooo".equals(easy)) {
					System.out.println("7天免登陆");
					cloudNameCookie.setMaxAge(60*60*24*7);
					cloudPasswordCookie.setMaxAge(60*60*24*7);
				}
				else {
					System.out.println("都没选，时间设置为0");
					cloudNameCookie.setMaxAge(0);
					cloudPasswordCookie.setMaxAge(0);
				}
				model.addAttribute("findUserByName",findUserByName);
				cloudNameCookie.setPath("/");
				cloudPasswordCookie.setPath("/");
				System.out.println(cloudPasswordCookie.getValue());
		
				response.addCookie(cloudPasswordCookie);
				response.addCookie(cloudNameCookie);
				return "index";
			}
			model.addAttribute("msg","密码错误");
			return "login";
		
		}
		model.addAttribute("msg","账号错误");
			return "login";
	}
	
	@RequestMapping("/easyLogin")
	public String easyLogin(HttpServletRequest request,HttpServletResponse response,Model model) {
		System.out.println("进入七天免登陆方法");
		String userName = "";
		String password = "";
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if("cloudNameCookie".equals(cookie.getName())) {
				userName = cookie.getValue();
			}
			if("cloudPasswordCookie".equals(cookie.getName())) {
				password = 	cookie.getValue();
			}
		}
		User findUserByName = cloudserviceImpl.findUserByName(userName);
		if(findUserByName != null) {
			if(findUserByName.getPassword().equals(password)) {
				model.addAttribute("findUserByName",findUserByName);
				return "index";
			}
		}
		return "login";
	}
	
	@ResponseBody
	@RequestMapping(value="/adminSerach",produces= {"text/html;charset=utf-8"})
	public String adminSerach(String projectname,int countPage){
		ProjectPage projectpage = cloudserviceImpl.findProjectByNameAndPage(projectname, countPage);
		JSONObject fromObject = JSONObject.fromObject(projectpage);
		return fromObject.toString();
	}
	
	@RequestMapping("/newUser")
	public String newUser(HttpServletRequest request,HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if("cloudNameCookie".equals(cookie.getName())) {
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
			if("cloudPasswordCookie".equals(cookie.getName())) {
				cookie.setMaxAge(0);
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
		
		
		return "login";
	}

}
