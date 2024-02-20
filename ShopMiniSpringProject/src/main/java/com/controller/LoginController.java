package com.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dto.MemberDTO;
import com.service.MemberService;

@Controller
public class LoginController {
	
	@Autowired
	MemberService service;
	
	@RequestMapping("/login")
	public String login(@RequestParam Map<String, String> map, Model m, HttpSession session) {
		MemberDTO dto = service.login(map);
		//System.out.println("로그인" + dto);
		String nextPage="";
		if(dto!=null) {
			session.setAttribute("login", dto);
			nextPage= "redirect:goodsList?gCategory=top";
		} else {
			m.addAttribute("mesg", "아이디 또는 비번이 잘못되었습니다.");
			session.setAttribute("mesg", "세션");
			nextPage="loginForm"; 
		}
		return nextPage;
	}
	@RequestMapping("/loginCheck/logout")
	public String logout(HttpSession session) {
		//인터셉터 통과 (로그인정보확인)
		session.invalidate();

		return "redirect:/loginForm";
	}
	
	@RequestMapping(value="/idDuplicateCheck", produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String idDuplicateCheck(@RequestParam String userid) {
		//System.out.println(userid); 
		MemberDTO member = service.myPage(userid);
		//System.out.println(member);
		String mesg = "";
		if(member==null) {
			mesg = "아이디 사용가능";
		} else {
			mesg = "사용불가";
		}
		//System.out.println(mesg);
		return mesg; 
	}
}
