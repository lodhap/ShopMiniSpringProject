package com.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import com.dto.OrderDTO;
import com.service.GoodsService;
import com.service.MemberService;

@Controller
public class GoodsController {

	@Autowired
	GoodsService service;
	
	@Autowired
	MemberService mService;
	
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
		//System.out.println(cart);
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
	
	@RequestMapping("/loginCheck/cartDelete")
	@ResponseBody
	public void cartDelte(String num) {
		//System.out.println(num);
		service.cartDelete(num);
	}
	
	@RequestMapping("/loginCheck/cartUpdate")
	@ResponseBody
	public void cartUpdate(@RequestParam Map<String, String>map) {
		//System.out.println(map);
		service.cartUpdate(map);
	}
	
	//배열로 파싱
//	@RequestMapping("/loginCheck/delAllCart")
//	public String delAllCart(String[] check) {
//		//System.out.println(check[0] + "\t" + check[1]);
//		List<String> list = Arrays.asList(check);
//		service.delAllCart(list);
//		return "redirect:../loginCheck/cartList";
//	}
	
	//리스트로 파싱
	@RequestMapping("/loginCheck/delAllCart")
	public String delAllCart(@RequestParam("check")ArrayList<String> list) {
		//System.out.println(check[0] + "\t" + check[1]);
		service.delAllCart(list);
		return "redirect:../loginCheck/cartList";
	}
	
	@RequestMapping("/loginCheck/orderConfirm")
	public String orderConfirm(String num, HttpSession session, RedirectAttributes xxx) {
		//System.out.println(num);
		
		CartDTO cart = service.cartByNum(num);
		//System.out.println(cart);
		
		MemberDTO login = (MemberDTO) session.getAttribute("login");
		String userid = login.getUserid();
		//System.out.println("유저아이디: " + userid);
		MemberDTO member = mService.myPage(userid);
		//System.out.println(member);
		
		xxx.addFlashAttribute("cart", cart);
		xxx.addFlashAttribute("member", member);
		
		return "redirect:../orderConfirm";
	}
	
	@RequestMapping("/loginCheck/orderDone")
	public String orderDone(OrderDTO order, String orderNum, HttpSession session, RedirectAttributes xxx) {
		System.out.println(order + "\t" + orderNum);
		// num, userid, oderdate
		// orderdto 등록
		// cart 삭제
		// tx처리
		MemberDTO login = (MemberDTO)session.getAttribute("login");
		String userid = login.getUserid();
		order.setUserid(userid);
		service.orderDone(order, orderNum);
		
		xxx.addFlashAttribute("order", order);
		return "redirect:../orderDone";
	}
}
