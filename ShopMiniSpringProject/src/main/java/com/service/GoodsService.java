package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.GoodsDAO;
import com.dto.CartDTO;
import com.dto.GoodsDTO;
import com.dto.OrderDTO;

@Service
public class GoodsService {
	
	@Autowired
	GoodsDAO dao;

	public List<GoodsDTO> goodsList(String gCategory) {
		List<GoodsDTO> list = dao.goodsList(gCategory);
		return list;
	}

	public GoodsDTO goodsRetrieve(String gCode) {
		GoodsDTO goods = dao.goodsRetrieve(gCode);
		return goods;
	}

	public void cartAdd(CartDTO cart) {
		dao.cartAdd(cart);
	}

	public List<CartDTO> cartList(String userid) {
		List<CartDTO> list = dao.cartList(userid);
		return list;
	}

	public void cartDelete(String num) {
		dao.cartDelete(num);
	}

	public void cartUpdate(Map<String, String> map) {
		dao.cartUpdate(map);
	}

	public void delAllCart(List<String> list) {
		dao.delAllCart(list);
	}

	public CartDTO cartByNum(String num) {
		CartDTO cart = dao.cartByNum(num);
		return cart;
	}

	// 문제가 생기면 롤백
	// 문제가 없으면 커밋
	@Transactional
	public void orderDone(OrderDTO order, String orderNum) {
		dao.orderDone(order); //주문상세정보저장
		dao.cartDelete(orderNum); //카트에서 삭제 두 처리를 tx 처리함 root-context.xml에 tx-Manager 등록 필요
	}
}
