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
		public String contentView(HttpServletRequest request, Model model, HttpSession session) {
		
	 		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
	 		boardDao.uphitDao(request.getParameter("bnum")); // 조회수 1증가 (bhit = bhit+1)
	 		
	 		
	 		BoardDto boardDto = boardDao.contentViewDao(request.getParameter("bnum"));
			
	 		
	 		String sessionid = (String) session.getAttribute("sessionid"); // 세션 아이디 가져오기

//	 		System.out.println("세션아이디 :" + sessionid);
	 		
	 		int idCheck = 0; // flag값 플래그 값(0아니면 1로 확인되는 값)
	 		
	 		if((sessionid != null) &&  (boardDto.getBid().equals(sessionid))) { // 현재 로그인한 아이디와 글쓴 아이디가 동일
	 			idCheck = 1;
	 		}
	 		
	 		model.addAttribute("idCheck", idCheck);
	 		// 현재 로그인 한 아이디와 글쓴이 아이디가 동일하면 idCheck=1 이 되고 아니면 0
	 		model.addAttribute("boardDto", boardDto);
	 		
			return "content_view";
		}
		
		
		@RequestMapping (value = "/modify")
		public String modify(HttpServletRequest request, Model model) {
		
	 		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
	 		BoardDto boardDto = boardDao.contentViewDao(request.getParameter("bnum"));
			
	 		
	 		model.addAttribute("boardDto", boardDto);
	 		
			return "modify_form";
		}
		
		
		@RequestMapping (value = "/modifyOk")
		public String modifyOk(HttpServletRequest request, Model model, HttpSession session) {
		
	 		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
	 		boardDao.modifyDao(request.getParameter("btitle"), request.getParameter("bcontent"),request.getParameter("bnum"));
			
	 		BoardDto boardDto = boardDao.contentViewDao(request.getParameter("bnum"));
	 		
	 		
	 		String sessionid = (String) session.getAttribute("sessionid"); // 세션 아이디 가져오기

//	 		System.out.println("세션아이디 :" + sessionid);
	 		
	 		int idCheck = 0; // flag값 플래그 값(0아니면 1로 확인되는 값)
	 		
	 		if((sessionid != null) &&  (boardDto.getBid().equals(sessionid))) { // 현재 로그인한 아이디와 글쓴 아이디가 동일
	 			idCheck = 1;
	 		}
	 		
	 		model.addAttribute("idCheck", idCheck);
 
	 		model.addAttribute("boardDto", boardDto);

			return "content_view";
		}
		
		@RequestMapping (value = "deleteview")
		public String deleteview(HttpServletRequest request, Model model) {
			
			BoardDao boardDao = sqlSession.getMapper(BoardDao. class);
			boardDao.deleteviewDao(request.getParameter("bnum"));
			
			model.addAttribute("boardDao", boardDao);
			
			return "redirect:list";
		}
		
	}	
