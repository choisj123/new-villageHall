package com.kh.villagehall.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.villagehall.board.model.service.BoardService;
import com.kh.villagehall.comment.model.vo.Comment;

@WebServlet("/comment/insertComment")
public class InsertCommentServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			int boardNo = Integer.parseInt(req.getParameter("boardNo"));
			int userNo = Integer.parseInt(req.getParameter("userNo"));
			String commentContent = req.getParameter("commentContent");
			
			Comment comment = new Comment();
			
			comment.setBoardNo(boardNo);
			comment.setUserNo(userNo);
			comment.setCommentContent(commentContent);
			
			BoardService service = new BoardService();
			
			int result = service.insertComment(comment);
			
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