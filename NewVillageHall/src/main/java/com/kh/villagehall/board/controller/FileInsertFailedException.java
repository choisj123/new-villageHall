package com.kh.villagehall.board.controller;


//파일 정보 삽입 실패 시 발생할 사용자 정의 예외
public class FileInsertFailedException extends Exception {
	
	public FileInsertFailedException() {
		super();
	}
		
	public FileInsertFailedException(String message) {
		super(message);
	}
}
