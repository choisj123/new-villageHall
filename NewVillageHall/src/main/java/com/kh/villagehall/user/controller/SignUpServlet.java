package com.kh.villagehall.user.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.villagehall.common.MyRenamePolicy;
import com.kh.villagehall.user.model.service.UserService;
import com.kh.villagehall.user.model.vo.User;
import com.oreilly.servlet.MultipartRequest;

 
@WebServlet("/user/signUp") 
public class SignUpServlet extends HttpServlet{   
	 

	// GET 방식 요청 시 JSP로 바로 응답할 수 있도록 요청 위임
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String path = "/WEB-INF/views/user/signUp.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}
	
	
	// POST 방식 요청 시 회원가입 서비스 수행
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
		// 파라미터를 모두 변수에 저장
				String userEmail = req.getParameter("userEmail");
				System.out.println("email"+userEmail);
				String userPw = req.getParameter("userPw");
				String userNickname = req.getParameter("userNickname");
				String userTel = req.getParameter("userTel");
			
				
				// 파라미터를 하나의 Member 객체에 저장
				User user = new User();
				
				user.setUserEmail(userEmail);
				user.setUserPw(userPw);
				user.setUserNickname(userNickname);
				user.setUserTel(userTel);
				
			

				try {
					
					UserService service = new UserService();
					
					// 회원가입 서비스 호출 후 결과 반환 받기
					int result = service.signUp(user);
					
					// 서비스 결과에 따라서 message를 다르게하여 메인 페이지 재요청(redirect)
					
					//HttpSession session = req.getSession();	
					
					resp.setContentType("text/html; charset=UTF-8");
			 		PrintWriter out = resp.getWriter();
			 		
			 		
						
					
					
					if(result > 0) { // 성공
						
						 
						out.println("<script>alert('가입이 완료되었습니다. 환영합니다!');location.href='login';</script>");
						 
						out.flush();

					
						
					}else { // 실패
						
						out.println("<script>alert('가입이 실패하였습니다.') location.href='signUp';</script>");
						 
						out.flush();
						
				
					
					}
					
					resp.sendRedirect( req.getContextPath() );
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				
			
			}
			
	
	
}