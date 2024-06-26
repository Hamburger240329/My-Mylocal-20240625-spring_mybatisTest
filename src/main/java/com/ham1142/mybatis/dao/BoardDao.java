package com.ham1142.mybatis.dao;

import java.util.ArrayList;

import com.ham1142.mybatis.dto.BoardDto;

public interface BoardDao {
	
	public void writeDao(String bid, String bname, String btitle, String Bcontent); // 글쓰기
	
	public ArrayList<BoardDto> boardListDao();
	
	public BoardDto contentViewDao(String bnum); // 글 번호로 내용 조회

	public void modifyDao(String title, String bcontent, String bnum);//글 수정
	
	public void uphitDao(String bnum); // 조회수 증가
	
	public void deleteviewDao(String bnum); // 글 삭제 기능 추가
	
}
