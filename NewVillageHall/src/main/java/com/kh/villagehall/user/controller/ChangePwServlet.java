package com.kh.villagehall.user.controller;

import java.io.IOException;

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

@WebServlet("/mypage/changePw")
public class ChangePwServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = "/WEB-INF/views/mypage/changePw.jsp";
		req.getRequestDispatcher(path).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("post loadeds");

		String newPw = req.getParameter("newPw");

		// ** 로그인 회원 번호 얻어오기 **
		HttpSession session = req.getSession(); // 세션 얻어오기

		// 로그인 정보 얻어오기
		User loginUser = (User) (session.getAttribute("loginUser"));

		int userNo = loginUser.getUserNo(); // 로그인 회원 번호

		try {
			UserService service = new UserService();

			int result = service.changePw(newPw, userNo);

			String path = null; // 리다이렉트 주소

			if (result > 0) { // 성공
				// session scope -> key="message", vlaue="비밀번호 변경 성공!" 세팅
				// path = "내 정보 페이지 주소"
				session.setAttribute("message", "비밀번호 변경 성공!");

				session.invalidate();
				// path = req.getContextPath()"/WEB-INF/views/user/login";
				path = req.getContextPath() + "/user/login";

			} else { // 실패
				// session scope -> key="message", vlaue="현재 비밀번호가 일치하지 않습니다" 세팅
				// path = "비밀번호 변경 페이지 주소"
				session.setAttribute("message", "현재 비밀번호가 일치하지 않습니다");

				// path = "/WEB-INF/views/mypage/changePw";
				path = "changePw";
			}

			resp.sendRedirect(path);
			// req.getRequestDispatcher(path).forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
