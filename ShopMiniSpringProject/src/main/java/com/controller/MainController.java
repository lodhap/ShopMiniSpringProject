package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dto.GoodsDTO;
import com.service.GoodsService;

@Controller
public class MainController {
	
	@Autowired
	GoodsService service;
	
//	@ExceptionHandler({Exception.class})
//	public String errorPage() {
//		return "error/error";
//	}
	
	@RequestMapping("/")
	public ModelAndView main() throws Exception{
		//int result = 1/0;
		List<GoodsDTO> list = service.goodsList("top");
		//System.out.println(list);
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodsList", list);
		mav.setViewName("main");
		return mav;
	}
}
