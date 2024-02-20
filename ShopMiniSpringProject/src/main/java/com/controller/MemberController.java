package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService service;
	
//	단순 ui 처리하는 주소는 servlet-context.xml
//	@RequestMapping("loginForm")
//	public void loginForm() {}
	
	@RequestMapping("/memberAdd") 
	public String memberAdd(MemberDTO dto, Model m) {
		
		int n = service.memberAdd(dto);
		System.out.println("인서트갯수: " + n);
		if(n>=1)
			m.addAttribute("success", "회원가입성공");
		else
			m.addAttribute("success", "회원가입에 실패하였습니다 !");
		
		return "main";
	}
	
	//세션에서 유저id 파싱, 해당하는 dto select, myPage에 전달
	@RequestMapping("/loginCheck/myPage")
	public String myPage(HttpSession session) {
		System.out.println("/loginCheck/myPage");
		MemberDTO login = (MemberDTO)session.getAttribute("login");
		MemberDTO member = service.myPage(login.getUserid());
		//System.out.println(member);
		session.setAttribute("login", member);
		return "redirect:../myPage"; // servlet-context 주소 요청
	}
	
	@RequestMapping("/loginCheck/memberUpdate")
	public String myPage(MemberDTO member, HttpSession session, RedirectAttributes flash) {
		System.out.println("memberUpdate: " + member);
		MemberDTO login = (MemberDTO) session.getAttribute("login");
		String mesg ="";
		if(login.getUserid().equals(member.getUserid())) {
			int n = service.memberUpdate(member);
			if(n==1) {
				mesg = "수정되었습니다.";
				
			} else {
				mesg = "수정되지 않았습니다.";
			}
		} else {
			mesg = "로그인 정보와 아이디가 일치하지 않습니다.";
		}
		flash.addFlashAttribute("mesg", mesg);
		
		// 로그인 데이터 갱신
		MemberDTO newMember = service.myPage(login.getUserid());
		session.setAttribute("login", newMember);
		
		return "redirect:../loginCheck/myPage";
		//return "redirect:../myPage"; 
		//return "/myPage"; 
	}
	
}
