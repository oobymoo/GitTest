package com.asjy.cloud.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asjy.cloud.util.ValidateCode;

/**
 * Servlet implementation class ImgServlet
 */
@WebServlet("/ImgController")
public class ImgController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImgController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//设置相应类型，图片格式
		response.setContentType("image/jpeg");
		//设置响应头，阻止浏览器缓存此响应的内容(节约内存)
		response.setHeader("Pragma", "No-cache");
		
		//引用工具类中的方法，绘制验证码图片
		ValidateCode code = new ValidateCode();
		code.getValidateCode(request, response);//调用绘制验证码的方法，绘制图片
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
