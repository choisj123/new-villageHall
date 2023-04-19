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

@WebServlet("/user/kakaoSignUp")
public class KakaoSignUpServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/user/SignUp.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		System.out.println("servlet");
		
		String path = null;
		
		String kakaoEmail = req.getParameter("kakaoEmail");
		String kakaoNickname = req.getParameter("kakaoNickname");
		String kakaoUserKey = req.getParameter("kakaoUserKey");
		
		
		kakaoEmail = kakaoEmail.replaceAll("\"","");
		kakaoNickname = kakaoNickname.replaceAll("\"","");
		
		
		User user = new User();
		
		user.setUserEmail(kakaoEmail);
		user.setUserPw("z4PhNX7vuL3xVChQ1m2AB9Yg5AULVxXcg/SpIdNs6c5H0NE8XYXysP+DGNKHfuwvY7kxvUdBeoGlODJ6+SfaPg==");
		user.setUserNickname(kakaoNickname);
		user.setKakaoUserKey(kakaoUserKey);
		
	
try {
			
			UserService service = new UserService();
			
			// 회원가입 서비스 호출 후 결과 반환 받기
			int result = service.kakaoSignUp(user);
			
			System.out.println("유저"+ user);
			
		
			HttpSession session = req.getSession();	
			
			resp.setContentType("text/html; charset=UTF-8");
	 		PrintWriter out = resp.getWriter();
			
			 //Member m = new MemberService().memberLogin(id);
		


			if(result > 0) { // 성공
				
							
				session.setAttribute("message", "가입이 완료되었습니다. 로그인을 해주시기 바랍니다.");
				
				
							
			}else { // 실패
				
				session.setAttribute("message", "실패");
			
			}
			
			//resp.sendRedirect( req.getContextPath() );
			
		}catch (Exception e) {
			
			System.out.println("SignUpServlet에서 예외 발생");
			e.printStackTrace();
			
			HttpSession session = req.getSession();	
			session.setAttribute("message", "이미 가입된 회원입니다. 로그인을 해주시기 바랍니다.");
			
			
		}
				
	}

}
