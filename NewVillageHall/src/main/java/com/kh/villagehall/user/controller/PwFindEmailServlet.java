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

@WebServlet("/user/pwFindEmail")
public class PwFindEmailServlet extends HttpServlet{
	

	
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String path = "/WEB-INF/views/user/pwFindEmail.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	// 파라미터를 모두 변수에 저장
	String userEmail = req.getParameter("userEmail");
	String newPw = req.getParameter("newPw");
	
	HttpSession session = req.getSession();

	// 파라미터를 하나의 Member 객체에 저장
	User user = new User();
	
	user.setUserEmail(userEmail);
	user.setUserPw(newPw);
	
	System.out.println(userEmail);
	System.out.println(newPw);

	
	
	try {
		
		UserService service = new UserService();
		
		// 서비스 호출 후 결과 반환 받기
		int result = service.pwFind(userEmail,newPw);
		
		// 서비스 결과에 따라서 message를 다르게하여 메인 페이지 재요청(redirect)
		
		//HttpSession session = req.getSession();
	
		
		if(result > 0) { // 성공
			
			session.setAttribute("message", "새 비밀번호로 변경이 완료되었습니다.");
			
		}else { // 실패
			
			session.setAttribute("message", "새 비밀번호로 변경을 실패하였습니다.");
		}
		
		resp.sendRedirect( req.getContextPath() );
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	

}



}
