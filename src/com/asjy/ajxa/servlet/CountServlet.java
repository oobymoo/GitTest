package com.asjy.ajxa.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CountServlet
 */
@WebServlet("/CountServlet")
public class CountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CountServlet() {
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
		
		String num=request.getParameter("value");
		String num1=request.getParameter("value1");
		String car=request.getParameter("carry");
		int result=0;
		if("+".equals(car)) {
			result=Integer.parseInt(num)+Integer.parseInt(num1);
		}
		if("-".equals(car)) {
			result=Integer.parseInt(num)-Integer.parseInt(num1);
		}
		if("*".equals(car)) {
			result=Integer.parseInt(num)*Integer.parseInt(num1);
		}
		if("/".equals(car)) {
			result=Integer.parseInt(num)/Integer.parseInt(num1);
		}
		
		response.getWriter().print(result);
	
	}

}
