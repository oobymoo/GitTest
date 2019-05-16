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
	 * @return ��ת��¼ҳ
	 */
	@RequestMapping("/goLogin")
	public String goLogin() {
		return "login";
	}
	
	
	/**
	 * 
	 * @param dayslogin ִ���������½����
	 * @param username �˺�
	 * @param password ����
	 * @param code	         ��֤��
	 * @param session  ��֤�ɹ�����û���Ϣװ��session��
	 * @param response ��ͻ��˷���cookie
	 * @param model	        ����¼�û���Ϣ���͸���Ȩ����
	 * @return		        ת����Ȩ����
	 */
	
	@RequestMapping(value="/doLogin",method=RequestMethod.POST)
	public String doLogin(String dayslogin,String username,String password,String code,HttpSession session,HttpServletResponse response,Model model) {
		if(username =="") {
			model.addAttribute("msg","�˺Ų���Ϊ��");
			return "login";	
		} 
		if(password =="") {
			model.addAttribute("msg","���벻��Ϊ��");
			return "login";
		}
		if(code =="") {
			model.addAttribute("msg","��֤�벻��Ϊ��");
			return "login";
		}
		String randomString=(String)session.getAttribute(ValidateCode.RANDOMCODEKEY);
		if(!code.equalsIgnoreCase(randomString)) {
			model.addAttribute("msg","��֤�����");
			return "login";
		}
		
		User findUserByName = userServiceImpl.findUserByName(username, password);
			
		if(findUserByName == null) {
			model.addAttribute("msg","�˺Ż��������");
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
	 * ��¼�ɹ���,�����û�id��ѯ�û�Ȩ�޷���
	 * @param user ��¼�û�����
	 * @param model ���淢������Ȩ�޼���
	 * @return ��¼��ҳ
	 */
	@RequestMapping("/goIndex")
	public String goIndex(Model model,HttpServletRequest request,HttpSession session) {
		
		User findUserByName = (User)request.getAttribute("findUserByName");

		List<Permission> majorPermissionList = userServiceImpl.majorPermissionList(findUserByName.getId());
		//String masg="�û�����ת·���ǣ�";
		for(Permission majorpermission : majorPermissionList) {
			System.out.println("�û�����Ȩ���У�"+majorpermission.getPermissionName());
			for (Permission minorpermission : majorpermission.getMinorPermissinoList()) {
				System.out.println("���û��ķ�Ȩ���ǣ�"+minorpermission.getPermissionName());
				System.out.println("�û�����ת·���ǣ�"+minorpermission.getPermissionUrl());
				//System.out.println(masg.concat(minorpermission.getPermissionUrl()));
			}
		}
		session.setMaxInactiveInterval(60*60);
		System.out.println("��ѯ��������Ȩ�޼���");
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
	 * �������½����
	 * @param request
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/easyLogin")
	public String easyLogin(HttpServletRequest request,HttpServletResponse response,Model model) {
		System.out.println("����easylogin");
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
			System.out.println("���²�ѯ�ҵ��û�");
			model.addAttribute("findUserByName",findUserByName);
			return "forward:goIndex";
		}
		System.out.println("û���ҵ��û�������cookie");
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
