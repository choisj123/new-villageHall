package com.kh.villagehall.comment.model.vo;

public class Comment {

	private int commentNo;
	private String commentContent;
	private String commentCreateDate;
	private String commentDeleteFG;
	private int userNo;
	private int boardNo;
	
	private String boardTitle;
	
	private String profileImg;
	private String userNickname;
	
	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public Comment() {}
		
	public Comment(int commentNo, String commentContent, String commentCreateDate, String commentDeleteFG, int userNo,
			int boardNo) {
		super();
		this.commentNo = commentNo;
		this.commentContent = commentContent;
		this.commentCreateDate = commentCreateDate;
		this.commentDeleteFG = commentDeleteFG;
		this.userNo = userNo;
		this.boardNo = boardNo;
	}




	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}


	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public String getCommentCreateDate() {
		return commentCreateDate;
	}

	public void setCommentCreateDate(String commentCreateDate) {
		this.commentCreateDate = commentCreateDate;
	}

	public String getCommentDeleteFG() {
		return commentDeleteFG;
	}

	public void setCommentDeleteFG(String commentDeleteFG) {
		this.commentDeleteFG = commentDeleteFG;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	@Override
	public String toString() {
		return "Comment [commentNo=" + commentNo + ", commentContent=" + commentContent + ", commentCreateDate="
				+ commentCreateDate + ", commentDeleteFG=" + commentDeleteFG + ", userNo=" + userNo + ", boardNo="
				+ boardNo + "]";
	}
	
	
}
