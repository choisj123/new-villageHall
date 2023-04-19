package com.kh.villagehall.user.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.villagehall.board.model.service.BoardService;
import com.kh.villagehall.board.model.vo.Board;
import com.kh.villagehall.user.model.vo.User;

@WebServlet("/mypage/myList")
public class MyListServlet extends HttpServlet {
	// 내글목록 페이지 이동
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			// 로그아웃되었을때 불어올 메세지용 세션
			HttpSession session = req.getSession();
			
			// 로그인 객체 불러오기
			User loginUser = (User)req.getSession().getAttribute("loginUser");
			
			if(loginUser != null) {
				int userNo = loginUser.getUserNo();
				
				String path = "/WEB-INF/views/mypage/myList.jsp";
				
				
				// 내글목록 데이터 불러오기
				try {		
					
					//쿼리스트링 얻어오기 == 파라미터 얻어오기
					int type = Integer.parseInt(req.getParameter("type"));
					
					// nav 메뉴(공지사항, 자유게시판, 질문게시판) 선택 시
					// 쿼리스트링에 cp가 없음 --> cp = 1 고정
					int cp = 1;
					
					// 페이지네이션의 번호 선택 시
					// 쿼리스트링에 cp가 있음 --> cp = 쿼리스트링의 cp 값
					if( req.getParameter("cp") != null  ) { // 쿼리스트링에 "cp"가 존재한다면
						cp = Integer.parseInt( req.getParameter("cp") );
					}
					
					BoardService service = new BoardService();
					
					// 게시판 이름, 페이지네이션 객체, 게시글 리스트를 한 번에 반환하는 Service 호출
					Map<String, Object> map = null;
										
					if( req.getParameter("key") == null ) { // 일반 목록 조회
									
						map = service.selectMyBoardList(type, cp, userNo);
									
					}
	
					
					req.setAttribute("map", map);
					req.getRequestDispatcher(path).forward(req, resp);
					
					
				} catch(Exception e) {
					e.printStackTrace();
				}
			} else {
				session.setAttribute("message","로그인 후 이용해주세요");
				resp.sendRedirect("/VillageHall/user/login");
			}
		}
}
