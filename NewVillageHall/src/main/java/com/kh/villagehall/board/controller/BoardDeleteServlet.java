package com.kh.villagehall.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.villagehall.board.model.service.BoardService;
import com.kh.villagehall.user.model.vo.User;


// 게시글 삭제 서블
@WebServlet("/board/deleteBoard")
public class BoardDeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		
		
		try {
			
			BoardService service = new BoardService();
			
			int result = service.deleteBoard(boardNo);
			

			resp.sendRedirect("/VillageHall/board/list?type=3&categoryNo=0");

			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
}
