package com.kh.villagehall.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 로그아웃
@WebServlet("/user/logout")
public class LogoutServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// session scope에 세팅된 회원 정보 삭제
		
		// session 얻어오기
		HttpSession session = req.getSession();
		
		// session 전체를 없애고 새로운 session 생성
		session.invalidate();
		
		String message = "로그인 후 이용해주세요.";
		req.setAttribute("message", message);
		
		// 메인페이지로 돌아가기
		resp.sendRedirect(req.getContextPath());

	}
}
