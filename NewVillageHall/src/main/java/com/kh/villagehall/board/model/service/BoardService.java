package com.kh.villagehall.board.model.service;

import static com.kh.villagehall.common.JDBCTemplate.close;
import static com.kh.villagehall.common.JDBCTemplate.commit;
import static com.kh.villagehall.common.JDBCTemplate.getConnection;
import static com.kh.villagehall.common.JDBCTemplate.rollback;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kh.villagehall.board.controller.FileInsertFailedException;
import com.kh.villagehall.board.model.dao.BoardDAO;
import com.kh.villagehall.board.model.vo.Board;
import com.kh.villagehall.board.model.vo.BoardImg;
import com.kh.villagehall.board.model.vo.Pagination;
import com.kh.villagehall.comment.model.vo.Comment;


public class BoardService {
	
	private BoardDAO dao = new BoardDAO();
	
	
	/** 내글 게시글 목록 조회 Service
	 * @param type
	 * @param cp
	 * @param userNo
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> selectMyBoardList(int type, int cp, int userNo) throws Exception {
		
		Connection conn = getConnection();
		
		// 게시판 이름 조회 DAO 호출
		String boardName = null;
		if(type == 1) {
			boardName = "내 글";
		} else if (type == 2) {
			boardName = "내 댓글";
		} else {
			boardName = "내 좋아요";
		}
		
		// 1. 특정 게시판 전체 게시글 수 조회 DAO 호출
		int ListCount = dao.getMyListCount(conn, type, userNo);
		
		// 2. 전체 게시글 수 + 현재 페이지(cp)를 이용해 페이지네이션 객체 생성
		Pagination pagination = new Pagination(cp, ListCount);
		
		// 3. 게시글 목록 조
		List<Object> list = dao.selectMyList(conn, pagination, type, userNo);
		
		// 4. Map 객체를 생성하여 1,2 결과 객체를 모두 저장
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("boardName", boardName);
		map.put("pagination", pagination);
		map.put("list", list);
		
		close(conn);
		
		return map; // Map 객체 반
	}
	
	
	/** 게시글 상세조회 service
	 * @param boardNo
	 * @return board
	 * @throws Exception
	 */
	public Board selectBoardDetail(int boardNo) throws Exception {

		Connection conn = getConnection();
		
		Board board = dao.selectBoardDetail(conn, boardNo);
		
		close(conn);

		return board;
	}

  /** 카카오맵 조회 service
	 * @return kakaoMapList
	 * @throws Exception
	 */
	public List<Board> kakaoMapBoard() throws Exception{
		Connection conn = getConnection();
		
		List<Board>kakaoMapList = dao.kakaoMapBoard(conn);
		
		close(conn);
		
		return kakaoMapList;
	}
	


	/** 조회수 증가 service
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int updateRead(int boardNo) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.updateRead(conn, boardNo);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}
  

	/** 좋아요 유무 확인 service
	 * @param userNo
	 * @param boardNo
	 * @return count
	 * @throws Exception
	 */
	public int selectLike(int userNo, int boardNo) throws Exception {
		
		Connection conn = getConnection();
		
		int count = dao.selectLike(conn, userNo, boardNo);
		
		close(conn);
		
		return count;
	}
  
	
	/** 좋아요 업데이트 service 
	 * @param userNo
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int updateLike(int userNo, int boardNo) throws Exception {

		Connection conn = getConnection();
		
		int result = dao.updateLike(conn, userNo, boardNo);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}
  

	/** 좋아요 취소 service
	 * @param userNo
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteLike(int userNo, int boardNo) throws Exception {

		Connection conn = getConnection();
		
		int result = dao.deleteLike(conn, userNo, boardNo);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 게시글 삭제 service
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteBoard(int boardNo) throws Exception {

		Connection conn = getConnection();
		
		int result = dao.deleteBoard(conn, boardNo);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	
	/** 인기글 조회 service
	 * @param sortBy
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectPopularBoard(String sortBy) throws Exception {

		Connection conn = getConnection();
		
		List<Board> boardList = dao.selectPopularBoard(conn, sortBy);
		
		close(conn);
		
		return boardList;
	}

	/** 카카오맵 service
	 * @return
	 * @throws Exception
	 */
	public List<Board> kakaoMapBoardRecent()throws Exception {
		
		Connection conn = getConnection();
		
		List<Board> kakaoBoardRecent = dao.kakaoMapBoardRecent(conn);
		
		close(conn);
		
		return kakaoBoardRecent;
	}  

	/** 게시글 내 댓글 목록 조회 service
	 * @param boardNo
	 * @param cp
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> selectCommentList(int boardNo, int cp) throws Exception {

		Connection conn = getConnection();
		
		int listCount = dao.getCommentListCount(conn, boardNo);
		
		Pagination pagination = new Pagination(cp, listCount);
		
		List<Comment> commentList = dao.selectCommentList(conn, pagination, boardNo);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("pagination", pagination);
		map.put("commentList", commentList);
		
		close(conn);
		
		return map;
	}

	
	/** 게시글 등록 service
	 * @param map
	 * @return result
	 * @throws Exception
	 */
	public int insertBoard(Map<String, Object> map, Board board) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.insertBoard(conn, map);
		
		int boardNo = 0;
		
		try {
			boardNo = dao.getBoardNo(conn, board);
			
			if(boardNo > 0) {
				map.put("boardNo", boardNo);
				//스크립팅 방지, 개행문자 처리 안함!!!!!!(content가 html코드이기 때문)
			}
			
			
			if(result > 0) {
				//4. 이미지 파일 목록 iList 꺼내서 향상된 for문으로 하나씩 dao 호출
				List<BoardImg> iList = (List<BoardImg>)map.get("iList");
				
				
				if(result > 0 && !iList.isEmpty()) {
					result = 0;//result 재활용2
					
					for(BoardImg img : iList) {
						//Image 객체에 글번호 추가
						img.setBoardNo(boardNo);
						
						result = dao.insertImage(conn, img);
						
						if(result == 0) {
							//삽입 실패 시 강제로 예외를 발생 시켜 
							//catch문에서 파일 삭제를 진행해야함
							throw new FileInsertFailedException("이미지 정보 삽입 실패");
							//밑에 있는 catch문에서 잡음
						}
					}//이미지 삽입 for문 끝
				}//이미지 삽입 if문 끝
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
			List<BoardImg> iList = (List<BoardImg>)map.get("iList");
			
			if(!iList.isEmpty()) {
				for(BoardImg img : iList) {
					if(!img.getFilePath().contains("http")) {//인터넷 주소로 연결한 img의 경우 삭제 진행X
						//현재 썸머 노트를 통해 저장된 이미지의 주소는 ../resource/uploadImages 
						//(썸머노트에서 src에 하려면 이 주소만 되어서 상대주소로 작성..)
						//→ 이미지 삭제를 위해 필요한 주소는 C:/workspace/semi/Chooru_Pj\semiProject\WebContent\resources/uploadImages
						//→ 바꿔줘야 함
						String filePath = (String)map.get("root");
						filePath += img.getFilePath().substring(3);
						
						String fileName = img.getFileName();
						
						File deleteFile = new File(filePath + fileName);
						//해당 파일의 전체 주소 : filePath + fileName
						//File 객체는 전체 주소에 있는 파일 객체를 선택할 때 사용함
						// → filePath+fileName인 파일이 없다면 deleteFile은 null 값.
						
						if(deleteFile.exists()) deleteFile.delete();
					}
				}
			}
				
		}finally{
			
			if(result > 0) {
				commit(conn);
				result = boardNo;
			}
			else rollback(conn);
			
			close(conn);
			
		}
				
		return result;
	
	}

	
	/** 썸머노트 사용 시 이미지 url 추출 service(html코드에서 img src 추출)
	 * @param content
	 * @return imageUrl
	 * @throws Exception
	 */
	public List<String> getImageList(String content) throws Exception{
		//자바 정규표현식을 사용하여 <img>태그의 src 가져오기
		Pattern nonValidPattern = Pattern.compile("<img[^>]*src=[\"']?([^>\"']+)[\"']?[^>]*>");
	
		List<String> imgUrl = new ArrayList<String>();
		Matcher matcher = nonValidPattern.matcher(content);
	
		while (matcher.find()) {
			imgUrl.add(matcher.group(1));
		}
		
		return imgUrl;
	}
	
	

	/** 메인페이지 인기글 목록 조회 Service
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectMainPagePopularBoard() throws Exception {
		
		Connection conn = getConnection();
		
		List<Board> boardList = dao.selectMainPagePopularBoard(conn);
		
		close(conn);
		
		return boardList;
	}

	/** 메인페이지 공지사항 목록 조회 Service
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectMainPageNotice() throws Exception {
		
		Connection conn = getConnection();
		
		List<Board> boardList = dao.selectMainPageNotice(conn);
		
		close(conn);
		
		return boardList;
	}
	
	/** 게시글 목록 조회 Service
	 * @param type
	 * @param cp
	 * @param categoryNo
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> selectBoardList(int type, int cp, int categoryNo) throws Exception {
		
		Connection conn = getConnection();
		
		// 게시판 이름 조회 DAO 호출
		String boardName = dao.selectBoardName(conn, type);
		
		// 1. 특정 게시판 전체 게시글 수 조회 DAO 호출
		int listCount = dao.getListCount(conn, type, categoryNo);
		
		// 2. 전체 게시글 수 + 현재 페이지(cp)를 이용해 페이지네이션 객체 생성
		Pagination pagination = new Pagination(cp, listCount);
		
		// 3. 게시글 목록 조
		List<Board> boardList = dao.selectBoardList(conn, pagination, type, categoryNo);
		
		// 4. Map 객체를 생성하여 1,2 결과 객체를 모두 저장
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("boardName", boardName);
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		map.put("categoryNo", categoryNo);
		
		close(conn);
		
		return map; // Map 객체 반
	}
	
	/** 검색 목록 조회 Service
	 * @param category
	 * @param cp
	 * @param key
	 * @param query
	 * @param categoryNo
	 * @return map
	 * @throws Exception
	 */
	public Map<String, Object> searchBoardList(int type, int cp, String key, String query, int categoryNo) throws Exception {
		
		Connection conn = getConnection();
		
		// 기존 목록 조회 Service, DAO, SQL을 참고하면서 진행
		// 게시판 이름 조회 DAO 호출
		String boardName = dao.selectBoardName(conn, type);	
		
		// 1. SQL 조건절에 추가될 구문 가공(key, query 사용)
		String condition = null;// 조건
		
		switch(key) {
		case "t"  : condition = " AND BOARD_TITLE LIKE '%"+query+"%' ";  break;
		case "c"  : condition = " AND BOARD_CONTENT LIKE '%"+query+"%' ";  break;
		case "tc" : condition = " AND (BOARD_TITLE LIKE '%"+query+"%' OR BOARD_CONTENT LIKE '%"+query+"%') ";  break;
		case "w"  : condition = " AND USER_NICKNAME LIKE '%"+query+"%' "; break;
		}
		
		// 3-1. 특정 게시판에서 조건을 만족하는 게시글 수 조회
		int listCount = dao.searchListCount(conn, type, condition, categoryNo);
				
		// 3-2. listCount  + 현재 페이지(cp)를 이용해 페이지네이션 객체 생성
		Pagination pagination = new Pagination(cp, listCount);		
		
		
		// 4. 특정 게시판에서 조건을 만족하는 게시글 목록 조회
		List<Board> boardList = dao.searchBoardList(conn, pagination, type, condition, categoryNo);
		
		// 5. 결과 값을 하나의 Map에 모아서 반환
		Map<String, Object> map = new HashMap<>();
		
		map.put("boardName", boardName);
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		map.put("categoryNo", categoryNo);
		
		close(conn);
	
		return map;
	}


	/** 게시글 수정 Service
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(Map<String, Object> map) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.updateBoard(conn, map);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	/** 댓글 등록 Service
	 * @param comment
	 * @return result
	 * @throws Exception
	 */
	public int insertComment(Comment comment) throws Exception {

		Connection conn = getConnection();
		
		int result = dao.insertComment(conn, comment);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 댓글 삭제 Service
	 * @param comment
	 * @return result
	 * @throws Exception
	 */
	public int deleteComment(Comment comment) throws Exception {

		Connection conn = getConnection();
		
		int result = dao.deleteComment(conn, comment);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}


	/** 댓글 수정 Service
	 * @param comment
	 * @return result
	 * @throws Exception
	 */
	public int updateComment(Comment comment) throws Exception {
		
		Connection conn = getConnection();
		
		int result = dao.updateComment(conn, comment);
		
		if(result > 0) commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}





}