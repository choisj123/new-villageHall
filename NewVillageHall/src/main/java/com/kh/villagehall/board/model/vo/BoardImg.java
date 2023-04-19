package com.kh.villagehall.board.model.vo;

public class BoardImg {
	
	private String fileName; //파일명(시스템명)
	private String filePath; // 
	private int boardNo;//게시글 번호

	
	
	public BoardImg() {
		// TODO Auto-generated constructor stub
	}



	public BoardImg(String fileName, String filePath, int boardNo) {
		super();
		this.fileName = fileName;
		this.filePath = filePath;
		this.boardNo = boardNo;
	}



	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public String getFilePath() {
		return filePath;
	}



	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}



	public int getBoardNo() {
		return boardNo;
	}



	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}



	@Override
	public String toString() {
		return "BoardImg [fileName=" + fileName + ", filePath=" + filePath + ", boardNo=" + boardNo + "]";
	}


	
	
	
}