package com.kh.villagehall.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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
import com.kh.villagehall.board.model.vo.BoardImg;
import com.kh.villagehall.user.model.vo.User;

@WebServlet("/board/writeBoard")
public class WriteBoardServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public WriteBoardServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {

			// insert / update 구분
			String mode = req.getParameter("mode");
			// insert는 별도 처리없이 위임

			// update는 기존 게시글 내용 조회하는 처리 필요
			if (mode.equals("update")) {

				int boardNo = Integer.parseInt(req.getParameter("boardNo"));

				BoardService service = new BoardService();

				Board board = service.selectBoardDetail(boardNo);

				board.setBoardContent(board.getBoardContent().replaceAll("<br>", "\n"));

				req.setAttribute("boardNo", board.getBoardNo());
				req.setAttribute("categoryNo", board.getCategoryNo());
				req.setAttribute("board", board);

			}
			String path = "/WEB-INF/views/board/writeBoard.jsp";

			req.setAttribute("mode", mode);
			req.getRequestDispatcher(path).forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		BoardService service = new BoardService();

		int result = 0;

		try {

			int categoryNo = Integer.parseInt(req.getParameter("category"));
			String boardTitle = req.getParameter("boardTitle");
			String boardContent = req.getParameter("boardContent");
			String latitude = "";
			String longitude = "";
			latitude = req.getParameter("latitude");
			longitude = req.getParameter("longitude");

			// ** 로그인 회원 번호 얻어오기 **
			// 로그인 정보 얻어오기
			User loginUser = (User) (session.getAttribute("loginUser"));

			int userNo = loginUser.getUserNo(); // 로그인 회원 번호

			// content에 있는 img 태그 내 src를 선택해 image url 목록 반환 받기
			List<String> imgUrl = service.getImageList(boardContent);

			// imgUrl을 사용하여 DB에 저장할 이미지 데이터 목록 만들기
			List<BoardImg> iList = new ArrayList<BoardImg>();

			if (!imgUrl.isEmpty()) {// imgUrl 있을 때 == 이미지가 첨부되었을 때

				for (String url : imgUrl) {
					int slash = url.lastIndexOf('/');
					// src에서 뒤에서부터 첫번째 '/'인 index 뽑기
					// => '경로 / 파일명.확장자' 의 경계선인 '/'의 index 확인 가능

					BoardImg temp = new BoardImg();

					// substring(start) ~ start인덱스부터 마지막 인덱스까지 잘라내서 저장
					// substring(start, end) ~ start인덱스부터 end인덱스 전까지 잘라내서 저장
					temp.setFileName(url.substring(slash + 1));
					// ex) uploadImages/image1.jpg → image1.jpg
					temp.setFilePath(url.substring(0, slash + 1));
					// ex) uploadImages/image1.jpg → uploadImages/

					iList.add(temp);
				} // iList 목록 만들기 끝
			}

			// 사진 삽입 실패 시 사진 삭제할 기본 주소 추가
			String root = req.getSession().getServletContext().getRealPath("/");

			// 입력 내용 + 이미지 List Map에 담아서 service로 호출 삽입 결과 반환
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("boardTitle", boardTitle);
			map.put("boardContent", boardContent);
			map.put("categoryNo", categoryNo);// 자유 카테고리
			map.put("userNo", userNo);// 회원번호 == 작성자
			map.put("iList", iList);
			map.put("root", root);
			map.put("latitude", latitude);
			map.put("longitude", longitude);

			Board board = new Board();

			board.setBoardTitle(boardTitle);
			board.setBoardContent(boardContent);
			board.setCategoryNo(categoryNo);
			board.setUserNo(userNo);

			// 모드가 insert일 경우
			String mode = req.getParameter("mode");

			if (mode.equals("insert")) {

				result = service.insertBoard(map, board);

				String path = null;

				if (result > 0) {

					int boardNo = (int) map.get("boardNo");

					path = req.getContextPath() + "/board/boardDetail?boardNo=" + boardNo;

				} else {

					session.setAttribute("message", "게시글 등록을 실패했습니다. 잠시 후 다시 시도해주세요.");

					path = req.getContextPath() + "/board/writeBoard.jsp";

				}

				req.setAttribute("board", board);
				// req.getRequestDispatcher(path).forward(req, resp);
				resp.sendRedirect(path);
				// resp.getWriter().print(result);
			}

			if (mode.equals("update")) {

				int boardNo = Integer.parseInt(req.getParameter("boardNo"));

				map.put("boardNo", boardNo);
				// board.setBoardNo(boardNo);
				// System.out.println(board);

				result = service.updateBoard(map);

				String path = null;

				if (result > 0) {

					path = req.getContextPath() + "/board/boardDetail?boardNo=" + boardNo;
				} else {

					session.setAttribute("message", "게시글 수정에 실패했습니다. 잠시 후 다시 시도해주세요.");

					path = req.getContextPath() + "/board/writeBoard?mode=update&boardNo=" + boardNo;

				}

				req.setAttribute("board", board);
				// req.getRequestDispatcher(path).forward(req, resp);
				resp.sendRedirect(path);
				// resp.getWriter().print(result);

			}

		} catch (Exception e) {
			System.out.println("WriteBoardServlet 수행 중 에러 발생");
			e.printStackTrace();

			String message = "파일 서버 업로드 과정에서 에러 발생";

			// path = "/WEB-INF/views/common/errorPage.jsp";
			req.setAttribute("message", message);
		}

	}
}