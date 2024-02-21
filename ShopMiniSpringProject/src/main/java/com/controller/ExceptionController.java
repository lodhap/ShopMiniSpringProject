package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExceptionController {
	
	@RequestMapping("/error404")
	public String test1() {
		System.out.println("error 404");
		return "error/404";
	}
	@RequestMapping("/error500")
	public String test2() {
		System.out.println("error 500");
		return "error/500";
	}
	@RequestMapping("/error405")
	public String test3() {
		System.out.println("error 405");
		return "error/405";
	}
}
