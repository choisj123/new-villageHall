package com.kh.villagehall.board.controller;

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
import com.kh.villagehall.comment.model.vo.Comment;
import com.kh.villagehall.user.model.vo.User;

@WebServlet("/board/boardDetail")
public class BoardDetailServlet extends HttpServlet {

	// 게시글 조회 기능
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				
		
		
		String path = "/WEB-INF/views/board/boardDetail.jsp";
		// 파라미터 얻어오기
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		
		try {
			BoardService service = new BoardService();
			
			// 게시글 정보 받아오기
			Board board = service.selectBoardDetail(boardNo);
			
			//---------------------------------------------------------------
			
			// nav 메뉴(공지사항, 자유게시판, 질문게시판) 선택 시
			// 쿼리스트링에 cp가 없음 --> cp = 1 고정
			int cp = 1;
						
			// 페이지네이션의 번호 선택 시
			// 쿼리스트링에 cp가 있음 --> cp = 쿼리스트링의 cp 값
			if( req.getParameter("cp") != null  ) { // 쿼리스트링에 "cp"가 존재한다면
				cp = Integer.parseInt( req.getParameter("cp") );
			}
			
			// 페이지네이션 객체, 댓글 리스트를 한 번에 반환하는 Service 호출
			Map<String, Object> map = null;
			
			// 댓글 정보 받아오기
			map = service.selectCommentList(boardNo, cp);
			
			req.setAttribute("board", board);
			req.setAttribute("map", map);

			// ----------------------------------------------------------------
			// 게시글 조회시 한번만 조회수 증가
						
			HttpSession session = req.getSession();
			User loginUser = (User) session.getAttribute("loginUser");

			if(loginUser != null && loginUser.getUserNo() == board.getUserNo()) {
			    req.getRequestDispatcher(path).forward(req, resp);
			} else {
			    Boolean alreadyRead = (Boolean) session.getAttribute("alreadyRead" + boardNo);
			    if (alreadyRead == null || !alreadyRead) {
			        int result = service.updateRead(boardNo);
			        session.setAttribute("alreadyRead" + boardNo, true);
			    }
			    req.getRequestDispatcher(path).forward(req, resp);
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
