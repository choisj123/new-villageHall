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

@WebServlet("/user/test")
public class TestServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user = new User();
		user.setUserEmail("test@gmail.com");
		user.setUserPw("AWK1nM+CjAOElicQOH742Hgdbvccpnn/sChKmDsie2JcQEGbfWqKblQ4JrLuN2ZtAVwTr3NTJqoC/05NxrR74g==");
		
		
		try {
			UserService service = new UserService();
			
			User loginUser = service.login(user);
			HttpSession session = req.getSession();
			System.out.println(loginUser);
			
			if(loginUser != null) {
				session.setAttribute("loginUser", loginUser);
				session.setMaxInactiveInterval(3600);
				
				
			} else {
				session.setAttribute("message", "실패");
			}
			
			
			// redirect
			resp.sendRedirect(req.getContextPath());

			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
