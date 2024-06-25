package com.ham1142.mybatis.comtroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {

	@RequestMapping (value = "/join")
	public String join() {
		return "join";
		
	}
}
