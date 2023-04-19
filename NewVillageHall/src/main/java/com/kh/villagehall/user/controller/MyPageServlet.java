package com.kh.villagehall.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.villagehall.user.model.service.UserService;
import com.kh.villagehall.user.model.vo.User;


@WebServlet("/mypage/myPage")
public class MyPageServlet extends HttpServlet {
	
	UserService service = new UserService();
	
	// 내 정보 수정 클릭 -> myPage 이동
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		String path = "/WEB-INF/views/mypage/myPage.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	
	// myPage에서 비밀번호 일치 검사 후 myInfo 이동
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = null;
		HttpSession session = req.getSession();
		
		User loginUser = (User)req.getSession().getAttribute("loginUser");
		
		if(loginUser != null) {
			
			int userNo = loginUser.getUserNo();
			String inputPw = req.getParameter("inputPw");
			
			int result = 0;
			
			try {
				
				result = service.myInfoCheckPw(userNo, inputPw);
				
				System.out.println("servlet result" + result);
				
				if(result == 1) {
					
					path = "/WEB-INF/views/mypage/changeInfo.jsp";
			
					
				}else {
					
					path = "/WEB-INF/views/mypage/myPage.jsp";
					
					req.setAttribute("result", result);
				}
				
				req.getRequestDispatcher(path).forward(req, resp);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else {
			session.setAttribute("message", "로그인 후 이용해주세요.");
			path = "/WEB-INF/views/user/login.jsp";
			req.getRequestDispatcher(path).forward(req, resp);
			
		}
		
		
		
	
	
	
	}
	
}


