package com.kh.villagehall.board.model.dao;

import static com.kh.villagehall.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.kh.villagehall.board.model.vo.Board;
import com.kh.villagehall.board.model.vo.BoardImg;
import com.kh.villagehall.board.model.vo.Pagination;
import com.kh.villagehall.comment.model.vo.Comment;


public class BoardDAO {

	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private Properties prop;
	
	public BoardDAO() {
		try {
			prop = new Properties();
			
			String filePath = BoardDAO.class.getResource("/com/kh/villagehall/sql/board-sql.xml").getPath();
			
			prop.loadFromXML(new FileInputStream(filePath));
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	/** 인기글 게시판 조회 DAO
	 * @param conn
	 * @param sortBy
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectPopularBoard(Connection conn, String sortBy) throws Exception {
		// 리스트 객체 생성
		List<Board> boardList = new ArrayList<>();
				
		try {
			
			String sql = "";
			
			if(sortBy.equals("read")) {
				sql = prop.getProperty("sortByReadCount");
			} else if(sortBy.equals("like")) {
				sql = prop.getProperty("sortByLikeCount");
			}
			
			
					
			stmt = conn.createStatement();
			
			
					
			rs = stmt.executeQuery(sql);
					
			while(rs.next()) {
				Board board = new Board();
				
				board.setBoardNo(rs.getInt(2));
				board.setBoardTitle(rs.getString(3));
				board.setBoardCreateDate(rs.getString(4));
				board.setUserNickname(rs.getString(5));
				board.setReadCount(rs.getInt(6));
				board.setLikeCount(rs.getInt(7));
				board.setCategoryName(rs.getString(8));
				board.setCommentCount(rs.getInt(9));
						
				boardList.add(board);
			}
					
			}finally{
				close(rs);
				close(stmt);
			}
		
		return boardList;
	}
	
	/** 게시글 상세조회 DAO
	 * @param conn
	 * @param boardNo
	 * @return
	 * @throws Exception
	 */
	public Board selectBoardDetail(Connection conn, int boardNo) throws Exception {
		
		Board board = null;
		
		try {
			
			String sql = prop.getProperty("selectBoardDetail");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new Board();
				
				board.setBoardNo(rs.getInt(1));
				board.setBoardTitle(rs.getString(2));				
				board.setBoardContent(rs.getString(3));
				board.setBoardCreateDate(rs.getString(4));
				board.setUserNickname(rs.getString(5));
				board.setReadCount(rs.getInt(6));
				board.setLikeCount(rs.getInt(7));
				board.setCategoryName(rs.getString(8));
				board.setCategoryNo(rs.getInt(9));
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return board;
	} 

  
  /** 카카오 맵 DAO
	 * @param conn
	 * @return kakaoMapList;
	 * @throws Exception
	 */
	public List<Board> kakaoMapBoard(Connection conn) throws Exception{
		List<Board> kakaoMapList = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("kakaoMapList");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Board boardList = new Board();
				
				boardList.setBoardNo(rs.getInt(1));
				boardList.setBoardTitle(rs.getString(2));
				boardList.setBoardContent(rs.getString(3));
				boardList.setBoardCreateDate(rs.getString(4));
				boardList.setLatitude(rs.getDouble(5));
				boardList.setLongtitude(rs.getDouble(6));
				boardList.setCategoryName(rs.getString(7));
				boardList.setUserNickname(rs.getString(8));
				boardList.setProfileImg(rs.getString(9));
				boardList.setLikeCount(rs.getInt(10));
				boardList.setCommentCount(rs.getInt(11));
				
				kakaoMapList.add(boardList);
			}
			
		}finally {
			close(rs);
			close(stmt);
		}
		
		return kakaoMapList;
	}
  

	/** 조회수 증가 dao
	 * @param conn
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int updateRead(Connection conn, int boardNo) throws Exception {

		int result = 0;
		
		try {
			String sql = prop.getProperty("updateRead");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
  

	/** 좋아요 유무 확인 dao
	 * @param conn
	 * @param userNo
	 * @param boardNo
	 * @return count
	 * @throws Exception
	 */
	public int selectLike(Connection conn, int userNo, int boardNo) throws Exception {

		int count = 0;
		
		try {
			String sql = prop.getProperty("selectLike");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, boardNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} finally {
			close(rs);
			close(pstmt);
		}
		return count;
	}
  
	
	/** 좋아요 업데이트 DAO
	 * @param conn
	 * @param userNo
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int updateLike(Connection conn, int userNo, int boardNo) throws Exception {

		int result = 0;
		
		try {
			String sql = prop.getProperty("updateLike");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, boardNo);
			
			result = pstmt.executeUpdate();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	

	/** 좋아요 취소 dao
	 * @param conn
	 * @param userNo
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteLike(Connection conn, int userNo, int boardNo) throws Exception {

		int result = 0;
		
		try {
			
			String sql = prop.getProperty("deleteLike");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, boardNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 게시글 삭제 DAO
	 * @param conn
	 * @param boardNo
	 * @return result
	 * @throws Exception
	 */
	public int deleteBoard(Connection conn, int boardNo) throws Exception {

		int result = 0;
		
		try {
			String sql = prop.getProperty("deleteBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	
	
	/** 맵 NAV DAO
	 * @param conn
	 * @return kakaoBoardRecent
	 * @throws Exception
	 */
	public List<Board> kakaoMapBoardRecent(Connection conn) throws Exception{
		
		List<Board> kakaoBoardRecent = new ArrayList<>();
		
		try {
			String sql = prop.getProperty("kakaoMapBoardRecent");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Board boardList = new Board();
				
			
				boardList.setBoardTitle(rs.getString(1));
				boardList.setBoardCreateDate(rs.getString(2));
				boardList.setCategoryName(rs.getString(3));
				boardList.setUserNickname(rs.getString(4));
				boardList.setBoardNo(rs.getInt(5));
				boardList.setProfileImg(rs.getString(6));
				
				kakaoBoardRecent.add(boardList);
			}
			System.out.println(kakaoBoardRecent);
			
		}finally {
			close(rs);
			close(stmt);
			
		}
		
		return kakaoBoardRecent;
	}

	
	/** 게시글 내 댓글 조회 DAO
	 * @param conn
	 * @param pagination
	 * @param boardNo
	 * @return commentList
	 * @throws Exception
	 */
	public List<Comment> selectCommentList(Connection conn, Pagination pagination, int boardNo) throws Exception {

		List<Comment> commentList = new ArrayList<>();
		
		try {
			
			String sql = prop.getProperty("selectCommentList");
			
			// BETWEEN 구문에 들어갈 범위 계산
			int start =  ( pagination.getCurrentPage() - 1 ) * pagination.getLimit() + 1;
			int end = start + pagination.getLimit() - 1;
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);		
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Comment comment = new Comment();
				comment.setCommentNo(rs.getInt(2));
				comment.setProfileImg(rs.getString(3));
				comment.setUserNickname(rs.getString(4));
				comment.setCommentContent(rs.getString(5));
				comment.setCommentCreateDate(rs.getString(6));

				
				commentList.add(comment);				
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return commentList;
	
	}
  
	
	/** 게시글 등록 DAO
	 * @param conn
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int insertBoard(Connection conn, Map<String, Object> map) throws Exception{
		int result = 0;
		
		
		try {
			String sql = prop.getProperty("insertBoard");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, (String)map.get("boardTitle"));
			pstmt.setString(2, (String)map.get("boardContent"));
			pstmt.setString(3, (String)map.get("latitude"));
			pstmt.setString(4, (String)map.get("longitude"));
			
			pstmt.setInt(5, (int)map.get("categoryNo"));
			pstmt.setInt(6, (int)map.get("userNo"));
			
			result = pstmt.executeUpdate();
			

		} finally {
			close(pstmt);
		}
		
		return result;
	}



	/** BOARD_IMG에 이미지 삽입
	 * @param conn
	 * @param img
	 * @return
	 * @throws Exception
	 */
	public int insertImage(Connection conn, BoardImg img) throws Exception{
		
		int result = 0;
		
		
		try {
			String sql = prop.getProperty("insertImage");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, img.getFileName());
			pstmt.setString(2, img.getFilePath());
			pstmt.setInt(3, img.getBoardNo());
			
			result = pstmt.executeUpdate();
			
		   
			
		} finally {
			close(pstmt);
		}
		
		return result;
	
	}
	
	/** 게시글 등록 - 게시글 번호 얻어오기 
	 * @param conn
	 * @param board
	 * @return
	 * @throws Exception
	 */
	public int getBoardNo(Connection conn, Board board) throws Exception{
		int boardNo = 0;
	
		try {
			
			String sql = prop.getProperty("getBoardNo");
			
			stmt = conn.createStatement();
	
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				boardNo = rs.getInt(1);
			}
		
			
		} finally {
			close(pstmt);
			close(rs);
		}
		
		return boardNo;
	}

	/** 메인페이지 인기글 목록 조회 dao
	 * @param conn
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectMainPagePopularBoard(Connection conn) throws Exception {
		
		List<Board> boardList = new ArrayList<>();
		
		try {
			
			String sql = prop.getProperty("selectMainPagePopularBoard");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Board board = new Board();
				
				board.setBoardNo(rs.getInt(2));
				board.setBoardTitle(rs.getString(3));
				board.setReadCount(rs.getInt(4));
				board.setCommentCount(rs.getInt(5));
				
				boardList.add(board);
			}
			
		} finally {
			close(rs);
			close(stmt);
		}
		
		return boardList;
	}

	/** 메인페이지 공지사항 목록 조회 dao
	 * @param conn
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectMainPageNotice(Connection conn) throws Exception {
		
		List<Board> boardList = new ArrayList<>();
		
		try {
			
			String sql = prop.getProperty("selectMainPageNotice");
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Board board = new Board();
				
				board.setBoardNo(rs.getInt(2));
				board.setBoardTitle(rs.getString(3));
				board.setReadCount(rs.getInt(4));
				
				boardList.add(board);
			}
			
		} finally {
			close(rs);
			close(stmt);
		}
		
		return boardList;
	}
	
	/** 게시판 이름 조회 DAO
	 * @param conn
	 * @param type
	 * @return boardName;
	 * @throws Exception
	 */
	public String selectBoardName(Connection conn, int type) throws Exception {
		int typeNo = 0;
		String boardName = null;
		
		try {
			String sql = prop.getProperty("selectBoardName");
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, type);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				typeNo = rs.getInt(1);
				if(typeNo == 1) {
					boardName = "공지사항";
				} else if (typeNo == 2) {
					boardName = "FAQ";
				} else {
					boardName = "전체글";
				}
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return boardName;
	}
	
	/** 특정 게시판 전체 게시글 수 조회 DAO
	 * @param conn
	 * @param category
	 * @return listCount
	 * @throws Exception
	 */
	public int getListCount(Connection conn, int type, int categoryNo) throws Exception{
		int listCount = 0;
		
		try {
			
			String categoryQuery = " AND CATEGORY_NO = ?";
			
			if(categoryNo == 0) {
				String sql = prop.getProperty("getListCount");
			
				pstmt = conn.prepareStatement(sql);
			
				pstmt.setInt(1, type);
			} else {
				String sql = prop.getProperty("getListCount") + categoryQuery;
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, type);
				pstmt.setInt(2, categoryNo);
			}
			
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		}finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}
	
	public int getCommentListCount(Connection conn, int boardNo) throws Exception {

		int listCount = 0;
		
		try {
			
			String sql = prop.getProperty("getCommentListCount");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		return listCount;
	}
	
	/** 내 게시판 게시글 수 조회
	 * @param conn
	 * @param type
	 * @param userNo
	 * @return listCount
	 * @throws Exception
	 */
	public int getMyListCount(Connection conn, int type, int userNo) throws Exception {

		int listCount = 0;
		
		try {
			
			if(type == 1) {
				String sql = prop.getProperty("getMyBoardListCount");
				pstmt = conn.prepareStatement(sql);
			} else if (type == 2) {
				String sql = prop.getProperty("getMyCommentListCount");
				pstmt = conn.prepareStatement(sql);
			} else {
				String sql = prop.getProperty("getMyLikeListCount");
				pstmt = conn.prepareStatement(sql);
			}
			pstmt.setInt(1, userNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
						
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return listCount;
	}
	
	/** 특정 게시판에서 일정한 범위의 목록 조회 DAO
	 * @param conn
	 * @param pagination
	 * @param type
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> selectBoardList(Connection conn, Pagination pagination, int type, int categoryNo) throws Exception {
		// 리스트 객체 생성
		List<Board> boardList = new ArrayList<>();		
		
		try {
			
			String categoryQuery = " AND CATEGORY_NO = ?";
			
			if(categoryNo == 0) {
				String sql = prop.getProperty("selectBoardList1")
						+ prop.getProperty("selectBoardList2");
				
				// BETWEEN 구문에 들어갈 범위 계산
				int start =  ( pagination.getCurrentPage() - 1 ) * pagination.getLimit() + 1;
				int end = start + pagination.getLimit() - 1;
				
				pstmt = conn.prepareStatement(sql);				
				pstmt.setInt(1, type);						
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);			
				
				rs = pstmt.executeQuery();
			} else {
				String sql = prop.getProperty("selectBoardList1") 
						+ categoryQuery
						+ prop.getProperty("selectBoardList2");
				
				// BETWEEN 구문에 들어갈 범위 계산
				int start =  ( pagination.getCurrentPage() - 1 ) * pagination.getLimit() + 1;
				int end = start + pagination.getLimit() - 1;
				
				pstmt = conn.prepareStatement(sql);				
				pstmt.setInt(1, type);
				pstmt.setInt(2, categoryNo);
				pstmt.setInt(3, start);
				pstmt.setInt(4, end);			
				
				rs = pstmt.executeQuery();
			}
			
			
			while(rs.next()) {
				Board board = new Board();
												
				board.setBoardNo(rs.getInt(2));
				board.setBoardTitle(rs.getString(3));
				board.setBoardCreateDate(rs.getString(4));
				board.setUserNickname(rs.getString(5));				
				board.setReadCount(rs.getInt(6));
				board.setLikeCount(rs.getInt(7));
				board.setCategoryName(rs.getString(8));
				board.setBoardContent(rs.getString(9));
				board.setCommentCount(rs.getInt(10));
				
				boardList.add(board);
			}
			
		  }finally{
			  close(rs);
			  close(pstmt);
		  }
      return boardList;
	}
	
	
	/** 내 게시글 목록 조회
	 * @param conn
	 * @param pagination
	 * @param type
	 * @param userNo
	 * @return list
	 * @throws Exception
	 */
	public List<Object> selectMyList(Connection conn, Pagination pagination, int type, int userNo) throws Exception {

		// 리스트 객체 생성
		List<Object> list = new ArrayList<>();
		
		try {
			
			if(type == 1) {
				String sql = prop.getProperty("selectMyBoardList");
				
				// BETWEEN 구문에 들어갈 범위 계산
				int start =  ( pagination.getCurrentPage() - 1 ) * pagination.getLimit() + 1;
				int end = start + pagination.getLimit() - 1;
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, userNo);			
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);			
				
				rs = pstmt.executeQuery();
				
				while(rs.next() ) {
					Board board = new Board();
					
					board.setBoardNo(rs.getInt(2));
					board.setBoardTitle(rs.getString(3));
					board.setBoardCreateDate(rs.getString(4));
					board.setReadCount(rs.getInt(5));
					board.setLikeCount(rs.getInt(6));
					board.setCategoryName(rs.getString(7));
					board.setCommentCount(rs.getInt(8));
					
					list.add(board);	
				}
			} else if (type == 2) {
				String sql = prop.getProperty("selectMyCommentList");
				
				int start =  ( pagination.getCurrentPage() - 1 ) * pagination.getLimit() + 1;
				int end = start + pagination.getLimit() - 1;
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, userNo);			
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);			
				
				rs = pstmt.executeQuery();
				
				while(rs.next() ) {
					Comment comment = new Comment();
					
					comment.setCommentContent(rs.getString(2));
					comment.setCommentCreateDate(rs.getString(3));
					comment.setBoardTitle(rs.getString(4));
					comment.setBoardNo(rs.getInt(5));
					
					list.add(comment);
				}
			} else {
				String sql = prop.getProperty("selectMyLikeList");
				
				int start =  ( pagination.getCurrentPage() - 1 ) * pagination.getLimit() + 1;
				int end = start + pagination.getLimit() - 1;
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, userNo);			
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);			
				
				rs = pstmt.executeQuery();
				
				while(rs.next() ) {
					Board board = new Board();
					
					board.setBoardNo(rs.getInt(2));
					board.setBoardTitle(rs.getString(3));
					board.setBoardCreateDate(rs.getString(4));
					board.setUserNickname(rs.getString(5));
					board.setReadCount(rs.getInt(6));
					board.setLikeCount(rs.getInt(7));
					board.setCategoryName(rs.getString(8));
					board.setCommentCount(rs.getInt(9));
									
					list.add(board);
				}
			}
			
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return list;
	}
	
	/** 검색한 게시글 수 조회 DAO
	 * @param conn
	 * @param type
	 * @param condition
	 * @param categoryNo
	 * @return listCount
	 * @throws Exception
	 */
	public int searchListCount(Connection conn, int type, String condition, int categoryNo) throws Exception {
		
		int listCount = 0;
		
		try {
			
			String categoryQuery = " AND CATEGORY_NO = ?";
			
			if(categoryNo == 0) {
				String sql = prop.getProperty("searchListCount") + condition;
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, type);
				rs = pstmt.executeQuery();
			} else {
				String sql = prop.getProperty("searchListCount") + condition + categoryQuery;
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, type);
				pstmt.setInt(2, categoryNo);
				rs = pstmt.executeQuery();
			}
						
			
			if(rs.next()) {
				listCount = rs.getInt(1);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		
		return listCount;
	}
	
	/** 검색한 게시글 목록 조회 dao
	 * @param conn
	 * @param pagination
	 * @param type
	 * @param condition
	 * @param categoryNo
	 * @return boardList
	 * @throws Exception
	 */
	public List<Board> searchBoardList(
			Connection conn, Pagination pagination, int type, String condition, int categoryNo) throws Exception {

		List<Board> boardList = new ArrayList<Board>();		
		
		try {
			
			String categoryQuery = " AND CATEGORY_NO = ?";
			
			if(categoryNo == 0) {
				String sql = prop.getProperty("searchBoardList1")
						   + condition
						   + prop.getProperty("searchBoardList2");
				
				// BETWEEN 구문에 들어갈 범위 계산
				int start =  ( pagination.getCurrentPage() - 1 ) * pagination.getLimit() + 1;
				int end = start + pagination.getLimit() - 1;
							
				pstmt = conn.prepareStatement(sql);
							
				pstmt.setInt(1, type);
				pstmt.setInt(2, start);
				pstmt.setInt(3, end);
							
				rs = pstmt.executeQuery();
			} else {
				String sql = prop.getProperty("searchBoardList1")
						   + categoryQuery
						   + condition
						   + prop.getProperty("searchBoardList2");
				
				// BETWEEN 구문에 들어갈 범위 계산
				int start =  ( pagination.getCurrentPage() - 1 ) * pagination.getLimit() + 1;
				int end = start + pagination.getLimit() - 1;
							
				pstmt = conn.prepareStatement(sql);
							
				pstmt.setInt(1, type);
				pstmt.setInt(2, categoryNo);
				pstmt.setInt(3, start);
				pstmt.setInt(4, end);
							
				rs = pstmt.executeQuery();
			}
			
			
			
			while(rs.next()) {
				Board board = new Board();
												
				board.setBoardNo(rs.getInt(2));
				board.setBoardTitle(rs.getString(3));
				board.setBoardCreateDate(rs.getString(4));
				board.setUserNickname(rs.getString(5));				
				board.setReadCount(rs.getInt(6));
				board.setLikeCount(rs.getInt(7));
				board.setCategoryName(rs.getString(8));
				board.setBoardContent(rs.getString(9));
				
				boardList.add(board);
			}
			
		} finally {
			close(rs);
			close(pstmt);
		}
		
		return boardList;
	}



	/** 게시글 수정 dao
	 * @param conn
	 * @param map
	 * @return result
	 * @throws Exception
	 */
	public int updateBoard(Connection conn, Map<String, Object> map) throws Exception {

		int result = 0;
		
		try {
			String sql = prop.getProperty("updateBoard");
			
			pstmt = conn.prepareStatement(sql);
						
			pstmt.setString(1, (String)map.get("boardTitle"));
			pstmt.setString(2, (String)map.get("boardContent"));
			pstmt.setInt(3, (int)map.get("categoryNo"));
			pstmt.setInt(4, (int)map.get("boardNo"));
			
			result = pstmt.executeUpdate();
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 댓글 등록 DAO
	 * @param conn
	 * @param comment
	 * @return result
	 * @throws Exception
	 */
	public int insertComment(Connection conn, Comment comment) throws Exception {

		int result = 0;
		
		try {
			
			String sql = prop.getProperty("insertComment");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, comment.getCommentContent());
			pstmt.setInt(2, comment.getUserNo());
			pstmt.setInt(3, comment.getBoardNo());
			
			result = pstmt.executeUpdate();			
			
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	/** 댓글 삭제 DAO
	 * @param conn
	 * @param comment
	 * @return result
	 * @throws Exception
	 */
	public int deleteComment(Connection conn, Comment comment) throws Exception {

		int result = 0;
		
		try {
			
			String sql = prop.getProperty("deleteComment");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, comment.getBoardNo());
			pstmt.setInt(2, comment.getCommentNo());
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}


	/** 댓글 수정 dao
	 * @param conn
	 * @param comment
	 * @return result
	 * @throws Exception
	 */
	public int updateComment(Connection conn, Comment comment) throws Exception {
		
		int result = 0;
		
		try {
			
			String sql = prop.getProperty("updateComment");
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, comment.getCommentContent());
			pstmt.setInt(2, comment.getCommentNo());
			
			result = pstmt.executeUpdate();
			
			
		} finally {
			close(pstmt);
		}
		return result;
	}











}