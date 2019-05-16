package com.asjy.wtb.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.asjy.wtb.model.Img;
import com.asjy.wtb.model.User;
import com.asjy.wtb.service.impl.PermissionServiceImpl;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("/PermissionController")
public class PermissionController {
	@Autowired
	PermissionServiceImpl permissionServiceImpl;
	
	/**
	 * 跳转用户授权页面方法
	 * @return 将包含jsp页面名的对象发送到主页，由动态包含页面自动匹配
	 */
	@RequestMapping("/allUser")
	public String allUser(Model model) {
		
		model.addAttribute("url","permission_show_alluser.jsp");
		return "index";
	}
	
	@RequestMapping("/backIndex")
	public String backIndex() {
		return "index";
	}
	
	/**
	 * 进行用户全查询方法
	 * @return 返回一个json数组
	 */
	@ResponseBody
	@RequestMapping(value="/showAllUser",produces="text/html;charset=utf-8")
	public String showAllUser() {
		System.out.println("进入授权用户全查询");
		List<User> allUserList= permissionServiceImpl.allUser();
		JSONArray userList = JSONArray.fromObject(allUserList);
		return userList.toString();
	}
	
	/**
	 * 图片上传方法
	 * @return
	 */
	
	@RequestMapping("/addImg")
	public String addImg(MultipartFile picture,HttpServletRequest request) {
		
		String realPath = request.getSession().getServletContext().getRealPath("img");
		String originalFilename = picture.getOriginalFilename();
		System.out.println(originalFilename);
		if(originalFilename!="")
			{
			
		UUID uuid = UUID.randomUUID();
		String uuidString = uuid.toString();
		File file = new File(realPath+"\\"+uuidString+"-"+originalFilename);
		if(!file.exists()) {
			file.mkdirs();
		}
		try {
			picture.transferTo(file);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		boolean result = permissionServiceImpl.addImg(uuidString+"-"+originalFilename);
		if(result) {
			
			return "forward:searchImg";
		}
			}
		
		return "index";
	}
	
	@RequestMapping("/searchImg")
	public String searchImg(Model model) {
		List<Img> findImgList = permissionServiceImpl.findImg();
		model.addAttribute("findImgList",findImgList);
		
		return "index";
		
	}
	
}
