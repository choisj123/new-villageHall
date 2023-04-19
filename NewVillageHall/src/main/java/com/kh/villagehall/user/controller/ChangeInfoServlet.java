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

@WebServlet("/mypage/changeInfo")
public class ChangeInfoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = "/WEB-INF/views/mypage/changeInfo.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int result = 0;
		
		try {
			String newNickname = req.getParameter("newNickname");
			String newTel = req.getParameter("newTel");

			HttpSession session = req.getSession();
			User loginUser = (User) (session.getAttribute("loginUser"));
			int userNo = loginUser.getUserNo();
			String userNickname = loginUser.getUserNickname();
			String userTel = loginUser.getUserTel();

			
			if(userNickname.equals(newNickname)) {
				
				User user = new User();
				
				user.setUserNo(userNo);
				user.setUserTel(newTel);
				
				UserService service = new UserService();
				
				result = service.updateUser(user);
				
				
			}else if(userTel.equals(newTel)) {
				
				User user = new User();
				
				UserService service = new UserService();
				
				int dupResult = service.nicknameDupCheck(newNickname);
				
				if(dupResult == 0) {
					user.setUserNo(userNo);
					user.setUserNickname(newNickname);
					result = service.updateUser(user);
				}				
				
			}else {
				User user = new User();	
				
				UserService service = new UserService();
				
				int dupResult = service.nicknameDupCheck(newNickname);
				
				if(dupResult == 0) {
					user.setUserNo(userNo);
					user.setUserNickname(newNickname);
					user.setUserTel(newTel);
					result = service.updateUser(user);
				}
				
				
				
				
				
				
		
				
			}
			

			if (result > 0) { // 성공
				session.setAttribute("message", "회원 정보가 수정되었습니다.");
 
				// DB는 수정되었지만
				// 로그인한 회원 정보가 담겨있는 Session의 정보는 그대로다!
				// -> 동기화 작업
				loginUser.setUserNickname(newNickname);
				loginUser.setUserTel(newTel);

			} else { // 실패
				session.setAttribute("message", "중복된 닉네임입니다.");

			}
			System.out.println(result);
			System.out.println(newNickname);
			System.out.println(newTel);

			// 성공/실패 여부 관계 없이 "내 정보" 화면 재요청

			// 절대 경로
			resp.sendRedirect(req.getContextPath() + "/mypage/changeInfo");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}