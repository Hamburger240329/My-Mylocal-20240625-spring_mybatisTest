package com.ham1142.mybatis.comtroller;

import java.util.ArrayList;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.SessionIdGenerator;
import org.apache.ibatis.session.SqlSession;
import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ham1142.mybatis.dao.BoardDao;
import com.ham1142.mybatis.dao.MemberDao;
import com.ham1142.mybatis.dto.BoardDto;
import com.ham1142.mybatis.dto.MemberDto;

@Controller
public class BoardController {

	@Autowired // 의존 자동 주입 DI
	private SqlSession sqlSession;
	
	@RequestMapping (value = "/write")
	public String wrtie(HttpSession session, Model model) {
		
		// 세션에서 sessionid 값 가져오기(세션 매겨변수 선언 필수)
		String sessionid = (String) session.getAttribute("sessionid");
		
		if(sessionid == null) { // 로그인 안된 상태
			model.addAttribute("loginFail","글쓰기는 로그인 상태에서만 가능합니다.");
			return "login";
		} else { //로그인 된 상태
			
			MemberDao membetDao = sqlSession.getMapper(MemberDao.class);
			MemberDto memberDto = membetDao.searchDao(sessionid);
			
			model.addAttribute("mid", sessionid);
			model.addAttribute("mname", memberDto.getMname());
			
			return "write_form";
		}
		
	}
		@RequestMapping (value = "/writeOk")
		public String writeOk(HttpServletRequest request, Model model) {
		
 		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
 		boardDao.writeDao(request.getParameter("bid"), request.getParameter("bname"),request.getParameter("btitle"),request.getParameter("bcontent"));
			
			return "redirect:list";
		}

		@RequestMapping (value = "/list")
		public String list(HttpServletRequest request, Model model) {
			
	 		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
	 		ArrayList<BoardDto> boardDtos = boardDao.boardListDao();
			
	 		model.addAttribute("boardList", boardDtos);
	 		
			return "boardlist";			
		}
		
		@RequestMapping (value = "/contentView")
		public String contentView(HttpServletRequest request, Model model) {
		
			
			
			return "content_view";
		}
	}	
