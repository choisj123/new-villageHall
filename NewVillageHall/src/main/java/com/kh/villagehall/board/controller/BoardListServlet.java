package com.kh.villagehall.board.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.villagehall.board.model.service.BoardService;
import com.kh.villagehall.board.model.vo.Board;

@WebServlet("/board/list")
public class BoardListServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
						
		try {
			
			//int categoryNo = Integer.parseInt(req.getParameter("categoryNo"));
			//System.out.println(categoryNo);
			//쿼리스트링 얻어오기 == 파라미터 얻어오기
			int type = Integer.parseInt(req.getParameter("type"));
			
			int categoryNo = Integer.parseInt(req.getParameter("categoryNo"));
			
			
			
			// nav 메뉴(공지사항, 자유게시판, 질문게시판) 선택 시
			// 쿼리스트링에 cp가 없음 --> cp = 1 고정
			int cp = 1;
			
			// 페이지네이션의 번호 선택 시
			// 쿼리스트링에 cp가 있음 --> cp = 쿼리스트링의 cp 값
			if( req.getParameter("cp") != null  ) { // 쿼리스트링에 "cp"가 존재한다면
				cp = Integer.parseInt( req.getParameter("cp") );
			}
			
			//서비스 객체 생성
			BoardService service = new BoardService();
			// 게시판 이름, 페이지네이션 객체, 게시글 리스트를 한 번에 반환하는 Service 호출
			Map<String, Object> map = null;
								
			if( req.getParameter("key") == null ) { // 일반 목록 조회
				map = service.selectBoardList(type, cp, categoryNo);
											
			}else { // 검색 목록 조회
				String key = req.getParameter("key");
				String query = req.getParameter("query");
							
				map = service.searchBoardList(type, cp, key, query, categoryNo);
							
			}
			
			// request 범위로 map을 세팅
			req.setAttribute("map", map);
			req.setAttribute("categoryNo", categoryNo);
			
			String path = "/WEB-INF/views/board/boardList.jsp";

			req.getRequestDispatcher(path).forward(req, resp);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
			
	}
}
