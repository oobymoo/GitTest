package com.asjy.text_user.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.asjy.ajax_user.model.User;
import com.asjy.ajax_user.service.UserService;
import com.asjy.ajax_user.service.Impl.UserServiceImpl;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id=request.getParameter("name");
		UserService us=new UserServiceImpl();
		List<User> list=us.findUserNameById(id);
		int i=list.size();
		response.setContentType("text/html;charset=UTF-8");
		if(i==0) {
			String dat="该账号可用";
			System.out.println(i);
			response.getWriter().print(dat);
		}
		else {
			System.out.println(i);
			String dat="该账号已存在";
			response.getWriter().print(dat);
		}
	}
}
