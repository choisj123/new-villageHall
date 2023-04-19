package com.kh.villagehall.board.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.villagehall.board.model.vo.BoardImg;
import com.kh.villagehall.common.MyRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

@WebServlet("/board/boardImg")
public class BoardImgServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public BoardImgServlet() {
		super();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("imgServlet");
		
		String path = null; // forward 또는 redirect 경로를 저장할 변수
		RequestDispatcher dispatcher = null; // 요청 위임 객체

		// 에러 메시지 전달용 변수
		String message = null;

		try {
			// 파일 서버에 저장하고 summernote에 다시 보내기 (글작성 과정에서 서버 저장만 진행 DB저장X)
			// 1. MultipartRequest객체 생성
			// 1) 전송 파일 용량 지정(byte 단위)
			int maxSize = 20 * 1024 * 1024;

			// 2) 서버에 업로드된 파일을 저장할 경로 지정
			// ..webapp/
			String root = req.getSession().getServletContext().getRealPath("/");

			// 배포되고 있는 최상위 경로의 실제 경로(webapp)
			// 화면에 보여지는 주소는 .....resources/
			String filePath = root + "resources/images/boardImg/";
			System.out.println("root: " + root);
			System.out.println(filePath);

			// 3) MultipartRequest 객체 생성
			MultipartRequest multiRequest = new MultipartRequest(req, filePath, maxSize,
					"UTF-8", new MyRenamePolicy());
			// 매개변수 (request, 파일주소, 최대용량, 텍스트파일 첨부시 문자인코딩, 새로운파일명이 담긴 file객체)

			System.out.println("객체 생성 완료");

			String fileName = null;// multiRequest에서 시스템 파일명 꺼내와 저장할 변수

			// 시스템파일명에 접근하기 위해 name 속성을 가져와 진행
			Enumeration<String> files = multiRequest.getFileNames();
			while (files.hasMoreElements()) {// 다음 요소가 있다면
				// 현재 접근한 요소 값 반환
				// name에는 파일의 name 속성이 담겨있고
				// 파일의 name 속성을 알면 파일의 정보를 다 구할 수 있음
				String name = files.nextElement();// img0
				if (multiRequest.getFilesystemName(name) != null) {
					fileName = multiRequest.getFilesystemName(name);
					System.out.println(fileName);
				}
			}

			BoardImg image = new BoardImg();

			image.setFilePath("../resources/images/boardImg/");
			image.setFileName(fileName);

			// 2. ajax로 반환(gson 사용) : file을 보내서 filePath와 fileName을 사용해야 함
			new Gson().toJson(image, resp.getWriter());

		} catch (Exception e) {

			e.printStackTrace();

			message = "파일 서버 업로드 과정에서 에러 발생";

			dispatcher = req.getRequestDispatcher(path);
			dispatcher.forward(req, resp);
		}

	}

}
