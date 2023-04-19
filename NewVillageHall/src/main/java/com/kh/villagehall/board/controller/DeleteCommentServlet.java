package com.kh.villagehall.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.villagehall.board.model.service.BoardService;
import com.kh.villagehall.comment.model.vo.Comment;

@WebServlet("/comment/deleteComment")
public class DeleteCommentServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int boardNo = Integer.parseInt(req.getParameter("boardNo"));
		int commentNo = Integer.parseInt(req.getParameter("commentNo"));
		
		Comment comment = new Comment();
		
		comment.setBoardNo(boardNo);
		comment.setCommentNo(commentNo);

		
		try {
			
			BoardService service = new BoardService();
			
			int result = service.deleteComment(comment);
			
			if(result > 0) {
				resp.sendRedirect("/VillageHall/board/boardDetail?boardNo=" + boardNo);
				
			} else {
				resp.sendRedirect("/VillageHall/board/boardDetail?boardNo=" + boardNo);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
