package com.kh.villagehall.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.villagehall.user.model.service.UserService;
import com.kh.villagehall.user.model.vo.User;

@WebServlet("/user/termsOfUse")
public class TermsOfUseServlet extends HttpServlet {
	
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String path = "/WEB-INF/views/user/termsOfUse.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	
	}

@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		//HttpSession session = req.getSession(); // 세션 얻어오기
		
		resp.setContentType("text/html; charset=UTF-8");
 		PrintWriter out = resp.getWriter();


	// 카카오 유저 여부

		try {
		
				out.println("<script>location.href='signUp';</script>");
			 
				out.flush();

		} catch (Exception e) {
			
			
				e.printStackTrace();
				
		}
	
	}

}



	


