package com.ham1142.mybatis.comtroller;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BoardController {

	@Autowired // 의존 자동 주입 DI
	private SqlSession sqlSession;
	
}
