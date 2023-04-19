package com.kh.villagehall.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.villagehall.user.model.service.UserService;

@WebServlet("/user/emailDupCheck")
public class EmailDupCheckServlet extends HttpServlet{

	// 이메일 중복 검사(비동기 통신)
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		// 비동기 통신으로 전달된 파라미터(data 속성의 key값) 얻어오기
		String userEmail = req.getParameter("userEmail");
				
	
		try {
			// 이메일 중복 검사 서비스 호출 후 결과 반환 받기
			
			UserService service = new UserService();
			
			int result = service.emailDupCheck(userEmail);
			
			// 보통 동기식 코드 작성 시
			// forward 또는 redirect를 이용해서 새로운 페이지가 보이게 동작함.
			
			// *** 비동기 통신 시 응답은 화면이 아닌 데이터(String, XML, JSON, int, ....)
			//	-> 응답용 스트림을 이용해서 단순 데이터 전달만 하면 된다
			
			resp.getWriter().print(result);
			// 응답 스트림을 이용해 result를 출력
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	
	}
	
	
	
	
	
	
}
