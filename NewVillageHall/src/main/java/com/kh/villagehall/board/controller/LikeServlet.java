package com.kh.villagehall.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.villagehall.board.model.service.BoardService;
import com.kh.villagehall.user.model.vo.User;

@WebServlet("/board/like")
public class LikeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {		
		
		User loginUser = (User)req.getSession().getAttribute("loginUser");
		int userNo = loginUser.getUserNo();
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
										
		try {
			// 보드 서비스 연결
			BoardService service = new BoardService();
			// 좋아요 유무 받아오기			
			int count = service.selectLike(userNo, boardNo);
			
			
			// 좋아요가 없을시(좋아요누르기)
			if(count == 0) {
				int result = service.updateLike(userNo, boardNo);
				
				req.setAttribute("result", result);
				resp.sendRedirect("/VillageHall/board/boardDetail?boardNo=" + boardNo);
				
			// 좋아요가 있을시(좋아요삭제)	
			} else if (count == 1) {
				int result = service.deleteLike(userNo, boardNo);
				
				req.setAttribute("result", result);
				resp.sendRedirect("/VillageHall/board/boardDetail?boardNo=" + boardNo);
				
				
			}
						
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
