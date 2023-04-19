package com.kh.villagehall.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.villagehall.user.model.service.UserService;
import com.kh.villagehall.user.model.vo.User;


@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
	
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/user/login.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	
	
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			String inputEmail = req.getParameter("userEmail");
			String inputPw = req.getParameter("userPw");
			
	
			User user = new User();
			user.setUserEmail(inputEmail);
			user.setUserPw(inputPw);
			
			 
			try {  
				 
				UserService service = new UserService();
				
				User loginUser = service.login(user);
				System.out.println(loginUser);
				
				HttpSession session = req.getSession();
				
				if(loginUser != null) {
					
					
					session.setAttribute("loginUser", loginUser);
					
					session.setMaxInactiveInterval(3600);
					

					// 쿠키 객체 생성
					//Cookie c = new Cookie("클라이언트쪽에 저장될 쿠키 이름", "쿠키 내용");
					Cookie c = new Cookie( "saveId" , inputEmail );
					
					
					// 아이디 저장이 체크된 경우
					if( req.getParameter("saveId") != null ) {
						// 쿠키 파일을 30일 동안 유지
						c.setMaxAge(60 * 60 * 24 * 30); // 30일(1초 단위)
						
					} else {
						// 쿠키 파일을 0초 동안 유지
						// -> 기존에 존재하던 쿠키 파일에 유지 시간을 0초 덮어씌움
						//	  == 삭제하겠다는 소리
						c.setMaxAge(0);
					}
					
					// 해당 쿠키 파일이 적용될 주소를 지정
					c.setPath( req.getContextPath()  );
					// req.getContextPath() : 최상위 주소(/community)
					// -> /community 로 시작하는 주소에서만 쿠키 적용
					
					
					// 응답 객체를 이용해서 클라이언트로 전달
					resp.addCookie(c); // 코드가 해석되는 순간 바로 전달
					
					
					 
				} else {
		
					session.setAttribute("message", "아이디 또는 비밀번호를 확인해주세요.");
					String path = "/user/login";
					resp.sendRedirect(req.getContextPath() + path);
				}
				
				
				
				// redirect
				resp.sendRedirect(req.getContextPath());

				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
}

