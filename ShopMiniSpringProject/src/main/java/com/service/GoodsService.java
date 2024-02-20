package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.GoodsDAO;
import com.dto.CartDTO;
import com.dto.GoodsDTO;

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
}
