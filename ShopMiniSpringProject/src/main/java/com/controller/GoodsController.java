package com.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.MemberDTO;
import com.service.GoodsService;

@Controller
public class GoodsController {

	@Autowired
	GoodsService service;
	
	@RequestMapping("/goodsList")
	public ModelAndView goodsList(String gCategory) {
		
		if(gCategory==null)
			gCategory = "top";
		//System.out.println(gCategory);
		
		List<GoodsDTO> list = service.goodsList(gCategory);
		//System.out.println(list);
		ModelAndView mav = new ModelAndView();
		mav.addObject("goodsList", list);
		mav.setViewName("main");
		return mav;
	}
	@RequestMapping("/goodsRetrieve") 
	@ModelAttribute("goodsRetrieve") //응답데이터 키값 강제 설정
	public GoodsDTO goodsRetrieve(String gCode) {
		GoodsDTO goods = goods = service.goodsRetrieve(gCode);
		return goods;
	}
	
	@RequestMapping("/loginCheck/cartAdd")
	public String cartAdd(CartDTO cart, HttpSession session) {
		MemberDTO login = (MemberDTO)session.getAttribute("login");
		cart.setUserid(login.getUserid());
		System.out.println(cart);
		session.setAttribute("mesg", cart.getgCode());
		service.cartAdd(cart);
		return "redirect:../goodsRetrieve?gCode="+cart.getgCode();
	}
	
	@RequestMapping("/loginCheck/cartList")
	public String cartList(RedirectAttributes attr, HttpSession session) {
		MemberDTO login = (MemberDTO)session.getAttribute("login");
		//System.out.println(login.getUserid());
		List<CartDTO> list = service.cartList(login.getUserid());
		//System.out.println(list);
		attr.addFlashAttribute("cartList",list);
		return "redirect:../cartList";
	}
	
	@RequestMapping(value = "/loginCheck/cartDelete")
	@ResponseBody
	public void cartDelte(String num) {
		//System.out.println(num);
		service.cartDelete(num);
	}
	
	@RequestMapping(value = "/loginCheck/cartUpdate")
	@ResponseBody
	public void cartUpdate(@RequestParam Map<String, String>map) {
		//System.out.println(map);
		service.cartUpdate(map);
	}
}
