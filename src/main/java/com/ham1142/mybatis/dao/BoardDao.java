package com.ham1142.mybatis.dao;

import java.util.ArrayList;

import com.ham1142.mybatis.dto.BoardDto;

public interface BoardDao {
	
	public void writeDao(String bid, String bname, String btitle, String Bcontent);
	
	public ArrayList<BoardDto> boardlistDao();

	public ArrayList<BoardDto> boardListDao();
	
	

}
