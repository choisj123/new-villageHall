package com.kh.villagehall.user.model.vo;



public class User {
	private int userNo;
	private String userEmail;
	private String userPw;
	private String userNickname;
	private String userTel;
	private String enrollDate;
	private String profileImg;
	private String kakaoUserKey;
	private String userDeleteFG;
	private String administer;
	
	public User() {}

	
	
	public User(int userNo, String userEmail, String userPw, String userNickname, String userTel, String enrollDate,
			String profileImg, String kakaoUserKey, String userDeleteFG, String administer) {
		super();
		this.userNo = userNo;
		this.userEmail = userEmail;
		this.userPw = userPw;
		this.userNickname = userNickname;
		this.userTel = userTel;
		this.enrollDate = enrollDate;
		this.profileImg = profileImg;
		this.kakaoUserKey = kakaoUserKey;
		this.userDeleteFG = userDeleteFG;
		this.administer = administer;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getUserTel() {
		return userTel;
	}

	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}

	public String getEnrollDate() {
		return enrollDate;
	}

	public void setEnrollDate(String enrollDate) {
		this.enrollDate = enrollDate;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getKakaoUserKey() {
		return kakaoUserKey;
	}

	public void setKakaoUserKey(String kakaoUserKey) {
		this.kakaoUserKey = kakaoUserKey;
	}

	public String getUserDeleteFG() {
		return userDeleteFG;
	}

	public void setUserDeleteFG(String userDeleteFG) {
		this.userDeleteFG = userDeleteFG;
	}

	public String getAdminister() {
		return administer;
	}

	public void setAdminister(String administer) {
		this.administer = administer;
	}


	@Override
	public String toString() {
		return "User [userNo=" + userNo + ", userEmail=" + userEmail + ", userPw=" + userPw + ", userNickname="
				+ userNickname + ", userTel=" + userTel + ", enrollDate=" + enrollDate + ", profileImg=" + profileImg
				+ ", kakaoUserKey=" + kakaoUserKey + ", userDeleteFG=" + userDeleteFG + ", administer=" + administer
				+ "]";
	}
	
	

}
