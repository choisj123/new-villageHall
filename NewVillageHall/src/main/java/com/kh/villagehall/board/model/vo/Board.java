package com.kh.villagehall.board.model.vo;

public class Board {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardCreateDate;
	private String boardUpdateDate;
	private int readCount;
	private double latitude;
	private double longtitude;
	private String boardDeleteFG;
	private int categoryNo;
	private String profileImg;

	private String categoryName;
	private int userNo;
	private int likeCount;	
	private String userNickname;
	private String boardImg;
	private int commentCount;
	private int rowNo;

	public Board() {
		// TODO Auto-generated constructor stub
	}

	public Board(int boardNo, String boardTitle, String boardContent, String boardCreateDate, String boardUpdateDate,
			int readCount, double latitude, double longtitude, String boardDeleteFG, int categoryNo, String profileImg,
			String categoryName, int userNo, int likeCount, String userNickname, String boardImg, int commentCount,
			int rowNo) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardCreateDate = boardCreateDate;
		this.boardUpdateDate = boardUpdateDate;
		this.readCount = readCount;
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.boardDeleteFG = boardDeleteFG;
		this.categoryNo = categoryNo;
		this.profileImg = profileImg;
		this.categoryName = categoryName;
		this.userNo = userNo;
		this.likeCount = likeCount;
		this.userNickname = userNickname;
		this.boardImg = boardImg;
		this.commentCount = commentCount;
		this.rowNo = rowNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getBoardTitle() {
		return boardTitle;
	}

	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}

	public String getBoardContent() {
		return boardContent;
	}

	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}

	public String getBoardCreateDate() {
		return boardCreateDate;
	}

	public void setBoardCreateDate(String boardCreateDate) {
		this.boardCreateDate = boardCreateDate;
	}

	public String getBoardUpdateDate() {
		return boardUpdateDate;
	}

	public void setBoardUpdateDate(String boardUpdateDate) {
		this.boardUpdateDate = boardUpdateDate;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(double longtitude) {
		this.longtitude = longtitude;
	}

	public String getBoardDeleteFG() {
		return boardDeleteFG;
	}

	public void setBoardDeleteFG(String boardDeleteFG) {
		this.boardDeleteFG = boardDeleteFG;
	}

	public int getCategoryNo() {
		return categoryNo;
	}

	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getBoardImg() {
		return boardImg;
	}

	public void setBoardImg(String boardImg) {
		this.boardImg = boardImg;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public int getRowNo() {
		return rowNo;
	}

	public void setRowNo(int rowNo) {
		this.rowNo = rowNo;
	}

	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardCreateDate=" + boardCreateDate + ", boardUpdateDate=" + boardUpdateDate + ", readCount="
				+ readCount + ", latitude=" + latitude + ", longtitude=" + longtitude + ", boardDeleteFG="
				+ boardDeleteFG + ", categoryNo=" + categoryNo + ", profileImg=" + profileImg + ", categoryName="
				+ categoryName + ", userNo=" + userNo + ", likeCount=" + likeCount + ", userNickname=" + userNickname
				+ ", boardImg=" + boardImg + ", commentCount=" + commentCount + ", rowNo=" + rowNo + "]";
	}
	
	
	
	
}
