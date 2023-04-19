package com.kh.villagehall.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.villagehall.board.model.service.BoardService;
import com.kh.villagehall.board.model.vo.Board;

@WebServlet("/board/kakaoMap")
public class KakaoMapServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		try {
			BoardService service = new BoardService();
			
			List<Board> kakaoMapList = service.kakaoMapBoard();
			
			new Gson().toJson(kakaoMapList, resp.getWriter() );
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	
		
	}
}
