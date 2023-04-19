package com.kh.villagehall.user.model.service;


import static com.kh.villagehall.common.JDBCTemplate.close;
import static com.kh.villagehall.common.JDBCTemplate.commit;
import static com.kh.villagehall.common.JDBCTemplate.getConnection;
import static com.kh.villagehall.common.JDBCTemplate.rollback;

import java.sql.Connection;

import com.kh.villagehall.user.model.dao.UserDAO;
import com.kh.villagehall.user.model.vo.User;


public class UserService {
	
	private UserDAO dao = new UserDAO();

	/** 로그인 서비스
	 * @param user
	 * @return loginUser
	 * @throws Exception 
	 */
	public User login(User user) throws Exception{
		
		// Connection 얻어오기
		Connection conn = getConnection();
		
		// DAO 수행
		User loginUser = dao.login(conn, user);
		
		// Connection 반환
		close(conn);
		
		// 결과 반환
		return loginUser;
	}
	
	/** 카카오 로그인 서비스
	 * @param kakaoUserKey
	 * @return loginUser
	 * @throws Exception 
	 */
	public User kakaoLogin(String kakaoUserKey) throws Exception{
		
		// Connection 얻어오기
		Connection conn = getConnection();
		
		// DAO 수행
		User loginUser = dao.kakaoLogin(conn, kakaoUserKey);
		
		// Connection 반환
		close(conn);
		
		// 결과 반환
		return loginUser;
	}
	
	
	/** 회원가입 Service
	 * @param user
	 * @return result
	 * @throws Exception
	 */
	public int signUp(User user) throws Exception {


		// 1) 커넥션 얻어오기
		Connection conn = getConnection(); // DBCP 에서 얻어옴
				
		// 2) DAO 메소드 호출 후 결과 반환 받기
		int result = dao.signUp(conn, user);
				 
		// 3) 트랜잭션 처리
		// result가 0인 경우 -> DAO return 구문 잘못 작성
				
		if(result > 0)	commit(conn);  
		else			rollback(conn);
				
		// 4) conn 반환(DBCP로 돌려주기)
		close(conn);
				
		// 5) 결과 반환
		return result;
	}
	


	 /** 인증 번호 DB 추가 Service
	  * @param inputEmail
	  * @param cNumber
	  * @return result
	  * @throws Exception
	  */
	  public int insertCertification(String inputEmail, String cNumber) throws Exception {
	      
	     Connection conn = getConnection();
	     
	     // 1) 입력한 이메일과 일치하는 값이 있으면 수정(UPDATE)
	     int result = dao.updateCertification(conn, inputEmail, cNumber);
	     
	     // 2) 일치하는 이메일이 없는경우 -> 처음으로 인증번호를 발급 받음 -> 삽입(INSERT)
	     if( result == 0 ) {
	        result = dao.insertCertification(conn, inputEmail, cNumber);
	     }
	     
	     if(result > 0)   commit(conn);
	     else         rollback(conn);
	     
	     close(conn);
	     return result;
	  }


	   
	  /** 인증번호 확인 Service
	   * @param inputEmail
	   * @param cNumber
	   * @return result
	   * @throws Exception
	   */
	  public int checkNumber(String inputEmail, String cNumber) throws Exception{
	     Connection conn = getConnection();
	     
	      int result = dao.checkNumber(conn, inputEmail, cNumber);
	     
	     close(conn);
	     return result;
	  }
	   
  
	  /** 이메일 중복 검사 Service
	    * @param userEmail
	    * @return result
	    * @throws Exception
	    */
	   public int emailDupCheck(String userEmail) throws Exception {
	      
	      Connection conn = getConnection(); // DBCP 에서 만들어둔 커넥션 얻어오기
	      
	      int result = dao.emailDupCheck(conn, userEmail);
	      
	      close(conn);
	      
	      return result;
	
	   }
	   

		/** 닉네임 중복 검사 Service
		 * @param userNickname
		 * @return result
		 * @throws Exception
		 */
		public int nicknameDupCheck(String userNickname) throws Exception{
			
			// DBCP에서 Connection 얻어오기
			Connection conn = getConnection();
			
			// DAO 메서드 수행 후 결과 반환 받기
			int result = dao.nicknameDupCheck(conn, userNickname);
			
			// Connection 반환
			close(conn);
			
			// 결과 반환
			return result;
		}

	   
	   
	/** 마이페이지 비밀번호 일치 검사 service
	 * @param userNo
	 * @param inputPw
	 * @return result
	 * @throws Exception
	 */
	public int myInfoCheckPw(int userNo, String inputPw) throws Exception{
		
		Connection conn = getConnection();
		
		int result = dao.myInfoCheckPw(conn, userNo, inputPw);
		
		System.out.println("service result" + result);
		
		close(conn);
		
		return result;
	}
	

	/** 프로필 이미지 변경 service
	 * @param userNo
	 * @param profileImg
	 * @return result
	 * @throws Exception
	 */
	public int updateProfileImage(int userNo, String profileImg) throws Exception{
		Connection conn = getConnection();
		
		int result = dao.updateProfileImage(conn, userNo, profileImg);
		
		// 트랜잭션 제어 처리
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}

	/** 마이페이지 회원정보 수정 service
	 * @param user
	 * @return result
	 * @throws Exception
	 */
	public int updateUser(User user) throws Exception{

		Connection conn = getConnection();
		
		int result = dao.updateUser(conn, user);
		
		// 트랜잭션 제어 처리
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
		
	}

	/** 마이페이지 비밀번호 변경 service
	 * @param newPw
	 * @param userNo
	 * @return result
	 * @throws Exception
	 */
	public int changePw(String newPw, int userNo) throws Exception {
		Connection conn = getConnection(); 
		
		int result = dao.changePw(conn, newPw, userNo);
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}


	/** 마이페이지 회원 탈퇴 service
	 * @param userNo
	 * @param userPw
	 * @param kakaoUserKey 
	 * @return result
	 * @throws Exception
	 */
	public int deleteUser(int userNo, String userPw) throws Exception{
		Connection conn = getConnection(); 
		int result = 0;
		
		result = dao.deleteUser(conn, userNo, userPw);
					
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}
	
	/** 마이페이지 회원 탈퇴 service
	 * @param userNo
	 * @param userPw
	 * @param kakaoUserKey 
	 * @return result
	 * @throws Exception
	 */
	public int deleteKakaoUser(int userNo, String kakaoUserKey) throws Exception{
		Connection conn = getConnection(); 
		int result = 0;

		result = dao.deleteKakaoUser(conn, userNo, kakaoUserKey);
				
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
	}


	/** 비밀번호 찾기 Service
	 * @param user
	 * @return
	 */
	public int pwFind(String userEmail, String newPw) throws Exception {
		
		Connection conn = getConnection(); // DBCP에서 얻어옴
		
		int result = dao.pwFind(conn, userEmail, newPw);
		
		if(result > 0)	commit(conn);
		else			rollback(conn);
		
		close(conn);
		
		return result;
		
		
	}


	/**카카오로가입하기 Service
	 * @param user
	 * @return
	 */
	public int kakaoSignUp(User user) throws Exception {

		// 1) 커넥션 얻어오기
		Connection conn = getConnection(); // DBCP 에서 얻어옴
				
		// 2) DAO 메소드 호출 후 결과 반환 받기
		int result = dao.kakaoSignUp(conn, user);
		
		System.out.println("Service" + user);
				 
		// 3) 트랜잭션 처리
		// result가 0인 경우 -> DAO return 구문 잘못 작성
				
		if(result > 0)	commit(conn);  
		else			rollback(conn);
				
		// 4) conn 반환(DBCP로 돌려주기)
		close(conn);
				
		// 5) 결과 반환
		return result;
	}


}
